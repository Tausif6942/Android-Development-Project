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

import com.example.first.Adapters.DescAdapter;
import com.example.first.Models.DescModel;
import com.example.first.Models.MainModel;
import com.example.first.databinding.ActivityDescBinding;
import com.example.first.databinding.DescActivityBinding;

import java.util.ArrayList;

public class DescActivity extends AppCompatActivity {
    DescActivityBinding binding;
    //ActivityDescBinding binding;
    ArrayList<DescModel> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DescActivityBinding.inflate(getLayoutInflater());
        //binding = ActivityDescBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int image=getIntent().getIntExtra("image", 0);
        String name=getIntent().getStringExtra("name");
        String description=getIntent().getStringExtra("description");
        //binding.imageView2.setImageResource(image);
        list.add(new DescModel(image, name, description));
        DescAdapter adapter = new DescAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

}