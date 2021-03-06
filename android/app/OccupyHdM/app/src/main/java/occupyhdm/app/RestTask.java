package occupyhdm.app;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestTask extends AsyncTask<String, Void, String> {
    Callback<String> callback;

    public RestTask (Callback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... urlStrings) {
        String urlStr = urlStrings[0];
        URL url = null;
        HttpURLConnection urlConnection = null;
        StringBuilder total = new StringBuilder();
        JSONObject result;

        try {
            url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            // assert urlConnection != null;
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            assert urlConnection != null;
            urlConnection.disconnect();
        }
        return total.toString();
    }

    @Override
    protected void onPostExecute(String string) {
        try {
            callback.setResult(string);
            callback.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
