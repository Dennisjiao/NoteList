/**
 * TO BE DELETED?
 */

package com.example.team05;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThingsToDobackup extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    Location mLocation;
    TextView latLng;
    GoogleApiClient mGoogleApiClient;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private long UPDATE_INTERVAL = 15000;  /* 15 secs */
    private long FASTEST_INTERVAL = 5000; /* 5 secs */

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList();
    private ArrayList<String> permissions = new ArrayList();

    private final static int ALL_PERMISSIONS_RESULT = 101;

    private static double latitude;
    private static double longitude;

    SupportMapFragment mapFragment;
    public static GoogleMap mMap;

    Spinner mySpinner;

    //Defines list view
//    ScrollView sv;

    static String markerInfo;

    public static String placeName;
    public static String placeUrl;
    public static String placePhoneNo;
    public static String placeRating;

    private TextView placeNameTV;
    private static WeakReference<TextView> weakTVRefPlaceName;
    private TextView placeURLTV;
    private static WeakReference<TextView> weakTVRefURL;
    private TextView placePhoneTV;
    private static WeakReference<TextView> weakTVRefphone;
    private TextView placeRatingTV;
    private static WeakReference<TextView> weakTVRefRating;


    private static ArrayList<String> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_to_do);

        //Request permission
        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);

        //get permissions that are not granted
        permissionsToRequest = findUnAskedPermissions(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0)
                requestPermissions((String[]) permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        // set bottom nav bar
        BottomNavigationView bottomNavBar = (BottomNavigationView) findViewById(R.id.bottomNav);
        bottomNavBar.getMenu().setGroupCheckable(0,false,true);
        bottomNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mainActivityNav:
                        Intent intent3 = new Intent(ThingsToDobackup.this, MainActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.bookingNav:
                        Intent intent = new Intent(ThingsToDobackup.this, Booking.class);
                        startActivity(intent);
                        break;

                    case R.id.moreNav:
                        Intent intent2 = new Intent(ThingsToDobackup.this, ThingsToDo.class);
                        startActivity(intent2);
                        break;

                }
                return false;
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //
        View imageButton1 = (View) findViewById(R.id.imageAlnwick);
        View imageButton2 = (View) findViewById(R.id.imageAuckland);
        View imageButton3 = (View) findViewById(R.id.imageBamburgh);
        View imageButton4 = (View) findViewById(R.id.imageBarnard);



        //Create castle spinner with default of current location
        mySpinner = (Spinner) findViewById(R.id.castleList);
        ArrayList<String> CastleName = new ArrayList<>();
        CastleName.add("Current Location");
        CastleName.add("Alnwick Castle");
        CastleName.add("Auckland Castle");
        CastleName.add("Bamburgh Castle");
        CastleName.add("Barnard Castle");
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ThingsToDobackup.this,
                android.R.layout.simple_list_item_1, CastleName);
        mySpinner.setAdapter(myAdapter);
        /**
         * */
//        mySpinner = (Spinner) findViewById(R.id.castleList);
//        ArrayList<String> CastleName = new ArrayList<>();
//        CastleName.add("Current Location");
//        CastleName.add("Alnwick Castle");
//        CastleName.add("Auckland Castle");
//        CastleName.add("Bamburgh Castle");
//        CastleName.add("Barnard Castle");
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ThingsToDo.this,
//                android.R.layout.simple_list_item_1, CastleName);
//        mySpinner.setAdapter(myAdapter);

        //press imagebutton to jump to castle
        imageButton1.setOnClickListener(new View.OnClickListener() {
            //mySpinner.getSelectedItem().equals("Alnwick Castle");
            @Override
            public void onClick(View view) {
                setLatLong();
            }
        });
        /***
         *
         */


        //Button to set current location
        Button searchButton = (Button) findViewById(R.id.Search_Button_Activities);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLatLong();
            }
        });

        //Button initialises search for nearby restaurants
        Button btnRestaurant = (Button) findViewById(R.id.btnRestaurant);
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            String Restaurant = "restaurant";
            @Override
            public void onClick(View v) {
                Log.d("onClick", String.valueOf(latitude));
                mMap.clear();
                String url = getUrl(latitude, longitude, Restaurant);
                Log.d("onClick", url);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(ThingsToDobackup.this,"Nearby Restaurants", Toast.LENGTH_LONG).show();
            }
        });

        //Button initialises search for nearby supermarkets
        Button btnSupermarket = (Button) findViewById(R.id.btnSupermarket);
        btnSupermarket.setOnClickListener(new View.OnClickListener() {
            String Supermarket = "supermarket";
            @Override
            public void onClick(View v) {
                Log.d("onClick", String.valueOf(latitude));
                mMap.clear();
                String url = getUrl(latitude, longitude, Supermarket);
                Log.d("onClick", url);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(ThingsToDobackup.this,"Nearby Supermarkets", Toast.LENGTH_LONG).show();
            }
        });

//        sv = (ScrollView) findViewById((R.id.places_list));

        // List Adapter for format
//        PlaceAdapter adapter = new PlaceAdapter(this, R.layout.places_list_adapter, list);

        placeNameTV = findViewById(R.id.PlaceName);
        weakTVRefPlaceName = new WeakReference<>(placeNameTV);

        placeURLTV = findViewById(R.id.PlaceUrl);
        weakTVRefURL = new WeakReference<>(placeURLTV);

        placePhoneTV = findViewById(R.id.PlacePhone);
        weakTVRefphone = new WeakReference<>(placePhoneTV);

        placeRatingTV = findViewById(R.id.PlaceRating);
        weakTVRefRating = new WeakReference<>(placeRatingTV);
    }

    //Finds URL to request from google maps API based on lat,long
    private String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + 10000);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyAHVJQxykBY31DrV2BZadqbtJkoyDaFVwA");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }

    //Displays any unasked permissions
    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    //Starts connection to google API client
    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!checkPlayServices()) {
            latLng.setText("Please install Google Play services.");
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //Main updater of location, when requested. Stops automatically.
    @Override
    public void onLocationChanged(Location location) {

        if(location!=null){
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            mMap.setMinZoomPreference(15.0f);
            mMap.setMaxZoomPreference(15.0f);
            LatLng Current_Position  = new LatLng(latitude,longitude);
            Log.d("Test","Latitude = "+latitude+" Longitude = "+longitude);
            mMap.addMarker(new MarkerOptions().position(Current_Position).title("Current Position"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Current_Position));
            stopLocationUpdates();
        }


    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else
                finish();

            return false;
        }
        return true;
    }

    protected void startLocationUpdates() {
        com.google.android.gms.location.LocationRequest mLocationRequest = new com.google.android.gms.location.LocationRequest();
        mLocationRequest.setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Enable Permissions", Toast.LENGTH_LONG).show();
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);


    }

    private boolean hasPermission(String permission) {
        if (versionUpToDate()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean versionUpToDate() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }

                break;
        }

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(ThingsToDobackup.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }

    public void stopLocationUpdates() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi
                    .removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    //first call of map - should always try to go to current location
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(15.0f);
        mMap.setMaxZoomPreference(15.0f);
        LatLng Current_Position  = new LatLng(latitude,longitude);
        Log.d("Test","Latitude = "+latitude+" Longitude = "+longitude);
        mMap.addMarker(new MarkerOptions().position(Current_Position).title("Current Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Current_Position));

        googleMap.setOnMarkerClickListener(this);
    }

    //sets new location based on spinner
    private void setLatLong(){

        View imageButton1 = (View) findViewById(R.id.imageAlnwick);
        View imageButton2 = (View) findViewById(R.id.imageAuckland);
        View imageButton3 = (View) findViewById(R.id.imageBamburgh);
        View imageButton4 = (View) findViewById(R.id.imageBarnard);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MarkerOptions markerOptions = new MarkerOptions();
//            markerOptions.title("Alnwick Castle");
                latitude = 55.41571066816451;
                longitude = -1.7058452995980735;
                LatLng Current_Position  = new LatLng(latitude,longitude);
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(Current_Position).title("Alnwick Castle"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(Current_Position));
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latitude = 54.67153810776224;
                longitude = -1.6712613553567615;
                LatLng Current_Position  = new LatLng(latitude,longitude);
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(Current_Position).title("Auckland Castle"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(Current_Position));
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latitude = 55.609080781406995;
                longitude = -1.7099322879491325;
                LatLng Current_Position  = new LatLng(latitude,longitude);
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(Current_Position).title("Bamburgh Castle"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(Current_Position));
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latitude = 54.5456698230093;
                longitude = -1.9236628163269331;
                LatLng Current_Position  = new LatLng(latitude,longitude);
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(Current_Position).title("Barnard Castle"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(Current_Position));
            }
        });



        if(mySpinner.getSelectedItem().equals("Current Location")){
            onStart();
        }

        if(mySpinner.getSelectedItem().equals("Alnwick Castle")){
//            MarkerOptions markerOptions = new MarkerOptions();
//            markerOptions.title("Alnwick Castle");
            latitude = 55.41571066816451;
            longitude = -1.7058452995980735;
            LatLng Current_Position  = new LatLng(latitude,longitude);
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(Current_Position).title("Alnwick Castle"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Current_Position));
        }

        if(mySpinner.getSelectedItem().equals("Auckland Castle")){
            latitude = 54.67153810776224;
            longitude = -1.6712613553567615;
            LatLng Current_Position  = new LatLng(latitude,longitude);
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(Current_Position).title("Auckland Castle"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Current_Position));
        }

        if(mySpinner.getSelectedItem().equals("Bamburgh Castle")){
            latitude = 55.609080781406995;
            longitude = -1.7099322879491325;
            LatLng Current_Position  = new LatLng(latitude,longitude);
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(Current_Position).title("Bamburgh Castle"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Current_Position));
        }

        if(mySpinner.getSelectedItem().equals("Barnard Castle")){
            latitude = 54.5456698230093;
            longitude = -1.9236628163269331;
            LatLng Current_Position  = new LatLng(latitude,longitude);
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(Current_Position).title("Barnard Castle"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Current_Position));
        }

    }

    //Adds nearby places to map
    public static void ShowNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList) {
        list.removeAll(list);

        for (int i = 0; i < nearbyPlacesList.size(); i++) {
            Log.d("onPostExecute","Entered into showing locations");
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
            String placeName = googlePlace.get("place_name");

            int placeIDPos = Integer.parseInt(googlePlace.get("placeIDPos"));


//            Log.d("Test",rating+" rating added in");
//            Log.d("Test",phoneNo+" phoneNo added in");
            Log.d("Test",placeName+" placeName added in MATCHES WITH");
            Log.d("Test",placeIDPos+" placeID ");
            Log.d("Test", "This: "+DataParser.placeIDs.get(placeIDPos));

            String vicinity = googlePlace.get("vicinity");
            LatLng latLng = new LatLng(Double.parseDouble(googlePlace.get("lat")), Double.parseDouble(googlePlace.get("lng")));
            Log.d("onPostExecute","Latlng = "+latitude+", "+longitude);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " : " + vicinity);

            mMap.addMarker(markerOptions).setTag(placeIDPos);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
            Log.d("onPostExecute",placeName);
        }



    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        Log.d("Test",marker.getTitle());
        Log.d("Test",marker.getId());
        Log.d("Test", String.valueOf(marker.getTag()));
        String placeID = DataParser.placeIDs.get(marker.getTag());
        Log.d("Test","On marker click placeID = "+DataParser.placeIDs.get(marker.getTag()));

        //add more fields here
        String url ="https://maps.googleapis.com/maps/api/place/details/json?place_id="+placeID+"&fields=name,website,formatted_phone_number,rating&key=AIzaSyAHVJQxykBY31DrV2BZadqbtJkoyDaFVwA";
        Object[] DataTransfer = new Object[1];
        DataTransfer[0] = url;
        GetPlaceData getPlaceData = new GetPlaceData();
        getPlaceData.execute(DataTransfer);

        Log.d("Test",Thread.currentThread().getName());
//        try {
//            getPlaceData.wait(10);
////            getPlaceData.notify();
//            Log.d("Test",getPlaceData.getStatus().toString());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



        return false;
    }


    public static void showPlaceInfo(){


        if(placeName.equals("null") || placeName==null){
            weakTVRefPlaceName.get().setText("Name not available");
        }else{
            weakTVRefPlaceName.get().setText(placeName);
        }

        if(placeUrl.equals("null") || placeUrl==null){
            weakTVRefURL.get().setText("URL not available");
        }else{
            weakTVRefURL.get().setText(placeUrl);
        }

        if(placePhoneNo.equals("null") || placePhoneNo==null){
            weakTVRefphone.get().setText("Phone Number not available");
        }else{
            weakTVRefphone.get().setText(placePhoneNo);
        }

        if(placeRating.equals("null") || placeRating==null){
            weakTVRefRating.get().setText("Rating not available");
        }else{
            weakTVRefRating.get().setText(placeRating);
        }




    }

}