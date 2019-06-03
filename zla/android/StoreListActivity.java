package com.zla.android;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.text.NumberFormat;
import java.util.ArrayList;

public class StoreListActivity extends AppCompatActivity {

    //google analytics
    private Tracker mTracker;

    //store listView
    ListView listView;

    //bicycle information
    String bicycleSeries;
    String bicycleName;
    int bicycleYear;
    String bicycleBrand;
    int brandSelectedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        //google analytics
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("storelist screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        Intent intent = getIntent();					//인텐트 얻기
        bicycleSeries = intent.getStringExtra("bicycleSeries");       //자전거 종류 구분
        bicycleName = intent.getStringExtra("bicycleName");
        bicycleYear = intent.getIntExtra("bicycleYear", 0);
        bicycleBrand = intent.getStringExtra("bicycleBrand");

        //브랜드에 맞춰 검색 진행할 때의 브랜드 값 설정. 매장 검색 시 브랜드 행을 맞춰주는 구문
        switch(bicycleBrand) {
            case "마지": brandSelectedNumber = 1; break;
            case "메리다": brandSelectedNumber = 3; break;
            case "보드만": brandSelectedNumber = 5; break;
            case "비앙키": brandSelectedNumber = 7; break;
            case "삼천리": brandSelectedNumber = 9; break;
            case "스캇": brandSelectedNumber = 11; break;
            case "스페셜라이즈드": brandSelectedNumber = 13; break;
            case "써벨로": brandSelectedNumber = 15; break;
            case "알톤": brandSelectedNumber = 17; break;
            case "엘파마": brandSelectedNumber = 19; break;
            case "윌리어": brandSelectedNumber = 21; break;
            case "자이언트": brandSelectedNumber = 23; break;
            case "첼로": brandSelectedNumber = 25; break;
            case "캐논데일": brandSelectedNumber = 27; break;
            case "트렉": brandSelectedNumber = 29; break;
            case "트리곤": brandSelectedNumber = 31; break;
            case "후지": brandSelectedNumber = 33; break;
            case "GT": brandSelectedNumber = 35; break;
            default: break;
        }

        //매장 xml과 리스트뷰를 연결. 리스트뷰를 클릭할 때 연결되어 있어야 함
        listView = (ListView) findViewById(R.id.layout_store_list);

        //자전거 리스트 검색 후 출력
        final ArrayList<ListActivity_Store_CustomDTO> list = new ArrayList<ListActivity_Store_CustomDTO>();    //검색 후의 값을 저장할 배열
        list.clear();  //반복해서 넣는 것을 방지하기 위해 배열 클리어 진행

        MainActivity_SQLiteHandler handler = MainActivity_SQLiteHandler.open(getApplicationContext());    //road 데이터베이스 생성
        Cursor c = handler.select_store();     //테이블 선택
        c.moveToLast();                        //데이터베이스 열 크기 확인을 위해 c를 마지막 행으로 이동
        int length = c.getCount();            //데이터베이스 열의 크기 저장
        c.moveToFirst();                      //데이터 검색 시 c를 사용하기 위해 첫 행으로 이동

        //자전거 종류에 맞게 검색값 변경
        if(bicycleSeries.equals("road")) { brandSelectedNumber += 1; }
        else if(bicycleSeries.equals("mtb")) { brandSelectedNumber += 2; }

        //조건에 맞는 자전거 리스트를 리스트에 추가
        for (int i = 0; i < length; i++) {
            // 브랜드 값 조건검색
            if(c.getString(16 + brandSelectedNumber).equals("o")) {
                //조건검색이 모두 맞을 시에만 ListActivity_Store_CustomDTO에 행 값 추가

                list.add(new ListActivity_Store_CustomDTO(
                        //문자열로 res폴더의 이미지 가져오기
                        c.getString(55),
                        c.getString(3),                                //매장명
                        c.getString(4),                                //위치
                        c.getString(9)));                              //매장연락처
            }
            c.moveToNext();    //다음 행으로 이동
        }
        handler.close();    //데이터베이스 및 테이블 close

        //데이터를 어뎁터에 세팅
        ListActivity_Store_CustomAdapter adapter = new ListActivity_Store_CustomAdapter(
                getApplicationContext(),
                R.layout.activity_store_list_low,
                list);
        listView.setAdapter(adapter);    //리스트 뷰에 어뎁터 세팅

        // 리스트 아이템 클릭 시 세부보기 페이지로 이동
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                //보낼 데이터 생성(리스트의 매장정보)
                String storeName = list.get(position).storeName;
                String contact = list.get(position).contact;

                //화면 전환하는 객체 선언
                Intent intent = new Intent().setClass(StoreListActivity.this, StoreActivity.class);
                //자전거 정보
                intent.putExtra("bicycleSeries", bicycleSeries);  //자전거 종류 구분
                intent.putExtra("bicycleName", bicycleName);
                intent.putExtra("bicycleYear", bicycleYear);
                //매장 정보
                intent.putExtra("storeName", storeName);
                intent.putExtra("contact", contact);

                //화면 전환 메소드
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_activity_store, menu);

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