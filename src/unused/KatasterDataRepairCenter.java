//package control;
//
//import model.Baum;
//import model.BaumKataster;
//import utility.BaumComparator;
//import utility.BaumKatasterEntryComparator;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class KatasterDataRepairCenter
//{
//
//
//    //region[Konstanten]
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
//    private BaumKataster baumKataster;
//    private boolean istRepariert;
//
//
//    //endregion
//    //region [Konstruktor]
//
//    //
//    //    public KatasterDataRepairCenter(BaumKataster baumKataster)
//    //    {
//    //        setSortierteBaeume(katasterSortieren(baumKataster));
//    //    }
//    //
//
//
//    public KatasterDataRepairCenter(BaumKataster baumKataster, float maximumIncreaseBetweenTwoValues)
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
//    private HashMap<String, HashMap<Integer, Baum>> katasterSortieren(BaumKataster baumKataster)
//    {
//        HashMap<String, HashMap<Integer, Baum>> sortierterKataster = new HashMap<>();
//        HashMap<Integer, Baum> baeume = baumKataster.getBaumKataster();
//
//        for (int key : baeume.keySet())
//        {
//            HashMap<Integer, Baum> spezifischeBaeume;
//
//            Baum baum = baeume.get(key);
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
//    //    public BaumKataster getReparierterKataster()throws Exception //todo custom
//    //    {
//    //        if(!getIstRepariert()) throw new Exception();
//    //
//    //        HashMap<Integer, Baum> reparierteBaume = new HashMap<>();
//    //
//    //        for (HashMap<Integer, Baum> spezifischeBaeume : getSortierteBaeume().values())
//    //        {
//    //            reparierteBaume.putAll(spezifischeBaeume);
//    //        }
//    //
//    //
//    //        return new BaumKataster(reparierteBaume);
//    //    }
//
//
//    //endregion
//    //region [Methoden]
//
//
//    public void alleExtremenEntriesVonBaumKatasterEntfernen()
//    {
//        ArrayList<Map.Entry<Integer, Baum>> sortierterBaumKataster = new ArrayList<>(getBaumKataster().getBaumKataster().entrySet());
//
//        for (Integer baumComparatorErlaubterAttributIndex : BaumComparator.getErlaubteAttributIndize())
//        {
//            BaumKatasterEntryComparator baumKatasterEntryComparator = new BaumKatasterEntryComparator(baumComparatorErlaubterAttributIndex);
//
//            sortierterBaumKataster.sort(baumKatasterEntryComparator);
//
//            sortierterBaumKataster = extremeEntriesStatisticallyRobustEntfernen(sortierterBaumKataster, baumComparatorErlaubterAttributIndex);
//        }
//
//        setBaumKataster(new BaumKataster(sortierterBaumKataster));
//        setIstRepariert(true);
//    }
//
//
//    private ArrayList<Map.Entry<Integer, Baum>> extremeEntriesStatisticallyRobustEntfernen(ArrayList<Map.Entry<Integer, Baum>> arrayList, int attributIndex)
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
//    private float[] quartileFinden(ArrayList<Map.Entry<Integer, Baum>> arrayList, int attributIndex)
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
