package unused;

public class Messwert
{
    private final String name;
    private int messwert;
    
    
    public Messwert(String name)
    {
        this.name = name;
    }
    
    
    public Messwert(String name, int messwert)
    {
        this.name = name;
        this.messwert = messwert;
    }
    
    
    public String getName()
    {
        return name;
    }
    
    
    public int getMesswert()
    {
        return messwert;
    }
    
    
    public void setMesswert(int messwert)
    {
        this.messwert = messwert;
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder=new StringBuilder(getName());
        stringBuilder.append(": ");
        stringBuilder.append(getMesswert());
        
        
        return stringBuilder.toString();
    }
}
