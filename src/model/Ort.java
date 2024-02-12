package model;

import resources.Strings;

public class Ort
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
        
        stringBuilder.append(Strings.OBJEKTNAME);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getObjektName());
        stringBuilder.append(Strings.CRLF);
        
        stringBuilder.append(Strings.BEZIRK);
        stringBuilder.append(Strings.TABULATOR);
        stringBuilder.append(getBezirk());
        
        return stringBuilder.toString();
    }
}
