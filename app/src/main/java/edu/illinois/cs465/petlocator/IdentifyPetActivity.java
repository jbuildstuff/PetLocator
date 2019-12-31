package edu.illinois.cs465.petlocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IdentifyPetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_pet);
    }

    public void buttonOnClick(View view) {

        Intent intent = new Intent(this, ThankYouActivity.class);
        startActivity(intent);
    }
}
