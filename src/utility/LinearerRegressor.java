package utility;

import model.LineareFunktion;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LinearerRegressor
{
    
    
    //region [Attribut]
    
    private final int basisIndex;
    
    
    //endregion
    //region [Konstruktor]
    
    
    public LinearerRegressor(int basisIndex)
    {
        this.basisIndex = basisIndex;
    }
    
    
    //endregion
    //region [Get]
    
    
    public int getBasisIndex()
    {
        return basisIndex;
    }
    
    
    //endregion
    //region [Methoden]
    
    
    public ArrayList<LineareFunktion> alleRegressierenZurBasis(ArrayList<ArrayList<Float>> regressierbare)
    {
        ArrayList<LineareFunktion> regressionen = new ArrayList<>();
        
        int basisIndex = getBasisIndex();
        
        ArrayList<ArrayList<Float>> zugeordneteRegressierbare = regressierbareZuordnen(regressierbare);
        
        for (int i = 0; i < zugeordneteRegressierbare.size(); i++)
        {
            if (i == basisIndex)
            {
                regressionen.add(new LineareFunktion(1f, 0));
                continue;
            }
            
            regressionen.add(regressieren(zugeordneteRegressierbare.get(basisIndex), zugeordneteRegressierbare.get(i)));
        }
        
        
        return regressionen;
    }
    
    
    private ArrayList<ArrayList<Float>> regressierbareZuordnen(ArrayList<ArrayList<Float>> regressierbare)
    {
        ArrayList<ArrayList<Float>> zugeordneteRegressierbare = new ArrayList<>();
        
        for (ArrayList<Float> spalte : regressierbare)
        {
            for (int j = 0; j < spalte.size(); j++)
            {
                ArrayList<Float> zeile;
                try
                {
                    zeile = zugeordneteRegressierbare.get(j);
                }
                catch (IndexOutOfBoundsException e)
                {
                    zugeordneteRegressierbare.add(zeile = new ArrayList<>());
                }
                zeile.add(spalte.get(j));
            }
        }
        
        
        return zugeordneteRegressierbare;
    }
    
    
    private LineareFunktion regressieren(ArrayList<Float> x, ArrayList<Float> y)
    {
        int anzahlRegressierterElemente = x.size();
        
        assert anzahlRegressierterElemente == y.size();
        
        float summeX = 0;
        float summeY = 0;
        float summeXY = 0;
        float summeXX = 0;
        
        for (int i = 0; i < anzahlRegressierterElemente; i++)
        {
            float currentX;
            float currentY;
            summeX += (currentX = x.get(i));
            summeY += (currentY = y.get(i));
            summeXY += currentX * currentY;
            summeXX += currentX * currentX;
        }
        float steigung = steigungBerechnen(summeX, summeY, summeXY, summeXX, anzahlRegressierterElemente);
        
        return new LineareFunktion(steigung, yAchsenAbschnittBerechnen(summeX, summeY, steigung, anzahlRegressierterElemente));
    }
    
    
    private float steigungBerechnen(float summeX, float summeY, float summeXY, float summeXX, int anzahlRegressierterElemente)
    {
        return (anzahlRegressierterElemente * summeXY - summeX * summeY) / (anzahlRegressierterElemente * summeXX - summeX * summeX);
    }
    
    
    private float yAchsenAbschnittBerechnen(float summeX, float summeY, float steigung, int anzahlRegressierterElemente)
    {
        return (summeY - steigung * summeX) / anzahlRegressierterElemente;
    }
    
    
}
