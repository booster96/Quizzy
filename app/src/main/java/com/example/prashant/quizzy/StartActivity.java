package com.example.prashant.quizzy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import stanford.androidlib.AutoSaveFields;

@AutoSaveFields
public class StartActivity extends AppCompatActivity {

    private static final int ADD_WORD_REQ_ID = 1234;

    private ArrayList<String> checked_interests = new ArrayList<>();

    Button interest_button;
    TextView selected_interests;
    String[] interest_list;
    boolean[] checkedItems;
    String item = "";
    ArrayList<Integer> checked_interests_position = new ArrayList<>();
    //public  ArrayList<String> checked_interests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final View view = findViewById(R.id.start_icon_id);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        view.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {}

            public void onAnimationRepeat(Animation animation) {}

            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(StartActivity.this,SelectActivity.class);
                startActivity(intent);
            }
        });

    }

    // call PlayGameActivity
    public void playTheGame(View view) {

        if (item == "") {
            checked_interests = null;
        } else {
            checked_interests = new ArrayList<String>(Arrays.asList(item.split("\\s*,\\s*")));
        }


        Intent playActivityIntent = new Intent(this, PlayGameActivity.class);
        playActivityIntent.putStringArrayListExtra("checked_interests", checked_interests);
        startActivity(playActivityIntent);
    }

    public void selectInterest(View view) {
        Intent intent = new Intent(StartActivity.this,SelectActivity.class);
        startActivity(intent);
    }
}
