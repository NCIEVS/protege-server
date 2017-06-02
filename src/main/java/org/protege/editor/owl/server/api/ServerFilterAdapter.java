package org.protege.editor.owl.server.api;

import edu.stanford.protege.metaproject.api.*;
import org.protege.editor.owl.server.api.exception.AuthorizationException;
import org.protege.editor.owl.server.api.exception.OutOfSyncException;
import org.protege.editor.owl.server.api.exception.ServerServiceException;
import org.protege.editor.owl.server.versioning.api.ChangeHistory;
import org.protege.editor.owl.server.versioning.api.ServerDocument;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Josef Hardi <johardi@stanford.edu> <br>
 *         Stanford Center for Biomedical Informatics Research
 */
public class ServerFilterAdapter extends ServerLayer {

	private ServerLayer delegate;

	public ServerFilterAdapter(ServerLayer delegate) {
		this.delegate = delegate;
	}

    @Override
    public void createUser(AuthToken token, User newUser, Optional<? extends Password> password)
            throws AuthorizationException, ServerServiceException {
        getDelegate().createUser(token, newUser, password);
    }

    @Override
    public void deleteUser(AuthToken token, UserId userId) throws AuthorizationException, ServerServiceException {
        getDelegate().deleteUser(token, userId);
    }

    @Override
    public void updateUser(AuthToken token, UserId userId, User user, Optional<? extends Password> password)
            throws AuthorizationException, ServerServiceException {
        getDelegate().updateUser(token, userId, user, password);
    }

    @Override
    public ServerDocument createProject(AuthToken token, ProjectId projectId, Name projectName, Description description,
            UserId owner, Optional<ProjectOptions> options) throws AuthorizationException, ServerServiceException {
        return getDelegate().createProject(token, projectId, projectName, description, owner, options);
    }

    @Override
    public void deleteProject(AuthToken token, ProjectId projectId, boolean includeFile)
            throws AuthorizationException, ServerServiceException {
        getDelegate().deleteProject(token, projectId, includeFile);
    }

    @Override
    public void updateProject(AuthToken token, ProjectId projectId, Project newProject)
            throws AuthorizationException, ServerServiceException {
        getDelegate().updateProject(token, projectId, newProject);
    }

    @Override
    public ServerDocument openProject(AuthToken token, ProjectId projectId)
            throws AuthorizationException, ServerServiceException {
        return getDelegate().openProject(token, projectId);
    }

    @Override
    public void createRole(AuthToken token, Role newRole) throws AuthorizationException, ServerServiceException {
        getDelegate().createRole(token, newRole);
    }

    @Override
    public void deleteRole(AuthToken token, RoleId roleId) throws AuthorizationException, ServerServiceException {
        getDelegate().deleteRole(token, roleId);
    }

    @Override
    public void updateRole(AuthToken token, RoleId roleId, Role newRole)
            throws AuthorizationException, ServerServiceException {
        getDelegate().updateRole(token, roleId, newRole);
    }

    @Override
    public void createOperation(AuthToken token, Operation operation)
            throws AuthorizationException, ServerServiceException {
        getDelegate().createOperation(token, operation);
    }

    @Override
    public void deleteOperation(AuthToken token, OperationId operationId)
            throws AuthorizationException, ServerServiceException {
        getDelegate().deleteOperation(token, operationId);
    }

    @Override
    public void updateOperation(AuthToken token, OperationId operationId, Operation newOperation)
            throws AuthorizationException, ServerServiceException {
        getDelegate().updateOperation(token, operationId, newOperation);
    }

    @Override
    public void assignRole(AuthToken token, UserId userId, ProjectId projectId, RoleId roleId)
            throws AuthorizationException, ServerServiceException {
        getDelegate().assignRole(token, userId, projectId, roleId);
    }

    @Override
    public void retractRole(AuthToken token, UserId userId, ProjectId projectId, RoleId roleId)
            throws AuthorizationException, ServerServiceException {
        getDelegate().retractRole(token, userId, projectId, roleId);
    }

    @Override
    public Host getHost(AuthToken token) throws AuthorizationException, ServerServiceException {
        return getDelegate().getHost(token);
    }

    @Override
    public void setHostAddress(AuthToken token, URI hostAddress) throws AuthorizationException, ServerServiceException {
        getDelegate().setHostAddress(token, hostAddress);
    }

    @Override
    public void setSecondaryPort(AuthToken token, int portNumber)
            throws AuthorizationException, ServerServiceException {
        getDelegate().setSecondaryPort(token, portNumber);
    }

    @Override
    public String getRootDirectory(AuthToken token) throws AuthorizationException, ServerServiceException {
        return getDelegate().getRootDirectory(token);
    }

    @Override
    public void setRootDirectory(AuthToken token, String rootDirectory)
            throws AuthorizationException, ServerServiceException {
        getDelegate().setRootDirectory(token, rootDirectory);
    }

    @Override
    public Map<String, String> getServerProperties(AuthToken token)
            throws AuthorizationException, ServerServiceException {
        return getDelegate().getServerProperties(token);
    }

    @Override
    public void setServerProperty(AuthToken token, String property, String value)
            throws AuthorizationException, ServerServiceException {
        getDelegate().setServerProperty(token, property, value);
    }

    @Override
    public void unsetServerProperty(AuthToken token, String property)
            throws AuthorizationException, ServerServiceException {
        getDelegate().unsetServerProperty(token, property);
    }

    @Override
    public ChangeHistory commit(AuthToken token, ProjectId projectId, CommitBundle commitBundle)
            throws AuthorizationException, OutOfSyncException, ServerServiceException {
        return getDelegate().commit(token, projectId, commitBundle);
    }

    

    @Override
    public void addServerListener(ServerListener listener) {
        getDelegate().addServerListener(listener);
    }

    @Override
    public void removeServerListener(ServerListener listener) {
        getDelegate().removeServerListener(listener);
    }

    @Override
    public List<User> getAllUsers(AuthToken token) throws AuthorizationException, ServerServiceException {
        return getDelegate().getAllUsers(token);
    }

    @Override
    public List<Project> getProjects(AuthToken token, UserId userId)
            throws AuthorizationException, ServerServiceException {
        return getDelegate().getProjects(token, userId);
    }

    @Override
    public List<Project> getAllProjects(AuthToken token) throws AuthorizationException, ServerServiceException {
        return getDelegate().getAllProjects(token);
    }

    @Override
    public Map<ProjectId, List<Role>> getRoles(AuthToken token, UserId userId, GlobalPermissions globalPermissions)
            throws AuthorizationException, ServerServiceException {
        return getDelegate().getRoles(token, userId, globalPermissions);
    }

    @Override
    public List<Role> getRoles(AuthToken token, UserId userId, ProjectId projectId, GlobalPermissions globalPermissions)
            throws AuthorizationException, ServerServiceException {
        return getDelegate().getRoles(token, userId, projectId, globalPermissions);
    }

    @Override
    public List<Role> getAllRoles(AuthToken token) throws AuthorizationException, ServerServiceException {
        return getDelegate().getAllRoles(token);
    }

    @Override
    public Map<ProjectId, List<Operation>> getOperations(AuthToken token, UserId userId, GlobalPermissions globalPermissions)
            throws AuthorizationException, ServerServiceException {
        return getDelegate().getOperations(token, userId, globalPermissions);
    }

    @Override
    public List<Operation> getOperations(AuthToken token, UserId userId, ProjectId projectId, GlobalPermissions globalPermissions)
            throws AuthorizationException, ServerServiceException {
        return getDelegate().getOperations(token, userId, projectId, globalPermissions);
    }

    @Override
    public List<Operation> getOperations(AuthToken token, RoleId roleId)
            throws AuthorizationException, ServerServiceException {
        return getDelegate().getOperations(token, roleId);
    }

    @Override
    public List<Operation> getAllOperations(AuthToken token) throws AuthorizationException, ServerServiceException {
        return getDelegate().getAllOperations(token);
    }

    @Override
    public boolean isOperationAllowed(AuthToken token, OperationId operationId, ProjectId projectId, UserId userId)
            throws AuthorizationException, ServerServiceException {
        return getDelegate().isOperationAllowed(token, operationId, projectId, userId);
    }

    @Override
    public boolean isOperationAllowed(AuthToken token, OperationId operationId, UserId userId)
            throws AuthorizationException, ServerServiceException {
        return getDelegate().isOperationAllowed(token, operationId, userId);
    }

	protected ServerLayer getDelegate() {
        return delegate;
    }

	@Override
    public ServerConfiguration getConfiguration() {
        return getDelegate().getConfiguration();
    }
}
