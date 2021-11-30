package com.example.android1finalproject.health;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.android1finalproject.R;
import com.example.android1finalproject.databinding.FragmentSecondHealthBinding;


public class SecondFragmentHealth extends Fragment {

    private FragmentSecondHealthBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondHealthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.OHIPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragmentHealth.this)
                        .navigate(R.id.action_SecondFragmentHealth_to_ohipFragment);
            }
        });


        //Action for Family Doctor button
        binding.familyDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragmentHealth.this)
                        .navigate(R.id.action_SecondFragmentHealth_to_familydoctorFragment);
            }
        });
        //Action for mental Health Buttton
        binding.mentalHealthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragmentHealth.this)
                        .navigate(R.id.action_SecondFragmentHealth_to_MentalHealthFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}