package com.example.master.mvpsignin;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface LoginView {


    void signInUser();
    void signOutUser();
    void updateUI(FirebaseUser user);
   void onActivityResult(int requestCode, int resultCode, Intent data);




}
