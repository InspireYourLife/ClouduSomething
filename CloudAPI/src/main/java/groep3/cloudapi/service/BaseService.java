package groep3.cloudapi.service;

import javax.ws.rs.NotFoundException;

public abstract class BaseService 
{
       protected void requireResult(Object obj, String message)
    {
        if(obj == null){
            throw new NotFoundException(message);
        }
    } 
}
