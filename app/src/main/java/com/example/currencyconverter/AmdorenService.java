package com.example.currencyconverter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lam Lieu on 9/23/2018.
 */
public interface AmdorenService {
    @GET(UrlManager.latest)
    Call<Currency> getConvertedAmount(
            @Query("api_key") String api_key, @Query("fromCurrency") String fromCurrency, @Query("ToCurrency") String toCurrency, @Query("amount") String amount
    );
}

