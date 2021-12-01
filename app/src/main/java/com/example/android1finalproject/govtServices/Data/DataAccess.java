package com.example.android1finalproject.govtServices.Data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class DataAccess  {
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

  JSONObject jsonObject;
  private DataAccess() throws JSONException {
    jsonObject = new JSONObject(jsonString);
  }

  private static DataAccess singleInstance = null;

  FirebaseFirestore db = FirebaseFirestore.getInstance();

  public static DataAccess getInstance() throws JSONException {
    if(singleInstance == null){
      singleInstance = new DataAccess();
    }
    return singleInstance;
  }
  public ServiceDetailsModel getServiceDetail(String serviceIdentifier) throws JSONException {
    JSONObject details = jsonObject.getJSONObject(serviceIdentifier);
    ServiceDetailsModel serviceDetailsModel = new ServiceDetailsModel();
    serviceDetailsModel.setServiceCenter(details.getString("serviceCenter"));
    serviceDetailsModel.setServiceName(details.getString("name"));
    List<String> documents = new ArrayList<>();
    JSONArray jsonArray = details.getJSONArray("documents");
    for(int i = 0 ; i < jsonArray.length(); i++){
      documents.add(jsonArray.get(i).toString());
    }
    serviceDetailsModel.setServiceCenter(details.getString("serviceCenter"));
    serviceDetailsModel.setDocuments(documents);
    addDatatoDatabase(details);
    return serviceDetailsModel;
  }

  private void addDatatoDatabase(JSONObject details) {
    db.collection("govtServices")
            .add(details)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
              @Override
              public void onSuccess(DocumentReference documentReference) {
                Log.d("documentSnapshotID", "DocumentSnapshot written with ID: " + documentReference.getId());
              }
            })
            .addOnFailureListener(new OnFailureListener() {
              @Override
              public void onFailure(@NonNull Exception e) {
                Log.w("ErrorDoc", "Error adding document", e);
              }
            });
  }
}