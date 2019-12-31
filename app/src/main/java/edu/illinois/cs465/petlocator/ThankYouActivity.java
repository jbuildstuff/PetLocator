package edu.illinois.cs465.petlocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThankYouActivity extends Activity implements View.OnClickListener {
    private Button homeScreen = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        homeScreen = findViewById(R.id.homeScreen);
        homeScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.homeScreen){
            Intent myIntent = new Intent(this, MainActivity.class);
            myIntent.putExtra("PetRegistered", true);
            startActivity(myIntent);
        }
    }
}
