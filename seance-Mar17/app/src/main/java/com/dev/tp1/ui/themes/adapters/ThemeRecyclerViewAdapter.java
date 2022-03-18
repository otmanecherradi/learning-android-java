package com.dev.tp1.ui.themes.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.tp1.R;
import com.dev.tp1.databinding.ElementThemeItemBinding;
import com.dev.tp1.models.Theme;
import com.dev.tp1.ui.themes.DisplayThemeFragment;

import java.util.List;


public class ThemeRecyclerViewAdapter extends RecyclerView.Adapter<ThemeRecyclerViewAdapter.ViewHolder> {
    private final List<Theme> mThemes;
    private final NavController navController;

    public ThemeRecyclerViewAdapter(List<Theme> themes, NavController navController) {
        mThemes = themes;

        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(ElementThemeItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), navController);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        Theme theme = mThemes.get(position);

        viewHolder.populateData(theme);
        viewHolder.setClickListener(theme);
    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ElementThemeItemBinding binding;
        private final NavController navController;

        public ViewHolder(@NonNull ElementThemeItemBinding binding, NavController navController) {
            super(binding.getRoot());

            this.binding = binding;
            this.navController = navController;
        }

        void populateData(@NonNull Theme theme) {
            binding.itemName.setText(theme.getName());
        }

        void setClickListener(Theme theme) {
            binding.themeElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Bundle bundle = new Bundle();
                    bundle.putSerializable(DisplayThemeFragment.ARG_THEME_ID, theme.getCode());
                    navController.navigate(R.id.action_fragment_list_theme_to_fragment_display_theme, bundle);
                }
            });
        }
    }
}