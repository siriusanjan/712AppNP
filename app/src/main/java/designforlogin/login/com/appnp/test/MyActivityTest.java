package designforlogin.login.com.appnp.test;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import designforlogin.login.com.appnp.R;
public class MyActivityTest extends AppCompatActivity implements TestInterfaceTest{

    FragmentManager fragmentManager;
    TestInterfaceTest testInterface;
    FrameLayout frameLayoutMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        frameLayoutMain = findViewById(R.id.frame_main);

        Log.d("lifecycle","onCreate invoked");
        FragmentA fragmentB = new FragmentA();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame_main, fragmentB, "FragmentB").commit();

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle","onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle","onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","onDestroy invoked");
    }


    @Override
    public void onDataReceived(final String myString) {

        FragmentB fragmentB = new FragmentB();
        testInterface = ((TestInterfaceTest) fragmentB);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_main, fragmentB, "FragmentB").commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                testInterface.onDataReceived(myString);


            }
        }, 10000);

    }

    @Override
    public void notificationCancelBoolen(boolean car) {

    }


}
