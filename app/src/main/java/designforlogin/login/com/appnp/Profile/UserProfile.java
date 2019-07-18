package designforlogin.login.com.appnp.Profile;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import designforlogin.login.com.appnp.Adapters.AdapterTopSellSubAdapter;
import designforlogin.login.com.appnp.DataModel.AdapterDataHolder;
import designforlogin.login.com.appnp.DataModel.AdapterTopSellSubData;
import designforlogin.login.com.appnp.MainActivity;
import designforlogin.login.com.appnp.Profile.AddSellItem.SellItemDetails;
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
public class UserProfile extends Fragment {


    public UserProfile() {
        // Required empty public constructor
    }

    Context mcontext;
    Fragment userFragement;
    TextView userBack, postItems;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ArrayList<AdapterTopSellSubData> myItemDetails;
    RecyclerView itemDetailsRecyclerView;
    FloatingActionButton addMoreAds;
    Toolbar mToolBar;
    String myString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
//        userFragement = fragmentManager.findFragmentById(R.id.fr_addMore);
        itemDetailsRecyclerView = view.findViewById(R.id.itemDetailsRecyclerView);
        addMoreAds = view.findViewById(R.id.addMoreAds);
        myItemDetails = new ArrayList<>();
        final ArrayList<AdapterTopSellSubData> a = getMyItemDetails();
        mToolBar = view.findViewById(R.id.fr_viewAllAds_toolbar);

//        if (myString!=null) {
//            mToolBar.setTitle(myString);
//        }else {mToolBar.setTitle("NoString Gain");}
        mToolBar.setTitle("Ads By You");
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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("run", "run: userProfile");
                AdapterTopSellSubAdapter myListAdapter = new AdapterTopSellSubAdapter(mcontext, a);
                itemDetailsRecyclerView.hasFixedSize();
                itemDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
                itemDetailsRecyclerView.setAdapter(myListAdapter);
            }
        }, 1000);
        floadingActionClickListioner();


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }

    public ArrayList<AdapterTopSellSubData> getMyItemDetails() {

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(StaticVariable.GetAddedData)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (e != null) {
                    String mMessage = e.getMessage().toString();
                    Log.w("failure userprofile", mMessage);
                    //call.cancel();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                try {
                    String mMessage = response.body().string();
                    Log.d("getSubCategory", "userprofile" + mMessage);

                    JSONArray jObj = new JSONArray(mMessage);
                    Log.d("getSubCategory", "userprofile: " + jObj.toString());

                    for (int i = 0; i <= jObj.length(); i++) {
                        JSONObject myString = jObj.getJSONObject(i);
                        String Category = myString.getString("Category");
                        String name = myString.getString("name");
                        Log.i("aaaaaaaaaaa", "userProfile: " + name);
                        String model = myString.getString("model");
                        String price = myString.getString("price");
                        String priceStatus = myString.getString("priceStatus");
                        String condition = myString.getString("condition");
                        String imageArr = myString.getString("image");



//                        String imageName = imageArray.getString(0);


                        myItemDetails.add(new AdapterTopSellSubData(AdapterTopSellSubData.My_Ads, name, imageArr));


                        Log.i("subCategoryList", "onResponse: " + name);

                    }

                    Log.i("ssssssssssss", "onResponsess: ");


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });

        return myItemDetails;

    }

    private void floadingActionClickListioner() {
        addMoreAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellItemDetails sellDetail = new SellItemDetails();
                //        userFragement = fragmentManager.findFragmentById(R.id.fr_addMore);

                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack("myTag");
                if (userFragement != null) {
                    fragmentTransaction.replace(R.id.fr_addMore, sellDetail);
                    fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out,
                            R.anim.fade_in, R.anim.fade_out);
                    fragmentTransaction.commit();
                } else {
                    fragmentTransaction.add(R.id.fr_addMore, sellDetail);
                    fragmentTransaction.addToBackStack("drawable_Layout");
                    fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out,
                            R.anim.fade_in, R.anim.fade_out);
                    fragmentTransaction.commit();
                }

            }
        });
    }
}

