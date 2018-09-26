package com.example.currencyconverter;

/**
 * Created by Lam Lieu on 9/23/2018.
 */

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
    private Spinner spinner1, spinner2;
    private EditText firstCurrencyAmt;
    private TextView displayResult;
    private Button convertButton;

    // defines array adapter of string type
    private ArrayAdapter<String> adapter;

    private String fromCurrency = "";
    private String toCurrency = "";
    private String convertedCurrencyAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gets currency-array from strings.xml
        Resources res = getResources();
        final String[] currencies = res.getStringArray(R.array.currency_array);
        final String[] spinner_dropdown = res.getStringArray(R.array.spinner_dropdown);

        // Sets spinners and convert button
        spinner1 = (Spinner) findViewById(R.id.fromSpinner);
        spinner2 = (Spinner) findViewById(R.id.toSpinner);
        convertButton = (Button) findViewById(R.id.convertButton);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinner_dropdown);

        // Sets convert amount
        firstCurrencyAmt = (EditText) findViewById(R.id.toConvertAmt);

        // Sets outputDisplay
        displayResult = (TextView) findViewById(R.id.outputDisplay);

        // Sets currency string from strings.xml for first dropdown
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

        // Sets currency string from strings.xml for second dropdown
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toCurrency = currencies[spinner2.getSelectedItemPosition()];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AmdorenService service = retrofit.create(AmdorenService.class);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Currency>> call = service.getConvertedAmount(fromCurrency, toCurrency, firstCurrencyAmt.toString());
                /*
                call.enqueue(new Callback<List<Currency>>() {
                    @Override
                    public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                        if (response.isSuccessful()) {
                            List<Currency> currencies = response.body();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Currency>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                */
            }
        });
    }
}
