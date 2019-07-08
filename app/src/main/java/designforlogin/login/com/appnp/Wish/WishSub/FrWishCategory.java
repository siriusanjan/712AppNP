package designforlogin.login.com.appnp.Wish.WishSub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import designforlogin.login.com.appnp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrWishCategory extends Fragment {


    public FrWishCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fr_wish_category, container, false);
    }

}
