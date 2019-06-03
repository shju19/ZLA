package com.zla.android;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.LruCache;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {

    public static Context mContext;

    //google analytics
    private Tracker mTracker;

    //액션바 연결 변수
    Menu menu;

    //검색 변수. 검색값을 메인페이지에 왔을 때도 유지하기 위해 메인페이지부터 변수 설정
    String[] brand = {"궤르쵸티", "라피에르", "룩", "리들리", "마린", "마지", "맥킨리", "메리다", "보드만", "비앙키", "삼천리",
            "세파스", "스캇", "스페셜라이즈드", "시포", "써벨로", "알톤", "엘파마", "예거", "오베아", "윌리어", "자이언트",
            "제이미스", "첼로", "캐논데일", "케스트렐", "코메트", "콜럼버스", "큐브", "타임", "트렉", "트리곤", "포커스", "후지", "GT"};
    String[] frame = {"스틸", "알루미늄", "카본", "티타늄"};
    int[] year = {2015, 2016};
    int minPrice = 0, maxPrice = 100000000;

    //매장검색 변수. 검색값을 메인페이지에 왔을 때도 유지하기 위해 메인페이지부터 변수 설정
    String[] location = {"강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구",
            "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};

    //백버튼 변수
    int backNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //google analytics
        //Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("main screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        // Enable Display Features. - 사용자 정보 획득
        mTracker.enableAdvertisingIdCollection(true);

        mContext = this;

        //메인페이지를 xml의 버튼과 연결
        Button buttonRoad = (Button) findViewById(R.id.buttonRoad);
        Button buttonMtb = (Button) findViewById(R.id.buttonMtb);
        Button buttonStore = (Button) findViewById(R.id.buttonStore);
        Button buttonWishlist = (Button) findViewById(R.id.buttonWishlist);

        //글꼴 객체 선언
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/10X10Bold.ttf");
        //버튼 글꼴 변경 - 버튼은 굵은 폰트로 표시
        buttonRoad.setTypeface(face); buttonMtb.setTypeface(face); buttonStore.setTypeface(face); buttonWishlist.setTypeface(face);

        //로드 버튼 클릭
        buttonRoad.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //google analytics button
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("main screen: road button").setLabel("key").build());

                        //리스트 액티비티 생성
                        makeListActivity("road");
                    }
                });

        //mtb 버튼 클릭
        buttonMtb.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //google analytics button
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("main screen: mtb button").setLabel("key").build());

                        //리스트 액티비티 생성
                        makeListActivity("mtb");
                    }
                });

        //매장 버튼 클릭
        buttonStore.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //google analytics button
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("main screen: store button").setLabel("key").build());

                        //리스트 액티비티 생성
                        makeStoreListActivity();
                    }
                });

        // 찜 목록 버튼 클릭
        buttonWishlist.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //google analytics button
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("main screen: wishlist button").setLabel("key").build());

                        //리스트 액티비티 생성
                        Intent intentWishList = new Intent().setClass(MainActivity.this, WishListActivity.class);
                        //화면 전환 메소드
                        startActivity(intentWishList);
                    }
                });
    }

    //리스트 액티비티를 생성하며 검색 변수값을 전달하는 함수
    public void makeListActivity(String bicycleSeries) {
        Intent intent = new Intent(this, ListActivity.class);
        //자전거 종류 구분 변수 전달
        intent.putExtra("bicycleSeries", bicycleSeries);
        //검색 변수 전달
        for(int i=0; i < brand.length; i++) {intent.putExtra("brand" + (i+1), brand[i]);}
        for(int i=0; i < frame.length; i++) {intent.putExtra("frame" + (i+1), frame[i]);}
        for(int i=0; i < year.length; i++) {intent.putExtra("year" + (i+1), year[i]);}
        intent.putExtra("minPrice", minPrice); intent.putExtra("maxPrice", maxPrice);
        startActivityForResult(intent, 1);
    }

    //매장 리스트 액티비티를 생성하며 검색 변수값을 전달하는 함수
    public void makeStoreListActivity() {
        Intent intent = new Intent(this, MainStoreListActivity.class);
        //검색 변수 전달
        for(int i=0; i < brand.length; i++) {intent.putExtra("brand" + (i+1), brand[i]);}
        for(int i=0; i < location.length; i++) {intent.putExtra("location" + (i+1), location[i]);}
        startActivityForResult(intent, 2);
    }

    //리스트 액티비티에서 검색 변수값 받기. 메인 액티비티에서도 검색 변수값을 유지하기 위해 값을 저장해놓음
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode) {
            case 1://SearchBrandActivity에서 데이터 받기
                //검색 변수 전달
                for(int i=0; i < brand.length; i++) {brand[i] = data.getStringExtra("brand" + (i+1));}
                for(int i=0; i < frame.length; i++) {frame[i] = data.getStringExtra("frame" + (i+1));}
                for(int i=0; i < year.length; i++) {year[i] = data.getIntExtra("year" + (i+1), 0);}
                minPrice = data.getIntExtra("minPrice", 0); maxPrice = data.getIntExtra("maxPrice", 0);
                break;
            case 2:
                for(int i=0; i < brand.length; i++) {brand[i] = data.getStringExtra("brand" + (i+1));}
                for(int i=0; i < location.length; i++) {location[i] = data.getStringExtra("location" + (i+1));}
                break;
            default:
                break;
        }
    }

    //액션바 내용
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            //로드, MTB 리스트에서 메인화면으로 넘어오는 값
            case android.R.id.home:
                finish();
                return true;
            //검색 버튼
            case R.id.action_search:
                //make WishListActivity
                Intent intentSearchBicycle = new Intent(this, SearchBicycleActivity.class);
                //화면 전환 메소드
                startActivity(intentSearchBicycle);
                break;
            //찜목록 버튼
            case R.id.action_wishlist:
                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("main screen(actionbar): wishlist button").setLabel("key").build());

                //make WishListActivity
                Intent intentWishList = new Intent(this, WishListActivity.class);
                //화면 전환 메소드
                startActivity(intentWishList);
                break;
            case R.id.action_information:
                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("main screen(actionbar): information button").setLabel("key").build());

                Intent informationIntent = new Intent().setClass(MainActivity.this, InformationListActivity.class);
                //화면 전환 메소드
                startActivity(informationIntent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {

        //빽(취소)키 누를 시 동작
        switch (backNumber) {
            case 0:
                finish();
                break;
            case 1:
                backNumber = 0;
                Toast toast = Toast.makeText(getApplicationContext(), "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                Handler handler_post = new Handler();
                handler_post.postDelayed(new Runnable() {
                    public void run() {
                        //해당 시간이 지나면 다시 backNumber를 1로 변경하여 '뒤로'버튼을 누를 시 다시 토스트 메시지 출력하게 함
                        backNumber = 1;
                    }
                }, 2000);
                break;
            default:
        }
    }
}
