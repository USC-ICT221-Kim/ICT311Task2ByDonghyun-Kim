package com.example.yohan.criminalintent;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class GPStracker implements LocationListener {
    Context context;
    public GPStracker(Context c){
        context = c;
    }



    public Location getLocation(){

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context,"Permission On granted", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager Lm = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        boolean isGPSworking = Lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGPSworking) {
            Lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,10,this);
            Location L = Lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return L;
        }else {
            Toast.makeText(context,"Please enable GPS", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
