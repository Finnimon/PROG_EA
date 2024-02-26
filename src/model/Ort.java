package model;

import resources.Messages;
import resources.Strings;

public class Ort implements Cloneable
{
    private final String objektName;
    private final String bezirk;
    
    
    public Ort(String objektName, String bezirk)
    {
        this.objektName = objektName;
        this.bezirk = bezirk;
    }
    
    
    public String getObjektName()
    {
        return objektName;
    }
    
    
    public String getBezirk()
    {
        return bezirk;
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Messages.OBJEKTNAME);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getObjektName());
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Messages.BEZIRK);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getBezirk());
        
        return stringBuilder.toString();
    }
    
    
    @Override
    public Ort clone()
    {
        
        return new Ort(getObjektName(), getBezirk());
    }
}
