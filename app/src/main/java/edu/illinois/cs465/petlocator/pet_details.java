package edu.illinois.cs465.petlocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class pet_details extends Activity {

    private Button photoUploadButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);
        Float latitude = getIntent().getFloatExtra("LostPetLatitude",0);
        Float longitude = getIntent().getFloatExtra("LostPetLongitude",0);
       // TextView txt = findViewById(R.id.positionDetails);
       // txt.setText("Pet lost at latitude: "+latitude);


        photoUploadButton = (Button) findViewById(R.id.tothePhotoUpload);
        photoUploadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openUpload();
            }
        });


        Spinner mySpinner = (Spinner) findViewById(R.id.breedDropDown);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(pet_details.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.breedNames));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        Spinner secondSpinner = (Spinner) findViewById(R.id.temperamentDropDown);

        ArrayAdapter<String> secondAdapter = new ArrayAdapter<String>(pet_details.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.temperNames));
        secondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secondSpinner.setAdapter(secondAdapter);


    }
    public void openUpload(){
        Intent intent = new Intent(this, photoActivity.class);
        startActivity(intent);
    }
}