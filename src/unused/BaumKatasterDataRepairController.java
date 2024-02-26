//package control;
//
//import model.Baum;
//import model.BaumKataster;
//
//import java.util.HashMap;
//
//public class BaumKatasterDataRepairController
//{
//
//
//    private BaumKataster baumKataster;
//
//
//    public BaumKatasterDataRepairController(BaumKataster baumKataster)
//    {
//        setBaumKataster(baumKataster);
//    }
//
//
//    public BaumKataster getBaumKataster()
//    {
//        return baumKataster;
//    }
//
//
//    public void setBaumKataster(BaumKataster baumKataster)
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
//    private <T> void deepRepairSortedBaumKataster(HashMap<T, BaumKataster> sortedBaumKataster)
//    {
//        BaumKataster baumKataster = getBaumKataster();
//        for (T gattungKey : sortedBaumKataster.keySet())
//        {
//            BaumKataster currentRepairKataster = sortedBaumKataster.get(gattungKey);
//            new StatisticalDataRepairCenter(currentRepairKataster, true).deepRepair();
//
//            baumKataster.setRepairableFloats(currentRepairKataster.getRepairableFloats());
//            baumKataster.getEditedDataSetKeys().addAll(currentRepairKataster.getEditedDataSetKeys());
//        }
//    }
//
//
//    private HashMap<String, BaumKataster> sortBaumKatasterByGattung()
//    {
//        BaumKataster baumKataster = getBaumKataster();
//        HashMap<String, HashMap<Integer, Baum>> sortedBaumHashMaps = sortBaumHashMapByGattungBotanisch(baumKataster.getBaumHashMap());
//
//        HashMap<String, BaumKataster> sortedBaumKataster = new HashMap<>();
//
//        for (String gattungKey : sortedBaumHashMaps.keySet())
//        {
//            sortedBaumKataster.put(gattungKey, new BaumKataster(sortedBaumHashMaps.get(gattungKey), baumKataster.getPermissableMaxima()));
//        }
//
//
//        return sortedBaumKataster;
//    }
//
//
//    private HashMap<String, HashMap<Integer, Baum>> sortBaumHashMapByGattungBotanisch(HashMap<Integer, Baum> baumHashMap)
//    {
//        HashMap<String, HashMap<Integer, Baum>> sortedBaumHashMaps = new HashMap<>();
//
//        for (Integer key : baumHashMap.keySet())
//        {
//            Baum baum = baumHashMap.get(key);
//            String sortingKey = baum.getTaxonomie().getGattungBotanisch();
//            HashMap<Integer, Baum> currentSortingMap;
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