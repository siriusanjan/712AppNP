package designforlogin.login.com.appnp.TabLayout.AllCategory;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.Objects;

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


public class FrSellCategory extends Fragment {
    RecyclerView recyclerSellCategory;
    ArrayList<AdapterTopSellSubData> catData;

    public FrSellCategory() {

    }

    int a;

    Context mcontext;
    ArrayList<AdapterTopSellSubData> categoryList;
    Toolbar fr_viewAllCategory_toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_fr_sell_category, container, false);
        recyclerSellCategory = v.findViewById(R.id.recyclerSellCategory);
        fr_viewAllCategory_toolbar=v.findViewById(R.id.fr_viewAllCategory_toolbar);

        categoryList = new ArrayList<>();
        fetchCategoryList();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AdapterTopSellSubAdapter catList = new AdapterTopSellSubAdapter(mcontext, categoryList);
                recyclerSellCategory.hasFixedSize();
                recyclerSellCategory.setLayoutManager(new GridLayoutManager(mcontext, 2));
                recyclerSellCategory.setAdapter(catList);
            }
        },1000);
        Bundle bundle = this.getArguments();
        String myString=null;
        if(bundle!=null) {
            if (Objects.equals(bundle.getString("trigerPoint"), "tab")) {
                fr_viewAllCategory_toolbar.setVisibility(View.GONE);
            }
        }

        fr_viewAllCategory_toolbar = v.findViewById(R.id.fr_viewAllCategory_toolbar);

//        if (myString!=null) {
//            mToolBar.setTitle(myString);
//        }else {mToolBar.setTitle("NoString Gain");}
        fr_viewAllCategory_toolbar.setTitle("Ads By You");
        fr_viewAllCategory_toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        fr_viewAllCategory_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        ((MainActivity) getActivity()).setSupportActionBar(fr_viewAllCategory_toolbar);
        fr_viewAllCategory_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment=fragmentManager.findFragmentById(R.id.fr_addMore);
                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragment);
                fragmentTransaction.commit();

            }
        });




        return v;


    }


    public void fetchCategoryList() {

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
                if(e!=null) {
                    String mMessage = e.getMessage();
                    Log.w("failure Response", mMessage);
                }
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                try {
                    String mMessage = response.body().string();
                    Log.d("refreshCheck", "mystirng" + mMessage);

                    JSONArray jObj = new JSONArray(mMessage);
                    Log.d("refreshCheck", "onResponsejjjjj: " + jObj.toString());

                    for (int i = 0; i < jObj.length(); i++) {
                        //eg len = 4
                        //condition
                        // 1. is i less than array length
                        // 2. is i equal to array length
                        JSONObject myString = jObj.getJSONObject(i);
                        String a = myString.getString("Category");
                        categoryList.add(new AdapterTopSellSubData(AdapterTopSellSubData.Category, a, StaticVariable.GetAddedDataImage + "1562571651364.png"));
                        Log.i("refreshCheck", "onResponse: looping");

                        if (i == jObj.length() - 1) {
//                            refreshInUIThread();
                            Log.i("refreshCheck", "onResponse: refreshInUIThread");

                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });
        //set the downloaded data to the adapter


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }
    //    public void getDataFromServer(final View v) {
//        catData = new ArrayList<>();

//        String a = "https://jsonplaceholder.typicode.com/todos/1";
//        String b = "https://jsonplaceholder.typicode.com/posts";
//        RequestQueue queue = Volley.newRequestQueue(context);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, a,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//
//
//                            JSONObject a = new JSONObject(response);
//                            String name = a.getString("title");
//
//
//                            catData.add(new AdapterTopSellSubData(AdapterTopSellSubData.Category, name, "aa"));
//
//                            AdapterTopSellSubAdapter myCat = new AdapterTopSellSubAdapter(getContext(), catData);
//
//                            RecyclerView recyclerSellCategory = v.findViewById(R.id.recyclerSellCategory);
//                            recyclerSellCategory.hasFixedSize();
//                            recyclerSellCategory.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//                            recyclerSellCategory.setAdapter(myCat);
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, b,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d("response", response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("title", "Ram");
//
//                return params;
//            }
//
//        };
//
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
//        requestQueue.add(stringRequest);
//        requestQueue.add(postRequest);

}


//}
//
//
//    }
//
//    public class GetData extends AsyncTask<Void,Void,Void>{
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(getActivity(),"Json Data is downloading",Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            HttpHandler handler = new HttpHandler();
//            // Making a request to url and getting response
//            String url = "http://api.androidhive.info/contacts/";
//            String jsonStr = handler.urlString(url);
//
//            try {
//                JSONObject jObject =new JSONObject(jsonStr);
//                JSONArray jarray =jObject.getJSONArray("contacts");
//                for(int i= 0; i<=jarray.length(); i++){
//                    JSONObject contacDetail =jarray.getJSONObject(i);
//                    String Name = contacDetail.getString("name");
//
//                    JSONObject ph = contacDetail.getJSONObject("phone");
//                    String number =ph.getString("phone");
//
//
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//
//}


//    private String readFromFile() {
//
//
//
//
//String myData ="";
//
//        try {
//            InputStream inputStream = context.getResources().openRawResource(R.raw.category);
//
//            if ( inputStream != null ) {
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String receiveString = "";
//                StringBuilder stringBuilder = new StringBuilder();
//
//                while (
//                        (receiveString = bufferedReader.readLine()) != null ) {
//                    stringBuilder.append(receiveString);
//                }
//
//                inputStream.close();
//                myData = stringBuilder.toString();
//            }
//        }
//        catch (FileNotFoundException e) {
//            Log.e("login activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("login activity", "Can not read file: " + e.toString());
//        }
//
//        return  myData;
//    }


//String strJson = readFromFile();
//    // "{ \"ItemType\" :[{\"name\":\"Sonoo Jaiswal\",\"salary\":\"50000\"},{\"name\":\"Vimal Jaiswal\",\"salary\":\"60000\"}] }";
//    String data;
// try {
//         JSONObject jobjec = new JSONObject(strJson);
//
//         JSONArray jsonArray = jobjec.optJSONArray("category");
//
//         for (int i = 0; i <= jsonArray.length(); i++) {
//         JSONObject jo = jsonArray.getJSONObject(i);
//         String name = jo.optString("type");
//         String imgUrl = jo.optString("imgurl");
//         catData.add(new AdapterTopSellSubData(AdapterTopSellSubData.Category, name + i, imgUrl));
//         }
//         } catch (JSONException e) {
//         e.printStackTrace();
//         }

