package me.otmane.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.otmane.myapplication.R;
import me.otmane.myapplication.models.Intern;
import me.otmane.myapplication.repositories.InternRepository;

public class InternRecyclerViewAdapter extends RecyclerView.Adapter<InternRecyclerViewAdapter.InternRecyclerViewHolder> {
    Context context;

    public InternRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public InternRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.element_intern, parent, false);
        return new InternRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InternRecyclerViewHolder holder, int position) {
        Intern intern = InternRepository.getInternList().get(position);
        holder.populateData(intern);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class InternRecyclerViewHolder extends RecyclerView.ViewHolder {

        public InternRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void populateData(Intern intern) {
            ((TextView) itemView.findViewById(R.id.internName)).setText(intern.getFullName());
        }
    }
}
