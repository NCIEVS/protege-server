package org.protege.owl.server.connect;

import org.protege.owl.server.api.LoginService;

import java.rmi.RemoteException;

import edu.stanford.protege.metaproject.api.AuthToken;
import edu.stanford.protege.metaproject.api.SaltedPasswordDigest;
import edu.stanford.protege.metaproject.api.UserId;

public class RmiLoginService implements RemoteLoginService {

    public static String LOGIN_SERVICE = "RmiLoginService";

    private LoginService loginService;

    public RmiLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public AuthToken login(UserId username, SaltedPasswordDigest password) throws RemoteException {
        try {
            return loginService.login(username, password);
        }
        catch (Exception e) {
            throw new RemoteException(e.getMessage(), e);
        }
    }
}