package designforlogin.login.com.appnp.DetailItemView;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import designforlogin.login.com.appnp.DataModel.AdapterTopSellSubData;
import designforlogin.login.com.appnp.InterfaceProject.Interface;
import designforlogin.login.com.appnp.MainActivity;
import designforlogin.login.com.appnp.R;

public class ItemDetailView extends Fragment {
    Interface interfaace;
    ScriptGroup.Binding binding;
    TabLayout tabDetailsDots;
    Toolbar mToolBar;
    Context mcontext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_detail_item_view, container, false);


        mToolBar = view.findViewById(R.id.fr_AddDetails_toolbar);
        mToolBar.setTitle(getResources().getString(R.string.see_details));
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        ((MainActivity) getActivity()).setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        FragmentManager fragmentManager = getFragmentManager();
                        Fragment fragment=fragmentManager.findFragmentById(R.id.fr_addMore);
                        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                        fragmentTransaction.remove(fragment);
                        fragmentTransaction.commit();


            }
        });
        ArrayList imgList = new ArrayList();

        //SupervisorActivityDatabase username = (SupervisorActivityDatabase) intent.getSerializableExtra("username");
        AdapterTopSellSubData imgUrlList = (AdapterTopSellSubData)getArguments().getSerializable("Item_Details");
        tabDetailsDots = view.findViewById(R.id.tabDetailsDots);

        String imgJson = String.valueOf(imgUrlList.getImgUrl());
        String itemName = String.valueOf(imgUrlList.getTopSellsubText());
        TextView itemNames = view.findViewById(R.id.itemName);
        itemNames.setText(itemName);
        Log.i("hhhhhhhhh", "onCreate: " + imgJson);
        try {
            JSONArray im = new JSONArray(imgJson);
            for (int i = 0; i < im.length(); i++) {

                imgList.add(im.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        interfaace = new ImageSliderAdapter(mcontext, imgList);
        interfaace.onImageReceive(imgList);


        ViewPager viewPager = view.findViewById(R.id.itemImageSlider);
//        ViewPager viewPager = (ViewPager) binding.slider;;

        if (viewPager != null) {
            viewPager.setAdapter(new ImageSliderAdapter(mcontext, imgList));
        }
        tabDetailsDots.setupWithViewPager(viewPager, true);
//        }
//        ImageSliderAdapter adapterView = null;
//        adapterView = new ImageSliderAdapter(this);
//
//
//        mViewPager.setAdapter(adapterView);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }
}
