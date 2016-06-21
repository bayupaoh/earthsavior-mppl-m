package id.or.codelabs.earthsavior;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.location.FusedLocationProviderApi;

public class CalculateActivity extends AppCompatActivity
        implements OnMapReadyCallback{

    Toolbar mToolbar;
    Button btnStop;
    private PolylineOptions mPolylineOptions;
    Location mCurrentLocation;
    Location mStartPosisition;
    Location mLocationBefore;
    boolean awal = true;
    Polyline piPolyline;
    Marker cursor;
    TextView txtKecepatan;
    TextView txtWaktu;
    TextView txtTotalJarak;
    TextView txtTotalEmisi;
    float jarak = 0;
    float avgSpeed = 0;

    GoogleMap googleMap;

    private long startTime = 0L;
    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int secs, mins, milliseconds, hour;
    double latTujuan,logTujuan;
    MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        btnStop = (Button) findViewById(R.id.button_stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
                finish();
            }
        });


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = this.getIntent().getExtras();
        if(b != null) {
            latTujuan = (double) b.get("latitude");
            logTujuan = b.getDouble("longitude", 0);
        }
        declarationWidget();

        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);




        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                Bundle b = new Bundle();
                b.putFloat("distance", jarak);
                b.putFloat("speed",avgSpeed);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }

    private void declarationWidget() {

        txtKecepatan = (TextView) findViewById(R.id.txtViewKecepatan);
        txtTotalEmisi = (TextView) findViewById(R.id.txtViewEmisi);
        txtTotalJarak = (TextView) findViewById(R.id.txtViewTotalJarak);
        txtWaktu = (TextView) findViewById(R.id.txtViewWaktu);
        btnStop = (Button) findViewById(R.id.button_stop);
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            secs = (int) (updatedTime / 1000);
            hour = mins / 60;
            mins = secs / 60;
            secs = secs % 60;
            milliseconds = (int) (updatedTime % 100);
            txtWaktu.setText(String.format("%02d", hour) + ":" + String.format("%02d", mins) + ":"
                    + String.format("%02d", secs));
            customHandler.postDelayed(this, 0);
        }

    };

    private void tandaitempat() {

   }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(getApplicationContext(),"Map Available",Toast.LENGTH_LONG).show();
        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        GoogleMapOptions option = new GoogleMapOptions();
        option.compassEnabled(true);
        this.googleMap.setTrafficEnabled(true);
        this.googleMap.setMyLocationEnabled(true);
        this.googleMap.setOnMyLocationChangeListener(myLocationChangeListener);
    }

    private OnMyLocationChangeListener myLocationChangeListener = new OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            if(awal) {
                awal=false;
                LatLng asal = new LatLng(location.getLatitude(),location.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(asal).title("Start")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                googleMap.addMarker(new MarkerOptions().position(new LatLng(latTujuan,logTujuan)).title("Finish")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(asal));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(19));

                mLocationBefore = location ;

            }else{
                LatLng latLngCurrent = new LatLng(location.getLatitude(), location.getLongitude());
                LatLng latLngBefore = new LatLng(mLocationBefore.getLatitude(), mLocationBefore.getLongitude());

                piPolyline = googleMap.addPolyline(new PolylineOptions().add(latLngBefore, latLngCurrent).width(15).color(Color.GREEN));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLngCurrent));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(19));


                jarak = jarak + mLocationBefore.distanceTo(location);
                avgSpeed = (avgSpeed + location.getSpeed()) / 2;

                txtTotalJarak.setText(String.valueOf(String.format("%.2f", jarak / 1000) + " KM"));
                txtKecepatan.setText(String.valueOf(String.format("%.2f", location.getSpeed()) + " M/S"));
                txtTotalEmisi.setText(String.valueOf(String.format("%.3f", 0.31 * (jarak / 1000)) + " KgCO2"));

                mLocationBefore = location;
            }
        }
    };

    /*
    public void tandaiTempat(){
        awal=false;
        LatLng asal = new LatLng(location.getLatitude(),location.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(asal).title("Start")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(latTujuan,logTujuan)).title("Finish")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(asal));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));

        mLocationBefore = mStartPosisition;
        cursor = googleMap.addMarker(new MarkerOptions().position(asal).title("Start")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.runningico)));

    }


                LatLng latLngCurrent = new LatLng(location.getLatitude(), location.getLongitude());
                LatLng latLngBefore = new LatLng(mLocationBefore.getLatitude(), mLocationBefore.getLongitude());

                piPolyline = googleMap.addPolyline(new PolylineOptions().add(latLngBefore, latLngCurrent).width(15).color(Color.GREEN));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLngCurrent));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));


                jarak = jarak + mLocationBefore.distanceTo(location);
                avgSpeed = (avgSpeed + location.getSpeed()) / 2;

                txtTotalJarak.setText(String.valueOf(String.format("%.2f", jarak / 1000) + " KM"));
                txtKecepatan.setText(String.valueOf(String.format("%.2f", location.getSpeed()) + " M/S"));
                txtTotalEmisi.setText(String.valueOf(String.format("%.3f", 0.31 * (jarak / 1000)) + " KgCO2"));

                cursor.setPosition(latLngCurrent);


                mLocationBefore = location;
*/
}
