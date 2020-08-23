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
import java.math.BigDecimal;
import java.math.RoundingMode;


public class MainActivity extends AppCompatActivity {

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private Station1Json[] Station1data = null;
    private Station2Json[] Station2data = null;
    private Station3Json[] Station3data = null;
    private WarningValueJson[] limit = null;
    private TextView VminMaxwind,XminAvgwind,temp,humi,updatedtime1;
    private TextView Dept1,updatedtime2;
    private TextView Dept2,Hourrain,DayRain,updatedtime3;
    private ImageView Warning,Warning_y;
    private double Vminr = 100,Xminr = 100,riverw1 = 100,riverr1 = 100,hourrainr = 100,dayrainr = 100,riverw2 = 100,riverr2 = 100;
    private Uri uri;
    private Ringtone r;
    private int flag1 = 0,flag2 = 0,flag3 = 0;
    private boolean blink = false,blink_y = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Warning_y = findViewById(R.id.warning_img);

        uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(MainActivity.this, uri);

        getlimit();
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

                            if(Station1data[0].getVminMax()>Vminr) {
                                VminMaxwind.setTextColor(android.graphics.Color.RED);
                                flag1 = 2;
                                Log.d("debug",String.valueOf(Vminr));
                            }
                            else VminMaxwind.setTextColor(Color.GREEN);
                            if(Station1data[0].getXminAvg()>Xminr){
                                XminAvgwind.setTextColor(android.graphics.Color.RED);
                                flag1 = 2;
                                Log.d("debug","X");
                            }
                            else XminAvgwind.setTextColor(Color.GREEN);
                            if(Station1data[0].getVminMax()<Vminr && Station1data[0].getXminAvg()<Xminr) flag1 = 0;

                            VminMaxwind.setText(String.valueOf(Station1data[0].getVminMax()));
                            XminAvgwind.setText(String.valueOf(Station1data[0].getXminAvg()));

                            if(flag1>=1){
                                r.stop();
                                r.play();
                            }
                            else if(flag1==0 && flag2==0 && flag3 ==0){
                                r.stop();
                            }

                            if(flag1 == 0 && flag2 == 0 && flag3 == 0){
                                blink = false;
                                blink_y = false;
                            }
                            else if(flag1 == 2 || flag2 == 2 || flag3 == 2){
                                blink = true;
                                blink_y = false;
                            }
                            else if(flag1 == 1 || flag2 == 1 || flag3 == 1){
                                blink_y = true;
                            }

                            if(Station1data[0].getTemp()<28){
                                temp.setTextColor(Color.GREEN);
                                humi.setTextColor(Color.GREEN);
                            }
                            else if(Station1data[0].getTemp()>=28&&Station1data[0].getTemp()<29){
                                if(Station1data[0].getHumi()<90){
                                    temp.setTextColor(Color.GREEN);
                                    humi.setTextColor(Color.GREEN);
                                }
                                else{
                                    temp.setTextColor(Color.YELLOW);
                                    humi.setTextColor(Color.YELLOW);
                                }
                            }
                            else if(Station1data[0].getTemp()>=29&&Station1data[0].getTemp()<30){
                                if(Station1data[0].getHumi()<75){
                                    temp.setTextColor(Color.GREEN);
                                    humi.setTextColor(Color.GREEN);
                                }
                                else{
                                    temp.setTextColor(Color.YELLOW);
                                    humi.setTextColor(Color.YELLOW);
                                }
                            }
                            else if(Station1data[0].getTemp()>=30&&Station1data[0].getTemp()<31){
                                if(Station1data[0].getHumi()<90){
                                    temp.setTextColor(Color.YELLOW);
                                    humi.setTextColor(Color.YELLOW);
                                }
                                else{
                                    temp.setTextColor(Color.rgb(255,127,80));
                                    humi.setTextColor(Color.rgb(255,127,80));
                                }
                            }

                            else if(Station1data[0].getTemp()>=31&&Station1data[0].getTemp()<32){
                                if(Station1data[0].getHumi()<80){
                                    temp.setTextColor(Color.YELLOW);
                                    humi.setTextColor(Color.YELLOW);
                                }
                                else{
                                    temp.setTextColor(Color.rgb(255,127,80));
                                    humi.setTextColor(Color.rgb(255,127,80));
                                }
                            }

                            else if(Station1data[0].getTemp()>=32&&Station1data[0].getTemp()<33){
                                if(Station1data[0].getHumi()<70){
                                    temp.setTextColor(Color.YELLOW);
                                    humi.setTextColor(Color.YELLOW);
                                }
                                else{
                                    temp.setTextColor(Color.rgb(255,127,80));
                                    humi.setTextColor(Color.rgb(255,127,80));
                                }
                            }

                            else if(Station1data[0].getTemp()>=33&&Station1data[0].getTemp()<34){
                                if(Station1data[0].getHumi()<85){
                                    temp.setTextColor(Color.rgb(255,127,80));
                                    humi.setTextColor(Color.rgb(255,127,80));
                                }
                                else{
                                    temp.setTextColor(Color.RED);
                                    humi.setTextColor(Color.RED);
                                }
                            }

                            else if(Station1data[0].getTemp()>=34&&Station1data[0].getTemp()<36){
                                if(Station1data[0].getHumi()<80){
                                    temp.setTextColor(Color.rgb(255,127,80));
                                    humi.setTextColor(Color.rgb(255,127,80));
                                }
                                else{
                                    temp.setTextColor(Color.RED);
                                    humi.setTextColor(Color.RED);
                                }
                            }

                            else if(Station1data[0].getTemp()>=36&&Station1data[0].getTemp()<37){
                                if(Station1data[0].getHumi()<70){
                                    temp.setTextColor(Color.rgb(255,127,80));
                                    humi.setTextColor(Color.rgb(255,127,80));
                                }
                                else{
                                    temp.setTextColor(Color.RED);
                                    humi.setTextColor(Color.RED);
                                }
                            }

                            else if(Station1data[0].getTemp()>=37&&Station1data[0].getTemp()<38){
                                if(Station1data[0].getHumi()<65){
                                    temp.setTextColor(Color.rgb(255,127,80));
                                    humi.setTextColor(Color.rgb(255,127,80));
                                }
                                else{
                                    temp.setTextColor(Color.RED);
                                    humi.setTextColor(Color.RED);
                                }
                            }

                            else {
                                temp.setTextColor(Color.RED);
                                humi.setTextColor(Color.RED);
                            }


                            temp.setText(String.valueOf(Station1data[0].getTemp()));
                            humi.setText(String.valueOf(Station1data[0].getHumi()));

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
                            if(Station2data[0].getWaterLevel()>riverr2){
                                Dept1.setTextColor(android.graphics.Color.RED);
                                flag2 = 2;
                            }
                            else if(Station2data[0].getWaterLevel()>riverw2){
                                Dept1.setTextColor(Color.YELLOW);
                                flag2 = 1;
                            }
                            else{
                                Dept1.setTextColor(Color.GREEN);
                                flag2 = 0;
                            }
                            BigDecimal i = new BigDecimal(Station2data[0].getWaterLevel()*100);
                            i=i.setScale(0, RoundingMode.HALF_UP);
                            Dept1.setText(String.valueOf(i));

                            if(flag2>=1){
                                r.stop();
                                r.play();
                            }
                            else if(flag2==0 && flag1==0 && flag3 ==0){
                                r.stop();
                            }

                            if(flag1 == 0 && flag2 == 0 && flag3 == 0){
                                blink = false;
                                blink_y = false;
                            }
                            else if(flag1 == 2 || flag2 == 2 || flag3 == 2){
                                blink = true;
                                blink_y = false;
                            }
                            else if(flag1 == 1 || flag2 == 1 || flag3 == 1){
                                blink_y = true;
                            }

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

                            if(Station3data[0].getWaterLevel()>riverr1) {
                                Dept2.setTextColor(android.graphics.Color.RED);
                                flag3 = 2;
                            }
                            else if(Station3data[0].getWaterLevel()>riverw1) {
                                Dept2.setTextColor(Color.YELLOW);
                                flag3 = 1;
                            }
                            else {
                                Dept2.setTextColor(Color.GREEN);
                            }
                            BigDecimal i = new BigDecimal(Station3data[0].getWaterLevel()*100);
                            i=i.setScale(0, RoundingMode.HALF_UP);
                            Dept2.setText(String.valueOf(i));

                            if(Station3data[0].getRainfall_1hour()>hourrainr) {
                                Hourrain.setTextColor(android.graphics.Color.RED);
                                flag3 = 2;
                            }
                            else {
                                Hourrain.setTextColor(Color.GREEN);
                            }

                            DayRain.setText(String.valueOf(Station3data[0].getRainfall_24hour()));
                            if(Station3data[0].getRainfall_24hour()>dayrainr) {
                                DayRain.setTextColor(android.graphics.Color.RED);
                                flag3 = 2;
                            }
                            else {
                                DayRain.setTextColor(Color.GREEN);
                            }
                            Hourrain.setText(String.valueOf(Station3data[0].getRainfall_1hour()));

                            if(Station3data[0].getRainfall_24hour()<dayrainr&&Station3data[0].getWaterLevel()<riverr1&&Station3data[0].getRainfall_1hour()<hourrainr){
                                flag3 = 0;
                            }

                            if(flag3>=1){
                                r.stop();
                                r.play();
                            }
                            else if(flag3==0 && flag2==0 && flag1 ==0){
                                r.stop();
                            }

                            if(flag1 == 0 && flag2 == 0 && flag3 == 0){
                                blink = false;
                                blink_y = false;
                            }
                            else if(flag1 == 2 || flag2 == 2 || flag3 == 2){
                                blink = true;
                                blink_y = false;
                            }
                            else if(flag1 == 1 || flag2 == 1 || flag3 == 1){
                                blink_y = true;
                            }
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

    private void getlimit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Document document = Jsoup.connect("https://api.thingspeak.com/channels/759374/feeds.html?results=10&timezone=Asia%2FTaipei").get();
                        Elements elements = document.select("body");
                        String S = elements.text();
                        com.google.gson.JsonObject j = gson.fromJson(S, com.google.gson.JsonObject.class);
                        JsonArray K =j.get("feeds").getAsJsonArray();
                        limit = gson.fromJson(K,WarningValueJson[].class);
                    } catch (IOException e) {
                        Log.e("getdata()", "connecterror3");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            parceForData();
                            Log.d("getdata()", String.valueOf(Vminr));
                            Log.d("getdata()", String.valueOf(Xminr));
                            Log.d("getdata()", String.valueOf(riverr1));
                            Log.d("getdata()", String.valueOf(riverw1));
                            Log.d("getdata()", String.valueOf(hourrainr));
                            Log.d("getdata()", String.valueOf(dayrainr));
                            Log.d("getdata()", String.valueOf(riverr2));
                            Log.d("getdata()", String.valueOf(riverw2));
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

    private void parceForData(){
        for(int i=0;i<9;i++){
            if(limit[i].getVminr()!=0) Vminr = limit[i].getVminr();
            if(limit[i].getXminr()!=0) Xminr = limit[i].getXminr();
            if(limit[i].getRiverw1()!=0) riverw1 = limit[i].getRiverw1();
            if(limit[i].getRiverr1()!=0) riverr1 = limit[i].getRiverr1();
            if(limit[i].getHourrainr()!=0) hourrainr = limit[i].getHourrainr();
            if(limit[i].getDayrainr()!=0) dayrainr = limit[i].getDayrainr();
            if(limit[i].getRiverw2()!=0) riverw2 = limit[i].getRiverw2();
            if(limit[i].getRiverr2()!=0) riverr2 = limit[i].getRiverr2();
        }
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
                            else if(blink_y){
                                if (Warning_y.getVisibility() == View.INVISIBLE)
                                    Warning_y.setVisibility(View.VISIBLE);
                                else Warning_y.setVisibility(View.INVISIBLE);
                            }
                            else{
                                if (Warning.getVisibility() == View.VISIBLE)
                                    Warning.setVisibility(View.INVISIBLE);
                                if (Warning_y.getVisibility() == View.VISIBLE)
                                    Warning_y.setVisibility(View.INVISIBLE);
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
