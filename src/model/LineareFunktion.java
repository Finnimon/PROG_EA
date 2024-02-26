package model;

public record LineareFunktion(float steigung, float yAchsenAbschnitt)
{
    
    
    public float fVonX(float x)
    {
        return steigung() * x + yAchsenAbschnitt();
    }
    
    
    public float inverseFVonY(float y)
    {
        return (y - yAchsenAbschnitt()) / steigung();
    }
    
    
    
}
