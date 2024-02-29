//package model;
//
//import Services.TreeServices;
//import resources.Constants;
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
//        this.speziesDt(TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(0)));
//        this.speziesBot(TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(Constants.EINS)));
//        this.gattungDt(TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(Constants.ZWEI)));
//        this.gattungBot(TreeServices.ausgabeStringUnbekanntesAttributZurueckgeben(zeileSublist.get(Constants.DREI)));
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
