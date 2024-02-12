package model;

import control.BaumController;
import resources.Konstanten;
import resources.Strings;

import java.util.List;

public class Taxonomie
{
    //todo nicht abkürzen
    private final String speziesBot;
    
    private final String speziesDt;
    
    private final String gattungBot;
    
    private final String gattungDt;
    
    
    
    public Taxonomie(String speziesBot, String speziesDt, String gattungBot, String gattungDt)
    {
        this.speziesBot = speziesBot;
        this.speziesDt = speziesDt;
        this.gattungBot = gattungBot;
        this.gattungDt = gattungDt;
    }
    
    
    public Taxonomie(List<String> zeileSublist)
    {
        this.speziesDt=BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(0));
        this.speziesBot=BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(Konstanten.EINS));
        this.gattungDt=BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(Konstanten.ZWEI));
        this.gattungBot=BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(Konstanten.DREI));
    }
    
    
    
    public String getSpeziesBot()
    {
        return speziesBot;
    }
    
    
    public String getSpeziesDt()
    {
        return speziesDt;
    }
    
    
    public String getGattungBot()
    {
        return gattungBot;
    }
    
    
    public String getGattungDt()
    {
        return gattungDt;
    }
    
    
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Strings.BAUMART);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getSpeziesDt());
        stringBuilder.append(Strings.SCHRAEGSTRICH);
        stringBuilder.append(getSpeziesBot());
        stringBuilder.append(Strings.CRLF);
        stringBuilder.append(Strings.GATTUNG);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getGattungDt());
        stringBuilder.append(Strings.SCHRAEGSTRICH);
        stringBuilder.append(getGattungBot());

        
        
        return stringBuilder.toString();
    }
    
    
    
    
}
