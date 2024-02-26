package model;

/**
 * @Summary: LineareFunktion ist eine Repraesentation einer mathematischen linearen Funktion.
 * @Custom.Author: Finn Lindig
 * @Custom.Since: 26.02.2024
 * @param steigung
 * @param yAchsenAbschnitt
 */
public record LineareFunktion(float steigung, float yAchsenAbschnitt)
{
    
    
    /**
     * @Custom.Precondition: steigung != float.nan
     * @Custom.Postcondition: Der return y wird nicht NaN sein.
     * @Summary: fVonX gibt das Ergebnis der linearen Funktion f(x)=y zurueck.
     * @Custom.Author: Finn Lindig
     * @Custom.Since: 26.02.2024
     * @param x
     * @return f(x)=y
     */
    public float fVonX(float x)
    {
        return steigung() * x + yAchsenAbschnitt();
    }
    
    
    /**
     * @Custom.Precondition: steigung != 0
     * @Custom.Postcondition: Der return x wird nicht NaN sein. Der return von fVonX(InverseFVonY(x)) ist der Parameter y.
     * @Summary: inverseFVonY gibt das Ergebnis der inversen linearen Funktion f^(-1)(y)=x zurueck.
     * Bei einer nicht invertierbaren Funktion wird float.nan zurueckgegeben.
     * @Custom.Author: Finn Lindig
     * @Custom.Since: 26.02.2024
     * @param y
     * @return f^(-1)(y)=x
     */
    public float inverseFVonY(float y)
    {
        return (y - yAchsenAbschnitt()) / steigung();
    }
    
    
}
