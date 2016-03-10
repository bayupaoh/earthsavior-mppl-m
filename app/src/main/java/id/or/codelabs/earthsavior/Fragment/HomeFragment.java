package id.or.codelabs.earthsavior.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import id.or.codelabs.earthsavior.Adapter.AdapterHome;
import id.or.codelabs.earthsavior.CalculateActivity;
import id.or.codelabs.earthsavior.Model.ModelHome;
import id.or.codelabs.earthsavior.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    View parentView;
    RecyclerView recyclerView;
    Context context;
    ArrayList<ModelHome> listHome = new ArrayList<>();
    FloatingActionButton fabMulai;
    GoogleApiClient mGoogleApiClient;
    int PLACE_PICKER_REQUEST = 1;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.fragment_home, container, false);
        setUpDataDummy();
        recyclerView = (RecyclerView) parentView.findViewById(R.id.recyclerview_home);

        mGoogleApiClient = new GoogleApiClient
                .Builder(parentView.getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();

        fabMulai = (FloatingActionButton) parentView.findViewById(R.id.fab_activity);

        fabMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        AdapterHome adapterHome = new AdapterHome(listHome,context);
        recyclerView.setAdapter(adapterHome);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return parentView;
    }

    private void setUpDataDummy() {
        listHome.add(new ModelHome("A1","Sabuga","27 Mei 2016",80));
        listHome.add(new ModelHome("A1","Sabuga","27 Mei 2016",80));
        listHome.add(new ModelHome("A1","Sabuga","27 Mei 2016",80));
        listHome.add(new ModelHome("A1","Sabuga","27 Mei 2016",80));
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(getContext(),data);
                LatLng koordinat = place.getLatLng();

                String toastMsg ="Place: "+ koordinat.latitude + " & "+koordinat.longitude;
                Snackbar.make(getView(),toastMsg,Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), CalculateActivity.class);
                startActivity(intent);
            }
        }
    }
}
