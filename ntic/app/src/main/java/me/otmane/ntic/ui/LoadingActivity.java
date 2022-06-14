package me.otmane.ntic.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.databinding.ActivityLoadingBinding;
import me.otmane.ntic.ui.schools.SchoolsActivity;

public class LoadingActivity extends AppCompatActivity {
    public static final String TAG = "LoadingActivity";

    private ActivityLoadingBinding binding;

    private final int PERMISSION_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoadingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handlePermissions();
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void handlePermissions() {
        String[] permissions = new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };

        if (!hasPermissions(this, permissions)) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST);
        }

        navigate();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length == 3) {
                for (String permission : permissions) {
                    if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show();
            }

            navigate();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void navigate() {
        String accessToken = DataStore.getInstance().getAccessToken();

        if (accessToken == null) {
            startActivity(new Intent(LoadingActivity.this, SchoolsActivity.class));
        } else {
            startActivity(new Intent(LoadingActivity.this, MainActivity.class));
        }

        finish();
    }
}