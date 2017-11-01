package groep3.cloudapi.persistence;

import groep3.cloudapi.model.EntityModel;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

@Singleton
public abstract class BaseDAO<T extends EntityModel> extends BasicDAO<T, ObjectId> 
{
    public BaseDAO( Class <T> entityClass, Datastore ds)
    {
        super(entityClass, ds);
    }
    
    public T get (String id)
    {
        return get (new ObjectId(id));
    }
    
    public List<T> getAll()
    {
        return find().asList();
    }
    
    public void create (T obj)
    {
        save(obj);
    }
    
    public void update (T obj)
    {   
        //TODO: Somehow make this Update
        save(obj);
    }
}
