package com.bagoes.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class CuacaMainActivity extends AppCompatActivity
{
    private RecyclerView _recyclerView;
    private SwipeRefreshLayout _swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuaca_main);

        _recyclerView = findViewById(R.id.recyclerView1);
        _swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout1);

        initRecyclerView1();
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout()
    {
        _swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                initRecyclerView1();
                _swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initRecyclerView1()
    {
        String url = "https://api.openweathermap.org/data/2.5/forecast?id=1630789&appid=cbbe8ca59ab7baa2457f26ccb183666c";
        AsyncHttpClient ahc = new AsyncHttpClient();

        ahc.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                CuacaRootModel rm = gson.fromJson(new String(responseBody), CuacaRootModel.class);

                RecyclerView.LayoutManager lm = new LinearLayoutManager(CuacaMainActivity.this);
                CuacaAdapter ca = new CuacaAdapter(rm);

                _recyclerView.setLayoutManager(lm);
                _recyclerView.setAdapter(ca);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}