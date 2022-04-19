package me.otmane.myapplication.ui.client;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import me.otmane.myapplication.R;
import me.otmane.myapplication.databinding.EditClientFragmentBinding;
import me.otmane.myapplication.models.Client;
import me.otmane.myapplication.repositories.ClientRepository;

public class EditClientFragment extends Fragment {
    private EditClientFragmentBinding binding;

    public static String CLIENT_ID = "client_id";
    private Client client = null;

    public static EditClientFragment newInstance(int client_id) {
        EditClientFragment editClientFragment = new EditClientFragment();

        Bundle args = new Bundle();
        args.putInt(CLIENT_ID, client_id);
        editClientFragment.setArguments(args);

        return editClientFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            client = ClientRepository.getInstance(requireContext().getApplicationContext()).get(getArguments().getInt(CLIENT_ID, 0));
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = EditClientFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        populateData();
        initializeEvents();
    }

    private void populateData() {
        if (client != null) {
            binding.submit.setText("Update client");
            binding.delete.setVisibility(View.VISIBLE);
            Objects.requireNonNull(binding.name.getEditText()).setText(client.getName());
            Objects.requireNonNull(binding.phone.getEditText()).setText(client.getPhone());
            Objects.requireNonNull(binding.address.getEditText()).setText(client.getAddress());
        } else {
            binding.submit.setText("Add new");
            binding.delete.setVisibility(View.GONE);
        }
    }

    private void initializeEvents() {
        if (client == null) {
            binding.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ContentValues values = new ContentValues();
                    values.put(Client.NAME_FIELD_NAME, String.valueOf(Objects.requireNonNull(binding.name.getEditText()).getText()));
                    values.put(Client.PHONE_FIELD_NAME, String.valueOf(Objects.requireNonNull(binding.phone.getEditText()).getText()));
                    values.put(Client.ADDRESS_FIELD_NAME, String.valueOf(Objects.requireNonNull(binding.address.getEditText()).getText()));

                    if (ClientRepository.getInstance(requireContext().getApplicationContext()).insert(values)) {
                        Snackbar.make(requireView(), "Inserted", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(requireView(), "Not inserted", Snackbar.LENGTH_SHORT).show();
                    }

                    NavHostFragment.findNavController(EditClientFragment.this).navigate(R.id.action_EditClientFragment_to_ListClientFragment);
                }
            });
        } else {
            binding.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ContentValues values = new ContentValues();
                    values.put(Client.NAME_FIELD_NAME, String.valueOf(Objects.requireNonNull(binding.name.getEditText()).getText()));
                    values.put(Client.PHONE_FIELD_NAME, String.valueOf(Objects.requireNonNull(binding.phone.getEditText()).getText()));
                    values.put(Client.ADDRESS_FIELD_NAME, String.valueOf(Objects.requireNonNull(binding.address.getEditText()).getText()));

                    if (ClientRepository.getInstance(requireContext().getApplicationContext()).update(client.getCode(), values)) {
                        Snackbar.make(requireView(), "Updated", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(requireView(), "Not updated", Snackbar.LENGTH_SHORT).show();
                    }

                    NavHostFragment.findNavController(EditClientFragment.this).navigate(R.id.action_EditClientFragment_to_ListClientFragment);
                }
            });

            binding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ClientRepository.getInstance(requireContext().getApplicationContext()).delete(client.getCode())) {
                        Snackbar.make(requireView(), "Deleted", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(requireView(), "Not deleted", Snackbar.LENGTH_SHORT).show();
                    }

                    NavHostFragment.findNavController(EditClientFragment.this).navigate(R.id.action_EditClientFragment_to_ListClientFragment);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}