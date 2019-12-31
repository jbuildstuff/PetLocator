package edu.illinois.cs465.petlocator;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PetTimelineActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, GoogleMap.OnMarkerDragListener, View.OnClickListener  {

    private GoogleMap mMap;
    private View view;
    private static LatLng fromPosition = null;
    private static LatLng toPosition = null;
    private static LatLng finalPos = null;
    private static LatLng defaultPos = null;
    private Button pickLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_timeline);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    public void addMarkers(GoogleMap googleMap){

        final LatLng GraingerWest = new LatLng(40.11235739477313, -88.22791278362274);
        Marker GraingerW = mMap.addMarker(new MarkerOptions()
                .position(GraingerWest).title("Grainger Engineering Library: 6pm")
                .alpha(0.9f));

        final LatLng GraingerEast = new LatLng(40.112390215667745, -88.22577774524689);
        Marker GraingerE = mMap.addMarker(new MarkerOptions()
                .position(GraingerEast).title("Grainger East: 2pm")
                .alpha(0.7f));

        final LatLng DCL = new LatLng(40.113440475933125, -88.22579383850098);
        Marker DCLMar = mMap.addMarker(new MarkerOptions()
                .position(DCL).title("DCL Building: 10am")
                .alpha(0.6f));

        final LatLng IlliniUnion = new LatLng(40.10939235, -88.22721870933967);
        Marker IlliniU = mMap.addMarker(new MarkerOptions()
                .position(IlliniUnion).title("Illini Union: 6am")
                .alpha(0.4f));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in uiuc and move the camera
        addMarkers(mMap);
        LatLng siebel = new LatLng(40.1138069, -88.2270939);
        defaultPos = siebel;
      //  mMap.addMarker(new MarkerOptions().position(siebel).title("Lost Pet Marker").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(siebel, 10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
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
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
         mMap.setOnMarkerClickListener(this);
      //  mMap.setOnMarkerDragListener(this);

    }
}
