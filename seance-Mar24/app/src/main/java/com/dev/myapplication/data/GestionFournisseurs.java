package com.dev.myapplication.data;

import com.dev.myapplication.models.Fournisseur;

import java.util.ArrayList;

public class GestionFournisseurs {
    private static ArrayList<Fournisseur> fournisseurs = new ArrayList<>();

    public static ArrayList<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public static Fournisseur search(String name) {
        for (Fournisseur fournisseur : fournisseurs) {
            if (fournisseur.getName().equals(name)) {
                return fournisseur;
            }
        }

        return null;
    }

    public static void add(Fournisseur fournisseur) {
        fournisseurs.add(fournisseur);
    }
}
