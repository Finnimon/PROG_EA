package model;

/**
 * @param slope
 * @param yIntersect
 * @Summary: This record represents the mathematical concept linear function.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public record LinearFunction(float slope, float yIntersect)
{
    
    
    /**
     * @param x the x value
     * @return y=slope*x+yIntersect
     * @Precondition: slope != float.nan
     * @Postcondition: f(x)!=float.nan
     * @Summary: f(x) returns the result of f(x), y.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public float f(float x)
    {
        return slope() * x + yIntersect();
    }
    
    
    /**
     * @param y the y value
     * @return f^(-1)(y)=x
     * @Precondition: slope != 0
     * @Postcondition: f^(-1)(y)!=float.nan. Der return von fVonX(InverseFVonY(x))==y.
     * @Summary: inverse returns f^(-1)(y)=x.
     * If f(x) is not invertible it will return float.nan.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public float inverse(float y)
    {
        return (y - yIntersect()) / slope();
    }
    
    
}
