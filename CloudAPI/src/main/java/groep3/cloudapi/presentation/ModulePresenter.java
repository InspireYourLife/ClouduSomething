package groep3.cloudapi.presentation;

import groep3.cloudapi.model.Module;
import groep3.cloudapi.presentation.model.ModuleView;
import java.util.ArrayList;
import java.util.List;

public class ModulePresenter extends BasePresenter
{
    public List<ModuleView> present (List<Module> modules)
    {
    int listCount = modules.size();
    int i = 0;
        
    List<ModuleView> safeData = new ArrayList<>(); 
        
        while (i < listCount)
        {
            Module m = modules.get(i);
            ModuleView mv = new ModuleView();
            mv.name = m.getName();
            mv.creationDate = m.getCreationDate();
            mv.isApproved = m.getIsApproved();
            mv.isCompleted = m.getIsCompleted();
            mv.isTemplate = m.getIsTemplate();
            
            safeData.add(mv);
            i++;
        }
        
        return safeData;
    }
    
    public ModuleView present(Module m)
    {
        ModuleView mv = new ModuleView();
        
        mv.name = m.getName();
        mv.creationDate = m.getCreationDate();
        mv.isApproved = m.getIsApproved();
        mv.isCompleted = m.getIsCompleted();
        mv.isTemplate = m.getIsTemplate();
        
        return mv;
    }
}
