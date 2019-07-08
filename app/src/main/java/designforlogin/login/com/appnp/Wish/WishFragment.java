package designforlogin.login.com.appnp.Wish;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class WishFragment extends Fragment {

    BottomNavigationView bmWish;

    public WishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wish, container, false);
        bmWish = v.findViewById(R.id.bm_menu_wish);
        bmWish.setOnNavigationItemSelectedListener(bmnavWish);

        return v;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bmnavWish = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_myWish:
                    Toast.makeText(WishFragment.this.getActivity(), "My wish", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.menu_trending_wish:
                    Toast.makeText(WishFragment.this.getActivity(), "My wish", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.wish_menu_Category:
                    Toast.makeText(WishFragment.this.getActivity(), "My wish", Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        }
    };

}
