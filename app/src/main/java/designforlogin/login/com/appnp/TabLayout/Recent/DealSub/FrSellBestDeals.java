package designforlogin.login.com.appnp.TabLayout.Recent.DealSub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import designforlogin.login.com.appnp.DataModel.AdapterDataHolder;
import designforlogin.login.com.appnp.DataModel.AdapterTopSellSubData;
import designforlogin.login.com.appnp.Adapters.AdapterSellTopSell;
import designforlogin.login.com.appnp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrSellBestDeals extends Fragment {


    public FrSellBestDeals() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fr_sell_best_deals, container, false);

        ArrayList<AdapterDataHolder> a= new ArrayList<>();
        ArrayList<AdapterTopSellSubData> b= new ArrayList<>();
        for(int i=0;i<=10; i++){
            b.add(new AdapterTopSellSubData(AdapterTopSellSubData.Best_Deal,"Deal Item" +i,"0"));
        }
        for(int i=0;i<=50; i++){
            a.add(new AdapterDataHolder(AdapterDataHolder.Main_Best_Deal,"Deal Type" +i, b));
        }

        AdapterSellTopSell forBestDeal = new AdapterSellTopSell(getContext(),a);
        RecyclerView viewMainBestDeal = v.findViewById(R.id.viewSellBestDeal);
        viewMainBestDeal.hasFixedSize();
        viewMainBestDeal.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewMainBestDeal.setAdapter(forBestDeal);


        return v;
    }



}
