package edu.illinois.cs465.petlocator;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SawAnAnimalActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, View.OnClickListener {

    private GoogleMap mMap;
    private View view;
    private static LatLng fromPosition = null;
    private static LatLng toPosition = null;
    private static LatLng finalPos = null;
    private static LatLng defaultPos = null;
    private static Marker lostPetMarker = null;
    private static Location currLocation = null;
    private Button pickLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saw_an_animal);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_saw_an_animal, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);
        // Add a marker in uiuc and move the camera

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        LatLng siebel = new LatLng(40.1138069, -88.2270939);
        defaultPos = siebel;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {
            defaultPos = new LatLng(location.getLatitude(), location.getLongitude());
        }
        currLocation = new Location("");
        currLocation.setLatitude(defaultPos.latitude);
        currLocation.setLongitude(defaultPos.longitude);
        lostPetMarker = mMap.addMarker(new MarkerOptions().position(defaultPos).title("Lost Pet Marker").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultPos, 10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        pickLocation = (Button) findViewById(R.id.PickLocation);
        pickLocation.setOnClickListener(this);


    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        currLocation = location;
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onMyLocationButtonClick() {
        if (currLocation == null) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return false;
            }
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            LatLng currLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            lostPetMarker.setPosition(currLatLng);
        }
        else {
            LatLng currLatLng = new LatLng(currLocation.getLatitude(), currLocation.getLongitude());
            lostPetMarker.setPosition(currLatLng);
        }
        // Toast.makeText(this, "Finding your location", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).

        return false;
    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.i("GoogleMapActivity", "onMarkerClick");
        Toast.makeText(getApplicationContext(),
                "Marker Clicked: " + marker.getTitle(), Toast.LENGTH_LONG)
                .show();
        return false;
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        // do nothing during drag
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        toPosition = marker.getPosition();
      /*  Toast.makeText(
                getApplicationContext(),
                "Marker " + marker.getTitle() + " dragged from " + fromPosition
                        + " to " + toPosition, Toast.LENGTH_LONG).show();*/
        finalPos = toPosition;

    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        fromPosition = marker.getPosition();
        Log.d(getClass().getSimpleName(), "Drag start at: " + fromPosition);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.PickLocation) {
            Intent intent = new Intent(SawAnAnimalActivity.this, IdentifyPetActivity.class);
            startActivity(intent);
        }
    }
}
