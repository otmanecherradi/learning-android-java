package me.otmane.ntic.ui.demands;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import me.otmane.ntic.R;
import me.otmane.ntic.databinding.ElementDoubleRvBinding;
import me.otmane.ntic.databinding.ElementSchoolRvBinding;
import me.otmane.ntic.databinding.FragmentDemandsBinding;
import me.otmane.ntic.models.Demand;

public class DemandsFragment extends Fragment {
    public static final String TAG = "DemandsFragment";

    private FragmentDemandsBinding binding;
    private DemandsViewModel demandsViewModel;
    private DemandsRecyclerViewAdapter demandsRecyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDemandsBinding.inflate(inflater, container, false);

        demandsViewModel = new ViewModelProvider(this).get(DemandsViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        initListeners();

        demandsViewModel.getDemands().observe(getViewLifecycleOwner(), new Observer<List<Demand>>() {
            @Override
            public void onChanged(List<Demand> demands) {
                Log.d(TAG, "onChanged: " + demands);
                demandsRecyclerViewAdapter.updateDemandsList(demands);
            }
        });
    }

    private void initRecyclerView() {
        demandsRecyclerViewAdapter = new DemandsRecyclerViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);

        binding.demandsRV.setAdapter(demandsRecyclerViewAdapter);
        binding.demandsRV.setLayoutManager(layoutManager);
    }

    private void initListeners() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigationDemands_to_navigationAddDemand);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class DemandsRecyclerViewAdapter
            extends RecyclerView.Adapter<DemandsRecyclerViewAdapter.ViewHolder> {
        private List<Demand> mDemands;

        public DemandsRecyclerViewAdapter() {
            this.mDemands = Collections.emptyList();
        }

        @SuppressLint("NotifyDataSetChanged")
        public void updateDemandsList(final List<Demand> demands) {
            this.mDemands.clear();
            this.mDemands = demands;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public DemandsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            ElementDoubleRvBinding binding = ElementDoubleRvBinding.inflate(
                    LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new DemandsRecyclerViewAdapter.ViewHolder(binding);

        }

        @Override
        public void onBindViewHolder(final DemandsRecyclerViewAdapter.ViewHolder holder, int position) {
            Demand d = mDemands.get(position);
            holder.populateData(d);
            holder.addListeners(d);
        }

        @Override
        public int getItemCount() {
            return mDemands.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            private final ElementDoubleRvBinding binding;

            public ViewHolder(@NonNull ElementDoubleRvBinding binding) {
                super(binding.getRoot());

                this.binding = binding;
            }

            public void populateData(Demand d) {
                binding.title.setText(d.getType());
                binding.description.setText(d.getNote());
            }

            public void addListeners(Demand d) {

            }

        }
    }
}