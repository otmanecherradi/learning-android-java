package me.otmane.ntic.ui.profile;

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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;

import me.otmane.ntic.DataStore;
import me.otmane.ntic.R;
import me.otmane.ntic.api.Result;
import me.otmane.ntic.databinding.FragmentProfileBinding;
import me.otmane.ntic.dtos.AuthDTOs;
import me.otmane.ntic.dtos.UsersDTOs;
import me.otmane.ntic.repositories.AuthRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";

    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;

    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        navController = NavHostFragment.findNavController(ProfileFragment.this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        populateData();
        initListeners();
    }

    private void populateData() {
        profileViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<UsersDTOs.MeResponseDTO>() {
            @Override
            public void onChanged(UsersDTOs.MeResponseDTO user) {
                Log.d(TAG, "onChanged: user => " + user);

                Glide.with(requireContext())
                        .load(user.getStudent().getAvatarURL())
                        .circleCrop()
                        .into(binding.avatar);

                binding.name.setText(user.getStudent().getFullName());

                binding.cin.getEditText().setText(user.getStudent().getCIN());
                binding.cne.getEditText().setText(user.getStudent().getCNE());
                binding.phone.getEditText().setText(user.getStudent().getPhone());
                binding.birthday.getEditText().setText(user.getStudent().getBirthday().toString());
                binding.bacMark.getEditText().setText(String.valueOf(user.getStudent().getBacMark()));
                binding.bacYear.getEditText().setText(String.valueOf(user.getStudent().getBacYear()));
                binding.bacSpecialty.getEditText().setText(user.getStudent().getBacSpecialty());
            }
        });
    }

    private void initListeners() {
        binding.colleagues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigationProfile_to_colleaguesFragment);
            }
        });
        
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthDTOs.LogoutRequestDTO logoutRequestDTO = new AuthDTOs.LogoutRequestDTO();
                logoutRequestDTO.setAccessToken(DataStore.getInstance().getAccessToken());

                AuthRepository.logout(logoutRequestDTO).enqueue(new Callback<Result<AuthDTOs.LogoutResponseDTO>>() {
                    @Override
                    public void onResponse(@NonNull Call<Result<AuthDTOs.LogoutResponseDTO>> call,
                                           @NonNull Response<Result<AuthDTOs.LogoutResponseDTO>> response) {
                        if (response.isSuccessful()) {
                            DataStore.getInstance().setAccessToken(null);
                            DataStore.getInstance().setRefreshToken(null);

                            navController.navigate(R.id.action_navigationProfile_to_loginActivity);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Result<AuthDTOs.LogoutResponseDTO>> call,
                                          @NonNull Throwable t) {

                    }
                });

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}