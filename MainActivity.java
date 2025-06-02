package com.example.first;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.first.Adapters.MainAdapter;
import com.example.first.Models.MainModel;
import com.example.first.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DBase myDbHelper;
    ArrayList<MainModel> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myDbHelper = new DBase(this);

        // Example of inserting data
        if (myDbHelper.readAllData().getCount() == 0) {
            myDbHelper.insertAnimal("বিড়াল", "বিড়াল একটি গৃহপালিত প্রাণী। এদের শরীর নরম লোমে ঢাকা থাকে। এরা দক্ষ ইদুর শিকারী হয়ে থাকে।", R.drawable.cat);
            myDbHelper.insertAnimal("কুকুর", "কুকুর একটি গৃহপালিত প্রাণী। এদের ধারালো দাঁত হয় এবং এরা সাধারণত জীব বার করে রাখে।", R.drawable.dog);
            myDbHelper.insertAnimal("ঘোড়া", "ঘোড়া একটি যাত্রীবাহী প্রাণী। এরা খুব দ্রুত ছুটতে পারে ও যাত্রী বহন কাজে ব্যবহৃত হয়।", R.drawable.horse);
            myDbHelper.insertAnimal("গরু", "গরু একটি তৃণভোজী প্রাণী। এদের দুটি সিং হয় এবং এরা আমাদের দুধ প্রদান করে।", R.drawable.cow);
            myDbHelper.insertAnimal("খরগোশ", "খরগোশ একটি তৃণভোজী ছোটো প্রাণী। এদের লম্বা কান এবং লোমাবৃত শরীর হয়ে থাকে।", R.drawable.rabbit);
        }
        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);
        displayData();
        adapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    void displayData(){
        Cursor cursor = myDbHelper.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                list.add(new MainModel(cursor.getInt(3), cursor.getString(1), cursor.getString(2)));
            }

        }
    }
}
