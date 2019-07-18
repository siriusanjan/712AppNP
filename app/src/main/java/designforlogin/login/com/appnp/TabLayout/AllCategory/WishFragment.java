package designforlogin.login.com.appnp.TabLayout.AllCategory;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import designforlogin.login.com.appnp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WishFragment extends Fragment {

    BottomNavigationView bmWish;

    public WishFragment() {
        // Required empty public constructor
    }
Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wish, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragment= fragmentManager.findFragmentById(R.id.fr_category);
        fragmentTransaction= fragmentManager.beginTransaction();
        FrSellCategory  categoryfragment= new FrSellCategory();
        Bundle a= new Bundle();
        a.putString("trigerPoint", "tab");
        categoryfragment.setArguments(a);
        fragmentTransaction.add(R.id.fr_category,categoryfragment);
        fragmentTransaction.commit();

        return v;
    }


//    private BottomNavigationView.OnNavigationItemSelectedListener bmnavWish = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.menu_myWish:
//                    Toast.makeText(WishFragment.this.getActivity(), "My wish", Toast.LENGTH_LONG).show();
//                    return true;
//                case R.id.menu_trending_wish:
//                    Toast.makeText(WishFragment.this.getActivity(), "My wish", Toast.LENGTH_LONG).show();
//                    return true;
//                case R.id.wish_menu_Category:
//                    Toast.makeText(WishFragment.this.getActivity(), "My wish", Toast.LENGTH_LONG).show();
//                    return true;
//            }
//            return false;
//        }
//    };

}
