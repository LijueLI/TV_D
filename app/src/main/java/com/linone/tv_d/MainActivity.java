package com.linone.tv_d;

import android.graphics.Color;
import android.media.Image;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private Station1Json[] Station1data = null;
    private Station2Json[] Station2data = null;
    private Station3Json[] Station3data = null;
    private TextView Location1,VminMaxwind,XminAvgwind,temp,humi,updatedtime1;
    private TextView Dept1,updatedtime2;
    private TextView Dept2,Hourrain,DayRain,updatedtime3;
    private ImageView Warning;
    private TextView Status;
    private Uri uri;
    private Ringtone r;
    private int flag1 = 0,flag2 = 0,flag3 = 0;
    private boolean blink = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Location1 = findViewById(R.id.Location1_d);
        VminMaxwind = findViewById(R.id.VminMaxwind_d);
        XminAvgwind = findViewById(R.id.XminAVGwind_d);
        temp = findViewById(R.id.Temp_d);
        humi = findViewById(R.id.Humi_d);
        updatedtime1 = findViewById(R.id.IstUpdatetime_d);

        Dept1 = findViewById(R.id.Dept_d);
        updatedtime2 = findViewById(R.id.IIndUpdatetime_d);


        Dept2 = findViewById(R.id.Dept2_d);
        Hourrain = findViewById(R.id.hourrain_d);
        DayRain = findViewById(R.id.dayrain_d);
        updatedtime3 = findViewById(R.id.IIIrdUpdatetime_d);

        Warning = findViewById(R.id.Warning_img);

        Status = findViewById(R.id. Status);
        uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(MainActivity.this, uri);

        getFirstStation();
        getSecondStation();
        getThirdStation();
        warningimgblink();


    }

    private void getFirstStation(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Document document = Jsoup.connect("https://api.thingspeak.com/channels/759371/feeds.html?results=1&timezone=Asia%2FTaipei").get();
                        Elements elements = document.select("body");
                        String S = elements.text();
                        com.google.gson.JsonObject j = gson.fromJson(S, com.google.gson.JsonObject.class);
                        JsonArray K =j.get("feeds").getAsJsonArray();
                        Station1data = gson.fromJson(K,Station1Json[].class);
                    } catch (IOException e) {
                        Log.e("getdata()", "connecterror");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(Integer.valueOf(Station1data[0].getWarning())==1){
                                r.stop();
                                r.play();
                            }
                            else if(Integer.valueOf(Station1data[0].getWarning())==0 && flag2==0 && flag3 ==0){
                                r.stop();
                            }
                            flag1 = Integer.valueOf(Station1data[0].getWarning());

                            if(flag1 == 0 && flag2 == 0 && flag3 == 0){
                                Status.setTextColor(Color.BLACK);
                                Status.setText("目前狀態:安全");
                                blink = false;
                            }
                            else {
                                Status.setTextColor(Color.RED);
                                Status.setText("目前狀態:危險");
                                blink = true;
                            }

                            Location1.setText("工務所");
                            VminMaxwind.setText(Station1data[0].getVminMax()+"  m/s");
                            XminAvgwind.setText(Station1data[0].getXminAvg()+"  m/s");
                            temp.setText(Station1data[0].getTemp()+"  °C");
                            humi.setText(Station1data[0].getHumi()+"  %");
                            String[] s = Station1data[0].getCreated_at().split("T");
                            s[1] = s[1].split("\\+")[0];
                            updatedtime1.setText(s[0]+"  "+s[1]);
                        }
                    });
                    try{
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }

    private void getSecondStation(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Document document = Jsoup.connect("https://api.thingspeak.com/channels/759368/feeds.html?results=1&timezone=Asia%2FTaipei").get();
                        Elements elements = document.select("body");
                        String S = elements.text();
                        com.google.gson.JsonObject j = gson.fromJson(S, com.google.gson.JsonObject.class);
                        JsonArray K =j.get("feeds").getAsJsonArray();
                        Station2data = gson.fromJson(K,Station2Json[].class);
                    } catch (IOException e) {
                        Log.e("getdata()", "connecterror2");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(Integer.valueOf(Station2data[0].getWarning())==1){
                                r.stop();
                                r.play();
                            }
                            else if(Integer.valueOf(Station2data[0].getWarning())==0 && flag1==0 && flag3 ==0){
                                r.stop();
                            }
                            flag2 = Integer.valueOf(Station2data[0].getWarning());

                            if(flag1 == 0 && flag2 == 0 && flag3 == 0){
                                Status.setText("目前狀態:安全");
                                blink = false;
                            }
                            else {
                                Status.setTextColor(Color.RED);
                                Status.setText("目前狀態:危險");
                                blink = true;
                            }

                            Dept1.setText(Station2data[0].getWaterLevel()+"  m");
                            String[] s = Station2data[0].getCreated_at().split("T");
                            s[1] = s[1].split("\\+")[0];
                            updatedtime2.setText(s[0]+"  "+s[1]);
                        }
                    });
                    try{
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }

    private void getThirdStation(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Document document = Jsoup.connect("https://api.thingspeak.com/channels/699140/feeds.html?results=1&timezone=Asia%2FTaipei").get();
                        Elements elements = document.select("body");
                        String S = elements.text();
                        com.google.gson.JsonObject j = gson.fromJson(S, com.google.gson.JsonObject.class);
                        JsonArray K =j.get("feeds").getAsJsonArray();
                        Station3data = gson.fromJson(K,Station3Json[].class);
                    } catch (IOException e) {
                        Log.e("getdata()", "connecterror3");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(Integer.valueOf(Station3data[0].getWarning())==1){
                                r.stop();
                                r.play();
                            }
                            else if(Integer.valueOf(Station3data[0].getWarning())==0 && flag2==0 && flag1 ==0){
                                r.stop();
                            }
                            flag3 = Integer.valueOf(Station3data[0].getWarning());

                            if(flag1 == 0 && flag2 == 0 && flag3 == 0){
                                Status.setText("目前狀態:安全");
                                blink = false;
                            }
                            else {
                                Status.setTextColor(Color.RED);
                                Status.setText("目前狀態:危險");
                                blink = true;
                            }

                            Dept2.setText(Station3data[0].getWaterLevel()+"  m");
                            Hourrain.setText(Station3data[0].getRainfall_1hour()+"  mm");
                            DayRain.setText(Station3data[0].getRainfall_24hour()+"  mm");
                            String[] s = Station3data[0].getCreated_at().split("T");
                            s[1] = s[1].split("\\+")[0];
                            updatedtime3.setText(s[0]+"  "+s[1]);
                        }
                    });
                    try{
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }
    private void warningimgblink(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(blink) {
                                if (Warning.getVisibility() == View.INVISIBLE)
                                    Warning.setVisibility(View.VISIBLE);
                                else Warning.setVisibility(View.INVISIBLE);
                            }
                            else{
                                if (Warning.getVisibility() == View.VISIBLE)
                                    Warning.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                    try{
                        Thread.sleep(800);
                    }
                    catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }
}
