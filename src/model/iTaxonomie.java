//package model;
//
//import control.BaumController;
//import resources.Konstanten;
//
//import java.util.List;
//
//public interface iTaxonomie
//{
//
//    String speziesBot();
//
//
//    void speziesBot(String s);
//
//
//    String speziesDt();
//
//
//    void speziesDt(String s);
//
//
//    String gattungBot();
//
//
//    void gattungBot(String s);
//
//
//    String gattungDt();
//
//
//    void gattungDt(String s);
//
//
//    public default void setTaxonomie(List<String> zeileSublist)
//    {
//        this.speziesDt(BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(0)));
//        this.speziesBot(BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(Konstanten.EINS)));
//        this.gattungDt(BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(Konstanten.ZWEI)));
//        this.gattungBot(BaumController.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(Konstanten.DREI)));
//    }
//
//
//    public default String getSpeziesBot()
//    {
//        return this.speziesBot();
//    }
//
//
//    public default String getSpeziesDt()
//    {
//        return this.speziesDt();
//    }
//
//
//    public default String getGattungBot()
//    {
//        return this.gattungBot();
//    }
//
//
//    public default String getGattungDt()
//    {
//        return this.gattungDt();
//    }
//
//
//}
