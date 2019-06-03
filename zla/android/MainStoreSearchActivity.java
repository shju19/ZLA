package com.zla.android;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainStoreSearchActivity extends AppCompatActivity {

    //google analytics
    private Tracker mTracker;

    TextView searchLocation;
    ToggleButton searchLocationAll, searchLocation1, searchLocation2, searchLocation3, searchLocation4, searchLocation5, searchLocation6;
    ToggleButton searchLocation7, searchLocation8, searchLocation9, searchLocation10, searchLocation11, searchLocation12, searchLocation13;
    ToggleButton searchLocation14, searchLocation15, searchLocation16, searchLocation17, searchLocation18, searchLocation19, searchLocation20;
    ToggleButton searchLocation21, searchLocation22, searchLocation23, searchLocation24, searchLocation25;

    String[] location = {"강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구",
            "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_store);

        //google analytics
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("store search screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        //리스트액티비티로부터 인텐트 얻기
        Intent intent = getIntent();
        for(int i=0; i < location.length; i++) {location[i] = intent.getStringExtra("location" + (i+1));Log.d("test2", location[i]);}

        //xml의 값과 연결
        searchLocation = (TextView) findViewById(R.id.searchLocation);
        searchLocationAll = (ToggleButton) findViewById(R.id.searchLocationAll);
        searchLocation1 = (ToggleButton) findViewById(R.id.searchLocation1);
        searchLocation2 = (ToggleButton) findViewById(R.id.searchLocation2);
        searchLocation3 = (ToggleButton) findViewById(R.id.searchLocation3);
        searchLocation4 = (ToggleButton) findViewById(R.id.searchLocation4);
        searchLocation5 = (ToggleButton) findViewById(R.id.searchLocation5);
        searchLocation6 = (ToggleButton) findViewById(R.id.searchLocation6);
        searchLocation7 = (ToggleButton) findViewById(R.id.searchLocation7);
        searchLocation8 = (ToggleButton) findViewById(R.id.searchLocation8);
        searchLocation9 = (ToggleButton) findViewById(R.id.searchLocation9);
        searchLocation10 = (ToggleButton) findViewById(R.id.searchLocation10);
        searchLocation11 = (ToggleButton) findViewById(R.id.searchLocation11);
        searchLocation12 = (ToggleButton) findViewById(R.id.searchLocation12);
        searchLocation13 = (ToggleButton) findViewById(R.id.searchLocation13);
        searchLocation14 = (ToggleButton) findViewById(R.id.searchLocation14);
        searchLocation15 = (ToggleButton) findViewById(R.id.searchLocation15);
        searchLocation16 = (ToggleButton) findViewById(R.id.searchLocation16);
        searchLocation17 = (ToggleButton) findViewById(R.id.searchLocation17);
        searchLocation18 = (ToggleButton) findViewById(R.id.searchLocation18);
        searchLocation19 = (ToggleButton) findViewById(R.id.searchLocation19);
        searchLocation20 = (ToggleButton) findViewById(R.id.searchLocation20);
        searchLocation21 = (ToggleButton) findViewById(R.id.searchLocation21);
        searchLocation22 = (ToggleButton) findViewById(R.id.searchLocation22);
        searchLocation23 = (ToggleButton) findViewById(R.id.searchLocation23);
        searchLocation24 = (ToggleButton) findViewById(R.id.searchLocation24);
        searchLocation25 = (ToggleButton) findViewById(R.id.searchLocation25);
        Button search = (Button) findViewById(R.id.search);

        //글꼴 설정
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/10X10.ttf");
        searchLocation.setTypeface(face); searchLocationAll.setTypeface(face); searchLocation1.setTypeface(face); searchLocation2.setTypeface(face);
        searchLocation3.setTypeface(face); searchLocation4.setTypeface(face); searchLocation5.setTypeface(face); searchLocation6.setTypeface(face);
        searchLocation7.setTypeface(face); searchLocation8.setTypeface(face); searchLocation9.setTypeface(face); searchLocation10.setTypeface(face);
        searchLocation11.setTypeface(face); searchLocation12.setTypeface(face); searchLocation13.setTypeface(face); searchLocation14.setTypeface(face);
        searchLocation15.setTypeface(face); searchLocation16.setTypeface(face); searchLocation17.setTypeface(face); searchLocation18.setTypeface(face);
        searchLocation19.setTypeface(face); searchLocation20.setTypeface(face); searchLocation21.setTypeface(face); searchLocation22.setTypeface(face);
        searchLocation23.setTypeface(face); searchLocation24.setTypeface(face); searchLocation25.setTypeface(face);

        //기본 체크여부 설정
        //지역 체크여부
        if(location[0].equals("강남구")) { searchLocation1.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[1].equals("강동구")) { searchLocation2.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[2].equals("강북구")) { searchLocation3.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[3].equals("강서구")) { searchLocation4.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[4].equals("관악구")) { searchLocation5.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[5].equals("광진구")) { searchLocation6.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[6].equals("구로구")) { searchLocation7.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[7].equals("금천구")) { searchLocation8.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[8].equals("노원구")) { searchLocation9.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[9].equals("도봉구")) { searchLocation10.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[10].equals("동대문구")) { searchLocation11.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[11].equals("동작구")) { searchLocation12.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[12].equals("마포구")) { searchLocation13.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[13].equals("서대문구")) { searchLocation14.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[14].equals("서초구")) { searchLocation15.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[15].equals("성동구")) { searchLocation16.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[16].equals("성북구")) { searchLocation17.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[17].equals("송파구")) { searchLocation18.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[18].equals("양천구")) { searchLocation19.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[19].equals("영등포구")) { searchLocation20.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[20].equals("용산구")) { searchLocation21.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[21].equals("은평구")) { searchLocation22.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[22].equals("종로구")) { searchLocation23.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[23].equals("중구")) { searchLocation24.setChecked(true); searchLocationAll.setChecked(false); }
        if(location[24].equals("중랑구")) { searchLocation25.setChecked(true); searchLocationAll.setChecked(false); }
        //모두 체크되어 있을 경우 all 버튼 선택
        if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}

        //LocationAll 클릭
        searchLocationAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //google analytics button
                makeLocationAll();
            }
        });
        //Location1 클릭
        searchLocation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation1.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 강남구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location2 클릭
        searchLocation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation2.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 강동구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location3 클릭
        searchLocation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation3.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 강북구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location4 클릭
        searchLocation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation4.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 강서구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location5 클릭
        searchLocation5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation5.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 관악구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location6 클릭
        searchLocation6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation6.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 광진구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location7 클릭
        searchLocation7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation7.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 구로구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location8 클릭
        searchLocation8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation8.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 금천구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location9 클릭
        searchLocation9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation9.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 노원구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location10 클릭
        searchLocation10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation10.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 도봉구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location11 클릭
        searchLocation11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation11.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 동대문구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location12 클릭
        searchLocation12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation12.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 동작구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location13 클릭
        searchLocation13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation13.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 마포구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location14 클릭
        searchLocation14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation14.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 서대문구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location15 클릭
        searchLocation15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation15.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 서초구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location16 클릭
        searchLocation16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation16.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 성동구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location17 클릭
        searchLocation17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation17.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 성북구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location18 클릭
        searchLocation18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation18.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 송파구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location19 클릭
        searchLocation19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation19.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 양천구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location20 클릭
        searchLocation20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation20.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 영등포구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location21 클릭
        searchLocation21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation21.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 용산구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location22 클릭
        searchLocation22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation22.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 은평구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location23 클릭
        searchLocation23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation23.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 종로구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location24 클릭
        searchLocation24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation24.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 중구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });
        //Location25 클릭
        searchLocation25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchLocation25.isChecked()) {searchLocationAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: 중랑구 on").setLabel("store search").build());}
                if(searchLocation1.isChecked()&&searchLocation2.isChecked()&&searchLocation3.isChecked()&&searchLocation4.isChecked()&&searchLocation5.isChecked()&&searchLocation6.isChecked()&&searchLocation7.isChecked()&&searchLocation8.isChecked()&&searchLocation9.isChecked()&&searchLocation10.isChecked()&&searchLocation11.isChecked()&&searchLocation12.isChecked()&&searchLocation13.isChecked()&&searchLocation14.isChecked()&&searchLocation15.isChecked()&&searchLocation16.isChecked()&&searchLocation17.isChecked()&&searchLocation18.isChecked()&&searchLocation19.isChecked()&&searchLocation20.isChecked()&&searchLocation21.isChecked()&&searchLocation22.isChecked()&&searchLocation23.isChecked()&&searchLocation24.isChecked()&&searchLocation25.isChecked()) {makeLocationAll();}
                else if(!searchLocation1.isChecked()&&!searchLocation2.isChecked()&&!searchLocation3.isChecked()&&!searchLocation4.isChecked()&&!searchLocation5.isChecked()&&!searchLocation6.isChecked()&&!searchLocation7.isChecked()&&!searchLocation8.isChecked()&&!searchLocation9.isChecked()&&!searchLocation10.isChecked()&&!searchLocation11.isChecked()&&!searchLocation12.isChecked()&&!searchLocation13.isChecked()&&!searchLocation14.isChecked()&&!searchLocation15.isChecked()&&!searchLocation16.isChecked()&&!searchLocation17.isChecked()&&!searchLocation18.isChecked()&&!searchLocation19.isChecked()&&!searchLocation20.isChecked()&&!searchLocation21.isChecked()&&!searchLocation22.isChecked()&&!searchLocation23.isChecked()&&!searchLocation24.isChecked()&&!searchLocation25.isChecked()) {makeLocationAll();}
            }
        });

        //검색 버튼 누를 시 검색 값 적용되어 전달됨
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: search button").setLabel("store search").build());

                if (searchLocation1.isChecked()) {
                    location[0] = "강남구";
                } else {
                    location[0] = "";
                }
                if (searchLocation2.isChecked()) {
                    location[1] = "강동구";
                } else {
                    location[1] = "";
                }
                if (searchLocation3.isChecked()) {
                    location[2] = "강북구";
                } else {
                    location[2] = "";
                }
                if (searchLocation4.isChecked()) {
                    location[3] = "강서구";
                } else {
                    location[3] = "";
                }
                if (searchLocation5.isChecked()) {
                    location[4] = "관악구";
                } else {
                    location[4] = "";
                }
                if (searchLocation6.isChecked()) {
                    location[5] = "광진구";
                } else {
                    location[5] = "";
                }
                if (searchLocation7.isChecked()) {
                    location[6] = "구로구";
                } else {
                    location[6] = "";
                }
                if (searchLocation8.isChecked()) {
                    location[7] = "금천구";
                } else {
                    location[7] = "";
                }
                if (searchLocation9.isChecked()) {
                    location[8] = "노원구";
                } else {
                    location[8] = "";
                }
                if (searchLocation10.isChecked()) {
                    location[9] = "도봉구";
                } else {
                    location[9] = "";
                }
                if (searchLocation11.isChecked()) {
                    location[10] = "동대문구";
                } else {
                    location[10] = "";
                }
                if (searchLocation12.isChecked()) {
                    location[11] = "동작구";
                } else {
                    location[11] = "";
                }
                if (searchLocation13.isChecked()) {
                    location[12] = "마포구";
                } else {
                    location[12] = "";
                }
                if (searchLocation14.isChecked()) {
                    location[13] = "서대문구";
                } else {
                    location[13] = "";
                }
                if (searchLocation15.isChecked()) {
                    location[14] = "서초구";
                } else {
                    location[14] = "";
                }
                if (searchLocation16.isChecked()) {
                    location[15] = "성동구";
                } else {
                    location[15] = "";
                }
                if (searchLocation17.isChecked()) {
                    location[16] = "성북구";
                } else {
                    location[16] = "";
                }
                if (searchLocation18.isChecked()) {
                    location[17] = "송파구";
                } else {
                    location[17] = "";
                }
                if (searchLocation19.isChecked()) {
                    location[18] = "양천구";
                } else {
                    location[18] = "";
                }
                if (searchLocation20.isChecked()) {
                    location[19] = "영등포구";
                } else {
                    location[19] = "";
                }
                if (searchLocation21.isChecked()) {
                    location[20] = "용산구";
                } else {
                    location[20] = "";
                }
                if (searchLocation22.isChecked()) {
                    location[21] = "은평구";
                } else {
                    location[21] = "";
                }
                if (searchLocation23.isChecked()) {
                    location[22] = "종로구";
                } else {
                    location[22] = "";
                }
                if (searchLocation24.isChecked()) {
                    location[23] = "중구";
                } else {
                    location[23] = "";
                }
                if (searchLocation25.isChecked()) {
                    location[24] = "중랑구";
                } else {
                    location[24] = "";
                }

                if (searchLocationAll.isChecked()) {
                    makelocationValuesAll();
                }

                //검색 실행
                makeSearch();
            }
        });
    }

    //브랜드 전체선택 함수
    public void makeLocationAll() {
        mTracker.send(new HitBuilders.EventBuilder().setCategory("store search screen: all button on").setLabel("store search").build());

        searchLocationAll.setChecked(true); searchLocation1.setChecked(false); searchLocation2.setChecked(false); searchLocation3.setChecked(false);
        searchLocation4.setChecked(false); searchLocation5.setChecked(false); searchLocation6.setChecked(false); searchLocation7.setChecked(false);
        searchLocation8.setChecked(false); searchLocation9.setChecked(false); searchLocation10.setChecked(false); searchLocation11.setChecked(false);
        searchLocation12.setChecked(false); searchLocation13.setChecked(false); searchLocation14.setChecked(false); searchLocation15.setChecked(false);
        searchLocation16.setChecked(false); searchLocation17.setChecked(false); searchLocation18.setChecked(false); searchLocation19.setChecked(false);
        searchLocation20.setChecked(false); searchLocation21.setChecked(false); searchLocation22.setChecked(false); searchLocation23.setChecked(false);
        searchLocation24.setChecked(false); searchLocation25.setChecked(false);
    }

    //브랜드 전체선택 시 변수값 설정
    public void makelocationValuesAll() {
        location[0] = "강남구"; location[1] = "강동구"; location[2] = "강북구"; location[3] = "강서구"; location[4] = "관악구"; location[5] = "광진구";
        location[6] = "구로구"; location[7] = "금천구"; location[8] = "노원구"; location[9] = "도봉구"; location[10] = "동대문구"; location[11] = "동작구";
        location[12] = "마포구"; location[13] = "서대문구"; location[14] = "서초구"; location[15] = "성동구"; location[16] = "성북구"; location[17] = "송파구";
        location[18] = "양천구"; location[19] = "영등포구"; location[20] = "용산구"; location[21] = "은평구"; location[22] = "종로구"; location[23] = "중구";
        location[24] = "중랑구";
    }

    public void makeSearch() {
        //ListActivity로 검색변수 전달
        Intent returnIntent = new Intent();
        //검색 변수 전달
        for(int i=0; i < location.length; i++) {returnIntent.putExtra("location" + (i + 1), location[i]);}

        setResult(1, returnIntent);
        finish();
    }

    public void makeReset() {

        //초기화 진행
        makeLocationAll();

        //초기화 문구 생성
        Toast toast = Toast.makeText(getApplicationContext(),
                "초기화 되었습니다.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        //액션바
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

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
