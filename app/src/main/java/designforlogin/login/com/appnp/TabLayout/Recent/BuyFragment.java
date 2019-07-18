package designforlogin.login.com.appnp.TabLayout.Recent;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import designforlogin.login.com.appnp.TabLayout.Recent.DealSub.FrBuyTopBuy;
import designforlogin.login.com.appnp.R;


public class BuyFragment extends Fragment {

FragmentManager fragmentManager;
Fragment fragment;
FragmentTransaction fragmentTransaction;

BottomNavigationView bmBuy;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_buy, container, false);
        fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.tab_recent_Fragment);
        FrBuyTopBuy frTopBuy = new FrBuyTopBuy();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction = fragmentManager.beginTransaction();


        if (fragment == null ) {
            fragmentTransaction.add(R.id.tab_recent_Fragment, frTopBuy);

            Log.i("fragment", "onNavigationItemSelected: "+ "fragment add");
        } else {
            fragmentTransaction.replace(R.id.tab_recent_Fragment, frTopBuy);

            Log.i("fragment", "onNavigationItemSelected: "+ "fragment replace");
        }
        fragmentTransaction.commit();
        Log.d("v", "top sell");


        return v;
    }
//BottomNavigationView.OnNavigationItemSelectedListener bmBurSelection =new BottomNavigationView.OnNavigationItemSelectedListener() {
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        switch (menuItem.getItemId()){
//            case R.id.action_TopBuy:
//                Toast.makeText(BuyFragment.this.getActivity(),"topbuy",Toast.LENGTH_LONG).show();
//                return true;
//            case R.id.action_TopCharts:
//                Toast.makeText(BuyFragment.this.getActivity(),"top chart",Toast.LENGTH_LONG).show();
//                return true;
//            case R.id.action_Category_Buy:
//                Toast.makeText(BuyFragment.this.getActivity(),"buy category",Toast.LENGTH_LONG).show();
//                return true;
//        }
//        return false;
//    }
//};

}
