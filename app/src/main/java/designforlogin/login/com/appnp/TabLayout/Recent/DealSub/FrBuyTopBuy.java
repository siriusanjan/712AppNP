package designforlogin.login.com.appnp.TabLayout.Recent.DealSub;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import designforlogin.login.com.appnp.Adapters.AdapterSellTopSell;
import designforlogin.login.com.appnp.DataModel.AdapterDataHolder;
import designforlogin.login.com.appnp.DataModel.AdapterTopSellSubData;
import designforlogin.login.com.appnp.R;
import designforlogin.login.com.appnp.StaticVariable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrBuyTopBuy extends Fragment {
    RecyclerView fr_top_recycler_view;
    Context mcontext;
    ArrayList<AdapterTopSellSubData> viewSubJson1, viewSubJson2;
    ArrayList<AdapterDataHolder> adapterDataHolders;


    public FrBuyTopBuy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fr_buy_top_buy, container, false);



        viewSubJson1 = new ArrayList<>();
        viewSubJson2 = new ArrayList<>();
        adapterDataHolders = new ArrayList<>();
        fr_top_recycler_view = v.findViewById(R.id.fr_recent_recycler_view);


        getAddedDetailData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AdapterSellTopSell adapter = new AdapterSellTopSell(getContext(), adapterDataHolders);
                fr_top_recycler_view.hasFixedSize();
                fr_top_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                fr_top_recycler_view.setAdapter(adapter);
                Log.i("DATAFROMSERVER", "run: " + "data from server");

            }
        }, 500);


        return v;

    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext=context;

    }


    public void getAddedDetailData() {

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(StaticVariable.GetAddedData)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(e!=null) {
                    String mMessage = e.getMessage().toString();
                    Log.w("failure Response", mMessage);
                }
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                try {
                    String mMessage = response.body().string();
                    Log.d("getSubCategory", "mystirng" + mMessage);

                    JSONArray jObj = new JSONArray(mMessage);
                    Log.d("getSubCategory", "onResponsejjjjj: " + jObj.toString());

                    for (int i = 0; i <= jObj.length(); i++) {
                        JSONObject myString = jObj.getJSONObject(i);
                        String Category = myString.getString("Category");
                        String name = myString.getString("name");
                        Log.i("aaaaaaaaaaa", "onResponse: " + name);
                        String model = myString.getString("model");
                        String price = myString.getString("price");
                        String priceStatus = myString.getString("priceStatus");
                        String condition = myString.getString("condition");
                        String imageArr = myString.getString("image");


//

//                        String imageName = imageArray.getString(0);

                        if (Category.equals("mobile")) {
                            Log.i("myimage", "onResponseArr: " + name);
                            viewSubJson1.add(new AdapterTopSellSubData(AdapterTopSellSubData.TOP_SELL, name, imageArr));
                        }
                        if (Category.equals("furniture")) {
                            viewSubJson2.add(new AdapterTopSellSubData(AdapterTopSellSubData.TOP_SELL, name, imageArr));

                        }

                        Log.i("subCategoryList", "onResponse: " + name);

                    }

                    Log.i("ssssssssssss", "onResponsess: ");


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });

        adapterDataHolders.add(new AdapterDataHolder(AdapterDataHolder.Main_TOP_TYOE,  "Mobile", viewSubJson1));
        adapterDataHolders.add(new AdapterDataHolder(AdapterDataHolder.Main_TOP_TYOE,  "Furniture", viewSubJson2));


        //set the downloaded data to the adapter
//        //        //set the downloaded data to the adapter
//        adapterDataHolders.add(new AdapterDataHolder(AdapterDataHolder.Main_TOP_TYOE, "<< " + Category, viewSubJson1));
//        adapterDataHolders.add(new AdapterDataHolder(AdapterDataHolder.Main_TOP_TYOE, "<< " + Category, viewSubJson2));
//        AdapterSellTopSell adapter = new AdapterSellTopSell(getContext(), adapterDataHolders);
//        fr_top_recycler_view.hasFixedSize();
//        fr_top_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
//        fr_top_recycler_view.setAdapter(adapter);

    }
}
