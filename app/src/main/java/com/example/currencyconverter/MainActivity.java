package com.example.currencyconverter;

/**
 * Created by Lam Lieu on 9/23/2018.
 */

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
    private String convertedCurrencyAmt = null;

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
                double firstCurrencyAmtDouble = Double.parseDouble(firstCurrencyAmt.getText().toString());
                Call<Currency> call = service.getConvertedAmount("5Y79WmSsgV5zsnLjQJXgwJHDvBETgh", fromCurrency, toCurrency, firstCurrencyAmt.getText().toString());

                call.enqueue(new Callback<Currency>() {
                    @Override
                    public void onResponse(Call<Currency> call, Response<Currency> response) {
                        Currency currency = response.body();
                        Log.d("AnotherSingleView","Please check RESPONSE: "+response.body().toString());
                        String test = Double.toString(response.body().getAmount());
                        displayResult.setText(currency.getAmount());
                    }

                    @Override
                    public void onFailure(Call<Currency> call, Throwable t) {
                        Log.d("uhh", "onFailure: ");
                    }
                });
            }
        });
    }
}
