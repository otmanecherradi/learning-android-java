package com.dev.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dev.myapplication.databinding.ElementFournisseurBinding;
import com.dev.myapplication.models.Fournisseur;

import java.util.List;

public class FournisseursAdapter extends BaseAdapter {
    private Context context;
    private List<Fournisseur> fournisseurs;

    public FournisseursAdapter(Context context, List<Fournisseur> fournisseurs) {
        this.context = context;
        this.fournisseurs = fournisseurs;
    }

    @Override
    public int getCount() {
        return fournisseurs.size();
    }

    @Override
    public Object getItem(int i) {
        return fournisseurs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ElementFournisseurBinding binding;

        if (view == null) {
            binding = ElementFournisseurBinding.inflate(LayoutInflater.from(context));
        } else {
            binding = ElementFournisseurBinding.bind(view);
        }

        Fournisseur fournisseur = (Fournisseur) getItem(i);

        binding.frName.setText(fournisseur.getName());
        binding.frAddress.setText(fournisseur.getAddress());
        binding.frPhone.setText(fournisseur.getPhone());

        return binding.getRoot();
    }
}
