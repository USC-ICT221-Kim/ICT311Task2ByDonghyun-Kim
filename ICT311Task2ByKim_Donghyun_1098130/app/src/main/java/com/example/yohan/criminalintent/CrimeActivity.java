package com.example.yohan.criminalintent;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_ID =" com.example.yohan.criminalintent.crime_id";
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
    Button getlocation;
    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        getlocation = (Button) findViewById(R.id.get_location);
        ActivityCompat.requestPermissions(CrimeActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123) ;
        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GPStracker g =new GPStracker(getApplicationContext());
                Location L = g.getLocation();
                if (L != null){
                    double lat = L.getLatitude();
                    double lon = L.getLongitude();
                    Toast.makeText(getApplicationContext(),"Lat: "+lat+" /n Lon: "+lon,Toast.LENGTH_LONG).show();
                }
            }
        });
        return CrimeFragment.newInstance(crimeId);
    }


}
