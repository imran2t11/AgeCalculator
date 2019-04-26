package com.example.master.socialsecurityapp.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.master.socialsecurityapp.R;

import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    EditText nameET, emailET, passwordET;
    EditText emergencyNumberET;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button signupbutton, loginbtn;
    private final int REQUEST_CODE = 99;
    String checkSharedPrefisnull;


    String name, email, emergencyPhoneNumeber, password1, password2;
    Context context;
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_sign_up);

           /* Button btPick = (Button) findViewById(R.id.btpick_contact);
            btPick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            });
*/


        nameET = (EditText) findViewById(R.id.nameET);
        emailET = (EditText) findViewById(R.id.emailET);
        //emergencyNumberET = (EditText) findViewById(R.id.emergencyNumberET);


        passwordET = (EditText) findViewById(R.id.passwordET);
        signupbutton = (Button) findViewById(R.id.signupbutton);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void signUPfunction(View view) {


        name = nameET.getText().toString();
        email = emailET.getText().toString();

        //  emergencyPhoneNumeber=emergencyNumberET.getText().toString();
        password1 = passwordET.getText().toString();


        Log.d("password", "signUPfunction: " + password1);
        editor.putString("name", name);
        //editor.putString("emergencyNo",emergencyPhoneNumeber);
        verifyAndCheckPattern();
        editor.putString("emailsaved", email);
        editor.putString("passsaved", password1);



        editor.commit();
       // contactModel=new ContactModel(name,email,emergencyPhoneNumeber,password1 );
        //contactModel=new ContactModel(name,emergencyPhoneNumeber);
        //contactDatabaseSource=new ContactDatabaseSource(this);
     //   status=contactDatabaseSource.addcontact(contactModel);
      //  Toast.makeText(this,String.valueOf(status), Toast.LENGTH_SHORT).show();









    }

    private void verifyAndCheckPattern() {

        if((name==null)||(email==null)||(password1==null))
        {
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);

            Toast.makeText(SignUpActivity.this, "Please input ", Toast.LENGTH_SHORT).show();
        }
        if(email.matches(emailPattern))
        {


           // Toast.makeText(context, "Login in page e gelo", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
           // Toast.makeText(this, String.valueOf(status), Toast.LENGTH_SHORT).show();

        }
        else
        {
           // Log.d("Wrong pattern", "verifyAndCheckPattern: ");
            Toast.makeText(SignUpActivity.this, "Please input your email in correct format ", Toast.LENGTH_SHORT).show();
            Log.e("Are you kidding", "verifyAndCheckPattern: "+emailET.getText().toString());
            Intent in=new Intent(this,SignUpActivity.class);
            startActivity(in);

    // Toast.makeText(SignUpActivity.this, "Recheck Email input", Toast.LENGTH_SHORT).show();

        }
    }












/*
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        switch (reqCode) {
            case (REQUEST_CODE):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                        String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String num = "";
                        if (Integer.valueOf(hasNumber) == 1) {
                            Cursor numbers = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                            while (numbers.moveToNext()) {
                                num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                emergencyNumberET.setText(num);
                                Toast.makeText(SignUpActivity.this, "Number="+num, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    break;
                }
        }
    }
*/

    public void login(View view) {

        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }


}


