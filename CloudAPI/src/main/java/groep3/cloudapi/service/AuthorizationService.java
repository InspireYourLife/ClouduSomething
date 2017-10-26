package groep3.cloudapi.service;

import groep3.cloudapi.model.User;
import io.dropwizard.auth.Authorizer;

public class AuthorizationService extends BaseService implements Authorizer<User>
{
    @Override
    public boolean authorize(User authenticator, String role)
    {
        return authenticator.hasRole(role);
    }
}
