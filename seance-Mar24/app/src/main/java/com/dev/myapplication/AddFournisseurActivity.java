package com.dev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.myapplication.data.GestionFournisseurs;
import com.dev.myapplication.databinding.ActivityAddFournisseurBinding;
import com.dev.myapplication.models.Fournisseur;

public class AddFournisseurActivity extends AppCompatActivity {
    public static final String TAG = "AddFournisseurActivity";

    public static final String INTENT = "f";

    ActivityAddFournisseurBinding binding;

    Fournisseur fournisseur = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddFournisseurBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getExtras() != null) {
            fournisseur = (Fournisseur) getIntent().getExtras().getSerializable(INTENT);
        }


        populateData();
        setListeners();
    }

    private void populateData() {

        if (fournisseur != null) {
            binding.nameTxt.setText(fournisseur.getName());
            binding.addressTxt.setText(fournisseur.getAddress());
            binding.phoneTxt.setText(fournisseur.getPhone());

            binding.addBtn.setEnabled(false);
            binding.updateBtn.setEnabled(true);
        } else {
            binding.addBtn.setEnabled(true);
            binding.updateBtn.setEnabled(false);
        }

    }

    private void setListeners() {
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.nameTxt.getText())) {
                    showToast("Le nom est requis");
                    return;
                }

                if (TextUtils.isEmpty(binding.addressTxt.getText())) {
                    showToast("L'addresse est requis");
                    return;
                }

                if (TextUtils.isEmpty(binding.phoneTxt.getText())) {
                    showToast("Le numero de telephone est requis");
                    return;
                }

                if (GestionFournisseurs.search(String.valueOf(binding.nameTxt.getText())) != null) {
                    showToast("Le fournisseur existe deja");
                    return;
                }

                Fournisseur fournisseur = new Fournisseur(
                        String.valueOf(binding.nameTxt.getText()),
                        String.valueOf(binding.addressTxt.getText()),
                        String.valueOf(binding.phoneTxt.getText()));

                GestionFournisseurs.add(fournisseur);

                showToast("Added");
            }
        });

        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.nameTxt.getText())) {
                    showToast("Le nom est requis");
                    return;
                }

                if (GestionFournisseurs.search(String.valueOf(binding.nameTxt.getText())) == null) {
                    showToast("Le fournisseur n'existe pas");
                    return;
                }

                if (TextUtils.isEmpty(binding.addressTxt.getText())) {
                    showToast("L'addresse est requis");
                    return;
                }

                if (TextUtils.isEmpty(binding.phoneTxt.getText())) {
                    showToast("Le numero de telephone est requis");
                    return;
                }

                GestionFournisseurs.search(String.valueOf(binding.nameTxt.getText()))
                        .setAddress(String.valueOf(binding.addressTxt.getText()));

                GestionFournisseurs.search(String.valueOf(binding.nameTxt.getText()))
                        .setPhone(String.valueOf(binding.phoneTxt.getText()));

                showToast("Updated");


            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}