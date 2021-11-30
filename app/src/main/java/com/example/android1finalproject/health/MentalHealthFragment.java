package com.example.android1finalproject.health;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android1finalproject.R;
import com.example.android1finalproject.databinding.FragmentMentalHealthBinding;
import com.example.android1finalproject.databinding.FragmentOhipBinding;
public class MentalHealthFragment extends Fragment {
    private FragmentMentalHealthBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMentalHealthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView t = (TextView) view.findViewById(R.id.mentalhealthmessagelbl);
        t.setMovementMethod(LinkMovementMethod.getInstance());
        binding.familydoctorback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MentalHealthFragment.this)
                        .navigate(R.id.action_MentalHealthFragment_to_FirstFragmentHealth);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}