package com.example.junio.v1.Service;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by junio on 06/12/17.
 */

public class ServiceInsertCategory extends AsyncTask<String, Integer, Boolean> {
    private OnListenerInsertCategory mListener;

    public ServiceInsertCategory(OnListenerInsertCategory mListener) {
        this.mListener = mListener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            return insertCategory(params[0], params[1]);
        } catch (IOException e) {
            e.printStackTrace();
            mListener.onInsertCategoryError();
            return false;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean isSuccess) {
        super.onPostExecute(isSuccess);

        if (isSuccess) {
            mListener.onInsertCategorySuccess();
        } else {
            mListener.onInsertCategoryError();
        }
    }


    private Boolean insertCategory(String url, String payload) throws IOException {
        URL requestUrl = null;

        try {
            requestUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());

            os.write(payload.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            StringBuilder response = new StringBuilder();
            while ((output = br.readLine()) != null) {
                response.append(output);
                response.append('\r');
            }

            String body = response.toString();
            conn.disconnect();

//            if (!body.isEmpty()) {
//                return true;
//            } else {
//                return false;
//            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            mListener.onInsertCategoryError();
            return false;
        }
    }

    public interface OnListenerInsertCategory {
        void onInsertCategorySuccess();

        void onInsertCategoryError();
    }
}
