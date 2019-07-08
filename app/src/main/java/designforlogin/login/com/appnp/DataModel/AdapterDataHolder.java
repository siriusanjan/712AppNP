package designforlogin.login.com.appnp.DataModel;

import java.io.Serializable;
import java.util.ArrayList;

public class AdapterDataHolder implements Serializable{
public static int Main_TOP_TYOE=1,Main_Best_Deal=2,Main_CATEGORY =2;

    String myText;
    int typeMain;
ArrayList<AdapterTopSellSubData> adapterTopSellSubData;

public AdapterDataHolder(){

}
    public AdapterDataHolder(int typeMain,String myText, ArrayList<AdapterTopSellSubData> adapterTopSellSubData ){
        this.typeMain=typeMain;
        this.adapterTopSellSubData=adapterTopSellSubData;
        this.myText=myText;
    }

    public int getTypeMain() {
        return typeMain;
    }

    public void setTypeMain(int typeMain) {
        this.typeMain = typeMain;
    }

    public ArrayList<AdapterTopSellSubData> getAdapterTopSellSubData() {
        return adapterTopSellSubData;
    }

    public void setAdapterTopSellSubData(ArrayList<AdapterTopSellSubData> adapterTopSellSubData) {
        this.adapterTopSellSubData = adapterTopSellSubData;
    }

    public String getMyText() {
        return myText;
    }

    public void setMyText(String myText) {
        this.myText = myText;
    }
}
