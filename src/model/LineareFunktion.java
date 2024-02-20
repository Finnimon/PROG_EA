package model;

import java.util.function.Function;

public class LineareFunktion
{
    
    
    //region[Attribute]
    
    
    private final float steigung;
    private final float yAchsenAbschnitt;
    
    
    //endregion
    //region[Konstruktor]
    
    
    public LineareFunktion(float steigung, float yAchsenAbschnitt)
    {
        this.steigung=steigung;
        this.yAchsenAbschnitt=yAchsenAbschnitt;
    }
    
    
    //endregion
    //region [Get]
    
    
    public float getSteigung()
    {
        return steigung;
    }
    
    
    public float getyAchsenAbschnitt()
    {
        return yAchsenAbschnitt;
    }
    
    
    //endregion
    //region[Methoden]
    
    
    public float fVonX(float x)
    {//todo func
        return getSteigung()*x+getyAchsenAbschnitt();
    }
    
    
    public float inverseFVonY(float y)
    {
        return (y-getyAchsenAbschnitt())/getSteigung();
    }
    
    
    //endregion
}
