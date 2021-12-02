package com.example.android1finalproject.health;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.android1finalproject.R;
import com.example.android1finalproject.databinding.FragmentHospitalBinding;
import com.example.android1finalproject.housing.models.House;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class HospitalFragment extends Fragment {

    private FragmentHospitalBinding binding;
EditText FindET;
TextView displayTV;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
//inflate fragment
        binding = FragmentHospitalBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FindET=  view.findViewById(R.id.healthSearchTxt);
        FindET.setHint("Enter Hospital Name");
        displayTV=view.findViewById(R.id.healthdisplayTxt);
        binding.Find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //check if text is empty
                if (FindET.getText().toString().isEmpty()) {
                    FindET.setError("Required");
                }else{
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("hospitals")
                        .whereEqualTo("name", FindET.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("Firestore", document.getId() + " => " + document.getData());
                                        displayTV.setText("Name: " + document.getString("name") + "\nAddress: " + document.getString("address") + "\nNumber: " + document.getString("phone"));

                                    }

                                } else {

                                    Log.d("Firestore", "Error getting documents: ", task.getException());

                                }

                            }

                        });
            }//end of else
                displayTV.setText("Sorry, Cannot Find Hospital ");
            }
        });
    }//On view create

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}//Fragment Create View


