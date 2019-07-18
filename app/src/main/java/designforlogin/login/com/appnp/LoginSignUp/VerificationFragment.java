package designforlogin.login.com.appnp.LoginSignUp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import designforlogin.login.com.appnp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerificationFragment extends Fragment {


    public VerificationFragment() {
        // Required empty public constructor
    }

    EditText txtVerifyNumber, txtYourCode;
    Button btnVerifyNumber;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_verification, container, false);
        txtVerifyNumber=v.findViewById(R.id.txtVerifyNumber);
        txtYourCode=v.findViewById(R.id.txtYourCode);
        btnVerifyNumber=v.findViewById(R.id.btnVerifyNumber);

        Bundle b= getArguments();
        txtVerifyNumber.setText(b.getString("number"));

        return v;
    }
    public void getTheSms(){
        SmsRetrieverClient client = SmsRetriever.getClient(this.getActivity() /* context */);

// Starts SmsRetriever, which waits for ONE matching SMS message until timeout
// (5 minutes). The matching SMS message will be sent via a Broadcast Intent with
// action SmsRetriever#SMS_RETRIEVED_ACTION.
        Task<Void> task = client.startSmsRetriever();

// Listen for success/failure of the start Task. If in a background thread, this
// can be made blocking using Tasks.await(task, [timeout]);
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }

}
