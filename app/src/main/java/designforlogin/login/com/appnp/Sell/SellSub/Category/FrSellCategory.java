package designforlogin.login.com.appnp.Sell.SellSub.Category;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import designforlogin.login.com.appnp.DataModel.AdapterTopSellSubData;
import designforlogin.login.com.appnp.R;


public class FrSellCategory extends Fragment {

    ArrayList<AdapterTopSellSubData> catData;

    public FrSellCategory() {

    }

    int a;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_fr_sell_category, container, false);
        context = getContext();

        getDataFromServer(v);


        return v;


    }


    public void getDataFromServer(final View v) {
        catData = new ArrayList<>();

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


}
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

