//package control;
//
//import model.Tree;
//import model.TreeCadastre;
//import utility.TreeComparator;
//import utility.TreeCadastreEntryComparator;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class KatasterDataRepairCenter
//{
//
//
//    //region[Constants]
//
//
//    private final int INDEX_ERSTES_QUARTIL=0;
//
//
//    private final int INDEX_DRITTES_QUARTIL=1;
//
//
//    private final float IQR_MULTIPLIKATOR=1.5f;
//    //region [Attribut]
//
//
//    private final float maximumIncreaseBetweenTwoValues;
//    private TreeCadastre baumKataster;
//    private boolean istRepariert;
//
//
//    //endregion
//    //region [Konstruktor]
//
//    //
//    //    public KatasterDataRepairCenter(TreeCadastre baumKataster)
//    //    {
//    //        setSortierteBaeume(katasterSortieren(baumKataster));
//    //    }
//    //
//
//
//    public KatasterDataRepairCenter(TreeCadastre baumKataster, float maximumIncreaseBetweenTwoValues)
//    {
//        setBaumKataster(baumKataster);
//        this.maximumIncreaseBetweenTwoValues = maximumIncreaseBetweenTwoValues;
//        initialisieren();
//    }
//
//
//    //endregion
//    //region [Initialisierung]
//
//
//    //todo init
//    private void initialisieren()
//    {
//        setIstRepariert(false);
//    }
//
//
//    private HashMap<String, HashMap<Integer, Tree>> katasterSortieren(TreeCadastre baumKataster)
//    {
//        HashMap<String, HashMap<Integer, Tree>> sortierterKataster = new HashMap<>();
//        HashMap<Integer, Tree> baeume = baumKataster.getBaumKataster();
//
//        for (int key : baeume.keySet())
//        {
//            HashMap<Integer, Tree> spezifischeBaeume;
//
//            Tree baum = baeume.get(key);
//
//            String artBotanisch = baum.getTaxonomie().getArtBotanisch();
//            try
//            {
//                spezifischeBaeume = sortierterKataster.get(artBotanisch);
//            }
//            catch (NullPointerException e)
//            {
//                spezifischeBaeume = new HashMap<>();
//            }
//            spezifischeBaeume.put(key, baum);
//
//            sortierterKataster.put(artBotanisch, spezifischeBaeume);
//        }
//        return sortierterKataster;
//    }
//
//
//    //endregion
//    //region[GetSet]
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
//    public float getMaximumIncreaseBetweenTwoValues()
//    {
//        return maximumIncreaseBetweenTwoValues;
//    }
//
//
//    public boolean isIstRepariert()
//    {
//        return istRepariert;
//    }
//
//
//    private boolean getIstRepariert()
//    {
//        return this.istRepariert;
//    }
//
//
//    private void setIstRepariert(boolean istRepariert)
//    {
//        this.istRepariert = istRepariert;
//    }
//
//
//    //    public TreeCadastre getReparierterKataster()throws Exception //todo custom
//    //    {
//    //        if(!getIstRepariert()) throw new Exception();
//    //
//    //        HashMap<Integer, Tree> reparierteBaume = new HashMap<>();
//    //
//    //        for (HashMap<Integer, Tree> spezifischeBaeume : getSortierteBaeume().values())
//    //        {
//    //            reparierteBaume.putAll(spezifischeBaeume);
//    //        }
//    //
//    //
//    //        return new TreeCadastre(reparierteBaume);
//    //    }
//
//
//    //endregion
//    //region [Methoden]
//
//
//    public void alleExtremenEntriesVonBaumKatasterEntfernen()
//    {
//        ArrayList<Map.Entry<Integer, Tree>> sortierterBaumKataster = new ArrayList<>(getBaumKataster().getBaumKataster().entrySet());
//
//        for (Integer baumComparatorErlaubterAttributIndex : TreeComparator.getErlaubteAttributIndize())
//        {
//            TreeCadastreEntryComparator baumKatasterEntryComparator = new TreeCadastreEntryComparator(baumComparatorErlaubterAttributIndex);
//
//            sortierterBaumKataster.sort(baumKatasterEntryComparator);
//
//            sortierterBaumKataster = extremeEntriesStatisticallyRobustEntfernen(sortierterBaumKataster, baumComparatorErlaubterAttributIndex);
//        }
//
//        setBaumKataster(new TreeCadastre(sortierterBaumKataster));
//        setIstRepariert(true);
//    }
//
//
//    private ArrayList<Map.Entry<Integer, Tree>> extremeEntriesStatisticallyRobustEntfernen(ArrayList<Map.Entry<Integer, Tree>> arrayList, int attributIndex)
//    {
//        //this sucks
//        boolean condition;
//        float[] quartile = quartileFinden(arrayList, attributIndex);
//
//        while (arrayList.getLast().getValue().getMetrik().getAtrributNachBaumComparatorIndex(attributIndex) > IQR_MULTIPLIKATOR*(quartile[INDEX_DRITTES_QUARTIL] - quartile[INDEX_ERSTES_QUARTIL]) + quartile[INDEX_DRITTES_QUARTIL])
//        {
//            arrayList.removeLast();
//        }
//        return arrayList;
//    }
//
//
//    private float[] quartileFinden(ArrayList<Map.Entry<Integer, Tree>> arrayList, int attributIndex)
//    {
//        int arrayListSize = arrayList.size();
//        return new float[]{arrayList.get(Math.round(arrayListSize / 4)).getValue().getMetrik().getAtrributNachBaumComparatorIndex(attributIndex), arrayList.get(Math.round(arrayListSize * 3 / 4)).getValue().getMetrik().getAtrributNachBaumComparatorIndex(attributIndex)};
//    }
//
//
//    //endregion
//
//
//}
