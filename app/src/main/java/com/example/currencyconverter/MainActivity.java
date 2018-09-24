package com.example.currencyconverter;

/**
 * Created by Lam Lieu on 9/23/2018.
 */

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
    private Spinner spinner1, spinner2;
    private TextView displayResult;
    private EditText convertAmount;
    private Button convertButton;

    // defines array adapter of string type
    private ArrayAdapter<String> adapter;

    private String fromCurrency = "";
    private String toCurrency = "";
    private double convertedAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gets currency-array from strings.xml
        Resources res = getResources();
        final String[] currencies = res.getStringArray(R.array.currency_array);
        final String[] spinner_dropdown = res.getStringArray(R.array.spinner_dropdown);

        // Sets spinners
        spinner1 = (Spinner) findViewById(R.id.fromSpinner);
        spinner2 = (Spinner) findViewById(R.id.toSpinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinner_dropdown);

        // Sets convert amount

        convertAmount = (EditText) findViewById(R.id.convertAmount);

        // Sets outputDisplay
        displayResult = (TextView) findViewById(R.id.outputDisplay);

        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromCurrency = currencies[spinner1.getSelectedItemPosition()];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toCurrency = currencies[spinner1.getSelectedItemPosition()];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        /*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AmdorenService service = retrofit.create(AmdorenService.class);

        Call<List<Currency>> call = service.getConvertedAmount(convertedAmount, fromCurrency, toCurrency, convertAmount.toString());

        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                List<Currency> currencies = response.body();
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                return;
            }
        });
        */
    }
}
