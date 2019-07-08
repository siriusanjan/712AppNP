package designforlogin.login.com.appnp.Deal;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import designforlogin.login.com.appnp.R;


public class BuyFragment extends Fragment {



BottomNavigationView bmBuy;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_buy, container, false);
        bmBuy = v.findViewById(R.id.bm_menu_buy);
        bmBuy.setOnNavigationItemSelectedListener(bmBurSelection);
        return v;
    }
BottomNavigationView.OnNavigationItemSelectedListener bmBurSelection =new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_TopBuy:
                Toast.makeText(BuyFragment.this.getActivity(),"topbuy",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_TopCharts:
                Toast.makeText(BuyFragment.this.getActivity(),"top chart",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_Category_Buy:
                Toast.makeText(BuyFragment.this.getActivity(),"buy category",Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }
};

}
