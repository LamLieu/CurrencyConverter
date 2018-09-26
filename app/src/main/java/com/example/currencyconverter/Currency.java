package com.example.currencyconverter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lam Lieu on 9/23/2018.
 */
public class Currency {
    @SerializedName("error")
    private int error;
    @SerializedName("error_message")
    private String error_message;
    @SerializedName("amount")
    private double amount;

    public Currency(int error, String error_message, double amount) {
        this.error = error;
        this.error_message = error_message;
        this.amount = amount;
    }

    public int getError() {
        return error;
    }

    public String getError_message() {
        return error_message;
    }

    public double getAmount() {
        return amount;
    }
}
