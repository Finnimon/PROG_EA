package utility;

import model.Tree;

import java.util.Comparator;
import java.util.Map;

public class BaumKatasterEntryComparator implements Comparator<Map.Entry<Integer, Tree>>
{
    
    
    //region [Attribut]
    
    
    private final BaumComparator baumComparator;
    
    
    //endregion
    //region [Konstruktor]
    
    
    public BaumKatasterEntryComparator(int attribut)throws IllegalArgumentException
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
    public int compare(Map.Entry<Integer, Tree> entry1, Map.Entry<Integer, Tree> entry2)
    {
            return getBaumComparator().compare(entry1.getValue(),entry2.getValue());
    }
    
    
    //endregion
    
    
}
