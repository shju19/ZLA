package com.zla.android;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.text.NumberFormat;
import java.util.ArrayList;

public class WishListActivity extends AppCompatActivity {

    //google analytics
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //google analytics
        //Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("wishlist screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        //로드, MTB 자전거 리스트 검색 후 출력
        ListView listView = (ListView) findViewById(R.id.layoutListView);    //리스트뷰에 로드 리스트뷰 보여주기
        final ArrayList<ListActivity_CustomDTO> list = new ArrayList<ListActivity_CustomDTO>();    //검색 후의 값을 저장할 배열
        list.clear();  //반복해서 넣는 것을 방지하기 위해 배열 클리어 진행

        MainActivity_SQLiteHandler handler = MainActivity_SQLiteHandler.open(getApplicationContext());    //road 데이터베이스 생성
        Cursor c_road = handler.select_road();    //테이블 선택
        Cursor c_mtb = handler.select_mtb();

        c_road.moveToLast(); c_mtb.moveToLast(); //데이터베이스 열 크기 확인을 위해 c를 마지막 행으로 이동
        int rLength = c_road.getCount(), mLength = c_mtb.getCount();
        c_road.moveToFirst(); c_mtb.moveToFirst(); //데이터 검색 시 c를 사용하기 위해 첫 행으로 이동
        NumberFormat nf = NumberFormat.getInstance();    //가격 "," 찍기 위한 객체 선언

        for (int i = 0; i < rLength; i++) {    //로드 데이터베이스 조건검색 진행
            //MainActivity의 찜여부 값 조건검색
            if (c_road.getString(62).equals("yes")) {
                //조건검색이 맞을 시에만 ListActivity_CustomDTO에 행 값 추가
                String resName = "@mipmap/" + c_road.getString(57);    //res폴더에 있는 이미지명

                list.add(new ListActivity_CustomDTO(
                        //문자열로 res폴더의 이미지 가져오기
                        getResources().getIdentifier(resName, "mipmap", "com.zla.android"),
                        c_road.getString(4),                                //자전거명
                        c_road.getString(1),                                //브랜드
                        c_road.getInt(3),                                   //연식
                        "프레임: " + c_road.getString(35),                    //프레임
                        "구동계: " + c_road.getString(38),                   //구동계
                        "정찰가: " + nf.format(c_road.getInt(34)) + "원"));    //가격
            }
            c_road.moveToNext();    //다음 행으로 이동
        }
        for (int i = 0; i < mLength; i++) {    //MTB 데이터베이스 조건검색 진행
            //MainActivity의 찜여부 값 조건검색
            if (c_mtb.getString(62).equals("yes")) {
                //조건검색이 맞을 시에만 ListActivity_CustomDTO에 행 값 추가
                String resName = "@mipmap/" + c_mtb.getString(57);    //res폴더에 있는 이미지명

                list.add(new ListActivity_CustomDTO(
                        //문자열로 res폴더의 이미지 가져오기
                        getResources().getIdentifier(resName, "mipmap", "com.zla.android"),
                        c_mtb.getString(4),                                //자전거명
                        c_mtb.getString(1),                                //브랜드
                        c_mtb.getInt(3),                                   //연식
                        "프레임: " + c_mtb.getString(35),                    //프레임
                        "구동계: " + c_mtb.getString(38),                   //구동계
                        "정찰가: " + nf.format(c_mtb.getInt(34)) + "원"));    //가격
            }
            c_mtb.moveToNext();    //다음 행으로 이동
        }
        handler.close();    //데이터베이스 및 테이블 close

        //로드, MTB 데이터를 어뎁터에 세팅
        ListActivity_CustomAdapter adapter = new ListActivity_CustomAdapter(
                getApplicationContext(),
                R.layout.activity_list_row,
                list);
        listView.setAdapter(adapter);    //리스트 뷰에 어뎁터 세팅

        // 리스트 아이템 클릭 시 세부보기 페이지로 이동
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                //보낼 데이터 생성
                String bicycleName = list.get(position).txtBicycle;
                int bicycleYear = list.get(position).year;

                //화면 전환하는 객체 선언
                Intent intent = new Intent().setClass(WishListActivity.this, WishListDetailActivity.class);
                intent.putExtra("bicycleSeries", "wish");            //자전거 종류 구분
                intent.putExtra("bicycleName", bicycleName);              //자전거 구분(년식마다 이름이 동일한 게 있어서 이름+연식으로 구분)
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