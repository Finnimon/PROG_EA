package model;

import Services.BaumServices;
import resources.Konstanten;
import resources.Strings;

import java.util.List;

public class Taxonomie
{
    //todo nicht abkürzen
    private final String artBotanisch;
    
    private final String artDeutsch;
    
    private final String gattungBotanisch;
    
    private final String gattungDeutsch;
    
    
    
    public Taxonomie(String artBotanisch, String artDeutsch, String gattungBotanisch, String gattungDeutsch)
    {
        this.artBotanisch = artBotanisch;
        this.artDeutsch = artDeutsch;
        this.gattungBotanisch = gattungBotanisch;
        this.gattungDeutsch = gattungDeutsch;
    }
    
    
    public Taxonomie(List<String> stringList)
    {
        this.artDeutsch = BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(stringList.get(0));
        this.artBotanisch = BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(stringList.get(Konstanten.EINS));
        this.gattungDeutsch = BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(stringList.get(Konstanten.ZWEI));
        this.gattungBotanisch = BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(stringList.get(Konstanten.DREI));
    }
    
    
    
    public String getArtBotanisch()
    {
        return artBotanisch;
    }
    
    
    public String getArtDeutsch()
    {
        return artDeutsch;
    }
    
    
    public String getGattungBotanisch()
    {
        return gattungBotanisch;
    }
    
    
    public String getGattungDeutsch()
    {
        return gattungDeutsch;
    }
    
    
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Strings.BAUMART);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getArtDeutsch());
        stringBuilder.append(Strings.SCHRAEGSTRICH);
        stringBuilder.append(getArtBotanisch());
        stringBuilder.append(Strings.CRLF);
        stringBuilder.append(Strings.GATTUNG);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getGattungDeutsch());
        stringBuilder.append(Strings.SCHRAEGSTRICH);
        stringBuilder.append(getGattungBotanisch());

        
        
        return stringBuilder.toString();
    }
    
    
    
    
}
