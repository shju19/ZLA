package com.zla.android;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class RankingActivity extends AppCompatActivity {

    //google analytics
    private Tracker mTracker;

    //DetailActivity에 보낼 데이터
    String bicycleSeries;
    String bicycleName;
    int bicycleYear;
    String[] rankingImage = {"이미지1", "이미지2", "이미지3", "이미지4", "이미지5", "이미지6", "이미지7", "이미지8"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //google analytics
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("ranking screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        Intent intent = getIntent();                    //인텐트 얻기
        String rank = intent.getStringExtra("rank");    //MainActivity에서 데이터 받기

        switch(rank) {
            case "1순위":
                //DetailActivity에 보낼 데이터 선정
                bicycleSeries = "road"; bicycleName = "스칼라티 105"; bicycleYear = 2016;
                //image 선정
                rankingImage[0] = "rank1_1"; rankingImage[1] = "rank1_2"; rankingImage[2] = "rank1_3"; rankingImage[3] = "rank1_4";
                rankingImage[4] = "rank1_5"; rankingImage[5] = "rank1_6"; rankingImage[6] = "rank1_7"; rankingImage[7] = "rank1_8";
                break;
            case "2순위":
                //DetailActivity에 보낼 데이터 선정
                bicycleSeries = "road"; bicycleName = "XLR 1 CF"; bicycleYear = 2016;
                //image 선정
                rankingImage[0] = "rank2_1"; rankingImage[1] = "rank2_2"; rankingImage[2] = "rank2_3"; rankingImage[3] = "rank2_4";
                rankingImage[4] = "rank2_5"; rankingImage[5] = "rank2_6"; rankingImage[6] = "rank2_7"; rankingImage[7] = "rank2_8";
                break;
            case "3순위":
                //DetailActivity에 보낼 데이터 선정
                bicycleSeries = "road"; bicycleName = "스컬트라 100 "; bicycleYear = 2016;
                //image 선정
                rankingImage[0] = "rank3_1"; rankingImage[1] = "rank3_2"; rankingImage[2] = "rank3_3"; rankingImage[3] = "rank3_4";
                rankingImage[4] = "rank3_5"; rankingImage[5] = "rank3_6"; rankingImage[6] = "rank3_7"; rankingImage[7] = "rank3_8";
                break;
            default: break;
        }

        // itemList 배열에 sqlite의 이미지 주소를 넣어 viewPager로 이미지 출력
        int itemList[] = {getResources().getIdentifier("@mipmap/" + rankingImage[0], "mipmap", "com.zla.android"),
                getResources().getIdentifier("@mipmap/" + rankingImage[1], "mipmap", "com.zla.android"),
                getResources().getIdentifier("@mipmap/" + rankingImage[2], "mipmap", "com.zla.android"),
                getResources().getIdentifier("@mipmap/" + rankingImage[3], "mipmap", "com.zla.android"),
                getResources().getIdentifier("@mipmap/" + rankingImage[4], "mipmap", "com.zla.android"),
                getResources().getIdentifier("@mipmap/" + rankingImage[5], "mipmap", "com.zla.android"),
                getResources().getIdentifier("@mipmap/" + rankingImage[6], "mipmap", "com.zla.android"),
                getResources().getIdentifier("@mipmap/" + rankingImage[7], "mipmap", "com.zla.android")};

        //image swipe 기능
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        RankingActivity_ViewPagerAdapter adapter = new RankingActivity_ViewPagerAdapter(this, itemList);
        viewPager.setAdapter(adapter);

        //confirm_detail 버튼 클릭
        Button confirm_detail = (Button) findViewById(R.id.confirm_detail);
        confirm_detail.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //화면 전환하는 객체 선언
                        Intent intent = new Intent().setClass(RankingActivity.this, DetailActivity.class);
                        intent.putExtra("bicycleSeries", bicycleSeries);
                        intent.putExtra("bicycleName", bicycleName);
                        intent.putExtra("bicycleYear", bicycleYear);

                        //화면 전환 메소드
                        startActivity(intent);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
