package me.otmane.myapplication.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.otmane.myapplication.R;
import me.otmane.myapplication.databinding.ClientElementBinding;
import me.otmane.myapplication.models.Client;
import me.otmane.myapplication.ui.client.EditClientFragment;

public class ClientRecyclerViewAdapter extends RecyclerView.Adapter<ClientRecyclerViewAdapter.ClientViewHolder> {
    List<Client> clientList;
    NavController navController;

    public ClientRecyclerViewAdapter(List<Client> clientList, NavController navController) {
        this.clientList = clientList;
        this.navController = navController;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ClientElementBinding binding = ClientElementBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ClientViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        Client client = clientList.get(position);

        holder.populateData(client);
        holder.initEvents(client, navController);
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        ClientElementBinding binding;

        public ClientViewHolder(@NonNull ClientElementBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void populateData(Client client) {
            binding.name.setText(client.getName());
            binding.phone.setText(client.getPhone());
            binding.address.setText(client.getAddress());
        }

        public void initEvents(Client client, NavController navController) {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(EditClientFragment.CLIENT_ID, client.getCode());
                    navController.navigate(R.id.action_ListClientFragment_to_EditClientFragment, bundle);
                }
            });
        }
    }
}
