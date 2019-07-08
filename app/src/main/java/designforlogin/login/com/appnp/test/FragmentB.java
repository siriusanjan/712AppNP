package designforlogin.login.com.appnp.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.util.HashMap;
import java.util.Map;

import designforlogin.login.com.appnp.R;

public class FragmentB extends Fragment implements TestInterfaceTest, View.OnClickListener {

    TextView txtFromDatabase;
    EditText edtdatabase, edtDatabaseID;
    Button btnAdd, btnUpdate, btnDelete;
    Context context;
    public static String b = "http://192.168.10.24:8282/testAndroid/AddData.php";
    public static String c = "http://192.168.10.24:8282/testAndroid/UpdateData.php";
    public static String d = "http://192.168.10.24:8282/testAndroid/DeleteData.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        txtFromDatabase = view.findViewById(R.id.txtFromDataBase);
        edtdatabase = view.findViewById(R.id.edtDatabase);
        edtDatabaseID = view.findViewById(R.id.edtDatabaseID);

        view.findViewById(R.id.btnAddToDataBase).setOnClickListener(this);
        view.findViewById(R.id.btnUpdateToDataBase).setOnClickListener(this);
        view.findViewById(R.id.btnDeleteToDataBase).setOnClickListener(this);

        return view;
    }

    @Override
    public void onDataReceived(String myString) {
        Toast.makeText(context, myString, Toast.LENGTH_SHORT).show();
        Log.d("myTestTExt", myString);

    }

    @Override
    public void notificationCancelBoolen(boolean car) {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        switch (i) {
            case R.id.btnAddToDataBase: {
                adddata();
            }
            case R.id.btnUpdateToDataBase: {

            }
            case R.id.btnDeleteToDataBase: {

            }

        }

    }

    public void adddata() {

//        final String a = edtdatabase.getText().toString();
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
//                        Log.d("error", "Error: " + error.getMessage());
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("name", a);
//
//                return params;
//            }
//
//
//        };
//
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
//        requestQueue.add(postRequest);
//
//
//    }
//
//    public void update() {
//        final String id = edtDatabaseID.getText().toString();
//        final String stringToUpdate = edtdatabase.getText().toString();
//
//        StringRequest request = new StringRequest(Request.Method.POST, c, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("id", id);
//                params.put("name", stringToUpdate);
//
//                return params;
//            }
//
//
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
//        requestQueue.add(request);
//    }
//
//
//    public void deleteData() {
//
//        final String idToDelete = edtDatabaseID.getText().toString();
//        StringRequest requestDelete = new StringRequest(Request.Method.POST, d, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("idToDelete", idToDelete);
//                return params;
//            }
//
//        };
//        RequestQueue newrequest = Volley.newRequestQueue(this.getActivity());
//        newrequest.add(requestDelete);
//    }
    }}

