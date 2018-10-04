package com.example.currencyconverter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lam Lieu on 9/23/2018.
 */
public class Currency {
    @SerializedName("error")
    @Expose
    private int error;
    @SerializedName("error_message")
    @Expose
    private String error_message;
    @SerializedName("amount")
    @Expose
    private double amount;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
