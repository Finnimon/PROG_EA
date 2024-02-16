package Services;

import model.Baum;

import java.util.Comparator;
import java.util.Map;

public class KatasterEntryComparator implements Comparator<Map.Entry<Integer, Baum>>
{
    
    
    //region [Attribut]
    
    
    private final BaumComparator baumComparator;
    
    
    //endregion
    //region [Konstruktor]
    
    
    public KatasterEntryComparator(int attribut)throws IllegalArgumentException
    {
        this.baumComparator=new BaumComparator(attribut);
    }
    
    
    //endregion
    //region [Get]
    
    
    private BaumComparator getBaumComparator()
    {
        return this.baumComparator;
    }
    
    
    //endregion
    //region[Override]
    
    
    @Override
    public int compare(Map.Entry<Integer, Baum> entry1, Map.Entry<Integer, Baum> entry2)
    {
            return getBaumComparator().compare(entry1.getValue(),entry2.getValue());
    }
    
    
    //endregion
    
    
}
