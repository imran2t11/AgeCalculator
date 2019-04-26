package com.example.master.navigationdrawerpro;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class HomePageFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    TextView locationTV, cityTV, countryTV, batteryPercentage, addresslineTV;
    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;


    Context context;

    double lat, lon;
    String x;
    String city, mycity, batteryNumber5;
    TextView nameTV, batteryreminder, userTV;
    Button button2;
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
    FusedLocationProviderClient fusedLocationProviderClient;

    String psnumber = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homepage, null);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        batteryreminder = view.findViewById(R.id.batteryreminder);
        batteryPercentage = view.findViewById(R.id.batteryPercentage);
        locationTV = view.findViewById(R.id.locationTV);
        addresslineTV = view.findViewById(R.id.addresslineTV);
        cityTV = view.findViewById(R.id.cityTV);
        countryTV = view.findViewById(R.id.countryTV);
        userTV = view.findViewById(R.id.userTV);
        button2=view.findViewById(R.id.button2);

        sharedPreferences = getActivity().getSharedPreferences("loginInfo", MODE_PRIVATE);
        Log.d("name", "onCreate: " + sharedPreferences.getString("name", null));

        String nameis = sharedPreferences.getString("name", null);
        //  Toast.makeText(getActivity(), "blue lagoon"+nameis, Toast.LENGTH_SHORT).show();
        //nameTV.setText(nameis+"%");
        userTV.setText(" " + nameis);
        batteryLevel();

        googleApiClient = new GoogleApiClient.Builder(getContext()).
                addApi(LocationServices.API).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).build();


     button2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
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
                 Toast.makeText(getActivity(), "No P S found.Please call 999 ", Toast.LENGTH_SHORT).show();
             psnumber="9";

             bat.sendTextMessage(psnumber, null,x, null, null);


             bat.sendTextMessage(batteryNumber, null,x, null, null);
             bat.sendTextMessage(batteryNumber1, null,x, null, null);
             bat.sendTextMessage(batteryNumber2, null,x, null, null);
             bat.sendTextMessage(batteryNumber3, null,x, null, null);
             bat.sendTextMessage(batteryNumber5, null,x, null, null);








         }
     });

    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onPause() {
        googleApiClient.disconnect();
        super.onPause();

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
                if (level <= 18) {
                    batteryreminder.setText("Warning!!Low Battery!");
                    SmsManager bat = SmsManager.getDefault();
                    batteryNumber = sharedPreferences.getString("contact1", null);
                    batteryNumber1 = sharedPreferences.getString("contact2", null);
                    batteryNumber2 = sharedPreferences.getString("contact3", null);
                    batteryNumber3 = sharedPreferences.getString("contact4", null);
                    //   batteryNumber=sharedPreferences.getString("contactLawEnforcement",null);

                    x = "Battery is low I'm in. " + "Lattitude is :" + lat + " Longitude is : " + lon + " " + mycity + " " + addresslinefull + " ";

                    bat.sendTextMessage(batteryNumber, null, x, null, null);
                    bat.sendTextMessage(batteryNumber1, null, x, null, null);
                    bat.sendTextMessage(batteryNumber2, null, x, null, null);
                    bat.sendTextMessage(batteryNumber3, null, x, null, null);

                }
            }
        };
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        getActivity().registerReceiver(batteryLevelReceiver, batteryLevelFilter);

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(100000);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest,this);
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

    private void getGeocoder() {


        Geocoder geocoder = new Geocoder(getContext(), Locale.ENGLISH);

        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);

            if (addresses.size() > 0) {
                String add = "";


                cityTV.setText(addresses.get(0).getLocality());

                countryTV.setText(addresses.get(0).getCountryName());
                //city=addresses.get(0).getFeatureName();
                //mycity=addresses.get(0).getLocality();


                addresslinefull=addresses.get(0).getAddressLine(0);

               //sharedPreferences=getActivity().getSharedPreferences("locationnow",MODE_PRIVATE);
    String latitude,longitude;

               editor=sharedPreferences.edit();
               editor.putString("location",addresslinefull);
latitude=String.valueOf(lat);
longitude=String.valueOf(lon);
                editor.putString("lat",latitude);
                editor.putString("lon",longitude);


               editor.commit();


                addresslineTV.setText(addresslinefull);

                Toast.makeText(getActivity(), "AddressLine:"+addresslinefull, Toast.LENGTH_LONG).show();


                String etc=addresses.get(0).getCountryCode();
                Toast.makeText(getActivity(), "country code:"+etc, Toast.LENGTH_SHORT).show();


                postalCode=addresses.get(0).getPostalCode();
                Toast.makeText(getActivity(), "Postal Code:"+postalCode, Toast.LENGTH_SHORT).show();
                subadminarea=addresses.get(0).getSubAdminArea();
                Toast.makeText(getActivity(), "District:"+subadminarea, Toast.LENGTH_SHORT).show();
                subadmin=addresses.get(0).getAdminArea();
                Toast.makeText(getActivity(), "Division:"+subadmin, Toast.LENGTH_SHORT).show();
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


}
