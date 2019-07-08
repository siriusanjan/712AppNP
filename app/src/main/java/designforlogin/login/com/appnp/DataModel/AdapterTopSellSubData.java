package designforlogin.login.com.appnp.DataModel;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdapterTopSellSubData implements Serializable{
    public static int TOP_SELL=1,Best_Deal=2,Category=3;
    private String topSellsubText;
    private String imgUrl;
    int type;
    public AdapterTopSellSubData(){

    }



    public AdapterTopSellSubData(int type, String topSellsubText, String imgUrl) {
        this.type=type;
        this.topSellsubText = topSellsubText;
        this.imgUrl = imgUrl;

    }




    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



    public String getTopSellsubText() {
        return topSellsubText;
    }

    public void setTopSellsubText(String topSellsubText) {
        this.topSellsubText = topSellsubText;
    }
}
