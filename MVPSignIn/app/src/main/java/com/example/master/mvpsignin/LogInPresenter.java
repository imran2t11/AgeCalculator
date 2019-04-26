package com.example.master.mvpsignin;

import com.google.firebase.auth.FirebaseAuth;

public interface LogInPresenter {

    void signIn();
    void signOut();
    FirebaseAuth.AuthStateListener getAuthListener();
}
