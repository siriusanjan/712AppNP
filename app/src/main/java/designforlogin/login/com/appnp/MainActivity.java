package designforlogin.login.com.appnp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
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
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import designforlogin.login.com.appnp.Adapters.AdapterSellTopSell;
import designforlogin.login.com.appnp.Adapters.AdapterTopSellSubAdapter;
import designforlogin.login.com.appnp.LoginSignUp.SignUpActivity;
import designforlogin.login.com.appnp.Profile.UserProfile;
import designforlogin.login.com.appnp.SignIn.RegisterSingInFragmant;
import designforlogin.login.com.appnp.TabLayout.AllCategory.FrSellCategory;
import designforlogin.login.com.appnp.TabLayout.Home.TopSell.SeeMoreFragment;
import designforlogin.login.com.appnp.test.TestInterfaceTest;

public class MainActivity extends AppCompatActivity implements TestInterface {
    DrawerLayout drawable_Layout;
    ActionBarDrawerToggle toogle;
    ViewPager viewPager;
    android.support.v7.widget.Toolbar toolbar_main;
    TestInterfaceTest testInterface;
    BroadCastReceiver broadCastReceiver;
    FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    ImageView imageProfile;
    NavigationView nav_View;
    FrameLayout fr_addDetail;
    Context context;
    EditText viewSearch;
    Fragment userFragement;
    CoordinatorLayout navChild;
    TabLayout tabLayout;
    public static boolean isRunning = false;
    public static boolean signIn = false;


    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);


//        onNewIntent(getIntent());

        viewPager = findViewById(R.id.viewPagerMain);
        tabLayout = findViewById(R.id.tablayout);
        viewSearch = findViewById(R.id.viewSearch);
        navChild = findViewById(R.id.navChild);

        userFragement = fragmentManager.findFragmentById(R.id.fr_addMore);
        Toast.makeText(this, "add", Toast.LENGTH_LONG).show();
        fragmentTransaction = fragmentManager.beginTransaction();
        RegisterSingInFragmant registerSingInFragmant = new RegisterSingInFragmant();
        context = getApplicationContext();
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
        nav_View = findViewById(R.id.nav_View);

        navigationItemClick();
        permission();
        changeTabColor();


        // set circle bitmap

        // TODO Auto-generated method stub
//        View header = nav_View.getHeaderView(0);
//        ImageView imageProfile = header.findViewById(R.id.imageProfile);
//        imageProfile.setImageDrawable(getDrawable(R.drawable.pp));
        fbProfileInfo();
//        imageProfile.setImageBitmap(makeBitmap());
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

    private void changeTabColor() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                    R.color.colorAccent));
                            toolbar_main.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            tabLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            navChild.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                        }
                        break;
                    case 1:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                    R.color.colorAccent));
                            toolbar_main.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            tabLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            navChild.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                        }
                        break;
                    case 2:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                    R.color.darkred));
                            toolbar_main.setBackgroundColor(getResources().getColor(R.color.darkred));
                            tabLayout.setBackgroundColor(getResources().getColor(R.color.darkred));
                            navChild.setBackgroundColor(getResources().getColor(R.color.darkred));
                            break;
                        }

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

//
//    private Bitmap makeBitmap(String url) {
//
////        Glide.with(context)
////                .load(StaticVariable.GetAddedDataImage + imgName)
////                .placeholder(new ColorDrawable(Color.BLACK))
////                .override(150, 150)
////                .into(((AdapterTopSellSubAdapter.AdsDetails) viewHolder).adsAddedImage);
//
//
//
//
//        Bitmap bm = BitmapFactory.decodeResource(getResources(),
//                R.drawable.profilepp);
//
//
//    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (toogle.onOptionsItemSelected(item)) {

            return false;
        } else {
            switch (item.getItemId()) {
                case R.id.action_notification:
                    Toast.makeText(MainActivity.this, "notification", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_message:
                    Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();

            }

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdapterSellTopSell a = new AdapterSellTopSell();
        int j;
        j = Integer.parseInt(String.valueOf(a.viewPosition()));

        if (j >= 0) {
            Log.i("position", "onResume: " + j);
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
            Log.i("fffdfdddf", "onReceive: " + intent.getExtras().getString("get"));
        }
    };

    private Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(100, 100, 90, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return output;
    }

    public void navigationItemClick() {
        nav_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            MenuItem activeMenuItem;


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.navigation_home:
                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();

                        if (activeMenuItem != null)
                            activeMenuItem.setChecked(false);
                        activeMenuItem = menuItem;
                        menuItem.setChecked(true);


                        return true;
                    case R.id.navigation_menuMyAds:

                        if (activeMenuItem != null) {
                            activeMenuItem.setChecked(false);
                            activeMenuItem = menuItem;
                            menuItem.setChecked(true);
                        } else {
                            activeMenuItem = menuItem;
                            menuItem.setChecked(true);
                        }
                        fragmentManager = getSupportFragmentManager();
                        Toast.makeText(MainActivity.this, "My Ads", Toast.LENGTH_SHORT).show();
                        UserProfile sellDetail = new UserProfile();

                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.addToBackStack("myTag");

                        if (userFragement != null) {
                            fragmentTransaction.replace(R.id.fr_addMore, sellDetail);
                            fragmentTransaction.commit();
                        } else {
                            fragmentTransaction.add(R.id.fr_addMore, sellDetail);
                            fragmentTransaction.commit();


                        }
                        drawable_Layout.closeDrawers();
                        return true;
                    case R.id.navigation_Watching:
                        if (activeMenuItem != null) {
                            activeMenuItem.setChecked(false);
                            activeMenuItem = menuItem;
                            menuItem.setChecked(true);
                        } else {
                            activeMenuItem = menuItem;
                            menuItem.setChecked(true);
                        }

                        activeMenuItem = menuItem;
                        menuItem.setChecked(true);
                        Toast.makeText(MainActivity.this, "watching", Toast.LENGTH_SHORT).show();
                        SeeMoreFragment seemore = new SeeMoreFragment();


                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.addToBackStack("myTag");
                        if (userFragement != null) {
                            fragmentTransaction.replace(R.id.fr_addMore, seemore);
                            fragmentTransaction.commit();
                        } else {
                            fragmentTransaction.add(R.id.fr_addMore, seemore);
                            fragmentTransaction.addToBackStack("drawable_Layout");
                            fragmentTransaction.commit();
                            activeMenuItem = menuItem;
                        }


                        drawable_Layout.closeDrawers();


                        return true;
                    case R.id.navigation_Trending:
                        if (activeMenuItem != null) {
                            activeMenuItem.setChecked(false);
                            activeMenuItem = menuItem;
                            menuItem.setChecked(true);
                        } else {
                            activeMenuItem = menuItem;
                            menuItem.setChecked(true);
                        }
                        Toast.makeText(MainActivity.this, "Trending", Toast.LENGTH_SHORT).show();
                        SeeMoreFragment viewTrending = new SeeMoreFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("viewName", "Trending");
                        viewTrending.setArguments(bundle);
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        Fragment seemoreFr = fragmentManager.findFragmentById(R.id.fr_addMore);
                        fragmentTransaction.addToBackStack("your");
                        if (seemoreFr != null) {
                            fragmentTransaction.replace(R.id.fr_addMore, viewTrending);
                            fragmentTransaction.commit();
                        } else {
                            fragmentTransaction.add(R.id.fr_addMore, viewTrending);
                            fragmentTransaction.commit();
                        }

                        drawable_Layout.closeDrawers();
                        return true;
                    case R.id.navigation_AllCategory:
                        if (activeMenuItem != null) {
                            activeMenuItem.setChecked(false);
                            activeMenuItem = menuItem;
                            menuItem.setChecked(true);
                        } else {
                            activeMenuItem = menuItem;
                            menuItem.setChecked(true);
                        }
                        Toast.makeText(MainActivity.this, "All Category", Toast.LENGTH_SHORT).show();
                        FrSellCategory categoryfragment = new FrSellCategory();

                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.addToBackStack("myTag");
                        if (userFragement != null) {
                            fragmentTransaction.replace(R.id.fr_addMore, categoryfragment);
                            fragmentTransaction.commit();
                        } else {
                            fragmentTransaction.add(R.id.fr_addMore, categoryfragment);
                            fragmentTransaction.addToBackStack("drawable_Layout");
                            fragmentTransaction.commit();
                            activeMenuItem = menuItem;
                        }
                        drawable_Layout.closeDrawers();
                        return true;
                    case R.id.navigation_Setting:
                        Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
                        if (activeMenuItem != null)
                            activeMenuItem.setChecked(false);
                        activeMenuItem = menuItem;
                        menuItem.setChecked(true);
                        return true;
                    case R.id.navigation_Help:
                        Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
                        if (activeMenuItem != null) {
                            activeMenuItem.setChecked(false);
                            activeMenuItem = menuItem;
                            menuItem.setChecked(true);
                        }
                        return true;
                    case R.id.signOut:
                        Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
                        if (activeMenuItem != null) {
                            activeMenuItem.setChecked(false);
                            activeMenuItem = menuItem;
                            menuItem.setChecked(true);
                        }

                        FirebaseAuth.getInstance().signOut();
                        LoginManager.getInstance().logOut();
                        finish();
                        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                        startActivity(intent);

                        return true;

                }

                return false;

            }
        });
    }

    private void permission() {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 77
                );

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 77
                );

//            ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},);
            }

        } else {

        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0)
            fragmentManager.popBackStackImmediate();
        else super.onBackPressed();
    }

    public void fbProfileInfo() {
        String facebookUserId = "";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        View header = nav_View.getHeaderView(0);

        TextView username = header.findViewById(R.id.userName);
        final ImageView profilePicture = header.findViewById(R.id.imageProfile);


// find the Facebook profile and get the user's id
        for (UserInfo profile : user.getProviderData()) {
            // check if the provider id matches "facebook.com"
            if (FacebookAuthProvider.PROVIDER_ID.equals(profile.getProviderId())) {

                facebookUserId = profile.getUid();
                username.setText(profile.getDisplayName());

                Log.d("hhhhhhh", "fbProfileInfo: " + profile.getEmail());
                final String photoUrl = "https://graph.facebook.com/" + facebookUserId + "/picture?height=500";
                Glide.with(this)
                        .asBitmap()
                        .load(photoUrl)
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                Bitmap resizedBitmap = Bitmap.createScaledBitmap(resource, 200, 200, false);
                                Bitmap conv_bm = getCircleBitmap(resizedBitmap, 100);
                                profilePicture.setImageBitmap(conv_bm);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                            }
                        });
                String name = user.getDisplayName();
                username.setText(profile.getDisplayName());





            }
        }

        String email = user.getEmail();
        String name = user.getDisplayName();
        username.setText(name);
        Uri url = user.getPhotoUrl();
        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Bitmap resizedBitmap = Bitmap.createScaledBitmap(resource, 200, 200, false);
                        Bitmap conv_bm = getCircleBitmap(resizedBitmap, 100);
                        profilePicture.setImageBitmap(conv_bm);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });

// construct the URL to the profile picture, with a custom height
// alternatively, use '?type=small|medium|large' instead of ?height=


    }

}


