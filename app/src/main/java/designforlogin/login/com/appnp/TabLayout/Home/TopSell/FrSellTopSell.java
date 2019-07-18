package designforlogin.login.com.appnp.TabLayout.Home.TopSell;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import designforlogin.login.com.appnp.DataModel.AdapterDataHolder;
import designforlogin.login.com.appnp.DataModel.AdapterTopSellSubData;
import designforlogin.login.com.appnp.Adapters.AdapterSellTopSell;
import designforlogin.login.com.appnp.R;
import designforlogin.login.com.appnp.StaticVariable;
import designforlogin.login.com.appnp.TestInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */

public class FrSellTopSell extends Fragment {

    RecyclerView fr_top_recycler_view;
    Context context;
    TestInterface testInterfacaefragemnt;
    ArrayList<AdapterTopSellSubData> viewSubJson1, viewSubJson2;
    ArrayList<AdapterDataHolder> adapterDataHolders;


    public FrSellTopSell() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fr_sell_top_sell, container, false);

        this.testInterfacaefragemnt.myViewHolderPosition(2222);

        viewSubJson1 = new ArrayList<>();
        viewSubJson2 = new ArrayList<>();
        adapterDataHolders = new ArrayList<>();
        fr_top_recycler_view = v.findViewById(R.id.fr_top_sell_recycler_view);


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


    private String readFromFile(Context context) {
        String myData = "";

        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.top_sell);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while (
                        (receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                myData = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return myData;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        testInterfacaefragemnt = ((TestInterface) context);
        testInterfacaefragemnt.myViewHolderPosition(2222);

    }

    public void getCategory() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(StaticVariable.AddItemCategoryUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

//        Response response = client.newCall(request).execute();
//        Log.e(TAG, response.body().string());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                try {
                    String mMessage = response.body().string();
                    Log.d("refreshCheck", "mystirng" + mMessage);

                    JSONArray jObj = new JSONArray(mMessage);
                    Log.d("refreshCheck", "onResponsejjjjj: " + jObj.toString());
                    getAddedDetailData();

                    for (int i = 0; i < jObj.length(); i++) {
                        JSONObject myString = jObj.getJSONObject(i);
                        String Category = myString.getString("Category");
                        Log.i("jsonCategoryValue", "onResponse: " + Category);
                        if ("mobile".equals(Category)) {
                            adapterDataHolders.add(new AdapterDataHolder(AdapterDataHolder.Main_TOP_TYOE,  Category, viewSubJson1));
                        }
                        if ("furniture".equals(Category)) {
                            adapterDataHolders.add(new AdapterDataHolder(AdapterDataHolder.Main_TOP_TYOE, "furniture", viewSubJson2));
                            Log.i("ssssssssssss", "onResponsess: " + viewSubJson2);
                        }


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });
        //set the downloaded data to the adapter

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
                    String mMessage = e.getMessage();
                    Log.w("failure Response", mMessage);
                    //call.cancel();
                }
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

        adapterDataHolders.add(new AdapterDataHolder(AdapterDataHolder.Main_TOP_TYOE,  "Trending", viewSubJson1));
        adapterDataHolders.add(new AdapterDataHolder(AdapterDataHolder.Main_TOP_TYOE,  "Recommended", viewSubJson2));
        adapterDataHolders.add(new AdapterDataHolder(AdapterDataHolder.Main_TOP_TYOE,  "Recently viewed", viewSubJson1));


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


