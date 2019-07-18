package designforlogin.login.com.appnp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import designforlogin.login.com.appnp.TabLayout.Recent.BuyFragment;
import designforlogin.login.com.appnp.TabLayout.Home.TopSell.SellFragment;
import designforlogin.login.com.appnp.TabLayout.AllCategory.WishFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

    PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs=numOfTabs;
    }



    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                return new SellFragment();
            case 1:
                return new BuyFragment();

            case 2:
                return new WishFragment();
            default:
                return null;
        }


    }
    @Override
    public int getCount() {
        return numOfTabs;
    }

}

