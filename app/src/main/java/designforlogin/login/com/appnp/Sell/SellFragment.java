package designforlogin.login.com.appnp.Sell;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

import designforlogin.login.com.appnp.Sell.SellSub.BestDeal.FrSellBestDeals;
import designforlogin.login.com.appnp.Sell.SellSub.Category.FrSellCategory;
import designforlogin.login.com.appnp.Sell.SellSub.TopSell.FrSellTopSell;
import designforlogin.login.com.appnp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends Fragment {
    BottomNavigationView bmSell;
    android.support.v4.app.FragmentManager fragmentManager;

    FragmentTransaction fragmentTransaction;

    Fragment fragment;

    public SellFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sell, container, false);
        bmSell = v.findViewById(R.id.bm_menu_sell);
       bmSell.setSelectedItemId(R.id.menu_itm_topSell);
        bmSell.setOnNavigationItemSelectedListener(navSelectedItem);

        fragmentManager = getActivity().getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.sell_sub_fr);
        FrSellTopSell frTopSell = new FrSellTopSell();
        fragmentTransaction = fragmentManager.beginTransaction();






        return v;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navSelectedItem = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            fragment = fragmentManager.findFragmentById(R.id.sell_sub_fr);
                fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {

                case R.id.menu_itm_topSell:
                  Fragment fragmentss = fragmentManager.findFragmentById(R.id.sell_sub_fr);
                    FrSellTopSell frTopSell = new FrSellTopSell();

                    if (fragmentss == null ) {
                        fragmentTransaction.add(R.id.sell_sub_fr, frTopSell);

                        Log.i("fragment", "onNavigationItemSelected: "+ "fragment add");
                    } else {
                        fragmentTransaction.replace(R.id.sell_sub_fr, frTopSell);

                        Log.i("fragment", "onNavigationItemSelected: "+ "fragment replace");
                    }
                    fragmentTransaction.commit();
                    Log.d("v", "top sell");
                    return true;

                case R.id.menu_itm_sell_best_deals:
                    fragmentManager = getActivity().getSupportFragmentManager();
                    FrSellBestDeals frBestDeals = new FrSellBestDeals();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragmentoo = fragmentManager.findFragmentById(R.id.sell_sub_fr);


                    if (fragmentoo == null) {
                        fragmentTransaction.add(R.id.sell_sub_fr, frBestDeals);
                    } else {

                        fragmentTransaction.replace(R.id.sell_sub_fr, frBestDeals);
                    }
                    fragmentTransaction.commit();
                    Log.d("b", "bestDeals");
                    return true;
                case R.id.menu_itm_sell_category:
                  Fragment  fragmentaaa = fragmentManager.findFragmentById(R.id.sell_sub_fr);
                    FrSellCategory frSellCategory = new FrSellCategory();
                    fragmentTransaction = fragmentManager.beginTransaction();

                    if (fragmentaaa == null) {
                        fragmentTransaction.add(R.id.sell_sub_fr, frSellCategory);
                    } else {
                        fragmentTransaction.replace(R.id.sell_sub_fr, frSellCategory);
                    }
                    fragmentTransaction.commit();

                    Log.d("a", "sssCategory");
                    return true;

            }

            return false;
        }
    };

    @Override
    public void onResume() {
        super.onResume();







       bmSell.setSelectedItemId(R.id.menu_itm_topSell);

    }
}
