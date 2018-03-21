package com.example.prashant.quizzy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity {

    boolean isBollywoodSelected = false;
    boolean isCricketSelected = false;
    boolean isFootballSelected = false;
    boolean isHistorySelected = false;
    boolean isGeographySelected = false;
    boolean isPolitySelected = false;
    private ArrayList<String> checked_interests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

    }

    public void selectInterest(View view) {

        int id = view.getId();

        if(id == R.id.bollywood_id){

            ImageButton ib = (ImageButton)findViewById(R.id.bollywood_id);

            if(isBollywoodSelected == false){
                checked_interests.add("Bollywood");
                isBollywoodSelected = true;
                ib.setImageResource(R.drawable.bollywoodfaded);
            }
            else{
                checked_interests.remove("Bollywood");
                isBollywoodSelected = false;
                ib.setImageResource(R.drawable.bollywood);
            }

        }
        else if(id == R.id.cricket_id){
            ImageButton ib = (ImageButton)findViewById(R.id.cricket_id);

            if(isCricketSelected == false){
                checked_interests.add("Cricket");
                isCricketSelected = true;
                ib.setImageResource(R.drawable.cricketfaded);
             }
            else{
                checked_interests.remove("Cricket");
                isCricketSelected = false;
                ib.setImageResource(R.drawable.cricket);
            }
        }
        else if(id == R.id.history_id){
            ImageButton ib = (ImageButton)findViewById(R.id.history_id);

            if(isHistorySelected == false){
                checked_interests.add("History");
                isHistorySelected = true;
                ib.setImageResource(R.drawable.historyfaded);
            }
            else{
                checked_interests.remove("History");
                isHistorySelected = false;
                ib.setImageResource(R.drawable.history);
            }
        }
        else if(id == R.id.polity_id){
            ImageButton ib = (ImageButton)findViewById(R.id.polity_id);

            if(isPolitySelected == false){
                checked_interests.add("Polity");
                isPolitySelected = true;
                ib.setImageResource(R.drawable.polityfaded);
            }
            else{
                checked_interests.remove("Polity");
                isPolitySelected = false;
                ib.setImageResource(R.drawable.polity);
            }
        }
        else if(id == R.id.geography_id){
            ImageButton ib = (ImageButton)findViewById(R.id.geography_id);

            if(isGeographySelected == false){
                checked_interests.add("Geography");
                isGeographySelected = true;
                ib.setImageResource(R.drawable.geographyfaded);
            }
            else{
                checked_interests.remove("Geography");
                isGeographySelected = false;
                ib.setImageResource(R.drawable.geography);
            }
        }
        else if(id == R.id.football_id){
            ImageButton ib = (ImageButton)findViewById(R.id.football_id);

            if(isFootballSelected == false){
                checked_interests.add("Football");
                isFootballSelected = true;
                ib.setImageResource(R.drawable.footballfaded);
             }
            else{
                checked_interests.remove("Football");
                isFootballSelected = false;
                ib.setImageResource(R.drawable.football);
            }

        }
    }

    public void startGame(View view) {

        if(checked_interests.isEmpty())
            checked_interests = null;

        Intent playActivityIntent = new Intent(this, PlayGameActivity.class);
        playActivityIntent.putStringArrayListExtra("checked_interests", checked_interests);
        startActivity(playActivityIntent);
    }
}
