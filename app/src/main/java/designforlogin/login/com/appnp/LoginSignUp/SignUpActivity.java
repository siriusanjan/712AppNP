package designforlogin.login.com.appnp.LoginSignUp;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.credentials.IdentityProviders;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import designforlogin.login.com.appnp.MainActivity;
import designforlogin.login.com.appnp.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar mToolBar;
    EditText txtUserName, txtUserNumber;
    TextInputLayout txtInputUserNameLayour, inputLayoutUserNumber;
    Button btnSignIn, btnResend, btnVerify;

    private static final int RESOLVE_HINT = 3333;
    private static final int SMS_CONSENT_REQUEST = 1111;
    String mVerificationId;
    // Set to an unused request code
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    GoogleApiClient mGoogleApiClient;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9901;

    FirebaseAuth mAuth;
    private boolean mVerificationInProgress = false;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    LoginButton facebook_login_button;
    private CallbackManager mCallBAckManager;


    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            finish();
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            txtUserName = findViewById(R.id.txtUserName);
            txtUserNumber = findViewById(R.id.txtUserNumber);
            btnSignIn = findViewById(R.id.btnSignIn);

            txtInputUserNameLayour = findViewById(R.id.txtInputUserNameLayour);
            inputLayoutUserNumber = findViewById(R.id.inputLayoutUserNumbr);
            btnResend = findViewById(R.id.btnResend);
            btnVerify = findViewById(R.id.btnVerify);
            findViewById(R.id.googleSignInButton).setOnClickListener(this);
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


            ///////FACEBOOK
            mCallBAckManager = CallbackManager.Factory.create();
            facebook_login_button = findViewById(R.id.facebook_login_button);
            facebook_login_button.setPermissions("email", "public_profile");
            facebook_login_button.registerCallback(mCallBAckManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.d("lll", "onSuccess: ");
                    handleFacebookAccessToken(loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {
                    Log.d("qqq", "facebook:onCancel");
                }

                @Override
                public void onError(FacebookException error) {
                    Log.d("qqqqq", "facebook:onError", error);
                }
            });


            btnVerify.setVisibility(View.GONE);
            txtUserName.setVisibility(View.GONE);

            mToolBar = findViewById(R.id.toolbar_main);
            setSupportActionBar(mToolBar);

            mAuth = FirebaseAuth.getInstance();

            txtUserName.addTextChangedListener(new MySignUpTextWAtcher(txtUserName));
            txtUserNumber.addTextChangedListener(new MySignUpTextWAtcher(txtUserNumber));


//        permission();

            onClickBtn();
            resendSms();


//        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
//        registerReceiver(smsVerificationReceiver, intentFilter);
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


                @Override
                public void onVerificationCompleted(PhoneAuthCredential credential) {
                    Log.d("llllll", "onVerificationCompleted:" + credential);

                    signInWithPhoneAuthCredential(credential);

                    if (credential != null) {
                        if (credential.getSmsCode() != null) {
                            txtUserName.setText(credential.getSmsCode());
                        } else {
                            txtUserName.setText(R.string.instant_validation);
                        }
                    }
                    mVerificationInProgress = false;

                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Log.i("kkkk", "onVerificationFailed", e);
                    // [START_EXCLUDE silent]
//                mVerificationInProgress = false;
                    // [END_EXCLUDE]

                    if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        // Invalid request
                        // [START_EXCLUDE]
//                    mPhoneNumberField.setError("Invalid phone number.");
                        Log.i("aaaaa", "onVerificationFailed: Invalid phone number.");

                        // [END_EXCLUDE]
                    } else if (e instanceof FirebaseTooManyRequestsException) {
                        // The SMS quota for the project has been exceeded
                        // [START_EXCLUDE]

                        Log.i("dddddd", "onVerificationFailed: ");
                        // [END_EXCLUDE]
                    }

                    // Show a message and update the UI
                    // [START_EXCLUDE]
//                updateUI(STATE_VERIFY_FA
                }

                @Override
                public void onCodeSent(String verificationId,
                                       PhoneAuthProvider.ForceResendingToken token) {
                    super.onCodeSent(verificationId, token);
                    // The SMS verification code has been sent to the provided phone number, we
                    // now need to ask the user to enter the code and then construct a credential
                    // by combining the code with a verification ID.
                    Log.d("jjjjjj", "onCodeSent:" + verificationId);

                    // Save verification ID and resending token so we can use them later
                    mVerificationId = verificationId;
                    mResendToken = token;
                    btnSignIn.setVisibility(View.GONE);
                    btnVerify.setVisibility(View.VISIBLE);
                    txtUserName.setVisibility(View.VISIBLE);
                    onVerify();

                    // [START_EXCLUDE]
                    // Update UI
//                updateUI(STATE_CODE_SENT);
                    // [END_EXCLUDE]
                }
            };
        }

    }

    public void onVerify() {
        btnVerify.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             String code = txtUserNumber.getText().toString();
                                             if (TextUtils.isEmpty(code)) {
                                                 txtUserNumber.setError("Cannot be empty.");
                                                 return;
                                             }

                                             verifyPhoneNumberWithCode(mVerificationId, code);
                                         }
                                     }
        );
    }

    public void onClickBtn() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startPhoneNumberVerification(txtUserNumber.getText().toString());

            }
        });

    }

    public void resendSms() {
        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resendVerificationCode(txtUserNumber.getText().toString(), mResendToken);
            }
        });
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.googleSignInButton:
                signIn();
                break;
        }

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private class MySignUpTextWAtcher implements TextWatcher {
        private View view;

        private MySignUpTextWAtcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.txtUserName:
                    validateUsername();
                    break;
                case R.id.txtUserNumber:
                    validateUserNumber();
                    break;
            }
        }

        private boolean validateUsername() {
            if (txtUserName.getText().toString().trim().isEmpty()) {
                txtInputUserNameLayour.setError(getString(R.string.err_model_number));
                requestFocus(txtUserName);
                return false;
            } else {
                txtInputUserNameLayour.setErrorEnabled(false);
            }

            return true;
        }
    }

    private boolean validateUserNumber() {
        if (txtUserNumber.getText().toString().trim().isEmpty()) {
            inputLayoutUserNumber.setError(getString(R.string.err_price));
            requestFocus(txtUserNumber);
            return false;
        } else {
            inputLayoutUserNumber.setErrorEnabled(false);
        }

        return true;
    }


    private void permission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 77
                );

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 77
                );

//            ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},);
            }

        } else {

        }

    }


    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                20,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

        mVerificationInProgress = true;
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("ppp", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);

                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("pppp", "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "signInWithCredential:failure", Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    // [START resend_verification]
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                20,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
        mVerificationInProgress = true;

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.


        // [START_EXCLUDE]
        if (mVerificationInProgress && validatePhoneNumber()) {
            startPhoneNumberVerification(txtUserNumber.getText().toString());
        }
        // [END_EXCLUDE]
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
    }

    private boolean validatePhoneNumber() {
        String phoneNumber = txtUserNumber.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            txtUserNumber.setError("Invalid phone number.");
            return false;
        }

        return true;
    }

    /*FACEBOOK LOGIN ANDROID*/
    public void handleFacebookAccessToken(AccessToken token) {
        Log.d("ssss", "handleFacebookAccessToken: " + token);
//    showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("oooooo", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            finish();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
//                        updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("tttttt", "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                        updateUI(null);
                        }

                        // [START_EXCLUDE]
//                    hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallBAckManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.w("eee", "Google sign in successful");
                fireBaseGoogleAuth(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("eee", "Google sign in failed", e);
                // [START_EXCLUDE]
//                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }

    private void fireBaseGoogleAuth(GoogleSignInAccount acct) {
        Log.d("uuu", "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
//        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("ooo", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            finish();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ooo", "signInWithCredential:failure", task.getException());
//                       Toast.makeText(this,"authentication failed",Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // [START_EXCLUDE]
//                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
}


///https://appnp-e8bb7.firebaseapp.com/__/auth/handler

