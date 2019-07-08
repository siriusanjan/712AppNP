package designforlogin.login.com.appnp;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import designforlogin.login.com.appnp.Adapters.AdapterSellTopSell;
import designforlogin.login.com.appnp.Profile.UserProfile;
import designforlogin.login.com.appnp.SignIn.RegisterSingInFragmant;
import designforlogin.login.com.appnp.test.TestInterfaceTest;

public class MainActivity extends AppCompatActivity implements TestInterface {
    DrawerLayout drawable_Layout;
    ActionBarDrawerToggle toogle;
    ViewPager viewPager;
    android.support.v7.widget.Toolbar toolbar_main;
    SearchView mySearch;
    TestInterfaceTest testInterface;
    BroadCastReceiver broadCastReceiver;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public static boolean isRunning = false;
    public static boolean signIn = false;


    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);


//        onNewIntent(getIntent());

        viewPager = findViewById(R.id.viewPagerMain);
        final TabLayout tabLayout = findViewById(R.id.tablayout);
        mySearch = findViewById(R.id.mySearch);

        Toast.makeText(this, "add", Toast.LENGTH_LONG).show();


        fragmentManager = this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        RegisterSingInFragmant registerSingInFragmant = new RegisterSingInFragmant();
        if (!signIn) {
//            fragmentTransaction.add(R.id.fr_addMore, registerSingInFragmant);
            ///todo fragment for the sign in purpose
            fragmentTransaction.commit();

        } else {
//            getSupportFragmentManager().beginTransaction().
//                    remove(getSupportFragmentManager().findFragmentById(R.id.fr_addMore)).commit();

        }

        toolbar_main = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //layout to get the menu item and acts as parent
        drawable_Layout = findViewById(R.id.drawable_Layout);
        //toogle acts as the mediator  between the navigation view and activity
        toogle = new ActionBarDrawerToggle(this, drawable_Layout, R.string.open, R.string.close);
        drawable_Layout.addDrawerListener(toogle);

        //syn the current state of toogle wheather there is nav view or activity
        toogle.syncState();
        NavigationView nav_View = findViewById(R.id.nav_View);


        PagerAdapter pageAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                    R.color.colorAccent));
        }
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab
                                                tab) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (toogle.onOptionsItemSelected(item)) {

            return false;
        } else {
            switch (item.getItemId()) {
                case R.id.action_add:
                    UserProfile userProfile = new UserProfile();
                    fragmentManager = this.getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.drawable_Layout, userProfile);
                    fragmentTransaction.addToBackStack("drawable_Layout");
                    fragmentTransaction.commit();

//                    FragmentManager fragmentManager;
//                    FragmentTransaction fragmentTransaction;
//
//                    fragmentManager = this.getSupportFragmentManager();
//                    Toast.makeText(this, "add", Toast.LENGTH_LONG).show();
//                    Fragment fragment = fragmentManager.findFragmentById(R.id.fr_addMore);
//                    AddData frTopSell = new AddData();
//                    fragmentTransaction = fragmentManager.beginTransaction();
//                    if (fragment == null) {
//
//                        fragmentTransaction.add(R.id.fr_addMore, frTopSell);
//                    } else {
//                        fragmentTransaction.replace(R.id.fr_addMore, frTopSell);
//
//                    }
//                    fragmentTransaction.commit();

                    return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);

//        MenuItem mSearch = menu.findItem(R.id.action_search);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdapterSellTopSell a = new AdapterSellTopSell();
        int j;
        j = Integer.parseInt(String.valueOf(a.viewPosition()));

        if (j >= 0) {
            Log.i("position", "onResume: "+j);
        }
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(receiver, new IntentFilter("my.action.string"));
        isRunning = true;


    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }

    @Override
    public void myViewHolderPosition(int i) {
        Log.d("fragmentID", String.valueOf(i));

    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//
//        Bundle b = intent.getExtras();
//        if (b != null) {
//            Log.d("my message", b.getString("getBody"));
//        }
//    }

    public BroadCastReceiver receiver = new BroadCastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            Log.i("fffdfdddf", "onReceive: "+intent.getExtras().getString("get"));
        }
    };
}


