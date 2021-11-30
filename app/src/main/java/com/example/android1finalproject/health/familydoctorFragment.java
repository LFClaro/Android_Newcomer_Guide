package com.example.android1finalproject.health;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.android1finalproject.R;
import com.example.android1finalproject.databinding.FragmentFamilyDoctorBinding;



public class familydoctorFragment extends Fragment {

    private FragmentFamilyDoctorBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFamilyDoctorBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView t = (TextView) view.findViewById(R.id.familydoctormessagelbl);
        t.setMovementMethod(LinkMovementMethod.getInstance());
        binding.familydoctorback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(familydoctorFragment.this)
                        .navigate(R.id.action_ohipFragment_to_FirstFragmentHealth);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}