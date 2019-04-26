package com.example.master.navigationdrawerpro;



import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.master.navigationdrawerpro.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomePage extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    TextView locationTV, cityTV, countryTV, batteryPercentage, addresslineTV;
    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;
    double lat, lon;
    String x;
    String city, mycity, batteryNumber5;
    TextView nameTV, batteryreminder;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String postalCode;
    String subadmin;
    String subadminarea;
    String addresslinefull, full;
    String batteryNumber, batteryNumber3, batteryNumber2, batteryNumber1;
    String low = "Battery Level is low. Need to be charged immediately";
    String keyword1 = "Sadarghat";   //sadarghat
    String keyword2 = "Mariner's";   //kotowali
    String keyword3 = "Satish";     //katowali
    String keyword4 = "Panchlaish";  //
    String keyword5 = "O.R.";       //panchlaish
    String keyword6 = "Fazlul";    //panchlaish
    String keyword7 = "Lal Chand";  //chawkbazar

    String psnumber = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        locationTV = (TextView) findViewById(R.id.locationTV);
        countryTV = (TextView) findViewById(R.id.countryTV);
        cityTV = (TextView) findViewById(R.id.cityTV);
        addresslineTV = (TextView) findViewById(R.id.addresslineTV);
        nameTV = (TextView) findViewById(R.id.nameTV);
        batteryPercentage = (TextView) findViewById(R.id.batteryPercentage);
        batteryreminder = (TextView) findViewById(R.id.batteryreminder);

        batteryLevel();
        simChange();


        //  Toast.makeText(this, "Phone number is "+mPhoneNumber, Toast.LENGTH_SHORT).show();


        sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        Log.d("name", "onCreate: " + sharedPreferences.getString("name", null));

        nameTV.setText(sharedPreferences.getString("name", "Data not found"));


        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


    }

    private void simChange() {


        TelephonyManager tm = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        String simID = tm.getSimSerialNumber();
        String mphone1 = tm.getLine1Number();
        String mphone = tm.getSubscriberId();
        String c = tm.getNetworkOperatorName();
        String all = simID + " " + mphone + " " + " " + mphone1 + " " + " " + c;
        Toast.makeText(this, "" + c, Toast.LENGTH_LONG).show();




    }

    private void batteryLevel() {

        BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int rawlevel = intent.getIntExtra("level", -1);
                int scale = intent.getIntExtra("scale", -1);
                int level = -1;
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                batteryPercentage.setText("Battery Level Remaining: " + level + "%");
                if(level<=18)
                {
                    batteryreminder.setText("Warning!!Low Battery!");
                    SmsManager bat = SmsManager.getDefault();
                    batteryNumber=sharedPreferences.getString("contact1",null);
                    batteryNumber1=sharedPreferences.getString("contact2",null);
                    batteryNumber2=sharedPreferences.getString("contact3",null);
                    batteryNumber3=sharedPreferences.getString("contact4",null);
                    //   batteryNumber=sharedPreferences.getString("contactLawEnforcement",null);

                    x= "Battery is low I'm in. " +"Lattitude is :"+lat+" Longitude is : "+lon+" "+mycity+" "+addresslinefull+" ";

                    bat.sendTextMessage(batteryNumber, null,x, null, null);
                    bat.sendTextMessage(batteryNumber1, null,x, null, null);
                    bat.sendTextMessage(batteryNumber2, null,x, null, null);
                    bat.sendTextMessage(batteryNumber3, null,x, null, null);

                }
            }
        };
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryLevelFilter);



    }





    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onPause() {
        googleApiClient.disconnect();
        super.onPause();
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(100000);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

    }
    public void getGeocoder()
    {
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);

            if (addresses.size() > 0) {
                String add = "";


                cityTV.setText(addresses.get(0).getLocality());

                countryTV.setText(addresses.get(0).getCountryName());
                //city=addresses.get(0).getFeatureName();
                //mycity=addresses.get(0).getLocality();


                addresslinefull=addresses.get(0).getAddressLine(0);


                addresslineTV.setText(addresslinefull);

                Toast.makeText(this, "AddressLine:"+addresslinefull, Toast.LENGTH_LONG).show();


                String etc=addresses.get(0).getCountryCode();
                Toast.makeText(this, "country code:"+etc, Toast.LENGTH_SHORT).show();


                postalCode=addresses.get(0).getPostalCode();
                Toast.makeText(this, "Postal Code:"+postalCode, Toast.LENGTH_SHORT).show();
                subadminarea=addresses.get(0).getSubAdminArea();
                Toast.makeText(this, "District:"+subadminarea, Toast.LENGTH_SHORT).show();
                subadmin=addresses.get(0).getAdminArea();
                Toast.makeText(this, "Division:"+subadmin, Toast.LENGTH_SHORT).show();
                // String iii=addresses.get(0).getSubLocality();
                // Toast.makeText(this, "sublocal: "+iii, Toast.LENGTH_SHORT).show();


                full =addresslinefull+" "+subadminarea+" "+subadmin+" "+postalCode+" ";







                // TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                //GsmCellLocation location = (((GsmCellLocation)tm.getCellLocation()));
                // int cellid= location.getCid();
                //  int celllac = location.getLac();

                // Log.d("CellLocation", location.toString());
                //  Log.d("GSM CELL ID",  String.valueOf(cellid));
                //  Log.d("GSM Location Code", String.valueOf(celllac));
                // Toast.makeText(this, "Locality from Phone Network  "+location.toString(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(this, "cellID: "+String.valueOf(cellid), Toast.LENGTH_SHORT).show();
                // Toast.makeText(this, " lac id "+String.valueOf(celllac), Toast.LENGTH_SHORT).show();




            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lat=location.getLatitude();
        lon=location.getLongitude();
        // lat=String.valueOf(location.getLatitude());
        //lon=String.valueOf(location.getLongitude());
        locationTV.setText("Lattitude"+lat+" "+"Longitude" +lon+" ");
        getGeocoder();



    }

    public void emergency(View view) {
        x= "This is an emergecy.Please reach me. " +"Lattitude is :"+lat+" Longitude is : "+lon+" "+mycity+" "+addresslinefull+" ";
        SmsManager bat = SmsManager.getDefault();
        batteryNumber=sharedPreferences.getString("contact1",null);
        batteryNumber1=sharedPreferences.getString("contact2",null);
        batteryNumber2=sharedPreferences.getString("contact3",null);
        batteryNumber3=sharedPreferences.getString("contact4",null);
        batteryNumber5=sharedPreferences.getString("contactLawEnforcement",null);


        String c=addresslineTV.getText().toString();




        if(c.contains(keyword1))
        {
            psnumber="0";
            bat.sendTextMessage(psnumber, null,x, null, null);

        }
        else if(c.contains(keyword2)||c.contains(keyword3))
        {
            psnumber="1";
            bat.sendTextMessage(psnumber, null,x, null, null);

        }



        else if(c.contains(keyword4)||c.contains(keyword5)||c.contains(keyword6))
        {

        }
        else if(c.contains(keyword7))
        {
            psnumber="2";
            bat.sendTextMessage(psnumber, null,x, null, null);


        }
        else
            Toast.makeText(this, "No P S found.Please call 999 ", Toast.LENGTH_SHORT).show();
        psnumber="9";

        bat.sendTextMessage(psnumber, null,x, null, null);


        bat.sendTextMessage(batteryNumber, null,x, null, null);
        bat.sendTextMessage(batteryNumber1, null,x, null, null);
        bat.sendTextMessage(batteryNumber2, null,x, null, null);
        bat.sendTextMessage(batteryNumber3, null,x, null, null);
        bat.sendTextMessage(batteryNumber5, null,x, null, null);









        // x= "This is an emergecy.Please reach me. " +"Lattitude is :"+lat+" Longitude is : "+lon+" "+mycity+" "+addresslinefull+" ";
/*
        bat.sendTextMessage(batteryNumber, null,x, null, null);
        bat.sendTextMessage(batteryNumber1, null,x, null, null);
        bat.sendTextMessage(batteryNumber2, null,x, null, null);
        bat.sendTextMessage(batteryNumber3, null,x, null, null);
        bat.sendTextMessage(batteryNumber5, null,x, null, null);
*/



    }

    public void reloadfunction(View view) {

        Intent refreshintent=new Intent(this,HomePage.class);
        startActivity(refreshintent);


    }

    public void callToEmergency(View view) {

        String number=sharedPreferences.getString("contactLawEnforcement",null);
        Intent callintent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",number, null));
        startActivity(callintent);
    }

    public void PSlistbtn(View view) {

       Intent list=new Intent(this,ListOfPS.class);
       startActivity(list);

    }
}
