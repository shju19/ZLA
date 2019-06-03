package com.zla.android;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class SearchActivity extends AppCompatActivity {

    //google analytics
    private Tracker mTracker;

    protected static final String TAG = "rangeSeekbar";

    TextView searchPrice, minValue, maxValue;
    TextView searchYear;
    ToggleButton searchYear1, searchYear2;
    TextView searchFrame;
    ToggleButton searchFrameAll, searchFrame1, searchFrame2, searchFrame3, searchFrame4;
    TextView searchBrand;
    ToggleButton searchBrandAll, searchBrand1, searchBrand2, searchBrand3, searchBrand4, searchBrand5, searchBrand6;
    ToggleButton searchBrand7, searchBrand8, searchBrand9, searchBrand10, searchBrand11, searchBrand12, searchBrand13;
    ToggleButton searchBrand14, searchBrand15, searchBrand16, searchBrand17, searchBrand18, searchBrand19, searchBrand20;
    ToggleButton searchBrand21, searchBrand22, searchBrand23, searchBrand24, searchBrand25, searchBrand26, searchBrand27;
    ToggleButton searchBrand28, searchBrand29, searchBrand30, searchBrand31, searchBrand32, searchBrand33, searchBrand34;
    ToggleButton searchBrand35;

    //자전거 종류 변수
    String bicycleSeries;
    //검색 변수. 검색값을 메인페이지에 왔을 때도 유지하기 위해 메인페이지부터 변수 설정
    String[] brand = {"궤르쵸티", "라피에르", "룩", "리들리", "마린", "마지", "맥킨리", "메리다", "보드만", "비앙키", "삼천리",
            "세파스", "스캇", "스페셜라이즈드", "시포", "써벨로", "알톤", "엘파마", "예거", "오베아", "윌리어", "자이언트",
            "제이미스", "첼로", "캐논데일", "케스트렐", "코메트", "콜럼버스", "큐브", "타임", "트렉", "트리곤", "포커스", "후지", "GT"};
    String[] frame = {"스틸", "알루미늄", "카본", "티타늄"};
    int[] year = {2015, 2016};
    int minPrice = 0, maxPrice = 100000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //google analytics
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("search screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        //리스트액티비티로부터 인텐트 얻기
        Intent intent = getIntent();
        //자전거 종류 구분 변수 전달
        bicycleSeries = intent.getStringExtra("bicycleSeries");
        //검색 변수 전달
        for(int i=0; i < brand.length; i++) {brand[i] = intent.getStringExtra("brand" + (i+1));}
        for(int i=0; i < frame.length; i++) {frame[i] = intent.getStringExtra("frame" + (i+1));}
        for(int i=0; i < year.length; i++) {year[i] = intent.getIntExtra("year" + (i + 1), 0);}
        minPrice = intent.getIntExtra("minPrice", 0); maxPrice = intent.getIntExtra("maxPrice", 0);

        //xml의 값과 연결
        searchPrice = (TextView) findViewById(R.id.searchPrice);
        minValue = (TextView) findViewById(R.id.minValue);
        maxValue = (TextView) findViewById(R.id.maxValue);
        searchYear = (TextView) findViewById(R.id.searchYear);
        searchYear1 = (ToggleButton) findViewById(R.id.searchYear1);
        searchYear2 = (ToggleButton) findViewById(R.id.searchYear2);
        searchFrame = (TextView) findViewById(R.id.searchFrame);
        searchFrameAll = (ToggleButton) findViewById(R.id.searchFrameAll);
        searchFrame1 = (ToggleButton) findViewById(R.id.searchFrame1);
        searchFrame2 = (ToggleButton) findViewById(R.id.searchFrame2);
        searchFrame3 = (ToggleButton) findViewById(R.id.searchFrame3);
        searchFrame4 = (ToggleButton) findViewById(R.id.searchFrame4);
        searchBrand = (TextView) findViewById(R.id.searchBrand);
        searchBrandAll = (ToggleButton) findViewById(R.id.searchBrandAll);
        searchBrand1 = (ToggleButton) findViewById(R.id.searchBrand1);
        searchBrand2 = (ToggleButton) findViewById(R.id.searchBrand2);
        searchBrand3 = (ToggleButton) findViewById(R.id.searchBrand3);
        searchBrand4 = (ToggleButton) findViewById(R.id.searchBrand4);
        searchBrand5 = (ToggleButton) findViewById(R.id.searchBrand5);
        searchBrand6 = (ToggleButton) findViewById(R.id.searchBrand6);
        searchBrand7 = (ToggleButton) findViewById(R.id.searchBrand7);
        searchBrand8 = (ToggleButton) findViewById(R.id.searchBrand8);
        searchBrand9 = (ToggleButton) findViewById(R.id.searchBrand9);
        searchBrand10 = (ToggleButton) findViewById(R.id.searchBrand10);
        searchBrand11 = (ToggleButton) findViewById(R.id.searchBrand11);
        searchBrand12 = (ToggleButton) findViewById(R.id.searchBrand12);
        searchBrand13 = (ToggleButton) findViewById(R.id.searchBrand13);
        searchBrand14 = (ToggleButton) findViewById(R.id.searchBrand14);
        searchBrand15 = (ToggleButton) findViewById(R.id.searchBrand15);
        searchBrand16 = (ToggleButton) findViewById(R.id.searchBrand16);
        searchBrand17 = (ToggleButton) findViewById(R.id.searchBrand17);
        searchBrand18 = (ToggleButton) findViewById(R.id.searchBrand18);
        searchBrand19 = (ToggleButton) findViewById(R.id.searchBrand19);
        searchBrand20 = (ToggleButton) findViewById(R.id.searchBrand20);
        searchBrand21 = (ToggleButton) findViewById(R.id.searchBrand21);
        searchBrand22 = (ToggleButton) findViewById(R.id.searchBrand22);
        searchBrand23 = (ToggleButton) findViewById(R.id.searchBrand23);
        searchBrand24 = (ToggleButton) findViewById(R.id.searchBrand24);
        searchBrand25 = (ToggleButton) findViewById(R.id.searchBrand25);
        searchBrand26 = (ToggleButton) findViewById(R.id.searchBrand26);
        searchBrand27 = (ToggleButton) findViewById(R.id.searchBrand27);
        searchBrand28 = (ToggleButton) findViewById(R.id.searchBrand28);
        searchBrand29 = (ToggleButton) findViewById(R.id.searchBrand29);
        searchBrand30 = (ToggleButton) findViewById(R.id.searchBrand30);
        searchBrand31 = (ToggleButton) findViewById(R.id.searchBrand31);
        searchBrand32 = (ToggleButton) findViewById(R.id.searchBrand32);
        searchBrand33 = (ToggleButton) findViewById(R.id.searchBrand33);
        searchBrand34 = (ToggleButton) findViewById(R.id.searchBrand34);
        searchBrand35 = (ToggleButton) findViewById(R.id.searchBrand35);
        Button search = (Button) findViewById(R.id.search);

        //글꼴 설정
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/10X10.ttf");
        searchPrice.setTypeface(face); minValue.setTypeface(face); maxValue.setTypeface(face);
        searchYear.setTypeface(face); searchYear1.setTypeface(face); searchYear2.setTypeface(face);
        searchFrame.setTypeface(face); searchFrameAll.setTypeface(face); searchFrame1.setTypeface(face); searchFrame2.setTypeface(face);
        searchFrame3.setTypeface(face); searchFrame4.setTypeface(face);
        searchBrand.setTypeface(face); searchBrandAll.setTypeface(face); searchBrand1.setTypeface(face); searchBrand2.setTypeface(face);
        searchBrand3.setTypeface(face); searchBrand4.setTypeface(face); searchBrand5.setTypeface(face); searchBrand6.setTypeface(face);
        searchBrand7.setTypeface(face); searchBrand8.setTypeface(face); searchBrand9.setTypeface(face); searchBrand10.setTypeface(face);
        searchBrand11.setTypeface(face); searchBrand12.setTypeface(face); searchBrand13.setTypeface(face); searchBrand14.setTypeface(face);
        searchBrand15.setTypeface(face); searchBrand16.setTypeface(face); searchBrand17.setTypeface(face); searchBrand18.setTypeface(face);
        searchBrand19.setTypeface(face); searchBrand20.setTypeface(face); searchBrand21.setTypeface(face); searchBrand22.setTypeface(face);
        searchBrand23.setTypeface(face); searchBrand24.setTypeface(face); searchBrand25.setTypeface(face); searchBrand26.setTypeface(face);
        searchBrand27.setTypeface(face); searchBrand28.setTypeface(face); searchBrand29.setTypeface(face); searchBrand30.setTypeface(face);
        searchBrand31.setTypeface(face); searchBrand32.setTypeface(face); searchBrand33.setTypeface(face); searchBrand34.setTypeface(face);
        searchBrand35.setTypeface(face);



        //기본 체크여부 설정
        //연식 체크여부
        if(year[0] == 2015) { searchYear1.setChecked(true); } else { searchYear1.setChecked(false); }
        if(year[1] == 2016) { searchYear2.setChecked(true); } else { searchYear2.setChecked(false); }
        //프레임 체크여부
        if(frame[0].equals("스틸")) { searchFrame1.setChecked(true); searchFrameAll.setChecked(false); }
        if(frame[1].equals("알루미늄")) { searchFrame2.setChecked(true); searchFrameAll.setChecked(false); }
        if(frame[2].equals("카본")) { searchFrame3.setChecked(true); searchFrameAll.setChecked(false); }
        if(frame[3].equals("티타늄")) { searchFrame4.setChecked(true); searchFrameAll.setChecked(false); }
        //모두 체크되어 있을 경우 all 버튼 선택
        if(searchFrame1.isChecked()&&searchFrame2.isChecked()&&searchFrame3.isChecked()&&searchFrame4.isChecked()) {makeFrameAll();}
        //브랜드 체크여부
        if(brand[0].equals("궤르쵸티")) { searchBrand1.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[1].equals("라피에르")) { searchBrand2.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[2].equals("룩")) { searchBrand3.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[3].equals("리들리")) { searchBrand4.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[4].equals("마린")) { searchBrand5.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[5].equals("마지")) { searchBrand6.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[6].equals("맥킨리")) { searchBrand7.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[7].equals("메리다")) { searchBrand8.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[8].equals("보드만")) { searchBrand9.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[9].equals("비앙키")) { searchBrand10.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[10].equals("삼천리")) { searchBrand11.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[11].equals("세파스")) { searchBrand12.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[12].equals("스캇")) { searchBrand13.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[13].equals("스페셜라이즈드")) { searchBrand14.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[14].equals("시포")) { searchBrand15.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[15].equals("써벨로")) { searchBrand16.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[16].equals("알톤")) { searchBrand17.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[17].equals("엘파마")) { searchBrand18.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[18].equals("예거")) { searchBrand19.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[19].equals("오베아")) { searchBrand20.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[20].equals("윌리어")) { searchBrand21.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[21].equals("자이언트")) { searchBrand22.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[22].equals("제이미스")) { searchBrand23.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[23].equals("첼로")) { searchBrand24.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[24].equals("캐논데일")) { searchBrand25.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[25].equals("케스트렐")) { searchBrand26.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[26].equals("코메트")) { searchBrand27.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[27].equals("콜럼버스")) { searchBrand28.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[28].equals("큐브")) { searchBrand29.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[29].equals("타임")) { searchBrand30.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[30].equals("트렉")) { searchBrand31.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[31].equals("트리곤")) { searchBrand32.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[32].equals("포커스")) { searchBrand33.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[33].equals("후지")) { searchBrand34.setChecked(true); searchBrandAll.setChecked(false); }
        if(brand[34].equals("GT")) { searchBrand35.setChecked(true); searchBrandAll.setChecked(false); }
        //모두 체크되어 있을 경우 all 버튼 선택
        if(searchBrand1.isChecked()&&searchBrand2.isChecked()&&searchBrand3.isChecked()&&searchBrand4.isChecked()&&searchBrand5.isChecked()&&searchBrand6.isChecked()&&searchBrand7.isChecked()&&searchBrand8.isChecked()&&searchBrand9.isChecked()&&searchBrand10.isChecked()&&searchBrand11.isChecked()&&searchBrand12.isChecked()&&searchBrand13.isChecked()&&searchBrand14.isChecked()&&searchBrand15.isChecked()&&searchBrand16.isChecked()&&searchBrand17.isChecked()&&searchBrand18.isChecked()) {makeBrandAll();}

        //연식1 클릭
        searchYear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchYear1.equals(true)) {searchYear1.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: year(2015) button on").setLabel("bicycle search").build());}
                else if(searchYear1.equals(false)) {searchYear1.setChecked(true);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: year(2015) button off").setLabel("bicycle search").build());}
            }
        });
        //연식2 클릭
        searchYear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchYear2.equals(true)) {searchYear2.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: year(2016) button on").setLabel("bicycle search").build());}
                else if(searchYear2.equals(false)) {searchYear2.setChecked(true);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: year(2016) button off").setLabel("bicycle search").build());}
            }
        });

        //FrameAll 클릭
        searchFrameAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeFrameAll();
            }
        });
        //Frame1 클릭
        searchFrame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchFrame1.isChecked()); {searchFrameAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: frame(스틸) button on").setLabel("bicycle search").build());}
                if(searchFrame1.isChecked()&&searchFrame2.isChecked()&&searchFrame3.isChecked()&&searchFrame4.isChecked()) {makeFrameAll();}
                else if(!searchFrame1.isChecked()&&!searchFrame2.isChecked()&&!searchFrame3.isChecked()&&!searchFrame4.isChecked()) {makeFrameAll();}
            }
        });
        //Frame2 클릭
        searchFrame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchFrame2.isChecked()); {searchFrameAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: frame(알루미늄) button on").setLabel("bicycle search").build());}
                if(searchFrame1.isChecked()&&searchFrame2.isChecked()&&searchFrame3.isChecked()&&searchFrame4.isChecked()) {makeFrameAll();}
                else if(!searchFrame1.isChecked()&&!searchFrame2.isChecked()&&!searchFrame3.isChecked()&&!searchFrame4.isChecked()) {makeFrameAll();}
            }
        });
        //Frame3 클릭
        searchFrame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchFrame3.isChecked()); {searchFrameAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: frame(카본) button on").setLabel("bicycle search").build());}
                if(searchFrame1.isChecked()&&searchFrame2.isChecked()&&searchFrame3.isChecked()&&searchFrame4.isChecked()) {makeFrameAll();}
                else if(!searchFrame1.isChecked()&&!searchFrame2.isChecked()&&!searchFrame3.isChecked()&&!searchFrame4.isChecked()) {makeFrameAll();}
            }
        });
        //Frame4 클릭
        searchFrame4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchFrame4.isChecked()); {searchFrameAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: frame(티타늄) button on").setLabel("bicycle search").build());}
                if(searchFrame1.isChecked()&&searchFrame2.isChecked()&&searchFrame3.isChecked()&&searchFrame4.isChecked()) {makeFrameAll();}
                else if(!searchFrame1.isChecked()&&!searchFrame2.isChecked()&&!searchFrame3.isChecked()&&!searchFrame4.isChecked()) {makeFrameAll();}
            }
        });

        //BrandAll 클릭
        searchBrandAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeBrandAll();
            }
        });
        //Brand1 클릭
        searchBrand1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand1.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(궤르쵸티) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand2 클릭
        searchBrand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand2.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(라피에르) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand3 클릭
        searchBrand3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand3.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(룩) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand4 클릭
        searchBrand4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand4.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(리들리) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand5 클릭
        searchBrand5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand5.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(마린) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand6 클릭
        searchBrand6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand6.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(마지) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand7 클릭
        searchBrand7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand7.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(맥킨리) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand8 클릭
        searchBrand8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand8.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(메리다) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand9 클릭
        searchBrand9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand9.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(보드만) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand10 클릭
        searchBrand10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand10.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(비앙키) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand11 클릭
        searchBrand11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand11.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(삼천리) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand12 클릭
        searchBrand12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand12.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(세파스) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand13 클릭
        searchBrand13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand13.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(스캇) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand14 클릭
        searchBrand14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand14.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(스페셜라이즈드) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand15 클릭
        searchBrand15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand15.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(시포) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand16 클릭
        searchBrand16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand16.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(써벨로) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand17 클릭
        searchBrand17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand17.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(알톤) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand18 클릭
        searchBrand18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand18.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(엘파마) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand19 클릭
        searchBrand19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand19.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(예거) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand20 클릭
        searchBrand20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand20.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(오베아) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand21 클릭
        searchBrand21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand21.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(윌리어) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand22 클릭
        searchBrand22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand22.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(자이언트) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand23 클릭
        searchBrand23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand23.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(제이미스) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand24 클릭
        searchBrand24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand24.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(첼로) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand25 클릭
        searchBrand25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand25.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(캐논데일) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand26 클릭
        searchBrand26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand26.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(케스트렐) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand27 클릭
        searchBrand27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand27.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(코메트) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand28 클릭
        searchBrand28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand28.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(콜럼버스) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand29 클릭
        searchBrand29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand29.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(큐브) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand30 클릭
        searchBrand30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand30.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(타임) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand31 클릭
        searchBrand31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand31.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(트렉) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand32 클릭
        searchBrand32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand32.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(트리곤) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand33 클릭
        searchBrand33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand33.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(포커스) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand34 클릭
        searchBrand34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand34.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(후지) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });
        //Brand35 클릭
        searchBrand35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBrand35.isChecked()) {searchBrandAll.setChecked(false);
                    //google analytics button
                    mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(GT) button on").setLabel("bicycle search").build());}
                //브랜드 선택없음, 전체선택 확인 메소드
                checkBrand();
            }
        });

        // create RangeSeekBar as Integer range between 0 and 500만원
        RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(0, 50, this);
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
                                                    @Override
                                                    public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer
                                                            minValue, Integer maxValue) {
                                                        // handle changed range values

                                                        Log.i(TAG, "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);

                                                        //시크바 가격표시 텍스트뷰
                                                        TextView minText = (TextView) findViewById(R.id.minValue);
                                                        TextView maxText = (TextView) findViewById(R.id.maxValue);

                                                        //구간간격 범위 조정: 0-500까지 10간격으로 증가
                                                        minPrice = minValue * 100000;
                                                        maxPrice = maxValue * 100000;
                                                        minText.setText("" + (minValue * 10) + " - ");
                                                        maxText.setText("" + (maxValue * 10) + "만원");
                                                        if (maxPrice == 5000000) {
                                                            maxPrice = 30000000; //max 값일 시 최소금액 이상으로 모든 가격의 자전거 검색
                                                            maxText.setText("제한없음");
                                                        }
                                                    }
                                                }
        );
        //터치 이동 시 가격변환 적용
        seekBar.setNotifyWhileDragging(true);

        // add RangeSeekBar to pre-defined layout
        ViewGroup layout = (ViewGroup) findViewById(R.id.layout);
        layout.addView(seekBar);

        //시크바 초기 가격 표시
        seekBar.setSelectedMinValue(minPrice / 100000);
        if(maxPrice < 5000000) { seekBar.setSelectedMaxValue(maxPrice / 100000); }
        else { seekBar.setSelectedMaxValue(50); }
        //시크바 가격표시 텍스트뷰
        TextView minText = (TextView) findViewById(R.id.minValue);
        TextView maxText = (TextView) findViewById(R.id.maxValue);
        minText.setText("" + (minPrice / 10000) + " - ");
        maxText.setText("" + (maxPrice / 10000) + "만원");
        if(maxPrice >= 5000000) {
            maxPrice = 30000000; //max 값일 시 최소금액 이상으로 모든 가격의 자전거 검색
            maxText.setText("제한없음");
        }

        //검색 버튼 누를 시 검색 값 적용되어 전달됨
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: search button").setLabel("bicycle search").build());

                //체크여부에 따라 검색변수값 설정
                if (searchYear1.isChecked()) {
                    year[0] = 2015;
                } else {
                    year[0] = 0;
                }
                if (searchYear2.isChecked()) {
                    year[1] = 2016;
                } else {
                    year[1] = 0;
                }
                if (searchFrame1.isChecked()) {
                    frame[0] = "스틸";
                } else {
                    frame[0] = "";
                }
                if (searchFrame2.isChecked()) {
                    frame[1] = "알루미늄";
                } else {
                    frame[1] = "";
                }
                if (searchFrame3.isChecked()) {
                    frame[2] = "카본";
                } else {
                    frame[2] = "";
                }
                if (searchFrame4.isChecked()) {
                    frame[3] = "티타늄";
                } else {
                    frame[3] = "";
                }
                if (searchFrameAll.isChecked()) {  //올버튼을 제일 나중에 진행하여 클릭이 안되어 있어도 변수값을 전달하도록 설정
                    frame[0] = "스틸";
                    frame[1] = "알루미늄";
                    frame[2] = "카본";
                    frame[3] = "티타늄";
                } else {
                }
                if (searchBrand1.isChecked()) {brand[0] = "궤르쵸티";} else {brand[0] = "";}
                if (searchBrand2.isChecked()) {brand[1] = "라피에르";} else {brand[1] = "";}
                if (searchBrand3.isChecked()) {brand[2] = "룩";} else {brand[2] = "";}
                if (searchBrand4.isChecked()) {brand[3] = "리들리";} else {brand[3] = "";}
                if (searchBrand5.isChecked()) {brand[4] = "마린";} else {brand[4] = "";}
                if (searchBrand6.isChecked()) {brand[5] = "마지";} else {brand[5] = "";}
                if (searchBrand7.isChecked()) {brand[6] = "맥킨리";} else {brand[6] = "";}
                if (searchBrand8.isChecked()) {brand[7] = "메리다";} else {brand[7] = "";}
                if (searchBrand9.isChecked()) {brand[8] = "보드만";} else {brand[8] = "";}
                if (searchBrand10.isChecked()) {brand[9] = "비앙키";} else {brand[9] = "";}
                if (searchBrand11.isChecked()) {brand[10] = "삼천리";} else {brand[10] = "";}
                if (searchBrand12.isChecked()) {brand[11] = "세파스";} else {brand[11] = "";}
                if (searchBrand13.isChecked()) {brand[12] = "스캇";} else {brand[12] = "";}
                if (searchBrand14.isChecked()) {brand[13] = "스페셜라이즈드";} else {brand[13] = "";}
                if (searchBrand15.isChecked()) {brand[14] = "시포";} else {brand[14] = "";}
                if (searchBrand16.isChecked()) {brand[15] = "써벨로";} else {brand[15] = "";}
                if (searchBrand17.isChecked()) {brand[16] = "알톤";} else {brand[16] = "";}
                if (searchBrand18.isChecked()) {brand[17] = "엘파마";} else {brand[17] = "";}
                if (searchBrand19.isChecked()) {brand[18] = "예거";} else {brand[18] = "";}
                if (searchBrand20.isChecked()) {brand[19] = "오베아";} else {brand[19] = "";}
                if (searchBrand21.isChecked()) {brand[20] = "윌리어";} else {brand[20] = "";}
                if (searchBrand22.isChecked()) {brand[21] = "자이언트";} else {brand[21] = "";}
                if (searchBrand23.isChecked()) {brand[22] = "제이미스";} else {brand[22] = "";}
                if (searchBrand24.isChecked()) {brand[23] = "첼로";} else {brand[23] = "";}
                if (searchBrand25.isChecked()) {brand[24] = "캐논데일";} else {brand[24] = "";}
                if (searchBrand26.isChecked()) {brand[25] = "케스트렐";} else {brand[25] = "";}
                if (searchBrand27.isChecked()) {brand[26] = "코메트";} else {brand[26] = "";}
                if (searchBrand28.isChecked()) {brand[27] = "콜럼버스";} else {brand[27] = "";}
                if (searchBrand29.isChecked()) {brand[28] = "큐브";} else {brand[28] = "";}
                if (searchBrand30.isChecked()) {brand[29] = "타임";} else {brand[29] = "";}
                if (searchBrand31.isChecked()) {brand[30] = "트렉";} else {brand[30] = "";}
                if (searchBrand32.isChecked()) {brand[31] = "트리곤";} else {brand[31] = "";}
                if (searchBrand33.isChecked()) {brand[32] = "포커스";} else {brand[32] = "";}
                if (searchBrand34.isChecked()) {brand[33] = "후지";} else {brand[33] = "";}
                if (searchBrand35.isChecked()) {brand[34] = "GT";} else {brand[34] = "";}
                if (searchBrandAll.isChecked()) {
                    brand[0] = "궤르쵸티";
                    brand[1] = "라피에르";
                    brand[2] = "룩";
                    brand[3] = "리들리";
                    brand[4] = "마린";
                    brand[5] = "마지";
                    brand[6] = "맥킨리";
                    brand[7] = "메리다";
                    brand[8] = "보드만";
                    brand[9] = "비앙키";
                    brand[10] = "삼천리";
                    brand[11] = "세파스";
                    brand[12] = "스캇";
                    brand[13] = "스페셜라이즈드";
                    brand[14] = "시포";
                    brand[15] = "써벨로";
                    brand[16] = "알톤";
                    brand[17] = "엘파마";
                    brand[18] = "예거";
                    brand[19] = "오베아";
                    brand[20] = "윌리어";
                    brand[21] = "자이언트";
                    brand[22] = "제이미스";
                    brand[23] = "첼로";
                    brand[24] = "캐논데일";
                    brand[25] = "케스트렐";
                    brand[26] = "코메트";
                    brand[27] = "콜럼버스";
                    brand[28] = "큐브";
                    brand[29] = "타임";
                    brand[30] = "트렉";
                    brand[31] = "트리곤";
                    brand[32] = "포커스";
                    brand[33] = "후지";
                    brand[34] = "GT";
                } else {
                }

                //검색 실행
                makeSearch();
            }
        });
    }

    //프레임 전체선택 함수
    public void makeFrameAll() {
        mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: frame(전체) button on").setLabel("bicycle search").build());
        searchFrame1.setChecked(false); searchFrame2.setChecked(false); searchFrame3.setChecked(false); searchFrame4.setChecked(false);
        searchFrameAll.setChecked(true);
    }

    //브랜드 전체선택 함수
    public void makeBrandAll() {
        mTracker.send(new HitBuilders.EventBuilder().setCategory("search screen: brand(전체) button on").setLabel("bicycle search").build());
        searchBrandAll.setChecked(true); searchBrand1.setChecked(false); searchBrand2.setChecked(false); searchBrand3.setChecked(false);
        searchBrand4.setChecked(false); searchBrand5.setChecked(false); searchBrand6.setChecked(false); searchBrand7.setChecked(false);
        searchBrand8.setChecked(false); searchBrand9.setChecked(false); searchBrand10.setChecked(false); searchBrand11.setChecked(false);
        searchBrand12.setChecked(false); searchBrand13.setChecked(false); searchBrand14.setChecked(false); searchBrand15.setChecked(false);
        searchBrand16.setChecked(false); searchBrand17.setChecked(false); searchBrand18.setChecked(false); searchBrand19.setChecked(false);
        searchBrand20.setChecked(false); searchBrand21.setChecked(false); searchBrand22.setChecked(false); searchBrand23.setChecked(false);
        searchBrand24.setChecked(false); searchBrand25.setChecked(false); searchBrand26.setChecked(false); searchBrand27.setChecked(false);
        searchBrand28.setChecked(false); searchBrand29.setChecked(false); searchBrand30.setChecked(false); searchBrand31.setChecked(false);
        searchBrand32.setChecked(false); searchBrand33.setChecked(false); searchBrand34.setChecked(false); searchBrand35.setChecked(false);
    }

    //브랜드 선택없음, 전체선택 함수
    public void checkBrand() {
        if(searchBrand1.isChecked()&&searchBrand2.isChecked()&&searchBrand3.isChecked()&&searchBrand4.isChecked()&&searchBrand5.isChecked()&&searchBrand6.isChecked()&&searchBrand7.isChecked()&&searchBrand8.isChecked()&&searchBrand9.isChecked()&&searchBrand10.isChecked()&&searchBrand11.isChecked()&&searchBrand12.isChecked()&&searchBrand13.isChecked()&&searchBrand14.isChecked()&&searchBrand15.isChecked()&&searchBrand16.isChecked()&&searchBrand17.isChecked()&&searchBrand18.isChecked()&&searchBrand19.isChecked()&&searchBrand20.isChecked()&&searchBrand21.isChecked()&&searchBrand22.isChecked()&&searchBrand23.isChecked()&&searchBrand24.isChecked()&&searchBrand25.isChecked()&&searchBrand26.isChecked()&&searchBrand27.isChecked()&&searchBrand28.isChecked()&&searchBrand29.isChecked()&&searchBrand30.isChecked()&&searchBrand31.isChecked()&&searchBrand32.isChecked()&&searchBrand33.isChecked()&&searchBrand34.isChecked()&&searchBrand35.isChecked()) {makeBrandAll();}
        else if(!searchBrand1.isChecked()&&!searchBrand2.isChecked()&&!searchBrand3.isChecked()&&!searchBrand4.isChecked()&&!searchBrand5.isChecked()&&!searchBrand6.isChecked()&&!searchBrand7.isChecked()&&!searchBrand8.isChecked()&&!searchBrand9.isChecked()&&!searchBrand10.isChecked()&&!searchBrand11.isChecked()&&!searchBrand12.isChecked()&&!searchBrand13.isChecked()&&!searchBrand14.isChecked()&&!searchBrand15.isChecked()&&!searchBrand16.isChecked()&&!searchBrand17.isChecked()&&!searchBrand18.isChecked()&&!searchBrand19.isChecked()&&!searchBrand20.isChecked()&&!searchBrand21.isChecked()&&!searchBrand22.isChecked()&&!searchBrand23.isChecked()&&!searchBrand24.isChecked()&&!searchBrand25.isChecked()&&!searchBrand26.isChecked()&&!searchBrand27.isChecked()&&!searchBrand28.isChecked()&&!searchBrand29.isChecked()&&!searchBrand30.isChecked()&&!searchBrand31.isChecked()&&!searchBrand32.isChecked()&&!searchBrand33.isChecked()&&!searchBrand34.isChecked()&&!searchBrand35.isChecked()) {makeBrandAll();}
    }

    //프레임 전체선택 시 변수값 설정
    public void makeFrameValuesAll() {
        frame[0] = "스틸"; frame[1] = "알루미늄"; frame[2] = "카본"; frame[3] = "티타늄";
    }

    //브랜드 전체선택 시 변수값 설정
    public void makeBrandValuesAll() {
        brand[0] = "궤르쵸티"; brand[1] = "라피에르"; brand[2] = "룩"; brand[3] = "리들리"; brand[4] = "마린"; brand[5] = "마지";
        brand[6] = "맥킨리"; brand[7] = "메리다"; brand[8] = "보드만"; brand[9] = "비앙키"; brand[10] = "삼천리"; brand[11] = "세파스";
        brand[12] = "스캇"; brand[13] = "스페셜라이즈드"; brand[14] = "시포"; brand[15] = "써벨로"; brand[16] = "알톤"; brand[17] = "엘파마";
        brand[18] = "예거"; brand[19] = "오베아"; brand[20] = "윌리어"; brand[21] = "자이언트"; brand[22] = "제이미스"; brand[23] = "첼로";
        brand[24] = "캐논데일"; brand[25] = "케스트렐"; brand[26] = "코메트"; brand[27] = "콜럼버스"; brand[28] = "큐브"; brand[29] = "타임";
        brand[30] = "트렉"; brand[31] = "트리곤"; brand[32] = "포커스"; brand[33] = "후지"; brand[34] = "GT";
    }

    public void makeSearch() {
        //ListActivity로 검색변수 전달
        Intent returnIntent = new Intent();
        //자전거 종류 구분 변수 전달
        returnIntent.putExtra("bicycleSeries", bicycleSeries);
        //검색 변수 전달
        for(int i=0; i < brand.length; i++) {returnIntent.putExtra("brand" + (i+1), brand[i]);}
        for(int i=0; i < frame.length; i++) {returnIntent.putExtra("frame" + (i+1), frame[i]);}
        for(int i=0; i < year.length; i++) {returnIntent.putExtra("year" + (i+1), year[i]);}
        returnIntent.putExtra("minPrice", minPrice); returnIntent.putExtra("maxPrice", maxPrice);

        setResult(1, returnIntent);
        finish();
    }

    public void makeReset() {

        //초기화 진행
        searchYear1.setChecked(true);
        searchYear2.setChecked(true);
        makeFrameAll();
        makeBrandAll();

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
