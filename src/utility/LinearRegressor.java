package utility;

import model.LinearFunction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LinearRegressor
{
    
    
    /**
     * @Summary: The index of the basis in all of the regressions.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final int basisIndex;
    
    
    /**
     * @param basisIndex the int to set as {@link #basisIndex}.
     * @Summary: Sets the int {@link #basisIndex} to the given int.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public LinearRegressor(int basisIndex)
    {
        this.basisIndex = basisIndex;
    }
    
    
    /**
     * @return {@link #basisIndex}
     * @Summary: Getter for {@link #basisIndex}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public int getBasisIndex()
    {
        return basisIndex;
    }
    
    
    /**
     * @param regressables all the regressables to regress to the basis index
     * @return {@link ArrayList} of {@link LinearFunction} that are the result of the regressions
     * @Precondition: regressables is not null. Each ArrayList in regressables is not null. Each ArrayList in regressables is the same size.
     * @Postcondition: The return is an ArrayList of {@link LinearFunction} that are the result of the regressions.
     * @Summary: Calculates {@link LinearFunction} that are the result of the regressions of the param regressables.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public ArrayList<LinearFunction> regressAllToBasisIndex( @NotNull ArrayList<ArrayList<Float>> regressables)
    {
        ArrayList<LinearFunction> regressionen = new ArrayList<>();
        
        int basisIndex = getBasisIndex();
        
        ArrayList<ArrayList<Float>> zugeordneteRegressierbare = rearrangeRegressables(regressables);
        
        for (int i = 0; i < zugeordneteRegressierbare.size(); i++)
        {
            if (i == basisIndex)
            {
                regressionen.add(new LinearFunction(1f, 0));
                continue;
            }
            
            regressionen.add(regress(zugeordneteRegressierbare.get(basisIndex), zugeordneteRegressierbare.get(i)));
        }
        
        
        return regressionen;
    }
    
    
    /**
     * @param regressables The values to rearrange.
     * @return The rearranged values.
     * @Precondition: regressables is not null.
     * @Postcondition: The return is the rearranged values suitable for regression with all values of the same fields within the same ArrayList.
     * @Summary: Turn rows into columns and columns into rows.
     *
     */
    private ArrayList<ArrayList<Float>> rearrangeRegressables(@NotNull ArrayList<ArrayList<Float>> regressables)
    {
        ArrayList<ArrayList<Float>> zugeordneteRegressierbare = new ArrayList<>();
        
        for (ArrayList<Float> column : regressables)
        {
            for (int j = 0; j < column.size(); j++)
            {
                ArrayList<Float> line;
                try
                {
                    line = zugeordneteRegressierbare.get(j);
                }
                catch (IndexOutOfBoundsException e)
                {
                    zugeordneteRegressierbare.add(line = new ArrayList<>());
                }
                line.add(column.get(j));
            }
        }
        
        
        return zugeordneteRegressierbare;
    }
    
    
    /**
     * @param x all the x values of the regression.
     * @param y all the y values of the regression.
     * @return A new {@link LinearFunction} that is the result of a linear regression between x and y.
     * @Precondition: x and y are the same size. Neither x nor y contain any null values.
     * @Summary: Calculates a new {@link LinearFunction} that is the result of a linear regression between x and y.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     * @throws IllegalArgumentException if x and y are not the same size.
     */
    public LinearFunction regress(@NotNull ArrayList<Float> x,@NotNull ArrayList<Float> y)
    {
        int countOfRegressedElements = x.size();
        
        assert countOfRegressedElements == y.size();
        
        float sumX = 0;
        float sumY = 0;
        float sumXY = 0;
        float sumXX = 0;
        
        for (int i = 0; i < countOfRegressedElements; i++)
        {
            float currentX;
            float currentY;
            sumX += (currentX = x.get(i));
            sumY += (currentY = y.get(i));
            sumXY += currentX * currentY;
            sumXX += currentX * currentX;
        }
        float slope = calculateSlope(sumX, sumY, sumXY, sumXX, countOfRegressedElements);
        
        return new LinearFunction(slope, calculateYIntersect(sumX, sumY, slope, countOfRegressedElements));
    }
    
    
    /**
     * @param sumX the sum of all x values.
     * @param sumY the sum of all y values.
     * @param sumXY the sum of all x*y values.
     * @param sumXX the sum of all x*x values.
     * @param countOfRegressedElements the number of elements that are regressed
     * @return The slope of the regression
     */
    private float calculateSlope(float sumX, float sumY, float sumXY, float sumXX, int countOfRegressedElements)
    {
        return (countOfRegressedElements * sumXY - sumX * sumY) / (countOfRegressedElements * sumXX - sumX * sumX);
    }
    
    
    /**
     * @param sumX the sum of all x values.
     * @param sumY the sum of all y values.
     * @param slope The slope of the regression as calculated by {@link #calculateSlope(float, float, float, float, int)}.
     * @param countOfRegressedElements the number of elements that are regressed
     * @return The y-intersect of the regression
     */
    private float calculateYIntersect(float sumX, float sumY, float slope, int countOfRegressedElements)
    {
        return (sumY - slope * sumX) / countOfRegressedElements;
    }
    
    
}
