package designforlogin.login.com.appnp.SignIn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import designforlogin.login.com.appnp.MainActivity;
import designforlogin.login.com.appnp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterSingInFragmant extends Fragment {

    public RegisterSingInFragmant() {
        // Required empty public constructor
    }

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_sing_in_fragmant, container, false);
        Button btnSignIn = view.findViewById(R.id.btnSignIn);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentTransaction.remove(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.fr_addMore)));
                fragmentTransaction.commit();

            }
        });

        return view;
    }



}
