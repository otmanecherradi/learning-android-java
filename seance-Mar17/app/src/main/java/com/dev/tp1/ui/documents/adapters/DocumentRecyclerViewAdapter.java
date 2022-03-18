package com.dev.tp1.ui.documents.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.tp1.R;
import com.dev.tp1.databinding.ElementDocumentItemBinding;
import com.dev.tp1.models.Document;
import com.dev.tp1.ui.documents.DisplayDocumentFragment;

import java.util.List;


public class DocumentRecyclerViewAdapter extends RecyclerView.Adapter<DocumentRecyclerViewAdapter.ViewHolder> {
    private final List<Document> mDocuments;
    private final NavController navController;

    public DocumentRecyclerViewAdapter(List<Document> documents, NavController navController) {
        mDocuments = documents;

        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ElementDocumentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), navController);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Document document = mDocuments.get(position);

        holder.populateData(document);
        holder.setListener(document);
    }

    @Override
    public int getItemCount() {
        return mDocuments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ElementDocumentItemBinding binding;
        private final NavController navController;

        public ViewHolder(@NonNull ElementDocumentItemBinding binding, NavController navController) {
            super(binding.getRoot());

            this.binding = binding;
            this.navController = navController;
        }

        void populateData(@NonNull Document document) {
            binding.itemDescription.setText(document.getDescription());
        }

        void setListener(Document document) {
            binding.documentElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Bundle bundle = new Bundle();
                    bundle.putSerializable(DisplayDocumentFragment.ARG_DOCUMENT_ID, document.getCode());
                    navController.navigate(R.id.action_fragment_list_document_to_fragment_edit_document, bundle);
                }
            });
        }
    }
}