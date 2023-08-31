package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView img = (ImageView) findViewById(R.id.img_image);

        Intent intent = getIntent();

        if(intent != null){
            String image  = intent.getStringExtra("poster");
            Glide.with(this.getApplicationContext()).load(image).into(img);

        }



    }
}