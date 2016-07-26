package app.my.testapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Result extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private LatLng center;
    TextView total;
    private MapView googleMap;
    GoogleMap mGoogleMap;
    boolean mUpdatesRequested = false;
    private LocationRequest mLocationRequest;
    GoogleApiClient googleApiClient = null;
    String location;
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//
//                new GraphRequest(
//                AccessToken.getCurrentAccessToken(),
//                "/{LiverpoolFC}/feed",
//                null,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    public void onCompleted(GraphResponse response) {
//            /* handle the result */
//                        String a =  null;
//                        String token = AccessToken.getCurrentAccessToken().toString();
//                    }
//                }
//        ).executeAsync();

//        total = (TextView) findViewById(R.id.total);
//        Intent i = getIntent();
//
//        int height = Integer.parseInt(i.getStringExtra("height"));
//        int width = Integer.parseInt(i.getStringExtra("width"));
//
//        int t = height * width;
//        total.setText(t + "");

// Getting reference to the SupportMapFragment
        // Create a new global location parameters object
        mLocationRequest = LocationRequest.create();

     	            /*
                      * Set the update interval
     	             */
        mLocationRequest.setInterval(1000);

        // Use high accuracy
        mLocationRequest
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Set the interval ceiling to one minute
        mLocationRequest
                .setFastestInterval(1);

        // Note that location updates are off until the user turns them on
        mUpdatesRequested = false;

     	            /*
                      * Create a new location client, using the enclosing class to handle
     	             * callbacks.
     	             */
     	          /*  mLocationClient = new LocationClient(this, this, this);
     	            mLocationClient.connect();*/
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(Result.this)
                .addOnConnectionFailedListener(this)
                .build();

        googleApiClient.connect();
//        if (mGoogleMap!=null){
//            Marker hamburg = mGoogleMap.addMarker(new MarkerOptions().position(HAMBURG)
//                    .title("Hamburg"));
//            Marker kiel = mGoogleMap.addMarker(new MarkerOptions()
//                    .position(KIEL)
//                    .title("Kiel")
//                    .snippet("Kiel is cool")
//                    .icon(BitmapDescriptorFactory
//                            .fromResource(R.drawable.cast_ic_notification_0)));
//
//            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
//
//// Zoom in, animating the camera.
//            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
//
//        }


//        mapView.setSatellite(false);
//        mapView.setTraffic(false);
//        mapView.setBuiltInZoomControls(true);   // Set android:clickable=true in main.xml
//
//        int maxZoom = mapView.getMaxZoomLevel();
//        int initZoom = maxZoom-2;
//
//        mapControl = mapView.getController();
//        mapControl.setZoom(initZoom);
//        // Convert lat/long in degrees into integers in microdegrees
//        latE6 =  (int) (lat*1e6);
//        lonE6 = (int) (lon*1e6);
//        gp = new GeoPoint(latE6, lonE6);
//        mapControl.animateTo(gp);
//
//        mapView.setBuiltInZoomControls(true);
//
//        List<Overlay> mapOverlays = mapView.getOverlays();
//        Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
//        HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable,this);
//        Barcode.GeoPoint point = new Barcode.GeoPoint(30443769,-91158458);
//        OverlayItem overlayitem = new OverlayItem(point, "Hi!", "Second!");
//
//        Barcode.GeoPoint point2 = new Barcode.GeoPoint(17385812,78480667);
//        OverlayItem overlayitem2 = new OverlayItem(point2, "Hello!", " fisrt one!");
//
//        itemizedoverlay.addOverlay(overlayitem);
//        itemizedoverlay.addOverlay(overlayitem2);
//
//        mapOverlays.add(itemizedoverlay);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        stupMap();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void stupMap() {
        try {

            LatLng latLong;
            // TODO Auto-generated method stub

            mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
		          /*  mGoogleMap.addMarker(new MarkerOptions()
	                .position(new LatLng(37.7750, 122.4183))
	                .title("San Francisco").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));*/

            mGoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(9.925201, 78.119775))

                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            /// Zoom Control Position
            View zoomControls = getFragmentManager().findFragmentById(
                    R.id.map).getView().findViewById(R.id.map);

            if (zoomControls != null && zoomControls.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                // ZoomControl is inside of RelativeLayout
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) zoomControls.getLayoutParams();

                // Align it to - parent top|left
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

                // Update margins, set to 10dp
                final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                        getResources().getDisplayMetrics());
                params.setMargins(margin + 450, margin + 500, margin, margin);
            }



		          /*  Polyline line = mGoogleMap.addPolyline(new PolylineOptions()
		            .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
		            .width(5)
		            .color(Color.RED));*/


            // Enabling MyLocation in Google Map
            mGoogleMap.setMyLocationEnabled(true);


            if (location == "test") {

                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Location gpslocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                latLong = new LatLng(gpslocation.getLatitude(), gpslocation.getLongitude());


            } else if (location != "test") {
                latLong = getLocationFromAddress(location);

                mGoogleMap.addMarker(new MarkerOptions()
                        .position(latLong)

                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            } else {
                latLong = new LatLng(12.9667, 77.5667);

            }
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLong).zoom(12f).tilt(0).build();

            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));


            mGoogleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

                @Override
                public void onCameraChange(CameraPosition arg0) {
                    // TODO Auto-generated method stub
                    center = mGoogleMap.getCameraPosition().target;

                    try {
//                        new GetLocationAsync(center.latitude, center.longitude)
//                                .execute();


                    } catch (Exception e) {
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(Result.this);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }

//    public class GetLocationAsync extends AsyncTask<String, Void, String> {
//
//
//        // boolean duplicateResponse;
//        double x, y;
//        StringBuilder str;
//
//        public GetLocationAsync(double latitude, double longitude) {
//            // TODO Auto-generated constructor stub
//
//            x = latitude;
//            y = longitude;
//
//        }
//
//        public GetLocationAsync() {
//            // TODO Auto-generated constructor stub
//        }
//
//        @Override
//        protected void onPreExecute() {
//            if (location != null) {
//
//            } else {
////                atvPlaces.setText(" Getting location ");
//            }
//
//        }
//
//        @SuppressLint("NewApi")
//        @Override
//        protected String doInBackground(String... params) {
//
//            try {
//                geocoder = new Geocoder(Result.this, Locale.ENGLISH);
//                addresses = geocoder.getFromLocation(x, y, 1);
//                str = new StringBuilder();
//                if (Geocoder.isPresent()) {
//                    //if(addresses!= null)
//                    if (addresses.size() > 0) {
//                        returnAddress = addresses.get(0);
//                        localityString = returnAddress.getLocality();
//                        city = returnAddress.getCountryName();
//                        region_code = returnAddress.getCountryCode();
//                        zipcode = returnAddress.getPostalCode();
//                        if (location != null) {
//                            addresses1 = location;
//                        } else {
//                            addresses1 = addresses.get(0).getAddressLine(0)
//                                    + addresses.get(0).getAddressLine(1) + " ";
//                        }
//
//                        str.append(localityString + "");
//                        str.append(city + "" + region_code + "");
//                        str.append(zipcode + "");
//
//                    } else {
//                    }
//                } else {
//                }
//            } catch (IOException e) {
//                Log.e("tag", e.getMessage());
//            }
//            return null;
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            try {
//
//                if (location != null) {
//
//                } else
//
//                    atvPlaces.setText(addresses.get(0).getAddressLine(0)
//                            + addresses.get(0).getAddressLine(1) + " ");
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//
//        }
//    }

}
