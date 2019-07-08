package designforlogin.login.com.appnp.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import designforlogin.login.com.appnp.MainActivity;
import designforlogin.login.com.appnp.Profile.AddSellItem.SellItemDetails;
import designforlogin.login.com.appnp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfile extends Fragment implements View.OnClickListener {


    public UserProfile() {
        // Required empty public constructor
    }

    Fragment userFragement;
    TextView userBack, postItems;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        userBack = view.findViewById(R.id.userBack);
        postItems = view.findViewById(R.id.postItems);
//        userFragement = fragmentManager.findFragmentById(R.id.userFragement);


        userBack.setOnClickListener(this);
        postItems.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        SellItemDetails sellDetail = new SellItemDetails();
        switch (view.getId()) {

            case R.id.postItems: {
                fragmentManager = this.getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
//                if( userFragement != null){
//                    fragmentTransaction.replace(R.id.drawable_Layout,sellDetail);
//                    fragmentTransaction.commit();
//            }
//                else{
                fragmentTransaction.add(R.id.drawable_Layout, sellDetail);
                fragmentTransaction.addToBackStack("drawable_Layout");
                fragmentTransaction.commit();
//            }
                return;

            }
            case R.id.userBack: {
                fragmentManager = this.getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.drawable_Layout)));
                fragmentTransaction.commit();
                return;

            }

        }

    }
}
