package com.example.jinlong.dailyartical.networktask;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jinlong.dailyartical.bean.Artical;
import com.example.jinlong.dailyartical.config.HTMLPraser;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.util.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

/**
 * Created by JinLong on 2015/2/8.
 */
public class HttpAsyncTask extends AsyncTask<String, Integer, Artical> {

    private TextView textViewTitle;
    private TextView textViewAuthor;
    private TextView textViewContent;
    private ImageView imageview;

    public HttpAsyncTask(TextView textViewTitle, TextView textViewAuthor,
                         TextView textViewContent, ImageView imageView) {

        this.textViewTitle = textViewTitle;
        this.textViewAuthor = textViewAuthor;
        this.textViewContent = textViewContent;
        this.imageview = imageView;

    }

    @Override
    protected Artical doInBackground(String... urls) {

        String url = urls[0];

        Artical artical = null;
        try {

            StringBuilder stringBuilder = getStringBuilderFromUrl(url);

            artical = HTMLPraser.getArticalsByHtml(stringBuilder);

        } catch (ParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return artical;

    }

    @Override
    protected void onPostExecute(Artical atical) {
        if (atical != null) {
            this.textViewTitle.setText(atical.getTitle().replace("<h1>", "").replace("</h1>", ""));
            this.textViewAuthor.setText(atical.getAuthor().replace("<span>", "").replace("</span>", ""));
            this.textViewContent.setText(atical.getContent().replace("<p>", "").replace("</p>", "\n"));

            this.imageview.setVisibility(View.VISIBLE);
            Log.i("setVisibility", "setVisibility");
        }
    }

    private StringBuilder getStringBuilderFromUrl(String url)
            throws ClientProtocolException, IOException {

        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(new HttpGet(url));
        InputStream input = response.getEntity().getContent();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(input));
        String tmp = "";
        while ((tmp = rd.readLine()) != null) {

            stringBuilder.append(tmp);
        }

        return stringBuilder;

    }

}
