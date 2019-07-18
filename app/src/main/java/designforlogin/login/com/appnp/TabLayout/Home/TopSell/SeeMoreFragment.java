package designforlogin.login.com.appnp.TabLayout.Home.TopSell;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import designforlogin.login.com.appnp.Adapters.AdapterTopSellSubAdapter;
import designforlogin.login.com.appnp.DataModel.AdapterTopSellSubData;
import designforlogin.login.com.appnp.MainActivity;
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
public class SeeMoreFragment extends Fragment {


    public SeeMoreFragment() {
        // Required empty public constructor
    }
Toolbar mToolBar;
    Context mContext;
    RecyclerView fr_viewAll;
    ArrayList<AdapterTopSellSubData> myAllItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_see_more, container, false);
        fr_viewAll=v.findViewById(R.id.fr_viewAll);
        Bundle bundle = this.getArguments();
        String myString=null;
        if (bundle != null) {
              myString = bundle.getString("viewName");
        }
        mToolBar = v.findViewById(R.id.fr_viewAll_toolbar);
        if (myString!=null) {
            mToolBar.setTitle(myString);
        }else {mToolBar.setTitle("NoString Gain");}
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
        myAllItem = new ArrayList<>();
        final ArrayList<AdapterTopSellSubData> a = getMyItemDetails();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("run", "run: userProfile");
                AdapterTopSellSubAdapter myListAdapter = new AdapterTopSellSubAdapter(mContext, a);
                fr_viewAll.hasFixedSize();
                fr_viewAll.setLayoutManager(new GridLayoutManager(mContext,2));
                fr_viewAll.setAdapter(myListAdapter);
            }
        }, 1000);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext= context;
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


                        myAllItem.add(new AdapterTopSellSubData(AdapterTopSellSubData.TRENDING, name, imageArr));


                        Log.i("subCategoryList", "onResponse: " + name);

                    }

                    Log.i("ssssssssssss", "onResponsess: ");


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });

        return myAllItem;

    }
}
