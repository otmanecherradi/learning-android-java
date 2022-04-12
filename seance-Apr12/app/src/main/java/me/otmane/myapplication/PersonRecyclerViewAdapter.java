package me.otmane.myapplication;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.otmane.myapplication.databinding.PersonRvElementBinding;

public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.PersonRecyclerViewHolder> {
    List<Person> list;

    public PersonRecyclerViewAdapter(List<Person> list) {
        super();

        this.list = list;
    }

    @NonNull
    @Override
    public PersonRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonRvElementBinding binding = PersonRvElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PersonRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonRecyclerViewHolder viewHolder, int position) {
        viewHolder.populateData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class PersonRecyclerViewHolder extends RecyclerView.ViewHolder {
        PersonRvElementBinding binding;

        public PersonRecyclerViewHolder(@NonNull PersonRvElementBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void populateData(Person person) {
            binding.fullName.setText(person.getFullName());
            binding.age.setText(String.valueOf(person.getAge()));
        }
    }
}
