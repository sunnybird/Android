package com.example.jinlong.dailyartical.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.jinlong.dailyartical.R;
import com.example.jinlong.dailyartical.bean.Artical;
import com.example.jinlong.dailyartical.config.Config;
import com.example.jinlong.dailyartical.util.HTMLPraser;
import com.example.jinlong.dailyartical.util.Decrypt;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * 欢迎界面
 */
public class WelcomeActivity extends ActionBarActivity {

    private static final int SUCCESS_LOAD = 0;
    private static final int MAX_WAIT_TIME = 1000;
    //下载完成更新UI
    private Handler handler = new Handler() {
		 	
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == SUCCESS_LOAD) {
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, ArticalActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }
    };
    //下载文章保存到本地
    private Thread thread = new Thread(new Runnable() {

        private long statrtime = 0;
        private long endtime = 0;
        @Override
        public void run() {

            Artical artical = null;
            try {
                statrtime = System.currentTimeMillis();
                StringBuilder stringBuilder = getStringBuilderFromUrl(Config.URL_DAY);
                //downloal the artical form website
                artical = HTMLPraser.getArticalsByHtml(stringBuilder);
                //write the artical to localfile
                if (artical != null)
                    writeFile(artical);

                //send massage to handler
                Message msg = handler.obtainMessage();
                msg.what = SUCCESS_LOAD;
                endtime = System.currentTimeMillis();
                long sleeptime = (endtime-statrtime)<MAX_WAIT_TIME?MAX_WAIT_TIME:0;
                Thread.sleep(sleeptime);
                handler.sendMessage(msg);

            } catch (Exception e) {
                  Log.i("load",e.getMessage());
            }

        }

        private StringBuilder getStringBuilderFromUrl(String url)
                throws Exception {

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

    });
    
    private ImageView imageView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("WelcomeActivity","onCreate");
        setContentView(R.layout.activity_welcome);
        imageView = (ImageView) findViewById(R.id.welcome_imageview);
        initUI();
        thread.start();
    }

    /**
     * 保存文章到本地文件
     * @param artical
     */
    private void writeFile(Artical artical) {
        File cacheDir = this.getCacheDir();
        File acticalFile = new File(cacheDir, Config.LOCALFILENAME);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(acticalFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(artical);
            objectOutputStream.close();
            fileOutputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化界面图片
     */
    private void initUI()   {
        int index = new Random().nextInt(90);
        String filepath = "images/bg_"+index+".jpg";
        Log.i("path=", filepath);
        try {
            InputStream inputStream = getAssets().open(filepath);
            Log.i("inoutstream",inputStream.available()+"");
            byte[] bitmapByte = Decrypt.getInstance().decrypt(inputStream,"asdfghjkl",2);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapByte,0,bitmapByte.length);
            imageView.setImageBitmap(bitmap);
        }catch (Exception e){
           Log.e("err",e.getMessage());
        }
    }
}
