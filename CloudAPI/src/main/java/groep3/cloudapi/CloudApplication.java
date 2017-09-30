package groep3.cloudapi;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        
    }
}
