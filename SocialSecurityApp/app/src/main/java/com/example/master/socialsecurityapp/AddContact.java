package com.example.master.socialsecurityapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.master.socialsecurityapp.Activity.HomePage;

public class AddContact extends AppCompatActivity {
    EditText contact1ET, contact2ET, contact3ET, contact4ET, contactLawEnforcementET;
    String contact1, contact2, contact3, contact4, contactLawEnforcement;
    Button choosenum1btn, choosenum2btn, choosenum3btn, choosenum4btn;
    private final int REQUEST_CODE = 99, REQUEST_CODE1 = 98, REQUEST_CODE2 = 97, REQUEST_CODE3 = 96;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Toast.makeText(this, "Please make sure every feild is filled up correctly ", Toast.LENGTH_LONG).show();

        Button btPick1 = (Button) findViewById(R.id.choosenum1btn);
        Button btPick2 = (Button) findViewById(R.id.choosenum2btn);
        Button btPick3 = (Button) findViewById(R.id.choosenum3btn);
        Button btPick4 = (Button) findViewById(R.id.choosenum4btn);
        btPick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btPick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE1);
            }
        });
        btPick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE2);
            }
        });
        btPick4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE3);
            }
        });


        contact1ET = (EditText) findViewById(R.id.contact1ET);
        contact2ET = (EditText) findViewById(R.id.contact2ET);
        contact3ET = (EditText) findViewById(R.id.contact3ET);
        contact4ET = (EditText) findViewById(R.id.contact4ET);
        contactLawEnforcementET = (EditText) findViewById(R.id.contactLawEnforcementET);
        choosenum1btn = (Button) findViewById(R.id.choosenum1btn);
        choosenum2btn = (Button) findViewById(R.id.choosenum2btn);
        choosenum3btn = (Button) findViewById(R.id.choosenum3btn);
        choosenum4btn = (Button) findViewById(R.id.choosenum4btn);

        sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }


    public void addcontact(View view) {

        contact1 = contact1ET.getText().toString();
        contact2 = contact2ET.getText().toString();
        contact3 = contact3ET.getText().toString();
        contact4 = contact4ET.getText().toString();
        contactLawEnforcement = contactLawEnforcementET.getText().toString();

        if (contact1.matches("")||(contact2.matches(""))||(contact3.matches(""))||(contact4.matches(""))||(contactLawEnforcement.matches(""))) {
            Toast.makeText(this, "You did not input all the numbers", Toast.LENGTH_SHORT).show();
            return;
        }




            editor.putString("contact1", contact1);
            editor.putString("contact2", contact2);
            editor.putString("contact3", contact3);
            editor.putString("contact4", contact4);
            editor.putString("contactLawEnforcement", contactLawEnforcement);

            editor.commit();



        String cont1 = sharedPreferences.getString("contact1", "Data not found");


        Log.e("Contact List", "contact1: " + cont1);


        Intent i = new Intent(AddContact.this, HomePage.class);
        startActivity(i);


    }


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
                                contact1ET.setText(num);

                                Toast.makeText(AddContact.this, "Number1=" + num, Toast.LENGTH_LONG).show();
                            }
                        }
                    }


                    break;

                }

            case (REQUEST_CODE1):
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

                                contact2ET.setText(num);

                                Toast.makeText(AddContact.this, "Number2=" + num, Toast.LENGTH_LONG).show();
                            }
                        }
                    }


                    break;

                }
            case (REQUEST_CODE2):
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

                                contact3ET.setText(num);

                                Toast.makeText(AddContact.this, "Number3=" + num, Toast.LENGTH_LONG).show();
                            }
                        }
                    }


                    break;

                }
            case (REQUEST_CODE3):
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

                                contact4ET.setText(num);
                                Toast.makeText(AddContact.this, "Number4=" + num, Toast.LENGTH_LONG).show();
                            }
                        }
                    }


                    break;

                }



        }
    }


}


