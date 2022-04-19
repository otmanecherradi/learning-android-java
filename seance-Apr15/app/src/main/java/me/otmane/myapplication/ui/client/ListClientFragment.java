package me.otmane.myapplication.ui.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import me.otmane.myapplication.R;
import me.otmane.myapplication.adapters.ClientRecyclerViewAdapter;
import me.otmane.myapplication.databinding.ListClientsFragmentBinding;
import me.otmane.myapplication.repositories.ClientRepository;

public class ListClientFragment extends Fragment {
    private ListClientsFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = ListClientsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeRecyclerView();
        initializeEvents();
    }

    private void initializeRecyclerView() {
        binding.clientRV.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.clientRV.setAdapter(new ClientRecyclerViewAdapter(
                ClientRepository.getInstance(requireContext().getApplicationContext()).all(),
                NavHostFragment.findNavController(ListClientFragment.this)));
        binding.clientRV.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
    }

    private void initializeEvents() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ListClientFragment.this)
                        .navigate(R.id.action_ListClientFragment_to_EditClientFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}