package com.zla.android;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ConfirmInventoryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //google analytics
    private Tracker mTracker;

    // 매장 별 재고 정보를 보여줄 리스트뷰 및 매장, 재고 정보를 담는 리스트 초기 선언
    ListView listView;
    ArrayList<ConfirmInventoryActivity_CustomDTO> list;

    //MySQL의 inventory_all 테이블에서 검색할 자전거 변수(자전거명과 연식으로 고유값을 만듦)와 재고화면에 노출할 색상, 사이즈 변수
    String bicycleName;
    int bicycleYear;
    String[] color = new String[5];
    String[] size = new String[8];

    //JSP 연결
    getInventoryFromMySQL connectJSP;
    int countInventory = 0; //JSP에서 받아온 재고의 개수.
    // MySQL에서 받아온 매장명과 재고 값을 저장하는 배열 선언. 액티비티 생성 시 한 번 불러온 값으로 변동 없음
    // 배열 크기를 500으로 설정. 서울지역 매장이 600개 내외이므로 재고가 있는 매장을 보여주기에는 충분이 큰 수임
    String[] mysqlStoreId = new String[500];
    int[][][] mysqlInventory = new int[500][5][8];  //색상과 재고 값을 각각 5개, 8개로 설정

    // 검색 변수값이며 버튼 선택에 따라 값이 유동적으로 변함. 배열 크기는 매장 개수에 맞춰 500개로 선정
    String[][] searchStore= new String[500][3];     //매장 정보는 store_id, 매장명, 지역구로 3가지 정보를 저장
    int[][][] searchInventory = new int[500][5][8]; //재고 정보
    String[] searchColor = new String[5];   //색상 변수
    String[] searchSize = new String[8];    //사이즈 변수

    //색상, 사이즈, 지역 버튼 선택을 나타내는 변수. 해당 값에 맞춰 매장 리스트 노출 값이 조정됨
    int[] setColor = {1, 1, 1, 1, 1};
    int[] setSize = {1, 1, 1, 1, 1, 1, 1, 1};
    String[] setLocation = {"강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구",
    "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구",
    "종로구", "중구", "중랑구"};
    //네비게이션 뷰 버튼 show/hide 변수
    String navColor = "hide", navSize = "hide", navLocation = "hide";
    //네비게이션 지역 상세 버튼 변수. 해당 값으로 지역 UI의 체크 표시 여부 결정
    String[] navLocationValues = {"off", "off", "off", "off", "off", "off", "off", "off", "off", "off",
            "off", "off", "off", "off", "off", "off", "off", "off", "off", "off", "off", "off", "off", "off", "off"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_inventory);

        //google analytics
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("inventory screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        // 툴바 연결 및 생성
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 툴바 제목 및 제목 색상 설정
        toolbar.setTitle("실시간 재고확인");
        toolbar.setTitleTextColor(Color.WHITE);

        //JSP 통신. 자전거명 및 연식 정보를 JSP에 전달하여 MySQL의 inventory_all 테이블에서 해당 값과 일치하는 매장 정보를 가져옴
        connectJSP = new getInventoryFromMySQL();
        connectJSP.execute();

        //DetailActivity에서 자전거 명 정보 받기
        Intent intent = getIntent();
        //자전거 정보
        bicycleName = intent.getStringExtra("bicycleName");
        bicycleYear = intent.getIntExtra("bicycleYear", 0);
        color[0] = intent.getStringExtra("color1");
        color[1] = intent.getStringExtra("color2");
        color[2] = intent.getStringExtra("color3");
        color[3] = intent.getStringExtra("color4");
        color[4] = intent.getStringExtra("color5");
        size[0] = intent.getStringExtra("size1");
        size[1] = intent.getStringExtra("size2");
        size[2] = intent.getStringExtra("size3");
        size[3] = intent.getStringExtra("size4");
        size[4] = intent.getStringExtra("size5");
        size[5] = intent.getStringExtra("size6");
        size[6] = intent.getStringExtra("size7");
        size[7] = intent.getStringExtra("size8");

        //DetailActivity에서 받아온 색상 및 사이즈 정보를 리스트뷰에 넘길 검색 변수에 저장. 추후 색상, 사이즈 버튼 클릭 시 유동적으로 값이 변함
        for(int c=0; c<5; c++) {searchColor[c] = color[c];}
        for(int s=0; s<8; s++) {searchSize[s] = size[s];}

        //Drawer화면 연결 및 해당 아이템 클릭 시 네비게이션 아이템 선택 내용 실행
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //네비게이션뷰 버튼 숨기기 기능
        if (color[0].equals("")) {navigationView.getMenu().findItem(R.id.nav_color1).setVisible(false);}
        if (color[1].equals("")) {navigationView.getMenu().findItem(R.id.nav_color2).setVisible(false);}
        if (color[2].equals("")) {navigationView.getMenu().findItem(R.id.nav_color3).setVisible(false);}
        if (color[3].equals("")) {navigationView.getMenu().findItem(R.id.nav_color4).setVisible(false);}
        if (color[4].equals("")) {navigationView.getMenu().findItem(R.id.nav_color5).setVisible(false);}
        if (size[0].equals("")) {navigationView.getMenu().findItem(R.id.nav_size1).setVisible(false);}
        if (size[1].equals("")) {navigationView.getMenu().findItem(R.id.nav_size2).setVisible(false);}
        if (size[2].equals("")) {navigationView.getMenu().findItem(R.id.nav_size3).setVisible(false);}
        if (size[3].equals("")) {navigationView.getMenu().findItem(R.id.nav_size4).setVisible(false);}
        if (size[4].equals("")) {navigationView.getMenu().findItem(R.id.nav_size5).setVisible(false);}
        if (size[5].equals("")) {navigationView.getMenu().findItem(R.id.nav_size6).setVisible(false);}
        if (size[6].equals("")) {navigationView.getMenu().findItem(R.id.nav_size7).setVisible(false);}
        if (size[7].equals("")) {navigationView.getMenu().findItem(R.id.nav_size8).setVisible(false);}

        //DrawView에 색상 및 사이즈 이름 넣기
        navigationView.getMenu().findItem(R.id.nav_color1).setTitle(color[0]);
        navigationView.getMenu().findItem(R.id.nav_color2).setTitle(color[1]);
        navigationView.getMenu().findItem(R.id.nav_color3).setTitle(color[2]);
        navigationView.getMenu().findItem(R.id.nav_color4).setTitle(color[3]);
        navigationView.getMenu().findItem(R.id.nav_color5).setTitle(color[4]);
        navigationView.getMenu().findItem(R.id.nav_size1).setTitle(size[0]);
        navigationView.getMenu().findItem(R.id.nav_size2).setTitle(size[1]);
        navigationView.getMenu().findItem(R.id.nav_size3).setTitle(size[2]);
        navigationView.getMenu().findItem(R.id.nav_size4).setTitle(size[3]);
        navigationView.getMenu().findItem(R.id.nav_size5).setTitle(size[4]);
        navigationView.getMenu().findItem(R.id.nav_size6).setTitle(size[5]);
        navigationView.getMenu().findItem(R.id.nav_size7).setTitle(size[6]);
        navigationView.getMenu().findItem(R.id.nav_size8).setTitle(size[7]);


        // DrawLayout 연결
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle dtToggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("inventory screen: search on").setLabel("inventory search").build());

                // DrawerLayout 닫을 시 검색 진행
                setStoreValues();       // 리스트뷰에 노출할 매장 정보 입력. 시작 시 한 번만 진행
                setInventoryValues();   // 리스트뷰에 노출할 재고 정보 입력. 시작 시 한 번 진행하며 이후 색상, 사이즈 버튼 클릭 시에도 입력
                doSearch();             // 색상, 사이즈, 지역 정보에 맞춰 리스트뷰 검색 진행. 이후 버튼 클릭 시에도 검색 진행
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(dtToggle);

        // 메인화면 상단의 색상, 지역, 버튼 버튼 클릭 시 네비게이션 뷰 띄우기. 초기화 및 닫기 버튼
        Button layout_inventory_search_button1 = (Button)findViewById(R.id.layout_inventory_search_button1);
        Button layout_inventory_search_button2 = (Button)findViewById(R.id.layout_inventory_search_button2);
        Button layout_inventory_search_button3 = (Button)findViewById(R.id.layout_inventory_search_button3);
        Button nav_reset = (Button)findViewById(R.id.nav_reset);
        Button nav_close = (Button)findViewById(R.id.nav_close);

        //재고확인 페이지 글꼴 설정
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/10X10.ttf");
        layout_inventory_search_button1.setTypeface(face);
        layout_inventory_search_button2.setTypeface(face);
        layout_inventory_search_button3.setTypeface(face);
        //네비게이션 뷰 버튼은 글꼴 적용 안해놓음. 추후 적용 필요

        //색상 버튼
        layout_inventory_search_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("inventory screen: color button on").setLabel("inventory search").build());

                // 네비게이션 뷰 오픈
                drawer.openDrawer(Gravity.RIGHT);
                // 네비게이션 뷰 오픈
                clickMainButton(true, false, false);
            }
        });
        //사이즈 버튼
        layout_inventory_search_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("inventory screen: size button on").setLabel("inventory search").build());

                // 네비게이션 뷰 오픈
                drawer.openDrawer(Gravity.RIGHT);
                // 네비게이션 뷰 오픈
                clickMainButton(false, true, false);
            }
        });
        //지역 버튼
        layout_inventory_search_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("inventory screen: location button on").setLabel("inventory search").build());

                // 네비게이션 뷰 오픈
                drawer.openDrawer(Gravity.RIGHT);
                // 네비게이션 뷰 오픈
                clickMainButton(false, false, true);
            }
        });


        //네비게이션 뷰 헤더 레이아웃 연결
        View headerLayout = navigationView.inflateHeaderView(R.layout.activity_confirm_inventory_nav_header);
        //네비게이션 뷰 초기화 버튼
        headerLayout.findViewById(R.id.nav_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("inventory screen: navigation_reset button on").setLabel("inventory search").build());

                setColor = new int[]{1, 1, 1, 1, 1};    // 재고 값 업데이트 변수: 색상
                setSize = new int[]{1, 1, 1, 1, 1, 1, 1, 1};    // 재고 값 업데이트 변수: 사이즈
                setInventoryValues();   // 재고 값 업데이트 진행
                for (int c = 0; c < 5; c++) {
                    searchColor[c] = color[c];
                }// 색상 값 선택여부 설정. 선택한 색상 값을 리스트뷰 어뎁터에 전달하여 노출. 색상값이 공백일 경우 리스트뷰에 노출 안됨
                for (int s = 0; s < 8; s++) {
                    searchSize[s] = size[s];
                }// 사이즈 값 선택여부 설정. 선택한 사이즈 값을 리스트뷰 어뎁터에 전달하여 노출. 사이즈값이 공백일 경우 리스트뷰에 노출 안됨
                selectNavColor("all");  // 전체 색상에 체크 표시
                selectNavSize("all");   // 전체 사이즈에 체크 표시
                // 리스트뷰에 넣는 지역 값 초기화
                setLocation[0] = "강남구";
                setLocation[1] = "강동구";
                setLocation[2] = "강북구";
                setLocation[3] = "강서구";
                setLocation[4] = "관악구";
                setLocation[5] = "광진구";
                setLocation[6] = "구로구";
                setLocation[7] = "금천구";
                setLocation[8] = "노원구";
                setLocation[9] = "도봉구";
                setLocation[10] = "동대문구";
                setLocation[11] = "동작구";
                setLocation[12] = "마포구";
                setLocation[13] = "서대문구";
                setLocation[14] = "서초구";
                setLocation[15] = "성동구";
                setLocation[16] = "성북구";
                setLocation[17] = "송파구";
                setLocation[18] = "양천구";
                setLocation[19] = "영등포구";
                setLocation[20] = "용산구";
                setLocation[21] = "은평구";
                setLocation[22] = "종로구";
                setLocation[23] = "중구";
                setLocation[24] = "중랑구";
                // UI 초기화
                // - 색상, 사이즈, 지역 항목 닫기
                clickMainButton(false, false, false);
                // - 색상, 사이즈는 '전체' 값에 선택 버튼 넣기. 지역은 모든 항목에 선택 버튼 없애기
                selectNavColor("all");
                selectNavSize("all");
                for (int i = 0; i < 25; i++) {
                    navLocationValues[i] = "off";
                }
                navigationView.getMenu().findItem(R.id.nav_location1).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location2).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location3).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location4).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location5).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location6).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location7).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location8).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location9).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location10).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location11).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location12).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location13).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location14).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location15).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location16).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location17).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location18).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location19).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location20).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location21).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location22).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location23).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location24).setIcon(R.drawable.nav_uncheck);
                navigationView.getMenu().findItem(R.id.nav_location25).setIcon(R.drawable.nav_uncheck);
            }
        });
        //네비게이션 뷰 닫기 버튼
        headerLayout.findViewById(R.id.nav_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("inventory screen: navigation_close button on").setLabel("inventory search").build());

                //네비게이션 뷰 닫기 실행
                drawer.closeDrawer(Gravity.RIGHT);
            }
        });

        //리스트뷰 연결
        listView = (ListView) findViewById(R.id.layout_inventory_list);
        // 리스트 세부 아이템 클릭(클릭 시 상세페이지로 이동)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                //보낼 데이터 생성. 매장명 및 지역구로 매장 구분
                String store = list.get(position).store;
                String location = list.get(position).location;

                //화면 전환하는 객체 선언
                Intent intent = new Intent().setClass(ConfirmInventoryActivity.this, StoreActivity.class);
                intent.putExtra("store", store);
                intent.putExtra("location", location);

                //화면 전환 메소드
                startActivity(intent);
            }
        });
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //Drawer화면 연결. 버튼 선택 시 Drawer 값들을 조정하기 위해 연결함
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //색상 show/hide
        if (id == R.id.nav_color) {
            if(navColor == "hide") { // navColorShow 값에 따라 show/hide 진행
                navColor = "show";
                item.setIcon(R.drawable.nav_up);
                // 하위 개별 Color 값 보이기
                navigationView.getMenu().findItem(R.id.nav_color_all).setVisible(true);
                if (!color[0].equals("")) {navigationView.getMenu().findItem(R.id.nav_color1).setVisible(true);}
                if (!color[1].equals("")) {navigationView.getMenu().findItem(R.id.nav_color2).setVisible(true);}
                if (!color[2].equals("")) {navigationView.getMenu().findItem(R.id.nav_color3).setVisible(true);}
                if (!color[3].equals("")) {navigationView.getMenu().findItem(R.id.nav_color4).setVisible(true);}
                if (!color[4].equals("")) {navigationView.getMenu().findItem(R.id.nav_color5).setVisible(true);}
            } else if(navColor == "show") { // navColorShow 값에 따라 show/hide 진행
                navColor = "hide";
                item.setIcon(R.drawable.nav_down);
                // 하위 개별 Color 값 숨기기
                navigationView.getMenu().findItem(R.id.nav_color_all).setVisible(false);
                if (!color[0].equals("")) {navigationView.getMenu().findItem(R.id.nav_color1).setVisible(false);}
                if (!color[1].equals("")) {navigationView.getMenu().findItem(R.id.nav_color2).setVisible(false);}
                if (!color[2].equals("")) {navigationView.getMenu().findItem(R.id.nav_color3).setVisible(false);}
                if (!color[3].equals("")) {navigationView.getMenu().findItem(R.id.nav_color4).setVisible(false);}
                if (!color[4].equals("")) {navigationView.getMenu().findItem(R.id.nav_color5).setVisible(false);}
            }
        }

        //사이즈 show/hide
        if (id == R.id.nav_size) {
            if(navSize == "hide") { // navColorShow 값에 따라 show/hide 진행
                navSize = "show";
                item.setIcon(R.drawable.nav_up);
                // 하위 개별 Size 값 보이기
                navigationView.getMenu().findItem(R.id.nav_size_all).setVisible(true);
                if (!size[0].equals("")) {navigationView.getMenu().findItem(R.id.nav_size1).setVisible(true);}
                if (!size[1].equals("")) {navigationView.getMenu().findItem(R.id.nav_size2).setVisible(true);}
                if (!size[2].equals("")) {navigationView.getMenu().findItem(R.id.nav_size3).setVisible(true);}
                if (!size[3].equals("")) {navigationView.getMenu().findItem(R.id.nav_size4).setVisible(true);}
                if (!size[4].equals("")) {navigationView.getMenu().findItem(R.id.nav_size5).setVisible(true);}
                if (!size[5].equals("")) {navigationView.getMenu().findItem(R.id.nav_size6).setVisible(true);}
                if (!size[6].equals("")) {navigationView.getMenu().findItem(R.id.nav_size7).setVisible(true);}
                if (!size[7].equals("")) {navigationView.getMenu().findItem(R.id.nav_size8).setVisible(true);}
            } else if(navSize == "show") { // navColorShow 값에 따라 show/hide 진행
                navSize = "hide";
                item.setIcon(R.drawable.nav_down);
                // 하위 개별 Size 값 숨기기
                navigationView.getMenu().findItem(R.id.nav_size_all).setVisible(false);
                if (!size[0].equals("")) {navigationView.getMenu().findItem(R.id.nav_size1).setVisible(false);}
                if (!size[1].equals("")) {navigationView.getMenu().findItem(R.id.nav_size2).setVisible(false);}
                if (!size[2].equals("")) {navigationView.getMenu().findItem(R.id.nav_size3).setVisible(false);}
                if (!size[3].equals("")) {navigationView.getMenu().findItem(R.id.nav_size4).setVisible(false);}
                if (!size[4].equals("")) {navigationView.getMenu().findItem(R.id.nav_size5).setVisible(false);}
                if (!size[5].equals("")) {navigationView.getMenu().findItem(R.id.nav_size6).setVisible(false);}
                if (!size[6].equals("")) {navigationView.getMenu().findItem(R.id.nav_size7).setVisible(false);}
                if (!size[7].equals("")) {navigationView.getMenu().findItem(R.id.nav_size8).setVisible(false);}
            }
        }

        //지역 show/hide
        if (id == R.id.nav_location) {
            if(navLocation == "hide") { // navColorShow 값에 따라 show/hide 진행
                navLocation = "show";
                item.setIcon(R.drawable.nav_up);
                // 하위 개별 Location 값 보이기
                navigationView.getMenu().findItem(R.id.nav_location1).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location2).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location3).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location4).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location5).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location6).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location7).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location8).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location9).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location10).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location11).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location12).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location13).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location14).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location15).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location16).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location17).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location18).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location19).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location20).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location21).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location22).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location23).setVisible(true);navigationView.getMenu().findItem(R.id.nav_location24).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_location25).setVisible(true);
            } else if(navLocation == "show") { // navColorShow 값에 따라 show/hide 진행
                navLocation = "hide";
                item.setIcon(R.drawable.nav_down);
                // 하위 개별 Location 값 숨기기
                navigationView.getMenu().findItem(R.id.nav_location1).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location2).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location3).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location4).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location5).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location6).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location7).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location8).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location9).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location10).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location11).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location12).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location13).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location14).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location15).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location16).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location17).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location18).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location19).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location20).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location21).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location22).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location23).setVisible(false);navigationView.getMenu().findItem(R.id.nav_location24).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_location25).setVisible(false);
            }
        }

        // 색상 선택. 선택한 색상 값에 1을 대입
        // 추후 해당 값과 사이즈 값을 곱한 값이 1인 경우에만 mysqlInventory값을 입력
        if (id == R.id.nav_color_all) {
            setColor = new int[]{1, 1, 1, 1, 1};    // 재고 값 업데이트 변수
            setInventoryValues();   // 재고 값 업데이트 진행
            for(int c=0;c<5;c++) {searchColor[c]=color[c];}// 색상 값 선택여부 설정. 선택한 색상 값을 리스트뷰 어뎁터에 전달하여 노출. 색상값이 공백일 경우 리스트뷰에 노출 안됨
            selectNavColor("all");  // 전체 색상에 체크 표시
        }
        else if (id == R.id.nav_color1) {setColor = new int[]{1, 0, 0, 0, 0}; setInventoryValues();for(int c=0;c<5;c++) {searchColor[c]="";
        }
            searchColor[0]=color[0];selectNavColor("color1");}
        else if (id == R.id.nav_color2) {setColor = new int[]{0, 1, 0, 0, 0}; setInventoryValues();for(int c=0;c<5;c++) {searchColor[c]="";
        }
            searchColor[1]=color[1];selectNavColor("color2");}
        else if (id == R.id.nav_color3) {setColor = new int[]{0, 0, 1, 0, 0}; setInventoryValues();for(int c=0;c<5;c++) {searchColor[c]="";
        }
            searchColor[2]=color[2];selectNavColor("color3");}
        else if (id == R.id.nav_color4) {setColor = new int[]{0, 0, 0, 1, 0}; setInventoryValues();for(int c=0;c<5;c++) {searchColor[c]="";
        }
            searchColor[3]=color[3];selectNavColor("color4");}
        else if (id == R.id.nav_color5) {setColor = new int[]{0, 0, 0, 0, 1}; setInventoryValues();for(int c=0;c<5;c++) {searchColor[c]="";
        }
            searchColor[4]=color[4];selectNavColor("color5");}

        // 사이즈 선택. 선택한 사이즈 값에 1을 대입
        // 추후 해당 값과 색상 값을 곱한 값이 1인 경우에만 mysqlInventory값을 입력
        if (id == R.id.nav_size_all) {
            setSize = new int[]{1, 1, 1, 1, 1, 1, 1, 1}; setInventoryValues();
            for(int s=0;s<8;s++) {searchSize[s]=size[s];}// 사이즈 = 값 선택여부 설정. 선택한 사이즈 값을 리스트뷰 어뎁터에 전달하여 노출. 사이즈값이 공백일 경우 리스트뷰에 노출 안됨
            selectNavSize("all");
        }
        else if (id == R.id.nav_size1) {setSize = new int[]{1, 0, 0, 0, 0, 0, 0, 0}; setInventoryValues();for(int s=0;s<8;s++) {searchSize[s]= "";
        }
            searchSize[0]=size[0];selectNavSize("size1");}
        else if (id == R.id.nav_size2) {setSize = new int[]{0, 1, 0, 0, 0, 0, 0, 0}; setInventoryValues();for(int s=0;s<8;s++) {searchSize[s]= "";
        }
            searchSize[1]=size[1];selectNavSize("size2");}
        else if (id == R.id.nav_size3) {setSize = new int[]{0, 0, 1, 0, 0, 0, 0, 0}; setInventoryValues();for(int s=0;s<8;s++) {searchSize[s]= "";
        }
            searchSize[2]=size[2];selectNavSize("size3");}
        else if (id == R.id.nav_size4) {setSize = new int[]{0, 0, 0, 1, 0, 0, 0, 0}; setInventoryValues();for(int s=0;s<8;s++) {searchSize[s]= "";
        }
            searchSize[3]=size[3];selectNavSize("size4");}
        else if (id == R.id.nav_size5) {setSize = new int[]{0, 0, 0, 0, 1, 0, 0, 0}; setInventoryValues();for(int s=0;s<8;s++) {searchSize[s]= "";
        }
            searchSize[4]=size[4];selectNavSize("size5");}
        else if (id == R.id.nav_size6) {setSize = new int[]{0, 0, 0, 0, 0, 1, 0, 0}; setInventoryValues();for(int s=0;s<8;s++) {searchSize[s]= "";
        }
            searchSize[5]=size[5];selectNavSize("size6");}
        else if (id == R.id.nav_size7) {setSize = new int[]{0, 0, 0, 0, 0, 0, 1, 0}; setInventoryValues();for(int s=0;s<8;s++) {searchSize[s]= "";
        }
            searchSize[6]=size[6];selectNavSize("size7");}
        else if (id == R.id.nav_size8) {setSize = new int[]{0, 0, 0, 0, 0, 0, 0, 1}; setInventoryValues();for(int s=0;s<8;s++) {searchSize[s]= "";
        }
            searchSize[7]=size[7];selectNavSize("size8");}

        // 지역 선택. 선택한 값에 지역구 명 대입. 추후 해당 값으로 재고 확인 진행
        //전체 선택되어 있을 경우, 선택한 버튼 값만 선택되어 있을 경우, 일반적인 버튼 선택의 경우 3가지 고려
        if (id == R.id.nav_location1) {setLocationValues(1, "강남구"); selectNavLocation(1);}      //selectNavLocation(1)는 해당 지역 값에 맞춰 UI 표시해주는 메소드
        else if (id == R.id.nav_location2) {setLocationValues(2, "강동구"); selectNavLocation(2);}
        else if (id == R.id.nav_location3) {setLocationValues(3, "강북구"); selectNavLocation(3);}
        else if (id == R.id.nav_location4) {setLocationValues(4, "강서구"); selectNavLocation(4);}
        else if (id == R.id.nav_location5) {setLocationValues(5, "관악구"); selectNavLocation(5);}
        else if (id == R.id.nav_location6) {setLocationValues(6, "광진구"); selectNavLocation(6);}
        else if (id == R.id.nav_location7) {setLocationValues(7, "구로구"); selectNavLocation(7);}
        else if (id == R.id.nav_location8) {setLocationValues(8, "금천구"); selectNavLocation(8);}
        else if (id == R.id.nav_location9) {setLocationValues(9, "노원구"); selectNavLocation(9);}
        else if (id == R.id.nav_location10) {setLocationValues(10, "도봉구"); selectNavLocation(10);}
        else if (id == R.id.nav_location11) {setLocationValues(11, "동대문구"); selectNavLocation(11);}
        else if (id == R.id.nav_location12) {setLocationValues(12, "동작구"); selectNavLocation(12);}
        else if (id == R.id.nav_location13) {setLocationValues(13, "마포구"); selectNavLocation(13);}
        else if (id == R.id.nav_location14) {setLocationValues(14, "서대문구"); selectNavLocation(14);}
        else if (id == R.id.nav_location15) {setLocationValues(15, "서초구"); selectNavLocation(15);}
        else if (id == R.id.nav_location16) {setLocationValues(16, "성동구"); selectNavLocation(16);}
        else if (id == R.id.nav_location17) {setLocationValues(17, "성북구"); selectNavLocation(17);}
        else if (id == R.id.nav_location18) {setLocationValues(18, "송파구"); selectNavLocation(18);}
        else if (id == R.id.nav_location19) {setLocationValues(19, "양천구"); selectNavLocation(19);}
        else if (id == R.id.nav_location20) {setLocationValues(20, "영등포구"); selectNavLocation(20);}
        else if (id == R.id.nav_location21) {setLocationValues(21, "용산구"); selectNavLocation(21);}
        else if (id == R.id.nav_location22) {setLocationValues(22, "은평구"); selectNavLocation(22);}
        else if (id == R.id.nav_location23) {setLocationValues(23, "종로구"); selectNavLocation(23);}
        else if (id == R.id.nav_location24) {setLocationValues(24, "중구"); selectNavLocation(24);}
        else if (id == R.id.nav_location25) {setLocationValues(25, "중랑구"); selectNavLocation(25);}
        return true;
    }

    // 색상 선택 시 체크 버튼 표시 메소드
    public void selectNavColor(String color) {
        // Drawer화면 연결. 버튼 선택 시 Drawer 값들을 조정하기 위해 연결함
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //모든 색상에 체크 표시 안함
        navigationView.getMenu().findItem(R.id.nav_color_all).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_color1).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_color2).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_color3).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_color4).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_color5).setIcon(R.drawable.nav_uncheck);

        //받아온 값에 맞춰 체크 표시
        switch(color) {
            case "all":navigationView.getMenu().findItem(R.id.nav_color_all).setIcon(R.drawable.nav_check);break;
            case "color1":navigationView.getMenu().findItem(R.id.nav_color1).setIcon(R.drawable.nav_check);break;
            case "color2":navigationView.getMenu().findItem(R.id.nav_color2).setIcon(R.drawable.nav_check);break;
            case "color3":navigationView.getMenu().findItem(R.id.nav_color3).setIcon(R.drawable.nav_check);break;
            case "color4":navigationView.getMenu().findItem(R.id.nav_color4).setIcon(R.drawable.nav_check);break;
            case "color5":navigationView.getMenu().findItem(R.id.nav_color5).setIcon(R.drawable.nav_check);break;
            default:break;
        }
    }

    // 사이즈 선택 시 체크 버튼 표시 메소드
    public void selectNavSize(String size) {
        // Drawer화면 연결. 버튼 선택 시 Drawer 값들을 조정하기 위해 연결함
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //모든 색상에 체크 표시 안함
        navigationView.getMenu().findItem(R.id.nav_size_all).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_size1).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_size2).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_size3).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_size4).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_size5).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_size6).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_size7).setIcon(R.drawable.nav_uncheck);
        navigationView.getMenu().findItem(R.id.nav_size8).setIcon(R.drawable.nav_uncheck);

        //받아온 값에 맞춰 체크 표시
        switch(size) {
            case "all":navigationView.getMenu().findItem(R.id.nav_size_all).setIcon(R.drawable.nav_check);break;
            case "size1":navigationView.getMenu().findItem(R.id.nav_size1).setIcon(R.drawable.nav_check);break;
            case "size2":navigationView.getMenu().findItem(R.id.nav_size2).setIcon(R.drawable.nav_check);break;
            case "size3":navigationView.getMenu().findItem(R.id.nav_size3).setIcon(R.drawable.nav_check);break;
            case "size4":navigationView.getMenu().findItem(R.id.nav_size4).setIcon(R.drawable.nav_check);break;
            case "size5":navigationView.getMenu().findItem(R.id.nav_size5).setIcon(R.drawable.nav_check);break;
            case "size6":navigationView.getMenu().findItem(R.id.nav_size6).setIcon(R.drawable.nav_check);break;
            case "size7":navigationView.getMenu().findItem(R.id.nav_size7).setIcon(R.drawable.nav_check);break;
            case "size8":navigationView.getMenu().findItem(R.id.nav_size8).setIcon(R.drawable.nav_check);break;
            default:break;
        }
    }

    // 지역 선택 시 체크 버튼 표시 메소드
    public void selectNavLocation(int locationNumber) {
        // Drawer화면 연결. 버튼 선택 시 Drawer 값들을 조정하기 위해 연결함
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        // 지역 선택 확인 변수
        String checkLocation = "";

        // 지역 선택값 확인. 전체 선택되어 있는지 확인
        for(int i=0; i<25; i++) {if(navLocationValues[i].equals("off")) {checkLocation="all_off";} else {checkLocation="else"; break;}}
        // 하나만 선택되어 있는지 확인
        if(checkLocation.equals("else")) {
            for(int i=0; i<25; i++) {
                if((i+1)==locationNumber) {if(navLocationValues[i]=="on") {checkLocation="one";} else {checkLocation="else"; break;}}   //선택한 버튼 값 확인
                else {if(navLocationValues[i]=="off") {checkLocation="one";} else {checkLocation="else"; break;}}    //선택하지 않은 버튼값 확인
            }
        }
        // 24번째 지역구 on 선택 후 25번째 새로운 지역구 선택 시 모든 지역에 off 값을 넣어줘 체크 표시를 모두 해제함
        if(checkLocation.equals("else")) {
            String checkAll = "";
            for(int i=0;i<25;i++) {
                if((i+1)==locationNumber) {if(navLocationValues[i]=="off") {checkLocation="all_on";} else {checkLocation="else"; break;}}
                else {if(navLocationValues[i]=="on") {checkLocation="all_on";} else {checkLocation="else"; break;}}
            }
        }

        // 지역 값에 맞춰 on/off 설정
        switch (checkLocation) {
            // 모든 지역구가 선택돼 있을 시 모두 off로 해제하고 선택한 지역구만 on을 넣어 체크 표시 진행
            case "all_off":for(int i=0;i<25;i++) {navLocationValues[i]="off";} navLocationValues[locationNumber-1]="on"; break;
            // 24번째 지역구 선택 후 25번째 지역구 선택 시 모든 체크를 해제
            case "all_on":for(int i=0;i<25;i++) {navLocationValues[i]="off";} break;
            // 지역구가 하나만 선택돼 있을 경우 같은 지역을 다시 누르면 모든 지역구에 off를 대입하여 체크 표시 해제
            case "one":for(int i=0;i<25;i++) {navLocationValues[i]="off";} break;
            // 일반적인 경우 지역구 선택 시 체크를 표시하며 다시 선택 시 체크 표시를 해제
            case "else":if(navLocationValues[locationNumber-1].equals("on")) {navLocationValues[locationNumber-1]="off";} else {navLocationValues[locationNumber-1]="on";}break;
            default:break;
        }

        // on/off 값에 맞춰 체크 표시 설정
        if(navLocationValues[0].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location1).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location1).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[1].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location2).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location2).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[2].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location3).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location3).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[3].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location4).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location4).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[4].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location5).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location5).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[5].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location6).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location6).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[6].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location7).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location7).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[7].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location8).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location8).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[8].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location9).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location9).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[9].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location10).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location10).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[10].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location11).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location11).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[11].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location12).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location12).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[12].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location13).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location13).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[13].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location14).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location14).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[14].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location15).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location15).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[15].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location16).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location16).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[16].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location17).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location17).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[17].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location18).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location18).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[18].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location19).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location19).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[19].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location20).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location20).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[20].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location21).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location21).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[21].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location22).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location22).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[22].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location23).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location23).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[23].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location24).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location24).setIcon(R.drawable.nav_uncheck);}
        if(navLocationValues[24].equals("on")) {navigationView.getMenu().findItem(R.id.nav_location25).setIcon(R.drawable.nav_check);} else {navigationView.getMenu().findItem(R.id.nav_location25).setIcon(R.drawable.nav_uncheck);}
    }

    // 메인 페이지의 색상, 사이즈, 지역 선택 버튼
    public void clickMainButton(boolean showColor, boolean showSize, boolean showLocation) {

        // 네비게이션 뷰 연결
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        // 선택한 그룹만 오픈하고 나머지 그룹은 숨기기
        if(showColor) {navColor = "show"; navSize = "hide"; navLocation = "hide";
            navigationView.getMenu().findItem(R.id.nav_color).setIcon(R.drawable.nav_up);
            navigationView.getMenu().findItem(R.id.nav_size).setIcon(R.drawable.nav_down);
            navigationView.getMenu().findItem(R.id.nav_location).setIcon(R.drawable.nav_down);}
        else if(showSize) {navColor = "hide"; navSize = "show"; navLocation = "hide";
            navigationView.getMenu().findItem(R.id.nav_color).setIcon(R.drawable.nav_down);
            navigationView.getMenu().findItem(R.id.nav_size).setIcon(R.drawable.nav_up);
            navigationView.getMenu().findItem(R.id.nav_location).setIcon(R.drawable.nav_down);}
        else if(showLocation) {navColor = "hide"; navSize = "hide"; navLocation = "show";
            navigationView.getMenu().findItem(R.id.nav_color).setIcon(R.drawable.nav_down);
            navigationView.getMenu().findItem(R.id.nav_size).setIcon(R.drawable.nav_down);
            navigationView.getMenu().findItem(R.id.nav_location).setIcon(R.drawable.nav_up);}
        else {  // 색상, 사이즈, 지역 값이 모두 선택되지 않은 경우. '초기화' 버튼을 눌렀을 때 실행
            navColor = "hide"; navSize = "hide"; navLocation = "hide";
            navigationView.getMenu().findItem(R.id.nav_color).setIcon(R.drawable.nav_down);
            navigationView.getMenu().findItem(R.id.nav_size).setIcon(R.drawable.nav_down);
            navigationView.getMenu().findItem(R.id.nav_location).setIcon(R.drawable.nav_down);}

        /** 색상, 사이즈, 지역 선택 값에 맞춰 show/hide 진행 */
        // 하위 개별 Color 값 show/hide
        navigationView.getMenu().findItem(R.id.nav_color_all).setVisible(showColor);
        if (!color[0].equals("")) {navigationView.getMenu().findItem(R.id.nav_color1).setVisible(showColor);}
        if (!color[1].equals("")) {navigationView.getMenu().findItem(R.id.nav_color2).setVisible(showColor);}
        if (!color[2].equals("")) {navigationView.getMenu().findItem(R.id.nav_color3).setVisible(showColor);}
        if (!color[3].equals("")) {navigationView.getMenu().findItem(R.id.nav_color4).setVisible(showColor);}
        if (!color[4].equals("")) {navigationView.getMenu().findItem(R.id.nav_color5).setVisible(showColor);}

        // 하위 개별 Size 값 show/hide
        navigationView.getMenu().findItem(R.id.nav_size_all).setVisible(showSize);
        if (!size[0].equals("")) {navigationView.getMenu().findItem(R.id.nav_size1).setVisible(showSize);}
        if (!size[1].equals("")) {navigationView.getMenu().findItem(R.id.nav_size2).setVisible(showSize);}
        if (!size[2].equals("")) {navigationView.getMenu().findItem(R.id.nav_size3).setVisible(showSize);}
        if (!size[3].equals("")) {navigationView.getMenu().findItem(R.id.nav_size4).setVisible(showSize);}
        if (!size[4].equals("")) {navigationView.getMenu().findItem(R.id.nav_size5).setVisible(showSize);}
        if (!size[5].equals("")) {navigationView.getMenu().findItem(R.id.nav_size6).setVisible(showSize);}
        if (!size[6].equals("")) {navigationView.getMenu().findItem(R.id.nav_size7).setVisible(showSize);}
        if (!size[7].equals("")) {navigationView.getMenu().findItem(R.id.nav_size8).setVisible(showSize);}

        // 하위 개별 지역 값 show/hide
        navigationView.getMenu().findItem(R.id.nav_location1).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location2).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location3).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location4).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location5).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location6).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location7).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location8).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location9).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location10).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location11).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location12).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location13).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location14).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location15).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location16).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location17).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location18).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location19).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location20).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location21).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location22).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location23).setVisible(showLocation);navigationView.getMenu().findItem(R.id.nav_location24).setVisible(showLocation);
        navigationView.getMenu().findItem(R.id.nav_location25).setVisible(showLocation);
    }

    //Drawer이 열린 상태로 back버튼을 눌렀을 때 꺼지는 게 아니라 Drawer화면이 메인 화면으로 돌아옴
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    //검색 진행 메소드
    public void doSearch() {

        listView = (ListView) findViewById(R.id.layout_inventory_list);    //리스트뷰 연결
        list = new ArrayList<ConfirmInventoryActivity_CustomDTO>();    //검색 후의 값을 저장할 배열
        list.clear();  //반복해서 넣는 것을 방지하기 위해 배열 클리어 진행

        //색상, 사이즈, 지역 정보 선택 후 매장 노출
        for(int i=0;i<countInventory;i++) {   //JSP에서 받아온 재고 수만큼 반복
            // 재고값의 합이 0인지 여부 확인. 선택한 색상, 사이즈 값에만 mysql 값이 들어가 있음
            // 실질적으로 mysql에서 받아온 재고 값이 0인지 여부를 확인
            int sumInventory= 0;
            for(int c=0; c<5; c++) {
                for(int s=0; s<8; s++) {
                    sumInventory += searchInventory[i][c][s];
                }
            }

            // 재고값의 합이 0이 아닌 경우
            if(sumInventory != 0) {
                // 매장의 지역구 값이 선택한 지역구 중 하나일 경우
                for(int l=0;l<25;l++) {
                    if(searchStore[i][2].equals(setLocation[l])) {
                        // 조건이 모두 충족할 경우 리스트뷰에 매장 노출
                        list.add(new ConfirmInventoryActivity_CustomDTO(
                                // 매장 정보
                                searchStore[i][1],
                                searchStore[i][2],
                                // 자전거 정보
                                searchColor[0], searchColor[1], searchColor[2], searchColor[3], searchColor[4],
                                searchSize[0], searchSize[1], searchSize[2], searchSize[3],
                                searchSize[4], searchSize[5], searchSize[6], searchSize[7],
                                // 재고 정보
                                searchInventory[i][0][0], searchInventory[i][0][1], searchInventory[i][0][2], searchInventory[i][0][3],
                                searchInventory[i][0][4], searchInventory[i][0][5], searchInventory[i][0][6], searchInventory[i][0][7],
                                searchInventory[i][1][0], searchInventory[i][1][1], searchInventory[i][1][2], searchInventory[i][1][3],
                                searchInventory[i][1][4], searchInventory[i][1][5], searchInventory[i][1][6], searchInventory[i][1][7],
                                searchInventory[i][2][0], searchInventory[i][2][1], searchInventory[i][2][2], searchInventory[i][2][3],
                                searchInventory[i][2][4], searchInventory[i][2][5], searchInventory[i][2][6], searchInventory[i][2][7],
                                searchInventory[i][3][0], searchInventory[i][3][1], searchInventory[i][3][2], searchInventory[i][3][3],
                                searchInventory[i][3][4], searchInventory[i][3][5], searchInventory[i][3][6], searchInventory[i][3][7],
                                searchInventory[i][4][0], searchInventory[i][4][1], searchInventory[i][4][2], searchInventory[i][4][3],
                                searchInventory[i][4][4], searchInventory[i][4][5], searchInventory[i][4][6], searchInventory[i][4][7]));
                    }
                }
            }
        }

        //데이터를 어뎁터에 세팅
        ConfirmInventoryActivity_CustomAdapter adapter = new ConfirmInventoryActivity_CustomAdapter(
                getApplicationContext(),
                R.layout.activity_confirm_inventory_row,
                list);
        listView.setAdapter(adapter);    //리스트 뷰에 어뎁터 세팅
    }

    // 매장 정보 입력 메소드. ConfirmInventoryActivity 실행 시 한 번만 실행됨
    public void setStoreValues() {

        //데이터베이스 및 테이블 open
        final MainActivity_SQLiteHandler handler = MainActivity_SQLiteHandler.open(getApplicationContext());
        Cursor c = handler.select_store();
        c.moveToLast();                                //전체 테이블의 데이터를 세기 위해 커서 c를 테이블의 맨 끝으로 이동
        int Length = c.getCount();                    //현재 row 번호(=전체 row 개수)
        c.moveToFirst();

        //storeName과 동일한 값을 가진 sqlite 행 값 선택(corsor로 선택)
        for (int i=0; i<Length; i++) {  //sqlite의 매장 테이블 만큼 반복
            for(int j=0; j<countInventory; j++) {   //JSP에서 받아온 재고 수만큼 반복
                if(c.getString(1).equals(mysqlStoreId[j])) {
                    searchStore[j][0] = mysqlStoreId[j];   // 매장ID 저장
                    searchStore[j][1] = c.getString(3);     // 매장명 저장
                    searchStore[j][2] = c.getString(2);     // 매장 지역구 저장
                }
            }
            c.moveToNext();
        }
        //데이터베이스 및 테이블 close
        handler.close();
    }

    // 색상, 사이즈에 맞춰 재고값을 입력하는 메소드. 선택한 색상, 사이즈에만 재고를 입력. 나머지는 0을 대입
    // 버튼 선택 시마다 실행됨
    public void setInventoryValues() {

        for(int i=0; i<countInventory; i++) {   // JSP 불러올 때의 매장 수 값에 맞춰 countInventory만큼 반복 진행
            for(int c=0;c<5;c++) {
                for(int s=0; s<8; s++) {
                    if (setColor[c] * setSize[s] == 0) {searchInventory[i][c][s] = 0;}  //색상, 사이즈를 선택하지 않은 경우 재고값에 0 대입
                    else {searchInventory[i][c][s] = mysqlInventory[i][c][s];}  //색상, 재고값을 모두 선택한 경우 재고값에 mysqlInventory 값 대입
                }
            }
        }
    }

    // 지역 버튼 검색값 설정 메소드. 이후 선택한 지역 값으로 리스트뷰에 넣을 매장 정보 검색 진행
    // 지역 버튼 선택 시마다 실행됨
    public void setLocationValues(int locationNumber, String location) { //지역구 순서값과 지역명 값을 받아옴
        // checkLocation값은 기존에 선택된 지역 값을 "all", "one", "else" 값으로 지정
        // all: 모든지역, one: 선택한 버튼의 지역, else: 그외
        String checkLocation = "";

        // 전체선택 확인
        for(int i=0;i<25;i++) {if(setLocation[i]!="") {checkLocation = "all";} else {checkLocation = "else"; break;}}

        //checkLocation 값이 else일 경우에만 값이 하나인지 확인. all이면 하나인지 확인하는 과정은 따로 안 거침
        if(checkLocation=="else") {
            // 하나만 선택되어 있는지 확인
            for(int i=0;i<25;i++) {
                if((i+1)==locationNumber) {if(setLocation[i]!="") {checkLocation="one";} else {checkLocation="else"; break;}} //선택한 버튼값 확인
                else {if(setLocation[i]=="") {checkLocation="one";} else {checkLocation="else"; break;}}  //선택하지 않은 버튼값 확인
            }
        }
        // 24번째 선택 후 25번째 새로운 지역구 값을 선택하면 25개 지역 모두 지역구 값이 들어감
        // UI는 모든 체크를 해제하기 위해 추가 작업이 필요하나 리스트뷰 검색을 위한 지역 변수 설정에는 추가적으로 해야할 것이 없음
        // 24번째 지역 선택 후 25번째 지역구 선택 시 모든 지역구 값을 검색해야 하는데 모든 지역구 값이 들어가서 검색을 모두 하기 때문임

        // 선택 여부에 따라 지역구 값 대입
        switch (checkLocation) {
            // 기존에 값이 모두 들어가 있을 경우 선택 시 모두 공백으로 두고 하나만 지역구 값 입력
            case "all":for(int i=0;i<25;i++) {setLocation[i]="";} setLocation[locationNumber-1]=location;break;
            // 하나만 선택된 것을 다시 선택할 경우 모든 지역구 값 입력
            case "one":
                setLocation[0]="강남구";setLocation[1]="강동구";setLocation[2]="강북구";setLocation[3]="강서구";setLocation[4]="관악구";
                setLocation[5]="광진구";setLocation[6]="구로구";setLocation[7]="금천구";setLocation[8]="노원구";setLocation[9]="도봉구";
                setLocation[10]="동대문구";setLocation[11]="동작구";setLocation[12]="마포구";setLocation[13]="서대문구";setLocation[14]="서초구";
                setLocation[15]="성동구";setLocation[16]="성북구";setLocation[17]="송파구";setLocation[18]="양천구";setLocation[19]="영등포구";
                setLocation[20]="용산구";setLocation[21]="은평구";setLocation[22]="종로구";setLocation[23]="중구";setLocation[24]="중랑구";
                break;
            // 일반적인 경우 지역구 값이 들어가 있을 경우 공백으로 입력. 공백일 경우 지역구 값 입력
            case "else":if(setLocation[locationNumber-1].equals(location)) {setLocation[locationNumber-1]="";} else {setLocation[locationNumber-1]=location;} break;
            default:break;
        }
    }

    // JSP 통신 메소드. 자전거명 및 연식을 JSP에 넘긴 후 JSP에서 MySQL에 접속
    // Inventory_all 테이블에서 매장 별 재고값을 받아온 후 다시 android로 넘겨줌
    private class getInventoryFromMySQL extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... sId) {
            String sResult = "Error";

            try {

                //URL 설정 및 접속
                URL url = new URL("http://zlabike.com/appConnection.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                //전송모드 설정
                conn.setRequestMethod("POST");
                //conn.setRequestProperty("Connection","Keep-Alive");
                //conn.setDoOutput(true);
                //conn.setDoInput(true);

                // 전송 변수. 자전거명과 자전거연식 정보 전달하여 선택한 자전거를 취급하는 매장을 확인함
                // 자전거명에 연식까지 해줘야 자전거 별로 구분을 할 수 있음
                String sendBicycleName = bicycleName;
                String sendBicycleYear = bicycleYear + "년";        // android에는 year 데이터형이 int이고 mysql은 '2016년'으로 string으로 설정돼 있음 차후 통일 필요

                //StringBuffer에 전송할 변수값 저장
                //저장 순서는 전송 매개변수, "="값, 실제 전송되는 데이터 순으로 연결함. 마지막에 "&"는 데이터 구분 변수
                StringBuffer buffer = new StringBuffer();
                buffer.append("sendBicycleName").append("=").append(sendBicycleName).append("&");
                buffer.append("sendBicycleYear").append("=").append(sendBicycleYear);

                //JSP로 데이터 전송
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                osw.write(buffer.toString());
                osw.flush();

                //JSP에서 데이터 받아오기 연결
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                String str; // JSP에서 읽어온 값을 저장하는 변수

                //JSP에서 읽어올 때 순서 맞추기 위해 reader.readLine() 4번 먼저 진행
                reader.readLine(); reader.readLine(); reader.readLine(); reader.readLine();
                //JSP에서 한줄씩 받아오며 값을 str에 저장. 해당 값을 매장 id 및 매장 재고 값에 입력
                for(;;) {
                    if((str = reader.readLine()) != null && (str != "") && (str != " ") && (str != "null")) {
                        //MySQL의 매장 id를 내부 변수 mysqlStoreId에 저장
                        mysqlStoreId[countInventory] = str;
                        //색상 및 사이즈에 맞춰 for문 반복
                        for(int c=0; c<5; c++) {
                            for(int s=0; s<8; s++) {
                                str = reader.readLine();
                                //MySQL의 매장 재고를 내부 변수 mysqlInventory에 저장. 받아온 값이 String이므로 int형으로 변환한 후 저장
                                mysqlInventory[countInventory][c][s] = Integer.parseInt(str);
                            }
                        }
                        countInventory++;    //JSP에서 값을 제대로 읽어올 때 재고 순서값을 증가시킴
                    } else if(str == null && str == "null") {
                        //MySQL에서 읽어온 값이 null일 경우 for문 종료
                        break;
                    } else {
                        break;
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sResult;
        }

        // doInBackground가 끝난 후 onPostExcute 실행
        // doInBackground에서 MySQL 데이터를 받아온 후 onPostExcute에서 데이터를 android 검색 값에 저장 후 리스트뷰 검색 진행
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // JSP에서 매장ID, 재고 데이터 받아온 후 초기 메소드 실행
            setStoreValues();       // 리스트뷰에 노출할 매장 정보 입력. 시작 시 한 번만 진행
            setInventoryValues();   // 리스트뷰에 노출할 재고 정보 입력. 시작 시 한 번 진행하며 이후 색상, 사이즈 버튼 클릭 시에도 입력
            doSearch();             // 색상, 사이즈, 지역 정보에 맞춰 리스트뷰 검색 진행. 이후 버튼 클릭 시에도 검색 진행

            // 프로그래스 바 감추기
            ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);

            // AsyncTask 연결 종료
            connectJSP.cancel(true);
        }

        // 백그라운드 실행 취소 시 실행
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}