package unused;

import java.util.ArrayList;

public class ReMetrik
{
    private ArrayList<Messwert> messwerte;
    
    
    public ReMetrik(ArrayList<Messwert> messwerte)
    {
        this.messwerte = messwerte;
    }
    
    
//    public ReMetrik(ArrayList<String> namen, int[] werte)
//    {
//        int namenSize
//        if ((namen.size() )!= werte.length)
//        {
//            throw new IllegalArgumentException();
//        }
//        ArrayList<Messwert> messwerte = new ArrayList<>();
//
//        for (int i = 0; i <nam; i++)
//        {
//
//        }
//    }
    
    
    public ReMetrik()
    {
        messwerte = new ArrayList<>();
    }
    
    
    public ArrayList<Messwert> getMesswerte()
    {
        return messwerte;
    }
    
    
    public void setMesswerte(ArrayList<Messwert> messwerte)
    {
        this.messwerte = messwerte;
    }
}
