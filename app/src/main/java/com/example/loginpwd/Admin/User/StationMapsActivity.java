package com.example.loginpwd.Admin.User;

import androidx.annotation.NonNull;
import
        androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.loginpwd.Admin.Station.Station;
import com.example.loginpwd.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

import static com.example.loginpwd.R.drawable.cycle_station;
import static com.example.loginpwd.R.drawable.googleg_disabled_color_18;

public class StationMapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    LocationManager man;
    Location loc;
    double lati, longi;
    Address address;
    boolean isNet, isGps;
    String area="";
    LatLng first, prev;
    Marker mark;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        man = (LocationManager) getSystemService(LOCATION_SERVICE);
        isNet = man.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        isGps = man.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isNet || isGps) {
            if (isNet) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                man.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 10, this);

                if(man!=null)
                {
                    loc=man.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if(loc!=null)
                    {
                        lati=loc.getLatitude();
                        longi=loc.getLongitude();
                    }

                }
            }

            if (isGps) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                man.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20000, 10, this);

                if(man!=null)
                {
                    loc=man.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(loc!=null)
                    {
                        lati=loc.getLatitude();
                        longi=loc.getLongitude();
                    }

                }
            }
            first=new LatLng(lati,longi);
            prev=first;
            Toast.makeText(getApplicationContext(),"LATI : "+lati+"  LONGI : "+longi,Toast.LENGTH_LONG).show();

            try
            {
                Geocoder geo=new Geocoder(this, Locale.getDefault());
                List<Address> list=geo.getFromLocation(lati,longi,1);

                address=list.get(0);



                area=address.getAddressLine(0);
                area=area+"\n"+address.getLocality();
                area=area+"\n"+address.getAdminArea();
                area=area+"\n"+address.getCountryName();
                area=area+"\n"+address.getPostalCode();


            }
            catch (Exception e) { }
        }

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
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        dbref= FirebaseDatabase.getInstance().getReference("Station");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Station s1=shot.getValue(Station.class);

                    LatLng stlocate = new LatLng(s1.latitude,s1.longitude);
                   // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    mMap.addMarker(new MarkerOptions().position(stlocate).title(s1.stationName));
                   // mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // Add a marker in Sydney and move the camera
        final LatLng current = new LatLng(lati, longi);
        prev=current;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

       mMap.addMarker(new MarkerOptions().position(current).title(area).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
       mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
       mMap.animateCamera(CameraUpdateFactory.newLatLng(current));
       mMap.animateCamera(CameraUpdateFactory.zoomTo(14.5f));

       // Polyline line=mMap.addPolyline(new PolylineOptions().add(first,current).width(5).color(Color.BLUE));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker)
            {

                double mklati=marker.getPosition().latitude;
                double mklongi=marker.getPosition().longitude;
                if(mklati==lati && mklongi==longi)
                {
                    Toast.makeText(getApplicationContext(),"YOU ARE HERE!! ",Toast.LENGTH_LONG).show();
                }
                else {

                    Intent i1=getIntent();
                    String email=i1.getStringExtra("Email");
                    Intent ii = new Intent(getApplicationContext(), BookCycleCard.class);
                    ii.putExtra("Latitude",""+mklati);
                    ii.putExtra("Longitude",""+mklongi);
                    ii.putExtra("Email",email);
                    startActivity(ii);

                }
                return true;
            }
        });

    }

    @Override
    public void onLocationChanged(Location location)
    {
/*
        if(mark!=null)
        {
            mark.setVisible(false);
        }


        double newlati=location.getLatitude();
        double newlongi=location.getLongitude();

        LatLng current=new LatLng(newlati,newlongi);
        Polyline line=mMap.addPolyline(new PolylineOptions().add(prev,current).width(5).color(Color.BLUE));
        prev=current;
        Marker curmark=mMap.addMarker(new MarkerOptions().position(current).title("IAM HERE"));

        mark=curmark;

        */
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }





    @Override
    public boolean onMarkerClick(Marker marker)
    {


        return false;
    }
}
