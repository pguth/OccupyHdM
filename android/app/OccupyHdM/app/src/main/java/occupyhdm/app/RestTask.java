package occupyhdm.app;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

public class RestTask extends AsyncTask<String, Void, String> {
    Callable callback;

    public RestTask (Callable<Void> callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... urlStrings) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        String urlStr = urlStrings[0];
        try {
            url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("JSON", "Pre async " + urlStr);

        StringBuilder total = new StringBuilder();

        Log.d("JSON", "Fetching async");

        try {
            assert urlConnection != null;
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
        Log.d("JSON", "received callback " + string);
        try {
            callback.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}