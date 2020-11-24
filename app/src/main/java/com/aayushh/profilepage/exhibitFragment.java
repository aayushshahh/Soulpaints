package com.aayushh.profilepage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link exhibitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class exhibitFragment extends Fragment implements LocationListener {
    private TextView locText;
    private Button nxtButton;
    private Button prevButton;
    private FusedLocationProviderClient locationProviderClient;
    private LocationRequest locationRequest;
    int flag = 1;

    private long UPDATE_INTERVAL = 10 * 1000;
    private long FASTEST_INTERVAL = 2000;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public exhibitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment exhibitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static exhibitFragment newInstance(String param1, String param2) {
        exhibitFragment fragment = new exhibitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        startLocationUpdates();
    }


    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        locationRequest.setInterval(UPDATE_INTERVAL);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(getActivity());
        settingsClient.checkLocationSettings(locationSettingsRequest);

        LocationServices.getFusedLocationProviderClient(getActivity()).requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Double latitude = locationResult.getLastLocation().getLatitude();
                Double longitude = locationResult.getLastLocation().getLongitude();
                String msg = "Location" + Double.toString(latitude) + " " + Double.toString(longitude);
                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG);
                Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                            try {
                                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                String locality = addresses.get(0).getLocality();
                                locText.setText(locality.toUpperCase());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
            }
        }, Looper.myLooper());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exhibit, container, false);
        locationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        locText = (TextView) v.findViewById(R.id.location);
        nxtButton = (Button) v.findViewById(R.id.nextButton);
        prevButton = (Button) v.findViewById(R.id.prevButton);

        final ImageView imageView1 = (ImageView) v.findViewById(R.id.exhibitImage);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductDetails.class);
                if (flag == 2) {
                    intent.putExtra("name", "ABSTRACT 3");
                } else if (flag == 3) {
                    intent.putExtra("name", "GOYU");
                } else if (flag == 4) {
                    intent.putExtra("name", "MODERN 1");
                } else {
                    intent.putExtra("name", "LANDSCAPE 1");
                }
                intent.putExtra("desc", "Description");
                intent.putExtra("price", "Rs 12000");
                startActivity(intent);
            }
        });
        nxtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    imageView1.setImageResource(R.drawable.abstract3);
                    flag = 2;
                } else if (flag == 2) {
                    imageView1.setImageResource(R.drawable.goyu);
                    flag = 3;
                } else if (flag == 3) {
                    imageView1.setImageResource(R.drawable.modern1);
                    flag = 4;
                } else {
                    imageView1.setImageResource(R.drawable.landscape1);
                    flag = 1;
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    imageView1.setImageResource(R.drawable.landscape1);
                    flag = 4;
                } else if (flag == 2) {
                    imageView1.setImageResource(R.drawable.abstract3);
                    flag = 1;
                } else if (flag == 3) {
                    imageView1.setImageResource(R.drawable.goyu);
                    flag = 2;
                } else {
                    imageView1.setImageResource(R.drawable.modern1);
                    flag = 3;
                }
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}