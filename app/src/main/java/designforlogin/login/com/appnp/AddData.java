package designforlogin.login.com.appnp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddData extends Fragment {

    String a;
    String b = "https://jsonplaceholder.typicode.com/posts";
    TextView jaysonData;

    public AddData() {
        // Required empty public constructor
    }

    EditText addNewItems;
    Button addMore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_data, container, false);
        addNewItems = v.findViewById(R.id.addNewItems);
        addMore = v.findViewById(R.id.btnAddMore);
        jaysonData = v.findViewById(R.id.jaysonData);



        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = addNewItems.getText().toString().trim();
                addItems();
            }
        });


        return v;
    }

    public void addItems() {
//        StringRequest postRequest = new StringRequest(Request.Method.POST, b,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
//                        Log.d("response", response);
//                        try {
//                            JSONObject job = new JSONObject(response);
//                            String d = job.getString("title");
//
//                            jaysonData.setText(d);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
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
//                params.put("title", a);
//                return params;
//            }
//
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
//        requestQueue.add(postRequest);

    }



}


