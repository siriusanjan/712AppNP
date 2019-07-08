package designforlogin.login.com.appnp.DetailItemView;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import designforlogin.login.com.appnp.DataModel.AdapterTopSellSubData;
import designforlogin.login.com.appnp.InterfaceProject.Interface;
import designforlogin.login.com.appnp.R;

public class ItemDetailView extends AppCompatActivity {
    Interface interfaace;
    ScriptGroup.Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item_view);


        ArrayList imgList = new ArrayList();
        Intent intent = getIntent();
        //SupervisorActivityDatabase username = (SupervisorActivityDatabase) intent.getSerializableExtra("username");
        AdapterTopSellSubData imgUrlList = (AdapterTopSellSubData) intent.getSerializableExtra("Item_Details");


        String imgJson = String.valueOf(imgUrlList.getImgUrl());
        Log.i("hhhhhhhhh", "onCreate: " + imgJson);
        try {
            JSONArray im = new JSONArray(imgJson);
            for (int i = 0; i < im.length(); i++) {

                imgList.add(im.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Context context = getApplicationContext();

        interfaace = new ImageSliderAdapter(this, imgList);
        interfaace.onImageReceive(imgList);


        ViewPager viewPager = findViewById(R.id.itemImageSlider);
//        ViewPager viewPager = (ViewPager) binding.slider;;

        if (viewPager != null) {
            viewPager.setAdapter(new ImageSliderAdapter(context, imgList));
        }
//        }
//        ImageSliderAdapter adapterView = null;
//        adapterView = new ImageSliderAdapter(this);
//
//
//        mViewPager.setAdapter(adapterView);
    }
}
