package com.example.junio.v1.Service;


import android.os.AsyncTask;

import com.example.junio.v1.model.Category;
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

public class ServiceAllCategory extends AsyncTask<String, Integer, List<Category>> {
    private OnListenerGetAllCategory mListener;

    public ServiceAllCategory(OnListenerGetAllCategory mListener) {
        this.mListener = mListener;
    }

    @Override
    protected List<Category> doInBackground(String... params) {
        try {
            return getAllCategory(params[0]);
        } catch (IOException e) {
            e.printStackTrace();

            mListener.onGetAllCategoryError();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Category> categories) {
        super.onPostExecute(categories);

        mListener.onGetAllCategorySuccess(categories);
    }

    private List<Category> getAllCategory(String url) throws IOException {
        URL requestUrl = null;

        try {
            requestUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        List<Category> categories = null;
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

            categories = Arrays.asList(new Gson().fromJson(response.toString(), Category[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    public interface OnListenerGetAllCategory {
        void onGetAllCategorySuccess(List<Category> categories);
        void onGetAllCategoryError();
    }
}
