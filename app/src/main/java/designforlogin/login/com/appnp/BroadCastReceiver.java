package designforlogin.login.com.appnp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

import static android.widget.Toast.LENGTH_LONG;

public class BroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        String action = intent.getAction();
//        if (action.equals("my.action.string")) {
////            String a = Objects.requireNonNull(intent.getExtras()).getString("get");
////            Log.d("new message", a);
////            Toast.makeText(context, a, LENGTH_LONG).show();
//        }
    }
}
