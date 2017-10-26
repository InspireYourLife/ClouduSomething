package groep3.cloudapi.model;

public enum Role 
{
    ADMIN(Labels.ADMIN),
    CLIENT(Labels.CLIENT),
    CARETAKER(Labels.CARETAKER),
    FAMILY(Labels.FAMILY);

    public class Labels
    {
        public static final String ADMIN = "ADMIN";
        public static final String CLIENT = "CLIENT";
        public static final String CARETAKER = "CARETAKER";
        public static final String FAMILY = "FAMILY";
    }
    
    private final String label;
    
    private Role (String label)
    {
        this.label = label;
    }
    
    @Override
    public String toString() 
    {
        return this.label;
    }
}
