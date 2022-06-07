package me.otmane.ntic.ui.demands;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.databinding.FragmentAddDemandBinding;
import me.otmane.ntic.dtos.DemandsDTOs;
import me.otmane.ntic.repositories.DemandsRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDemandFragment extends Fragment {
    public static final String TAG = "AddDemandFragment";

    private FragmentAddDemandBinding binding;
    private DemandsViewModel demandsViewModel;

    private NavController navController;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAddDemandBinding.inflate(inflater, container, false);

        demandsViewModel = new ViewModelProvider(this).get(DemandsViewModel.class);

        navController = NavHostFragment.findNavController(AddDemandFragment.this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initTypeAutoComplete();
        initListeners();
    }

    private void initTypeAutoComplete() {
        binding.typeAC.setAdapter(new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"Marks", "Inscription"}));
    }

    private void initListeners() {
        binding.date.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis(MaterialDatePicker.todayInUtcMilliseconds());

                CalendarConstraints constraints = new CalendarConstraints.Builder()
                        .setOpenAt(MaterialDatePicker.todayInUtcMilliseconds())
                        .setStart(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();

                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder
                        .datePicker()
                        .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                        .setTitleText("Select date of birth")
                        .setCalendarConstraints(constraints)
                        .build();
                materialDatePicker.show(getChildFragmentManager(), "DATE_PICKER");

                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        String date = simpleDateFormat.format(selection);
                        binding.date.getEditText().setText(date);
                    }
                });
            }
        });
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.loadingProgress.setVisibility(View.VISIBLE);
                binding.submit.setVisibility(View.GONE);

                DemandsDTOs.AddDemandsRequestDTO addDemandsRequestDTO = new DemandsDTOs.AddDemandsRequestDTO();
                addDemandsRequestDTO.setAccessToken(DataStore.getInstance().getAccessToken());
                addDemandsRequestDTO.setType(binding.typeAC.getText().toString());
                addDemandsRequestDTO.setForDate(binding.date.getEditText().getText().toString());
                addDemandsRequestDTO.setNote(binding.note.getEditText().getText().toString());

                DemandsRepository.addDemand(addDemandsRequestDTO)
                        .enqueue(new Callback<Result<DemandsDTOs.AddDemandsResponseDTO>>() {
                            @Override
                            public void onResponse(@NonNull Call<Result<DemandsDTOs.AddDemandsResponseDTO>> call, @NonNull Response<Result<DemandsDTOs.AddDemandsResponseDTO>> response) {
                                if (!response.isSuccessful()) {
                                    binding.loadingProgress.setVisibility(View.GONE);
                                    binding.submit.setVisibility(View.VISIBLE);

                                    return;
                                }

                                showSnackBar("Done");

                                navController.navigateUp();
                            }

                            @Override
                            public void onFailure(@NonNull Call<Result<DemandsDTOs.AddDemandsResponseDTO>> call, @NonNull Throwable t) {
                                binding.loadingProgress.setVisibility(View.GONE);
                                binding.submit.setVisibility(View.VISIBLE);

                                showSnackBar("Error");
                            }
                        });
            }
        });
    }

    private void showSnackBar(String s) {
        Snackbar.make(requireView(), s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}