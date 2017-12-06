package com.example.junio.v1.Service;


import android.os.AsyncTask;

import com.example.junio.v1.model.Car;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by junio on 05/12/17.
 */

public class ServiceAllCar extends AsyncTask<String, Integer, List<Car>> {
    private OnListenerGetAllCars mListener;

    public ServiceAllCar(OnListenerGetAllCars mListener) {
        this.mListener = mListener;
    }

    @Override
    protected List<Car> doInBackground(String... params) {
        try {
            return getAllCars(params[0]);
        } catch (IOException e) {
            e.printStackTrace();

            mListener.onGetAllCarsError();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Car> cars) {
        super.onPostExecute(cars);

        mListener.onGetAllCarsSuccess(cars);
    }

    private List<Car> getAllCars(String url) throws IOException {
        URL requestUrl = null;

        try {
            requestUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        List<Car> cars = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));

            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            cars = Arrays.asList(new Gson().fromJson(response.toString(), Car[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cars;
    }

    public interface OnListenerGetAllCars {
        void onGetAllCarsSuccess(List<Car> cars);
        void onGetAllCarsError();
    }
}
