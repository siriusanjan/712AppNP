package designforlogin.login.com.appnp.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import designforlogin.login.com.appnp.R;

public class FragmentA extends Fragment {

    public  FragmentA(){

    }
    TestInterfaceTest testInterface;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        testInterface=((TestInterfaceTest) context);
        testInterface.onDataReceived("this is the string from another fragment");
    }
}
