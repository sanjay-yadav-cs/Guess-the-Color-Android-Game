package com.rjcollege.gusessthecolor;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.rjcollege.gusessthecolor.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static Random rn = new Random();
    static int level = 0;
    static int currentlevel = 1;
    static int score = 0;
    static int currentInput = 1;
    ActivityMainBinding binding;
    DatabaseHelper databaseHelper;
    ArrayList<MyColor> myColorArrayList;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.textView.setText(String.valueOf("Lavel " + currentlevel));
        databaseHelper = DatabaseHelper.getDB(this);



        databaseHelper.colorsDao().cleartable();
        databaseHelper.colorsDao().insertMyColor(new MyColor("Blue", "#0000FF"));
        databaseHelper.colorsDao().insertMyColor(new MyColor("Red", "#FF0000"));
        databaseHelper.colorsDao().insertMyColor(new MyColor("Yellow", "#FFFF00"));
        databaseHelper.colorsDao().insertMyColor(new MyColor("Orange", "#FF8C00"));
        databaseHelper.colorsDao().insertMyColor(new MyColor("Black", "#000000"));
        databaseHelper.colorsDao().insertMyColor(new MyColor("pink", "#FF1493"));
        databaseHelper.colorsDao().insertMyColor(new MyColor("White", "#FFFFFF"));
        databaseHelper.colorsDao().insertMyColor(new MyColor("green", "#00FF00"));
        databaseHelper.colorsDao().insertMyColor(new MyColor("brown", "#964B00"));
        databaseHelper.colorsDao().insertMyColor(new MyColor("purple", "#A020F0"));
        databaseHelper.colorsDao().insertMyColor(new MyColor("gray", "#808080"));
        databaseHelper.colorsDao().insertMyColor(new MyColor("skyblue", "#1E90FF"));



        myColorArrayList = (ArrayList<MyColor>) databaseHelper.colorsDao().getMyColors();


        level=rn.nextInt(myColorArrayList.size()-1) + 1;
        updatekeybord();
        NoOfInput();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.input1.setShowSoftInputOnFocus(false);
            binding.input2.setShowSoftInputOnFocus(false);
            binding.input3.setShowSoftInputOnFocus(false);
            binding.input4.setShowSoftInputOnFocus(false);
            binding.input5.setShowSoftInputOnFocus(false);
            binding.input6.setShowSoftInputOnFocus(false);
            binding.input7.setShowSoftInputOnFocus(false);
        }


        binding.hintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = myColorArrayList.get(level).getColorName();
                List<String> characters = Arrays.asList(str.split(""));
                Collections.shuffle(characters);
                String afterShuffle = "";
                for (String character : characters) {
                    afterShuffle += character + " ";
                }

                Toast.makeText(MainActivity.this, afterShuffle.toLowerCase(), Toast.LENGTH_LONG).show();


            }
        });


        binding.recyclerview.addOnItemTouchListener(new RecyclerItemClickListener(this, binding.recyclerview, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                setTextOnInput(list.get(position));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


    }


    public void setTextOnInput(String text) {
        switch (currentInput) {
            case 1:
                binding.input1.setText(text);
                binding.input2.requestFocus();
                currentInput++;
                break;


            case 2:
                binding.input2.setText("");
                binding.input2.setText(text);
                binding.input3.requestFocus();
                currentInput++;
                break;


            case 3:
                binding.input3.setText(text);
                binding.input4.requestFocus();
                currentInput++;
                break;


            case 4:
                binding.input4.setText(text);
                binding.input5.requestFocus();
                currentInput++;
                break;

            case 5:
                binding.input5.setText(text);
                binding.input6.requestFocus();
                currentInput++;
                break;

            case 6:
                binding.input6.setText(text);
                binding.input7.requestFocus();
                currentInput++;
                break;


            case 7:
                binding.input7.setText(text);
                currentInput++;
                break;

            default:
                break;
        }


        if (currentInput > databaseHelper.colorsDao().getMyColors().get(level).getColorName().length()) {
            String answer = binding.input1.getText().toString()
                    + binding.input2.getText().toString()
                    + binding.input3.getText().toString()
                    + binding.input4.getText().toString()
                    + binding.input5.getText().toString()
                    + binding.input6.getText().toString()
                    + binding.input7.getText().toString();

            if (answer.toLowerCase().contains(databaseHelper.colorsDao().getMyColors().get(level).getColorName().toLowerCase())) {


                if (level > myColorArrayList.size() - 2) {
                    level = 0;
                } else {

                    level = rn.nextInt(myColorArrayList.size()-1) + 1;
//                    level+=randomint;
//                    level++;
                    score++;
                    binding.textView2.setText(String.valueOf(score));
                    binding.textView.setText("Level " + String.valueOf(currentlevel++));
                    updatekeybord();
                    currentInput = 1;
                    binding.input1.setText("");
                    binding.input2.setText("");
                    binding.input3.setText("");
                    binding.input4.setText("");
                    binding.input5.setText("");
                    binding.input6.setText("");
                    binding.input7.setText("");
                    binding.input1.requestFocus();
                }
                NoOfInput();
            } else {
                Toast.makeText(this, "Name of Color Wrong Try Again", Toast.LENGTH_SHORT).show();
                currentInput = 1;
                binding.input1.setText("");
                binding.input2.setText("");
                binding.input3.setText("");
                binding.input4.setText("");
                binding.input5.setText("");
                binding.input6.setText("");
                binding.input7.setText("");
                binding.input1.requestFocus();
                if (score > 0) {
                    score--;
                    binding.textView2.setText(String.valueOf(score));
                }

            }


        }
    }


    public void NoOfInput() {
        binding.colorboard.setBackgroundColor(Color.parseColor(myColorArrayList.get(level).getColorCode()));

        switch (myColorArrayList.get(level).getColorName().length()) {
            case 6:
                binding.input1.setVisibility(View.VISIBLE);
                binding.input2.setVisibility(View.VISIBLE);
                binding.input3.setVisibility(View.VISIBLE);
                binding.input4.setVisibility(View.VISIBLE);
                binding.input5.setVisibility(View.VISIBLE);
                binding.input6.setVisibility(View.VISIBLE);
                binding.input7.setVisibility(View.GONE);
                break;
            case 5:
                binding.input1.setVisibility(View.VISIBLE);
                binding.input2.setVisibility(View.VISIBLE);
                binding.input3.setVisibility(View.VISIBLE);
                binding.input4.setVisibility(View.VISIBLE);
                binding.input5.setVisibility(View.VISIBLE);
                binding.input6.setVisibility(View.GONE);
                binding.input7.setVisibility(View.GONE);
                break;

            case 4:
                binding.input1.setVisibility(View.VISIBLE);
                binding.input2.setVisibility(View.VISIBLE);
                binding.input3.setVisibility(View.VISIBLE);
                binding.input4.setVisibility(View.VISIBLE);
                binding.input5.setVisibility(View.GONE);
                binding.input6.setVisibility(View.GONE);
                binding.input7.setVisibility(View.GONE);
                break;


            case 3:
                binding.input1.setVisibility(View.VISIBLE);
                binding.input2.setVisibility(View.VISIBLE);
                binding.input3.setVisibility(View.VISIBLE);
                binding.input4.setVisibility(View.GONE);
                binding.input5.setVisibility(View.GONE);
                binding.input6.setVisibility(View.GONE);
                binding.input7.setVisibility(View.GONE);
                break;

            default:
                binding.input1.setVisibility(View.VISIBLE);
                binding.input2.setVisibility(View.VISIBLE);
                binding.input3.setVisibility(View.VISIBLE);
                binding.input4.setVisibility(View.VISIBLE);
                binding.input5.setVisibility(View.VISIBLE);
                binding.input6.setVisibility(View.VISIBLE);
                binding.input7.setVisibility(View.VISIBLE);
        }


    }


    void updatekeybord() {

        list.clear();
        list.add("A".toUpperCase());
        list.add("B".toUpperCase());
        list.add("C".toUpperCase());
        list.add("D".toUpperCase());
        list.add("E".toUpperCase());
        list.add("F".toUpperCase());
        list.add("G".toUpperCase());
        list.add("H".toUpperCase());
        list.add("I".toUpperCase());
        list.add("J".toUpperCase());
        list.add("K".toUpperCase());
        list.add("M".toUpperCase());
        list.add("N".toUpperCase());


        String str = myColorArrayList.get(level).getColorName();
        List<String> characters = Arrays.asList(str.split(""));
        Collections.shuffle(characters);
        String afterShuffle = "";
        for (String character : characters) {
            list.add(character.toUpperCase());
        }

        Collections.shuffle(list);


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list, MainActivity.this);

        binding.recyclerview.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 5);
        binding.recyclerview.setLayoutManager(gridLayoutManager);


    }


}