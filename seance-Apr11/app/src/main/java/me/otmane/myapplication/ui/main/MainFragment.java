package me.otmane.myapplication.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import me.otmane.myapplication.R;
import me.otmane.myapplication.adapters.InternRecyclerViewAdapter;

public class MainFragment extends Fragment {
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        FloatingActionButton fab = view.findViewById(R.id.fab);

        recyclerView.setAdapter(new InternRecyclerViewAdapter(requireContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container_view, AddFragment.newInstance())
                        .commit();
            }
        });
    }
}