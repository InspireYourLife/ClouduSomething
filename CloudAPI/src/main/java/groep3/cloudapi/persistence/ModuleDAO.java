package groep3.cloudapi.persistence;

import groep3.cloudapi.model.Module;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.mongodb.morphia.Datastore;

/**
 *
 * @author Tim
 */

@Path( "/modules" )
@Consumes ( MediaType.APPLICATION_JSON )
@Produces ( MediaType.APPLICATION_JSON )

public class ModuleDAO extends BaseDAO<Module>
{
    @Inject
    public ModuleDAO(Datastore ds)
    {
        super(Module.class, ds);
    }
}
