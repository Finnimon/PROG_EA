package model;

import Services.BaumServices;
import resources.Konstanten;
import resources.Messages;
import resources.Strings;
import utility.Core;

import java.util.ArrayList;
import java.util.List;

public class Taxonomie implements Cloneable
{
    
    
    private final int INDEX_ART_DEUTSCH = 0;
    
    private final int INDEX_ART_BOTANISCH = 1;
    
    private final int INDEX_GATTUNG_DEUTSCH = 2;
    
    private final int INDEX_GATTUNG_BOTANISCH = 3;
    
    
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
        this.artDeutsch = geeignetenStringFuerEinheitlichkeitZurueckgeben(stringList.get(INDEX_ART_DEUTSCH));
        this.artBotanisch = geeignetenStringFuerEinheitlichkeitZurueckgeben(stringList.get(INDEX_ART_BOTANISCH));
        this.gattungDeutsch = geeignetenStringFuerEinheitlichkeitZurueckgeben(stringList.get(INDEX_GATTUNG_DEUTSCH));
        this.gattungBotanisch = geeignetenStringFuerEinheitlichkeitZurueckgeben(stringList.get(INDEX_GATTUNG_BOTANISCH));
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
    
    
    public ArrayList<String> getAll()
    {
        ArrayList<String> werte = new ArrayList<>();

        werte.add(getArtDeutsch());
        werte.add(getArtBotanisch());
        werte.add(getGattungDeutsch());
        werte.add(getGattungBotanisch());
        
        
        return werte;
    }
    
    
    private String geeignetenStringFuerEinheitlichkeitZurueckgeben(String string)
    {
        return BaumServices.ausgabeStringUnbekanntesAttributZurueckgeben(Core.capitalizeAndTrimString(string));
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Messages.BAUMART);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getArtDeutsch());
        stringBuilder.append(Strings.SCHRAEGSTRICH);
        stringBuilder.append(getArtBotanisch());
        stringBuilder.append(Strings.CRLF);
        stringBuilder.append(Messages.GATTUNG);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getGattungDeutsch());
        stringBuilder.append(Strings.SCHRAEGSTRICH);
        stringBuilder.append(getGattungBotanisch());
        
        
        return stringBuilder.toString();
    }
    
    
    @Override
    protected Taxonomie clone()
    {
        return new Taxonomie(this.getAll());
    }
}
