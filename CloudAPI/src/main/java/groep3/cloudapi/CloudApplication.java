package groep3.cloudapi;

import com.hubspot.dropwizard.guice.GuiceBundle;
import groep3.cloudapi.model.User;
import groep3.cloudapi.service.AuthenticationService;
import groep3.cloudapi.service.AuthorizationService;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class CloudApplication extends Application<CloudConfiguration>
{
    private GuiceBundle<CloudConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception
    {
        new CloudApplication().run(new String[] { "server" } );
    }

    @Override
    public void initialize( final Bootstrap<CloudConfiguration> bootstrap)
    {
        configureGuice();
        bootstrap.addBundle(guiceBundle);
    }

    private void configureGuice()
    {
        guiceBundle = GuiceBundle.<CloudConfiguration>newBuilder()
            .addModule( new CloudModule() )
            .enableAutoConfig(getClass().getPackage().getName())
            .setConfigClass(CloudConfiguration.class)
            .build();
    }

    @Override
    public void run( final CloudConfiguration configuration, 
                     final Environment environment) throws Exception
    {       
       environment.jersey().register(new AuthDynamicFeature(
       new BasicCredentialAuthFilter.Builder<User>()
       .setAuthenticator(
            guiceBundle.getInjector().getInstance(
            AuthenticationService.class))
       .setAuthorizer(
            guiceBundle.getInjector().getInstance(
            AuthorizationService.class))
       .setRealm( "Super Secret Stuff")
       .buildAuthFilter()));
       
       environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
       
       environment.jersey().register(RolesAllowedDynamicFeature.class);
    }
}
