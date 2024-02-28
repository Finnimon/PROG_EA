//package control;
//
//import model.Tree;
//import model.TreeCadastre;
//
//import java.util.HashMap;
//
//public class BaumKatasterDataRepairController
//{
//
//
//    private TreeCadastre baumKataster;
//
//
//    public BaumKatasterDataRepairController(TreeCadastre baumKataster)
//    {
//        setBaumKataster(baumKataster);
//    }
//
//
//    public TreeCadastre getBaumKataster()
//    {
//        return baumKataster;
//    }
//
//
//    public void setBaumKataster(TreeCadastre baumKataster)
//    {
//        this.baumKataster = baumKataster;
//    }
//
//
//    public void shallowRepairBaumKataster()
//    {
//        new StatisticalDataRepairCenter(getBaumKataster()).shallowRepair();
//    }
//
//
//    public void deepRepairBaumKataster()
//    {
//        deepRepairSortedBaumKataster(sortBaumKatasterByGattung());
//    }
//
//
//    private <T> void deepRepairSortedBaumKataster(HashMap<T, TreeCadastre> sortedBaumKataster)
//    {
//        TreeCadastre baumKataster = getBaumKataster();
//        for (T gattungKey : sortedBaumKataster.keySet())
//        {
//            TreeCadastre currentRepairKataster = sortedBaumKataster.get(gattungKey);
//            new StatisticalDataRepairCenter(currentRepairKataster, true).deepRepair();
//
//            baumKataster.setRepairableFloats(currentRepairKataster.getRepairableFloats());
//            baumKataster.getEditedDataSetKeys().addAll(currentRepairKataster.getEditedDataSetKeys());
//        }
//    }
//
//
//    private HashMap<String, TreeCadastre> sortBaumKatasterByGattung()
//    {
//        TreeCadastre baumKataster = getBaumKataster();
//        HashMap<String, HashMap<Integer, Tree>> sortedBaumHashMaps = sortBaumHashMapByGattungBotanisch(baumKataster.getBaumHashMap());
//
//        HashMap<String, TreeCadastre> sortedBaumKataster = new HashMap<>();
//
//        for (String gattungKey : sortedBaumHashMaps.keySet())
//        {
//            sortedBaumKataster.put(gattungKey, new TreeCadastre(sortedBaumHashMaps.get(gattungKey), baumKataster.getPermissableMaxima()));
//        }
//
//
//        return sortedBaumKataster;
//    }
//
//
//    private HashMap<String, HashMap<Integer, Tree>> sortBaumHashMapByGattungBotanisch(HashMap<Integer, Tree> baumHashMap)
//    {
//        HashMap<String, HashMap<Integer, Tree>> sortedBaumHashMaps = new HashMap<>();
//
//        for (Integer key : baumHashMap.keySet())
//        {
//            Tree baum = baumHashMap.get(key);
//            String sortingKey = baum.getTaxonomie().getGattungBotanisch();
//            HashMap<Integer, Tree> currentSortingMap;
//
//
//            currentSortingMap = sortedBaumHashMaps.get(sortingKey);
//            if (currentSortingMap == null)
//            {
//                currentSortingMap = new HashMap<>();
//            }
//
//
//            currentSortingMap.put(key, baum);
//            sortedBaumHashMaps.put(sortingKey, currentSortingMap);
//        }
//
//
//        return sortedBaumHashMaps;
//    }
//
//}