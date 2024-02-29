package Utility;

import Model.Tree;
import control.TreeCadastreQueryController;

import java.util.Arrays;
import java.util.Comparator;


public class TreeComparator implements Comparator<Tree>
{
    
    
    public static final int INDEX_HOEHE_METER = TreeCadastreQueryController.INDEX_DISTRICT_OF_HIGHEST_TREE;
    
    
    public static final int INDEX_KRONE_METER = TreeCadastreQueryController.INDEX_FIND_TREE_OF_GREATEST_TOP;
    
    
    public static final int INDEX_UMFANG_ZENTIMETER = TreeCadastreQueryController.INDEX_FIND_TREE_OF_GREATEST_CIRCUMFERENCE;
    
    
    public static final int INDEX_ALTER = TreeCadastreQueryController.INDEX_FIND_OLDEST_TREE;
    
    
    private static final Integer[] erlaubteAttributIndize =
            {
            INDEX_HOEHE_METER,
            INDEX_KRONE_METER,
            INDEX_UMFANG_ZENTIMETER,
            INDEX_ALTER
            };
    
    
    private final int attributeIndex;
    
    
    public TreeComparator(int attributeIndex) throws IllegalArgumentException
    {
        this.attributeIndex = attributeIndex;
        
        if (Arrays.binarySearch(getErlaubteAttributIndize(), getAttributeIndex()) < 0)
        {
            throw new IllegalArgumentException();
        }
    }
    
    
    public static Integer[] getErlaubteAttributIndize()
    {
        return erlaubteAttributIndize;
    }
    
    
    public int getAttributeIndex()
    {
        return attributeIndex;
    }
    
    
    @Override
    public int compare(Tree tree, Tree otherTree)
    {
        if (null == tree && null == otherTree)
        {
            return 0;
        }
        else if (null == tree)
        {
            return -1;
        }
        else if (null == otherTree)
        {
            return 1;
        }
        Float treeFloat = tree.getMetric().getAttributeByTreeComparatorIndex(getAttributeIndex());
        float otherTreeFloat = otherTree.getMetric().getAttributeByTreeComparatorIndex(getAttributeIndex());
        
        
        return treeFloat.compareTo(otherTreeFloat);
    }
    
    
    
}