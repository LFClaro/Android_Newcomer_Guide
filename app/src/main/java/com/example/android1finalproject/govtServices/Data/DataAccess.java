package com.example.android1finalproject.govtServices.Data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.android1finalproject.housing.models.House;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class DataAccess {
    String jsonString = "{\n" +
            "\t\"healthCard\": {\n" +
            "\t\t\"name\": \"Health Card Application\",\n" +
            "\t\t\"documents\": [\n" +
            "\t\t\t\"Proof of Canadian status\",\n" +
            "\t\t\t\"Proof of residency in Ontario\",\n" +
            "\t\t\t\"Proof of identity\"\n" +
            "\t\t],\n" +
            "\t\t\"serviceCenter\": \"Service Ontario\"\n" +
            "\t}\n" +
            "}";

    /* JSONObject jsonObject;*/
    private DataAccess() {
        //  jsonObject = new JSONObject(jsonString);
    }

    private static DataAccess singleInstance = null;

    public static DataAccess getInstance() {
        if (singleInstance == null) {
            singleInstance = new DataAccess();
        }
        return singleInstance;
    }

    private ServiceDetailsModel createDetailsModel(Map<String, Object> map) {
        ServiceDetailsModel serviceDetailsModel = new ServiceDetailsModel();
        serviceDetailsModel.setServiceCenter(map.get("serviceCenter").toString());
        serviceDetailsModel.setDocuments((List<String>) map.get("documents"));
        serviceDetailsModel.setServiceName(map.get("name").toString());

        return serviceDetailsModel;
    }


    public void fetchServiceDetails(String serviceId, Consumer<ServiceDetailsModel> consumer) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("govtservices").document(serviceId);
        Task<DocumentSnapshot> task = docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                ServiceDetailsModel model = createDetailsModel(document.getData());
                                consumer.accept(model);
                            } else {
                                Log.d("Firestore", "No such document");
                            }
                        } else {
                            Log.d("Firestore", "get failed with ", task.getException());
                        }
                    }
                });


    }
}