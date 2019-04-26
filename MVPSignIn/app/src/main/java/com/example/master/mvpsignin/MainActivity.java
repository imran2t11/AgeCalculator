package com.example.master.mvpsignin;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity implements LoginView{

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    ImageView imageIV;
    Button signInbtn, signOutbtn;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth mAuth;
    LoginPresenterCompl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        checkNetworkConnection();
        gsoinit();
        mAuth=FirebaseAuth.getInstance();


        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });

        signOutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutUser();
            }
        });

    }

    private void gsoinit() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);



    }

    private void initViews() {

        imageIV=findViewById(R.id.imageIV) ;
        signInbtn=findViewById(R.id.signInbtn);
        signOutbtn=findViewById(R.id.signOutbtn);

    }

    public boolean checkNetworkConnection() {


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isConnected = false;
        if (networkInfo != null && (isConnected = networkInfo.isConnected())) {
            // show "Connected" & type of network "WIFI or MOBILE"


            Toast.makeText(this, "Network Connected" + networkInfo.getTypeName(), Toast.LENGTH_SHORT).show();


        } else {



            Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();

        }

        return isConnected;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            imageIV.setImageResource(R.drawable.unnamed);
                            Toast.makeText(MainActivity.this, "Failed!Please try again", Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }


                    }
                });
    }




    @Override
    public void signInUser() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void signOutUser() {

        mAuth.signOut();

        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });

    }




    public void updateUI(FirebaseUser user) {
        if (user != null) {
            // mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
            // mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
            Log.e("daddddaaaa", "Email ID: "+user.getEmail());
            Log.e("tatatattata", "Display Name: "+user.getDisplayName());


            findViewById(R.id.signInbtn).setVisibility(View.GONE);
            findViewById(R.id.signOutbtn).setVisibility(View.VISIBLE);
            Picasso.with(this)
                    .load("https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX11070424.jpg")
                    .resize(200, 200)
                    .centerCrop()
                    .into(imageIV);

        } else {

            Picasso.with(this)
                    .load("http://chadjmiller.com/wp-content/uploads/2017/11/Sign-Out.jpg")
                    .resize(200, 200)
                    .centerCrop()
                    .into(imageIV);


            findViewById(R.id.signInbtn).setVisibility(View.VISIBLE);
            findViewById(R.id.signOutbtn).setVisibility(View.GONE);
        }
    }
    }



