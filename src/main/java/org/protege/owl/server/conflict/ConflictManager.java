package org.protege.owl.server.conflict;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.protege.owl.server.api.AuthToken;
import org.protege.owl.server.api.ChangeHistory;
import org.protege.owl.server.api.OntologyDocumentRevision;
import org.protege.owl.server.api.RevisionPointer;
import org.protege.owl.server.api.Server;
import org.protege.owl.server.api.ServerOntologyDocument;
import org.protege.owl.server.api.exception.OWLServerException;
import org.protege.owl.server.util.CollectingChangeVisitor;
import org.protege.owl.server.util.ServerFilterAdapter;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.ImportChange;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLAxiomChange;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class ConflictManager extends ServerFilterAdapter {

    public ConflictManager(Server delegate) {
        super(delegate);
    }
    
    @Override
    public void commit(AuthToken u, ServerOntologyDocument doc, ChangeHistory proposedChanges) throws OWLServerException {
        super.commit(u, doc, proposedChanges);
    }
    
    private Set<OWLOntologyChange> getConflicts(AuthToken u, ServerOntologyDocument doc, ChangeHistory proposedChanges) throws OWLServerException {
        Set<OWLOntologyChange> conflicts = new TreeSet<OWLOntologyChange>();
        OWLOntology fakeOntology;
        try {
            fakeOntology = OWLManager.createOWLOntologyManager().createOntology();
        }
        catch (OWLOntologyCreationException ooce) {
            throw new RuntimeException("Could not create empty ontology", ooce);
        }
        List<OWLOntologyChange> clientChanges = proposedChanges.getChanges(fakeOntology);
        CollectingChangeVisitor collectedClientChanges = CollectingChangeVisitor.collectChanges(clientChanges);
        OntologyDocumentRevision head = super.evaluateRevisionPointer(u, doc, RevisionPointer.HEAD_REVISION);
        for (OntologyDocumentRevision revision = proposedChanges.getStartRevision();
                revision.compareTo(head) < 0;
                revision = revision.next()) {
            List<OWLOntologyChange> serverChanges = getChanges(u, doc, revision, revision.next()).getChanges(fakeOntology);
            CollectingChangeVisitor collectedServerChanges = CollectingChangeVisitor.collectChanges(serverChanges);
            addConflicts(collectedClientChanges, collectedServerChanges, conflicts);
        }
        return conflicts;
    }
    
    private void addConflicts(CollectingChangeVisitor clientChanges, CollectingChangeVisitor serverChanges, Set<OWLOntologyChange> conflicts) {
        if (clientChanges.getLastOntologyIDChange() != null && serverChanges.getLastOntologyIDChange() != null) {
            conflicts.add(clientChanges.getLastOntologyIDChange());
        }
        for (Entry<OWLImportsDeclaration, ImportChange> entry : clientChanges.getLastImportChangeMap().entrySet()) {
            OWLImportsDeclaration decl = entry.getKey();
            if (serverChanges.getLastImportChangeMap().containsKey(decl)) {
                conflicts.add(entry.getValue());
            }
        }
        for (Entry<OWLAnnotation, OWLOntologyChange> entry : clientChanges.getLastOntologyAnnotationChangeMap().entrySet()) {
            OWLAnnotation annotation = entry.getKey();
            if (serverChanges.getLastOntologyAnnotationChangeMap().containsKey(annotation)) {
                conflicts.add(entry.getValue());
            }
        }
        for (Entry<OWLAxiom, OWLAxiomChange> entry : clientChanges.getLastAxiomChangeMap().entrySet()) {
            OWLAxiom axiom = entry.getKey();
            if (serverChanges.getLastAxiomChangeMap().containsKey(axiom)) {
                conflicts.add(entry.getValue());
            }
        }
    }
    


}