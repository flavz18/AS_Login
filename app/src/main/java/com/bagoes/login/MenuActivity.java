package com.bagoes.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MenuActivity extends AppCompatActivity
{
    private Button _tampilMahasiswaButton, _forexRateButton, _cuacaButton;
    private Intent _tampilMahasiswaIntent, _forexRateIntent, _cuacaIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        _tampilMahasiswaButton = findViewById(R.id.mahasiswaButton);
        _forexRateButton = findViewById(R.id.forexRateButton);
        _cuacaButton = findViewById(R.id.cuacaButton);

        _tampilMahasiswaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttpClient asyncHttpClient;
                String url = "https://stmikpontianak.net/011100862/tampilMahasiswa.php";
                asyncHttpClient = new AsyncHttpClient();

                asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(getApplicationContext(),"Selamat Datang, di Menu Mahasiswa", Toast.LENGTH_SHORT).show();
                        _tampilMahasiswaIntent = new Intent(getApplicationContext(), TampilMahasiswaActivity.class);
                        startActivity(_tampilMahasiswaIntent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        _forexRateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttpClient asyncHttpClient;
                String url = "https://openexchangerates.org/api/latest.json?app_id=6d6520a89623478c8b4081d0de22203c";
                asyncHttpClient = new AsyncHttpClient();

                asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(getApplicationContext(),"Selamat Datang, di Menu Forex Rates", Toast.LENGTH_SHORT).show();
                        _forexRateIntent = new Intent(getApplicationContext(), ForexMainActivity.class);
                        startActivity(_forexRateIntent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        _cuacaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttpClient asyncHttpClient;
                String url = "https://api.openweathermap.org/data/2.5/forecast?id=1630789&appid=cbbe8ca59ab7baa2457f26ccb183666c";
                asyncHttpClient = new AsyncHttpClient();

                asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(getApplicationContext(),"Selamat Datang, di Menu Cuaca", Toast.LENGTH_SHORT).show();
                        _cuacaIntent = new Intent(getApplicationContext(), CuacaMainActivity.class);
                        startActivity(_cuacaIntent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}