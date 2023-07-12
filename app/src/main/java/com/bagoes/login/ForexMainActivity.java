package com.bagoes.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class ForexMainActivity extends AppCompatActivity
{
    private ProgressBar loadingProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView sarTextView, sgdTextView, krwTextView, rubTextView, jpyTextView, cnyTextView, gbpTextView, eurTextView, idrTextView, usdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forex_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout1);
        sarTextView = findViewById(R.id.sarTextView);
        sgdTextView = findViewById(R.id.sgdTextView);
        krwTextView = findViewById(R.id.krwTextView);
        rubTextView = findViewById(R.id.rubTextView);
        jpyTextView = findViewById(R.id.jpyTextView);
        cnyTextView = findViewById(R.id.cnyTextView);
        gbpTextView = findViewById(R.id.gpbTextView);
        eurTextView = findViewById(R.id.eurTextView);
        idrTextView = findViewById(R.id.idrTextView);
        usdTextView = findViewById(R.id.usdTextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);

        initSwipeRefreshLayout();
        initForex();
    }

    private void initSwipeRefreshLayout()
    {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            initForex();

            swipeRefreshLayout.setRefreshing(false);
        });
    }

    public String formatNumber(double number, String format)
    {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(number);
    }

    private void initForex()
    {
        loadingProgressBar.setVisibility(TextView.VISIBLE);

        String url = "https://openexchangerates.org/api/latest.json?app_id=6d6520a89623478c8b4081d0de22203c";

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                Gson gson = new Gson();
                ForexRootModel rootModel = gson.fromJson(new String(responseBody), ForexRootModel.class);
                ForexRatesModel ratesModel = rootModel.getRatesModel();

                double sar = ratesModel.getIDR() / ratesModel.getSAR();
                double sgd = ratesModel.getIDR() / ratesModel.getSGD();
                double krw = ratesModel.getIDR() / ratesModel.getKRW();
                double rub = ratesModel.getIDR() / ratesModel.getRUB();
                double jpy = ratesModel.getIDR() / ratesModel.getJPY();
                double cny = ratesModel.getIDR() / ratesModel.getCNY();
                double gbp = ratesModel.getIDR() / ratesModel.getGBP();
                double eur = ratesModel.getIDR() / ratesModel.getEUR();
                double idr = ratesModel.getIDR();
                double usd = ratesModel.getIDR() / ratesModel.getUSD();

                sarTextView.setText(formatNumber(sar, "###,##0.##"));
                sgdTextView.setText(formatNumber(sgd, "###,##0.##"));
                krwTextView.setText(formatNumber(krw, "###,##0.##"));
                rubTextView.setText(formatNumber(rub, "###,##0.##"));
                jpyTextView.setText(formatNumber(jpy, "###,##0.##"));
                cnyTextView.setText(formatNumber(cny, "###,##0.##"));
                gbpTextView.setText(formatNumber(gbp, "###,##0.##"));
                eurTextView.setText(formatNumber(eur, "###,##0.##"));
                idrTextView.setText(formatNumber(idr, "###,##0.##"));
                usdTextView.setText(formatNumber(usd, "###,##0.##"));

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }
        });
    }
}