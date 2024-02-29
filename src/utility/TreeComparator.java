package utility;

import control.TreeCadastreQueryController;
import model.Tree;

import java.util.Arrays;
import java.util.Comparator;


/**
 * @Summary: This class is used to compare {@link Tree} objects based on their {@link Tree#getMetric()}.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class TreeComparator implements Comparator<Tree>
{
    
    
    public static final int INDEX_HEIGHT_METERS = TreeCadastreQueryController.INDEX_DISTRICT_OF_HIGHEST_TREE;
    
    
    public static final int INDEX_CIRCUMFERENCE_CENTIMETERS = TreeCadastreQueryController.INDEX_FIND_TREE_OF_GREATEST_CIRCUMFERENCE;
    
    
    public static final int INDEX_TREETOP_DIAMETER_METERS = TreeCadastreQueryController.INDEX_FIND_TREE_OF_GREATEST_TOP;
    
    
    public static final int INDEX_AGE = TreeCadastreQueryController.INDEX_FIND_OLDEST_TREE;
    
    
    public static final Integer[] ALLOWED_ATTRIBUTE_INDICES = {
            INDEX_HEIGHT_METERS, INDEX_CIRCUMFERENCE_CENTIMETERS, INDEX_TREETOP_DIAMETER_METERS, INDEX_AGE
    };
    
    
    /**
     * @Summary: the index of the attribute of a {@link model.Metric} to compare by.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final int attributeIndex;
    
    
    /**
     * @param attributeIndex the index to set {@link #attributeIndex} to.
     * @throws IllegalArgumentException if {@link #attributeIndex} is not in {@link #ALLOWED_ATTRIBUTE_INDICES}.
     * @Precondition: {@link #attributeIndex} is in {@link #ALLOWED_ATTRIBUTE_INDICES}.
     * @Postcondition: {@link #attributeIndex} is set to the param.
     * @Summary: Sets {@link #attributeIndex} to the param.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public TreeComparator(int attributeIndex) throws IllegalArgumentException
    {
        this.attributeIndex = attributeIndex;
        if (Arrays.binarySearch(ALLOWED_ATTRIBUTE_INDICES, getAttributeIndex()) < 0)
        {
            throw new IllegalArgumentException();
        }
    }
    
    
    /**
     * @return {@link #attributeIndex}
     * @Summary: Getter for {@link #attributeIndex}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public int getAttributeIndex()
    {
        return attributeIndex;
    }
    
    
    /**
     * @param tree the first object to be compared.
     * @param otherTree the second object to be compared.
     * @return 1 if tree is greater, 0 if tree is equal to otherTree, -1 if tree is less than otherTree.
     * @Summary: Compares {@link Tree} objects based on their {@link Tree#getMetric()}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
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