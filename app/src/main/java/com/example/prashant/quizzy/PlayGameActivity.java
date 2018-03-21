package com.example.prashant.quizzy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;


import stanford.androidlib.AutoSaveFields;

@AutoSaveFields
public class PlayGameActivity extends AppCompatActivity {

    private LinkedHashMap<String, ArrayList<String>> optionsMap = new LinkedHashMap<>();

    private LinkedHashMap<String, String> keyValueMapBollywood = new LinkedHashMap<>();
    private ArrayList<String> allKeysBollywood = new ArrayList<>();

    private LinkedHashMap<String, String> keyValueMapGeography = new LinkedHashMap<>();
    private ArrayList<String> allKeysGeography = new ArrayList<>();

    private LinkedHashMap<String, String> keyValueMapSports_cricket = new LinkedHashMap<>();
    private ArrayList<String> allKeysSports_cricket = new ArrayList<>();

    private LinkedHashMap<String, String> keyValueMapSports_football = new LinkedHashMap<>();
    private ArrayList<String> allKeysSports_football = new ArrayList<>();

    private LinkedHashMap<String, String> keyValueMapHistory = new LinkedHashMap<>();
    private ArrayList<String> allKeysHistory = new ArrayList<>();

    private LinkedHashMap<String, String> keyValueMapPolity = new LinkedHashMap<>();
    private ArrayList<String> allKeysPolity = new ArrayList<>();

    private int countOfQuestions = 0;
    private int points = 0;
    private String originalDefn;

    ArrayList<String> checkedInterests;
    ArrayList<String> allFiles = new ArrayList<>();

    private Integer totalNoOfQuestions = 0;
    HashMap<String, Boolean> uniqueQuestionsCheckMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        countOfQuestions = 0;
        points = 0;
        totalNoOfQuestions = 0;

        allFiles.add("Bollywood");
        allFiles.add("Geography");
        allFiles.add("Cricket");
        allFiles.add("Football");
        allFiles.add("History");
        allFiles.add("Polity");

        Bundle bundle = getIntent().getExtras();
        checkedInterests = bundle.getStringArrayList("checked_interests");
        storeInfo(checkedInterests);
        getNewCategoryQuestionAndOptions();
    }

    public void correctMessage() {
        Toast toast = Toast.makeText(this, "Yes this is correct!!", Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.GREEN);
        v.setBackgroundColor(Color.parseColor("#ffffff"));
        toast.show();
    }

    public void wrongMessage() {
        Toast toast = Toast.makeText(this, "Correct answer was: " + originalDefn, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.RED);
        v.setBackgroundColor(Color.parseColor("#ffffff"));
        toast.show();
    }

    public void getNewCategoryQuestionAndOptions() {

        if (checkedInterests == null) {
            Collections.shuffle(allFiles);
            String field = allFiles.get(0);

            if ("Bollywood".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysBollywood, optionsMap, field);
            }
            else if ("Geography".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysGeography, optionsMap, field);
            }
            else if ("Cricket".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysSports_cricket, optionsMap, field);
            }
            else if ("Football".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysSports_football, optionsMap, field);
            }
            else if ("History".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysHistory, optionsMap, field);
            }
            else if ("Polity".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysPolity, optionsMap, field);
            }

        } else {
            Collections.shuffle(checkedInterests);
            String field = checkedInterests.get(0);

            if ("Bollywood".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysBollywood, optionsMap, field);
            }
            else if ("Geography".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysGeography, optionsMap, field);
            }
            else if ("Cricket".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysSports_cricket, optionsMap, field);
            }
            else if ("Football".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysSports_football, optionsMap, field);
            }
            else if ("History".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysHistory, optionsMap, field);
            }
            else if ("Polity".equalsIgnoreCase(field)){
                getNewquestionAndOptions(allKeysPolity, optionsMap, field);
            }


        }
    }

    // gets a new question and random 4 options
    public void getNewquestionAndOptions(ArrayList<String> keys, LinkedHashMap<String, ArrayList<String>> questionOptionsMap, String field) {

        Random randy = new Random();
        int random_number = randy.nextInt(keys.size());
        String random_word = keys.get(random_number);
        boolean questionRemaining = false;

        for (String key : keys) {
            if (uniqueQuestionsCheckMap.get(key) == false) {
                questionRemaining = true;
                break;
            }
        }

        if (questionRemaining) {
            while (uniqueQuestionsCheckMap.get(random_word)) {
                //getNewCategoryQuestionAndOptions();
                randy = new Random();
                random_number = randy.nextInt(keys.size());
                random_word = keys.get(random_number);
            }
        } else {
            if(checkedInterests != null && !checkedInterests.isEmpty())
            checkedInterests.remove(field);
            allFiles.remove(field);
            getNewCategoryQuestionAndOptions();
        }


        uniqueQuestionsCheckMap.put(random_word, true);
        TextView word = (TextView) findViewById(R.id.question_id);
        word.setText(random_word);

        TextView points_word = (TextView) findViewById(R.id.points_id);
        points_word.setText("Points : " + points);


        ArrayList<String> correspondingOptions = new ArrayList<>(questionOptionsMap.get(word.getText().toString()));
        originalDefn = correspondingOptions.get(0);

        // pick the shuffled options
        Collections.shuffle(correspondingOptions);

        ListView listView = (ListView) findViewById(R.id.options_id);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(correspondingOptions));
        listView.setAdapter(adapter);
        clickTheOption(listView);
    }


    // on click of listview option
    public void clickTheOption(ListView listView) {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String defn = adapterView.getItemAtPosition(i).toString();
                if (defn.equalsIgnoreCase(originalDefn)) {
                    correctMessage();
                    points++;
                } else {
                    wrongMessage();
                    points--;
                }
                getLimits();
            }
        });
    }

    public void getLimits() {

        countOfQuestions++;
        if (countOfQuestions == totalNoOfQuestions) {

            Intent intent = new Intent(this, FinishActivity.class);
            intent.putExtra("points", points);
            intent.putExtra("total_marks", totalNoOfQuestions);
            startActivity(intent);
        } else {
            getNewCategoryQuestionAndOptions();
        }
    }

    // store info in map
    public void storeInfo(ArrayList<String> checkedInterests) {

        if (checkedInterests == null) {
            Scanner file = new Scanner(getResources().openRawResource(R.raw.bollywood));
            fileReadHelperBollywood(file);

            Scanner fileGeography = new Scanner(getResources().openRawResource(R.raw.geography));
            fileReadHelperfileGeography(fileGeography);

            Scanner fileSports_cricket = new Scanner(getResources().openRawResource(R.raw.sports_cricket));
            fileReadHelperSportsCricket(fileSports_cricket);

            Scanner fileSports_football = new Scanner(getResources().openRawResource(R.raw.sports_football));
            fileReadHelperSportsFootball(fileSports_football);

            Scanner fileHistory = new Scanner(getResources().openRawResource(R.raw.history));
            fileReadHelperHistory(fileHistory);

            Scanner filePolity = new Scanner(getResources().openRawResource(R.raw.indianpolity));
            fileReadHelperPolity(filePolity);

        } else {

            for (String checkedInterest : checkedInterests) {

                if ("Bollywood".equalsIgnoreCase(checkedInterest)) {
                    Scanner file = new Scanner(getResources().openRawResource(R.raw.bollywood));
                    fileReadHelperBollywood(file);
                }
                if ("Geography".equalsIgnoreCase(checkedInterest)) {
                    Scanner fileGeography = new Scanner(getResources().openRawResource(R.raw.geography));
                    fileReadHelperfileGeography(fileGeography);
                }
                if ("Cricket".equalsIgnoreCase(checkedInterest)) {
                    Scanner fileSports_cricket = new Scanner(getResources().openRawResource(R.raw.sports_cricket));
                    fileReadHelperSportsCricket(fileSports_cricket);
                }
                if ("Football".equalsIgnoreCase(checkedInterest)) {
                    Scanner fileSports_football = new Scanner(getResources().openRawResource(R.raw.sports_football));
                    fileReadHelperSportsFootball(fileSports_football);
                }
                if ("History".equalsIgnoreCase(checkedInterest)) {
                    Scanner fileHistory = new Scanner(getResources().openRawResource(R.raw.history));
                    fileReadHelperHistory(fileHistory);
                }
                if ("Polity".equalsIgnoreCase(checkedInterest)) {
                    Scanner filePolity = new Scanner(getResources().openRawResource(R.raw.indianpolity));
                    fileReadHelperPolity(filePolity);
                }
            }
        }
    }

    public void fileReadHelperBollywood(Scanner file) {

        try {
            while (file.hasNextLine()) {

                String line = file.nextLine();
                String[] strToArr = line.split(",");
                if (strToArr.length < 5)
                    continue;
                totalNoOfQuestions++;
                keyValueMapBollywood.put(strToArr[0], strToArr[1]);
                allKeysBollywood.add(strToArr[0]);

                uniqueQuestionsCheckMap.put(strToArr[0], false);

                ArrayList<String> options = new ArrayList<>();
                for(int i=1;i<5;i++){
                    options.add(strToArr[i]);
                }
                optionsMap.put(strToArr[0], options);
            }
        } finally {
            file.close();
        }

    }

    public void fileReadHelperfileGeography(Scanner file) {

        try {
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] strToArr = line.split(",");
                if (strToArr.length < 5)
                    continue;
                totalNoOfQuestions++;
                keyValueMapGeography.put(strToArr[0], strToArr[1]);
                allKeysGeography.add(strToArr[0]);
                uniqueQuestionsCheckMap.put(strToArr[0], false);

                ArrayList<String> options = new ArrayList<>();
                for(int i=1;i<5;i++){
                    options.add(strToArr[i]);
                }
                optionsMap.put(strToArr[0], options);
            }
        } finally {
            file.close();
        }

    }

    public void fileReadHelperSportsCricket(Scanner file) {
        try {
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] strToArr = line.split(",");
                if (strToArr.length < 5)
                    continue;
                totalNoOfQuestions++;
                keyValueMapSports_cricket.put(strToArr[0], strToArr[1]);
                allKeysSports_cricket.add(strToArr[0]);
                uniqueQuestionsCheckMap.put(strToArr[0], false);

                ArrayList<String> options = new ArrayList<>();
                for(int i=1;i<5;i++){
                    options.add(strToArr[i]);
                }
                optionsMap.put(strToArr[0], options);
            }
        } finally {
            file.close();
        }
    }

    public void fileReadHelperSportsFootball(Scanner file) {

        try {
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] strToArr = line.split(",");
                if (strToArr.length < 5)
                    continue;
                totalNoOfQuestions++;
                keyValueMapSports_football.put(strToArr[0], strToArr[1]);
                allKeysSports_football.add(strToArr[0]);
                uniqueQuestionsCheckMap.put(strToArr[0], false);

                ArrayList<String> options = new ArrayList<>();
                for(int i=1;i<5;i++){
                    options.add(strToArr[i]);
                }
                optionsMap.put(strToArr[0], options);
            }
        } finally {
            file.close();
        }
    }

    public void fileReadHelperHistory(Scanner file) {

        try {
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] strToArr = line.split(",");
                if (strToArr.length < 5)
                    continue;
                totalNoOfQuestions++;
                keyValueMapHistory.put(strToArr[0], strToArr[1]);
                allKeysHistory.add(strToArr[0]);
                uniqueQuestionsCheckMap.put(strToArr[0], false);

                ArrayList<String> options = new ArrayList<>();
                for(int i=1;i<5;i++){
                    options.add(strToArr[i]);
                }
                optionsMap.put(strToArr[0], options);
            }
        } finally {
            file.close();
        }

    }

    public void fileReadHelperPolity(Scanner file) {

        try {
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] strToArr = line.split(",");
                if (strToArr.length < 5)
                    continue;
                totalNoOfQuestions++;
                keyValueMapPolity.put(strToArr[0], strToArr[1]);
                allKeysPolity.add(strToArr[0]);
                uniqueQuestionsCheckMap.put(strToArr[0], false);

                ArrayList<String> options = new ArrayList<>();
                for(int i=1;i<5;i++){
                    options.add(strToArr[i]);
                }
                optionsMap.put(strToArr[0], options);
            }
        } finally {
            file.close();
        }

    }

}

