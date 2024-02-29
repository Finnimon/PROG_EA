package Utility;

import Model.Tree;

import java.util.Comparator;
import java.util.Map;

public class TreeCadastreEntryComparator implements Comparator<Map.Entry<Integer, Tree>>
{
    
    
    private final TreeComparator treeComparator;
    
    
    public TreeCadastreEntryComparator(int attributeIndex)throws IllegalArgumentException
    {
        this.treeComparator =new TreeComparator(attributeIndex);
    }
    
    
    private TreeComparator getTreeComparator()
    {
        return this.treeComparator;
    }
    
    
    @Override
    public int compare(Map.Entry<Integer, Tree> entry1, Map.Entry<Integer, Tree> entry2)
    {
            return getTreeComparator().compare(entry1.getValue(),entry2.getValue());
    }
    
    
}
