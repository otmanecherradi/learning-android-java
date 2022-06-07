package me.otmane.ntic.ui.schedules;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import me.otmane.ntic.databinding.FragmentSchedulesBinding;
import me.otmane.ntic.models.Schedule;

public class SchedulesFragment extends Fragment {
    public static final String TAG = "SchedulesFragment";

    private FragmentSchedulesBinding binding;
    private SchedulesViewModel schedulesViewModel;

    private SchedulesAdapter schedulesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSchedulesBinding.inflate(inflater, container, false);

        schedulesViewModel = new ViewModelProvider(this).get(SchedulesViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initDropDownView();
        initListeners();

        schedulesViewModel.getSchedules().observe(getViewLifecycleOwner(), new Observer<List<Schedule>>() {
            @Override
            public void onChanged(List<Schedule> schedules) {
                schedulesAdapter.updateSchedulesList(schedules);
            }
        });
    }

    private void initDropDownView() {
        schedulesAdapter = new SchedulesAdapter(requireContext());
        binding.schedulesAC.setAdapter(schedulesAdapter);
    }

    private void initListeners() {
        binding.schedulesAC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Schedule schedule = (Schedule) adapterView.getItemAtPosition(i);

                Log.d(TAG, "onItemClick: "+schedule.getScheduleURL());
                Glide.with(requireContext())
                        .load(schedule.getScheduleURL())
                        .into(binding.scheduleImg);

                binding.scheduleImg.setVisibility(View.VISIBLE);
                binding.download.setVisibility(View.VISIBLE);
            }
        });

        binding.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FileOutputStream outStream = null;
//                File dataDirectory = Environment.getDataDirectory();
//                File dir = new File(dataDirectory.getAbsolutePath() + "/YourFolderName");
//                dir.mkdirs();
//                String fileName = String.format("%d.jpg", System.currentTimeMillis());
//                File outFile = new File(dir, fileName);
//                try {
//                    outStream = new FileOutputStream(outFile);
//                    binding.scheduleImg.getDrawable();
//                            btma.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
//                    outStream.flush();
//                    outStream.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }


            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class SchedulesAdapter extends ArrayAdapter<Schedule> {
        private List<Schedule> mSchedules;

        public SchedulesAdapter(@NonNull Context context) {
            super(context, android.R.layout.simple_dropdown_item_1line);

            mSchedules = Collections.emptyList();
        }

        @SuppressLint("NotifyDataSetChanged")
        public void updateSchedulesList(final List<Schedule> schedules) {
            this.mSchedules.clear();
            this.mSchedules = schedules;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mSchedules.size();
        }

        @Override
        public Schedule getItem(int i) {
            return mSchedules.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null)
                view = LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(android.R.layout.simple_dropdown_item_1line, viewGroup, false);

            Schedule schedule = getItem(i);

            Log.d(TAG, "getView: " + schedule);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            TextView textView = view.findViewById(android.R.id.text1);
            textView.setText(simpleDateFormat.format(schedule.getWeekStart()));

            return view;
        }
    }

}