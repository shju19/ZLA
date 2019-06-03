package com.zla.android;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.IOException;
import java.io.InputStream;

public class InformationActivity extends AppCompatActivity {

    //google analytics
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //google analytics
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("information screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        ImageView imageView = (ImageView) findViewById(R.id.informationImage);

        Intent intent = getIntent();					//인텐트 얻기
        String information = intent.getStringExtra("information");

        //Assets폴더의 이미지 가져오기
        AssetManager assetmanager = getResources().getAssets();
        Bitmap bitmap = null;
        try {
            //InputStream으로 사진 불러오며 초기값 설정을 해줌
            InputStream is = assetmanager.open("information/informationBicycle1.jpg", AssetManager.ACCESS_BUFFER);
            //받아오는 백과사전 정보에 맞춰 이미지를 저장
            switch(information) {
                case "informationBicycle1":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationBicycle1 button").setLabel("information").build());
                    is = assetmanager.open("information/informationBicycle1.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationBicycle2":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationBicycle2 button").setLabel("information").build());
                    is = assetmanager.open("information/informationBicycle2.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationBicycle3":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationBicycle3 button").setLabel("information").build());
                    is = assetmanager.open("information/informationBicycle3.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationBicycle4":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationBicycle4 button").setLabel("information").build());
                    is = assetmanager.open("information/informationBicycle4.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationBicycle5":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationBicycle5 button").setLabel("information").build());
                    is = assetmanager.open("information/informationBicycle5.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationConstructure":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationConstructure button").setLabel("information").build());
                    is = assetmanager.open("information/informationConstructure.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent1":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent1 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent1.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent2":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent2 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent2.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent3":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent3 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent3.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent4":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent4 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent4.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent5":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent5 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent5.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent6":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent6 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent6.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent7":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent7 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent7.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent8":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent8 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent8.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent9":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent9 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent9.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent10":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent10 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent10.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent11":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent11 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent11.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent12":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent12 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent12.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent13":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent13 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent13.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent14":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent14 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent14.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent15":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent15 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent15.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent16":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent16 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent16.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent17":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent17 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent17.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent18":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent18 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent18.jpg", AssetManager.ACCESS_BUFFER); break;
                case "informationComponent19":
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("information screen: informationComponent19 button").setLabel("information").build());
                    is = assetmanager.open("information/informationComponent19.jpg", AssetManager.ACCESS_BUFFER); break;
            }
            //이미지를 비트맵에 저장
            bitmap = BitmapFactory.decodeStream(is);
        } catch(Exception e) {
        }
        //이미지 노출
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        //액션바
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_activity_information, menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
