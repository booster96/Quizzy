package com.example.prashant.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Intent intent = getIntent();
        Integer finalPoints = intent.getIntExtra("points", 0);
        Integer outOfPoints = intent.getIntExtra("total_marks", 0);

        TextView displayFinalPoints = (TextView) findViewById(R.id.final_score);
        displayFinalPoints.setText("Final Points: " + finalPoints + "/" + outOfPoints);
    }

    public void goToStartActivity(View view) {
        Intent intent = new Intent(this, SelectActivity.class);
        startActivity(intent);
    }
}
