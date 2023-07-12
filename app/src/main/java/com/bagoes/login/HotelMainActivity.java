package com.bagoes.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class HotelMainActivity extends AppCompatActivity
{
    private ImageView _imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_main);

        _imageView =findViewById(R.id.imageView1);

        String imageUrl = "https://blog.airpaz.com/wp-content/uploads/alila-seminyak-panoramic-1.png";
        Picasso.with(this).load(imageUrl).into(_imageView);
    }
}