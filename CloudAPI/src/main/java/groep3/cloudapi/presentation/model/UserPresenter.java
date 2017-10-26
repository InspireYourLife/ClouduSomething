package groep3.cloudapi.presentation.model;

import groep3.cloudapi.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserPresenter extends BasePresenter
{

    public List<UserView> present(List<User> users)
    {
        int listCount = users.size();
        int i = 0;
        
        List<UserView> safeData = new ArrayList<>(); 
        
        while (i < listCount)
        {
            User u = users.get(i);
            UserView uv = new UserView();
            
            uv.name = u.getName();
            uv.email = u.getEmail();
            uv.address = u.getAddress();
            uv.collectedPoints = u.getCollectedPoints();
            uv.telephone = u.getTelephone();
            uv.image = u.getImage();
            
            safeData.add(uv);
            i++;
        }
        
        return safeData;
    }

    public UserView present(User u)
    {
        UserView uv = new UserView();
        
        uv.name = u.getName();
        uv.email = u.getEmail();
        uv.address = u.getAddress();
        uv.collectedPoints = u.getCollectedPoints();
        uv.telephone = u.getTelephone();
        uv.image = u.getImage();
        
        return uv;
    }
    
}
