package groep3.cloudapi.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public abstract class EntityModel 
{
    @Id
    protected ObjectId id = new ObjectId();

    public ObjectId getId()
    {
        return id;
    }

    public void setId( ObjectId id )
    {
        this.id = id;
    }   
}
