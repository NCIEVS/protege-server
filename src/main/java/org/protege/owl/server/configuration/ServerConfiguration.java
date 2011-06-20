package org.protege.owl.server.configuration;

import java.util.Set;

import org.protege.owl.server.metaproject.Vocabulary;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 * This class will specify a single server configuration.  A single jvm may contain several different 
 * servers each of which has a server configuration.  This class will ultimately include things like the type of 
 * conflict management, the style of ServerConnection instance and the set of ontologies to be served up.
 * 
 * @author tredmond
 *
 */
public class ServerConfiguration {
	private static final long serialVersionUID = -2667140662344108331L;
	
	private OWLOntology ontology;
	private OWLIndividual serverDeclaration;
	private OWLIndividual serverBackendDeclaration;
	private OWLIndividual conflictManagerDeclaration;
	private OWLIndividual connectionManagerDeclaration;
	
    public ServerConfiguration(OWLOntology ontology, OWLIndividual serverDeclaration) {
        this.ontology = ontology;
        this.serverDeclaration = serverDeclaration;
    }
    
    public static boolean isSuitable(OWLOntology ontology, OWLIndividual serverDeclaration) {
        return calculateBackendDeclaration(ontology, serverDeclaration) != null &&
                calculateConflictDeclaration(ontology, serverDeclaration) != null &&
                calculateConnectionDeclaration(ontology, serverDeclaration) != null;
    }

    public OWLOntology getOntology() {
        return ontology;
    }

    public OWLIndividual getServerDeclaration() {
        return serverDeclaration;
    }
    
    
    
    public OWLIndividual getServerBackendDeclaration() {
        if (serverBackendDeclaration == null) {
            serverBackendDeclaration = calculateBackendDeclaration(ontology, serverDeclaration);
        }
        return serverBackendDeclaration;
    }

    public OWLIndividual getConflictManagerDeclaration() {
        if (conflictManagerDeclaration == null) {
            conflictManagerDeclaration = calculateConflictDeclaration(ontology, serverDeclaration);
        }
        return conflictManagerDeclaration;
    }

    public OWLIndividual getConnectionManagerDeclaration() {
        if (connectionManagerDeclaration == null) {
            connectionManagerDeclaration = calculateConnectionDeclaration(ontology, serverDeclaration);
        }
        return connectionManagerDeclaration;
    }

    private static OWLIndividual calculateBackendDeclaration(OWLOntology ontology, OWLIndividual serverDeclaration) {
        return calculatePropertyValue(ontology, serverDeclaration, Vocabulary.SERVER_BAKEND_COMPONENT_PROPERTY);
    }
    
    private static OWLIndividual calculateConflictDeclaration(OWLOntology ontology, OWLIndividual serverDeclaration) {
        return calculatePropertyValue(ontology, serverDeclaration, Vocabulary.CONFLICT_MANAGER_COMPONENT_PROPERTY);
    }
    
    private static OWLIndividual calculateConnectionDeclaration(OWLOntology ontology, OWLIndividual serverDeclaration) {
        return calculatePropertyValue(ontology, serverDeclaration, Vocabulary.CONNECTION_COMPONENT_PROPERTY);
    }
    
    private static OWLIndividual calculatePropertyValue(OWLOntology ontology, 
                                                        OWLIndividual serverDeclaration,
                                                        OWLObjectProperty property) {
        Set<OWLIndividual> values = serverDeclaration.getObjectPropertyValues(property, ontology);
        if (values == null || values.isEmpty()) {
            return null;
        }
        return values.iterator().next();
    }
    
    @Override
    public String toString() {
        return getServerDeclaration().toString();
    }

}
