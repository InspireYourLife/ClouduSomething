package groep3.cloudapi;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class CloudModule extends AbstractModule
{
    @Override
    protected void configure()
    {
    }
    
    @Provides
    @Singleton
    Datastore providesDatastore()
    {
        ServerAddress address = new ServerAddress ("ds040167.mlab.com", 40167);
        MongoCredential credits;
        credits = MongoCredential.createCredential("Cloud3", "cloud3db", "Waffl3s".toCharArray());
        
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(credits);
        
        MongoClient mongo = new MongoClient(address, credentials);
        
        Morphia morphia = new Morphia();
        return morphia.createDatastore(mongo, "cloud3db");
    }
}
