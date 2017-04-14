package com.codepath.photospot.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.photospot.R;
import com.codepath.photospot.fragments.PhotoListFragment;
import com.codepath.photospot.services.LocationService;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements LocationService.LocationUpdateListener {

    private PhotoListFragment photoListFragment;
    private LocationService locationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Display icon in the toolbar
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        if (savedInstanceState == null) {
            photoListFragment = (PhotoListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_search_results);

        }
        locationService = new LocationService(MainActivity.this);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.i("DEBUG", "Place: " + place.getName());
                LatLng latLng = place.getLatLng();
                photoListFragment.photoSearch(latLng.latitude, latLng.longitude);
            }

            @Override
            public void onError(Status status) {
                Log.i("DEBUG", "An error occurred: " + status);
            }
        });
    }

    @Override
    protected void onStart() {
        locationService.connect();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        locationService = null;
        super.onDestroy();
    }

    public void onLocationUpdate(Location location) {
        this.photoListFragment.photoSearch(location.getLatitude(), location.getLongitude());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_add:
                Toast.makeText(this, "Add was selected...", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), AddPhotoActivity.class);
                startActivity(i);
                return true;
            case R.id.action_search:
                Toast.makeText(this, "Search was selected...", Toast.LENGTH_LONG).show();
                i = new Intent(getApplicationContext(), AddPhotoActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
