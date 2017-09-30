package groep3.cloudapi;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
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
        MongoClient mongo = new MongoClient(new ServerAddress ("localhost", 27017));
        Morphia morphia = new Morphia();
        return morphia.createDatastore(mongo, "Example");
    }
}
