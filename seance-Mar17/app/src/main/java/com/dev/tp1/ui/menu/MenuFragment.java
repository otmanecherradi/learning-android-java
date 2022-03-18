package com.dev.tp1.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.dev.tp1.R;
import com.dev.tp1.databinding.FragmentMenuBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MenuFragment extends Fragment {
    public static final String TAG = "MenuFragment";

    private FragmentMenuBinding binding;
    private NavController navController;

    private final int GOTO_THEMES_FRAGMENT = R.id.action_fragment_menu_to_fragment_list_theme;
    // private final int GOTO_DOCUMENTS_FRAGMENT = R.id.action_FirstFragment_to_SecondFragment;

    private final List<HashMap<String, ?>> menuItems = Arrays.asList(
            new HashMap() {{
                put("icon", R.drawable.outline_folder_24);
                put("name", "Themes");
            }},
            new HashMap() {{
                put("icon", R.drawable.outline_description_24);
                put("name", "Documents");
            }}
    );

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater, container, false);

        navController = NavHostFragment.findNavController(MenuFragment.this);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeMenu();
        initializeMenuClickListeners();

     /*   binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/
    }

    private void initializeMenu() {
        final SimpleAdapter menuAdapter = new SimpleAdapter(requireContext(), menuItems,
                R.layout.element_menu_item, new String[]{"name", "icon"},
                new int[]{R.id.menu_text, R.id.menu_icon});

        binding.mainMenuListView.setAdapter(menuAdapter);
    }

    private void initializeMenuClickListeners() {
        binding.mainMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        navController.navigate(GOTO_THEMES_FRAGMENT);
                        break;
                    case 1:
//                        navController.navigate(GOTO_DOCUMENTS_FRAGMENT);
                        break;
                    default:
                        Snackbar.make(view, "Action is not defined", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}