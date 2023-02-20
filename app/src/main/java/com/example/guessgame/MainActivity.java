package com.example.guessgame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Declare Variables
    private Button button;
//    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
//        Toolbar toolbar = findViewById(R.id.toolbar); // inflate your custom Toolbar
//        setSupportActionBar(toolbar); // set it as the action bar for the activity
        getSupportActionBar().hide();
        // get the button view and set an onClickListener
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextPage();
            }
        });
    }

    // method to go to the next page (GuessingGame activity)
    public void goToNextPage() {
        Intent intent = new Intent(this, GuessingGame.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
    }
}