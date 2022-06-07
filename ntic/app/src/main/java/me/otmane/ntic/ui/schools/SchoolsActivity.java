package me.otmane.ntic.ui.schools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collections;
import java.util.List;

import me.otmane.ntic.R;
import me.otmane.ntic.databinding.ActivitySchoolsBinding;
import me.otmane.ntic.databinding.ElementSchoolRvBinding;
import me.otmane.ntic.models.School;
import me.otmane.ntic.ui.login.LoginActivity;

public class SchoolsActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final String TAG = "SchoolsActivity";

    private ActivitySchoolsBinding binding;

    private SchoolsViewModel schoolsViewModel;
    private SchoolsRecyclerViewAdapter schoolsRecyclerViewAdapter;

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySchoolsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        schoolsViewModel = new ViewModelProvider(this).get(SchoolsViewModel.class);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        initRecyclerView();
        initListeners();

        schoolsViewModel.getSchools().observe(this, new Observer<List<School>>() {
            @Override
            public void onChanged(List<School> schools) {
                schoolsRecyclerViewAdapter.updateSchoolsList(schools);

                Log.d(TAG, "schoolsViewModel.getSchools " + schools.toString());

                for (School s : schools) {
                    LatLng schoolLatLng = new LatLng(s.getLat(), s.getLng());
                    googleMap.addMarker(new MarkerOptions().position(schoolLatLng).title(s.getName()));
                }

            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng tangier = new LatLng(35.76727, -5.79975);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tangier, 12.0f));
    }

    private void initRecyclerView() {
        schoolsRecyclerViewAdapter = new SchoolsRecyclerViewAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        binding.schoolsRV.setAdapter(schoolsRecyclerViewAdapter);
        binding.schoolsRV.setLayoutManager(layoutManager);
    }

    private void initListeners() {
        binding.goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SchoolsActivity.this, LoginActivity.class));
            }
        });
    }

    public static class SchoolsRecyclerViewAdapter
            extends RecyclerView.Adapter<SchoolsRecyclerViewAdapter.ViewHolder> {
        private final Activity activity;
        private List<School> mSchools;

        public SchoolsRecyclerViewAdapter(Activity activity) {
            this.activity = activity;

            this.mSchools = Collections.emptyList();
        }

        @SuppressLint("NotifyDataSetChanged")
        public void updateSchoolsList(final List<School> schools) {
            this.mSchools.clear();
            this.mSchools = schools;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public SchoolsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            ElementSchoolRvBinding binding = ElementSchoolRvBinding.inflate(
                    LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new ViewHolder(binding, activity);

        }

        @Override
        public void onBindViewHolder(final SchoolsRecyclerViewAdapter.ViewHolder holder, int position) {
            School s = mSchools.get(position);
            holder.populateData(s);
            holder.addListeners(s);
        }

        @Override
        public int getItemCount() {
            return mSchools.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            private final ElementSchoolRvBinding binding;
            private final Activity activity;

            public ViewHolder(@NonNull ElementSchoolRvBinding binding, Activity activity) {
                super(binding.getRoot());

                this.binding = binding;
                this.activity = activity;
            }

            public void populateData(School s) {
                binding.schoolName.setText(s.getName());
            }

            public void addListeners(School s) {
                binding.learnMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s.getUrl())));
                    }
                });
            }

        }
    }
}