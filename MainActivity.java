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
        displayData();
        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    void displayData(){
        DBase helper = new DBase(this);
        Cursor cursor = helper.readAllData();
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