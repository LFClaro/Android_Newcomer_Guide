package com.example.android1finalproject.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.android1finalproject.R;
import com.example.android1finalproject.databinding.FragmentFirstHealthBinding;
import com.example.android1finalproject.housing.HousingActivity;
import com.example.android1finalproject.main.HomeActivity;
import com.example.android1finalproject.main.MainActivity;

public class FirstFragmentHealth extends Fragment {

    private FragmentFirstHealthBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstHealthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //firstButton was there before PUT as REMINDER
        binding.generalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragmentHealth.this)
                        .navigate(R.id.action_FirstFragmentHealth_to_SecondFragmentHealth);
            }
        });

        //Activity for hospital button

        binding.hospitalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragmentHealth.this)
                        .navigate(R.id.action_FirstFragmentHealth_to_HospitalFragment);
            }


        });
        //Activity for maps button

        binding.healthCentreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragmentHealth.this)
                        .navigate(R.id.action_FirstFragmentHealth_to_HealthMapsFragment);
            }


        });

        //button to go back to main page
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
             //  NavHostFragment.findNavController(FirstFragmentHealth.this)
             //           .navigate(R.id.action_FirstFragmentHealth_to_MainActivity);
            }


        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}