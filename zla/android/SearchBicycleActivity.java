package com.zla.android;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
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

public class SearchBicycleActivity extends AppCompatActivity {

    //google analytics
    private Tracker mTracker;

    //액션바 연결 변수
    Menu menu;

    //searchview 변수
    SearchView searchView;

    //자전거 검색 리스트뷰 및 리스트 초기설정
    ListView listView;
    ArrayList<ListActivity_CustomDTO> list;

    //search 변수
    String bicycleKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbicycle);

        //google analytics
        //Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("keyword search screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        listView = (ListView) findViewById(R.id.layoutListView);    //리스트뷰 연결

        // 리스트 세부 아이템 클릭(클릭 시 상세페이지로 이동)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                //보낼 데이터 생성
                String bicycleName = list.get(position).txtBicycle;
                int bicycleYear = list.get(position).year;

                //bicycleSeries값을 테이블 내 자전거명으로 검색하여 road 또는 mtb로 구분
                //데이터베이스 생성 및 bicycleSeries에 맞춰 테이블 연결
                MainActivity_SQLiteHandler handler = MainActivity_SQLiteHandler.open(getApplicationContext());
                //초기값으로 로드 테이블에 커서를 설정
                Cursor c = handler.select_road();

                //자전거 리스트 검색 시 필요한 변수
                c.moveToLast();                        //데이터베이스 열 크기 확인을 위해 c를 마지막 행으로 이동
                final int length = c.getCount();            //데이터베이스 열의 크기 저장
                c.moveToFirst();                      //데이터 검색 시 c를 사용하기 위해 첫 행으로 이동

                //자전거 종류 변수 초기값 설정
                String bicycleSeries = "mtb";

                for (int i = 0; i < length; i++) {    //로드 데이터베이스 조건검색 진행

                    if(bicycleName.equals(c.getString(4))) {
                        bicycleSeries = "road";
                        break;
                    }
                    c.moveToNext();    //다음 행으로 이동
                } handler.close();    //데이터베이스 및 테이블 close

                //화면 전환하는 객체 선언
                Intent intent = new Intent().setClass(SearchBicycleActivity.this, DetailActivity.class);
                intent.putExtra("bicycleSeries", bicycleSeries);            //자전거 종류 구분
                intent.putExtra("bicycleName", bicycleName);              //자전거 구분(년식마다 이름이 동일한 게 있어서 이름+연식으로 구분)
                intent.putExtra("bicycleYear", bicycleYear);
                //화면 전환 메소드
                startActivity(intent);
            }
        });

    }

    public void doSearch(String bicycleSeries) {

        //데이터베이스 생성 및 bicycleSeries에 맞춰 테이블 연결
        MainActivity_SQLiteHandler handler = MainActivity_SQLiteHandler.open(getApplicationContext());

        //make search accodding to query - road
        Cursor c = handler.searchBicycle("road", bicycleKeyword);

        //자전거 리스트 검색 시 필요한 변수
        c.moveToLast();                        //데이터베이스 열 크기 확인을 위해 c를 마지막 행으로 이동
        int length = c.getCount();            //데이터베이스 열의 크기 저장
        c.moveToFirst();                      //데이터 검색 시 c를 사용하기 위해 첫 행으로 이동
        NumberFormat nf = NumberFormat.getInstance();    //가격 "," 찍기 위한 객체 선언

        //자전거 리스트 검색 후 출력
        //listView = (ListView) findViewById(R.id.layoutListView);    //리스트뷰 연결
        list = new ArrayList<ListActivity_CustomDTO>();    //검색 후의 값을 저장할 배열(리스트 세부 아이템 클릭용으로 final 지정해둠)
        list.clear();  //반복해서 넣는 것을 방지하기 위해 배열 클리어 진행

        for (int i = 0; i < length; i++) {    //로드 데이터베이스 조건검색 진행
            //키워드 검색
            if (true) {
                //조건검색이 모두 맞을 시에만 ListActivity_CustomDTO에 행 값 추가
                String resName = "@mipmap/" + c.getString(6);    //res폴더에 있는 이미지명
                list.add(new ListActivity_CustomDTO(
                        //문자열로 res폴더의 이미지 가져오기
                        getResources().getIdentifier(resName, "mipmap", "com.zla.android"),
                        c.getString(0),                                //자전거명
                        c.getString(1),                                //브랜드
                        c.getInt(2),                                   //연식
                        "프레임: " + c.getString(3),                    //프레임
                        "구동계: " + c.getString(4),                   //구동계
                        "정찰가: " + nf.format(c.getInt(5)) + "원"));    //가격
            }
            c.moveToNext();    //다음 행으로 이동
        }

        //make search accodding to query - mtb
        c = handler.searchBicycle("mtb", bicycleKeyword);

        //자전거 리스트 검색 시 필요한 변수
        c.moveToLast();                        //데이터베이스 열 크기 확인을 위해 c를 마지막 행으로 이동
        length = c.getCount();            //데이터베이스 열의 크기 저장
        c.moveToFirst();                      //데이터 검색 시 c를 사용하기 위해 첫 행으로 이동

        for (int i = 0; i < length; i++) {    //로드 데이터베이스 조건검색 진행
            //키워드 검색
            if (true) {
                //조건검색이 모두 맞을 시에만 ListActivity_CustomDTO에 행 값 추가
                String resName = "@mipmap/" + c.getString(6);    //res폴더에 있는 이미지명
                list.add(new ListActivity_CustomDTO(
                        //문자열로 res폴더의 이미지 가져오기
                        getResources().getIdentifier(resName, "mipmap", "com.zla.android"),
                        c.getString(0),                                //자전거명
                        c.getString(1),                                //브랜드
                        c.getInt(2),                                   //연식
                        "프레임: " + c.getString(3),                    //프레임
                        "구동계: " + c.getString(4),                   //구동계
                        "정찰가: " + nf.format(c.getInt(5)) + "원"));    //가격
            }
            c.moveToNext();    //다음 행으로 이동
        } handler.close();    //데이터베이스 및 테이블 close

        //데이터를 어뎁터에 세팅
        ListActivity_CustomAdapter adapter = new ListActivity_CustomAdapter(
                getApplicationContext(),
                R.layout.activity_list_row,
                list);
        listView.setAdapter(adapter);    //리스트 뷰에 어뎁터 세팅
    }

    //액션바 내용
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_searchbicycle, menu);

        //뒤로가기 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Create the search view
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("자전거 명을 입력하세요");

        //make searchview open when new activity open
        menu.findItem(R.id.action_search).expandActionView();

        //menuItem expand and collapse
        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.action_search), new MenuItemCompat.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                //finish activity when people press the home button or back button
                finish();
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String arg0) {
                Log.w("#####", "검색어 : " + arg0);
                bicycleKeyword = arg0;

                //search road, mtb table
                doSearch("road");
                //doSearch("mtb");
                //submit the string into arg0 that is string people submit

                return false;
            }

            @Override
            public boolean onQueryTextChange(String arg0) {
                return false;
            }
        });

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
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
