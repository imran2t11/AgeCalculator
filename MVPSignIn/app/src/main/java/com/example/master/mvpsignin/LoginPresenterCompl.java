package com.example.master.mvpsignin;

import android.net.Uri;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.internal.firebase_auth.zzap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;

import java.util.List;

public class LoginPresenterCompl implements LogInPresenter {
    @Override
    public void signIn() {

    }

    @Override
    public void signOut() {

    }

    @Override
    public FirebaseAuth.AuthStateListener getAuthListener() {
        return null;
    }
    public void initUser(){
       FirebaseUser user=new FirebaseUser() {
           @NonNull
           @Override
           public String getUid() {
               return null;
           }

           @NonNull
           @Override
           public String getProviderId() {
               return null;
           }

           @Override
           public boolean isAnonymous() {
               return false;
           }

           @Nullable
           @Override
           public List<String> getProviders() {
               return null;
           }

           @NonNull
           @Override
           public List<? extends UserInfo> getProviderData() {
               return null;
           }

           @NonNull
           @Override
           public FirebaseUser zza(@NonNull List<? extends UserInfo> list) {
               return null;
           }

           @Override
           public FirebaseUser zzp() {
               return null;
           }

           @NonNull
           @Override
           public FirebaseApp zzq() {
               return null;
           }

           @Nullable
           @Override
           public String getDisplayName() {
               return null;
           }

           @Nullable
           @Override
           public Uri getPhotoUrl() {
               return null;
           }

           @Nullable
           @Override
           public String getEmail() {
               return null;
           }

           @Nullable
           @Override
           public String getPhoneNumber() {
               return null;
           }

           @NonNull
           @Override
           public zzap zzr() {
               return null;
           }

           @Override
           public void zza(@NonNull zzap zzap) {

           }

           @NonNull
           @Override
           public String zzs() {
               return null;
           }

           @NonNull
           @Override
           public String zzt() {
               return null;
           }

           @Nullable
           @Override
           public FirebaseUserMetadata getMetadata() {
               return null;
           }

           @Override
           public void writeToParcel(Parcel dest, int flags) {

           }

           @Override
           public boolean isEmailVerified() {
               return false;
           }
       };
    }

}
