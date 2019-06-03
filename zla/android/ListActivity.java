package com.zla.android;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    //google analytics
    private Tracker mTracker;

    //액션바 연결 변수
    Menu menu;

    //자전거 검색 리스트뷰 및 리스트 초기설정
    ListView listView;
    ArrayList<ListActivity_CustomDTO> list;

    //자전거 종류 변수
    String bicycleSeries;
    //검색 변수
    String[] brand = {"궤르쵸티", "라피에르", "룩", "리들리", "마린", "마지", "맥킨리", "메리다", "보드만", "비앙키", "삼천리",
            "세파스", "스캇", "스페셜라이즈드", "시포", "써벨로", "알톤", "엘파마", "예거", "오베아", "윌리어", "자이언트",
            "제이미스", "첼로", "캐논데일", "케스트렐", "코메트", "콜럼버스", "큐브", "타임", "트렉", "트리곤", "포커스", "후지", "GT"};
    String[] frame = {"스틸", "알루미늄", "카본", "티타늄"};
    int[] year = {2015, 2016};
    int minPrice = 0, maxPrice = 100000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //google analytics
        //Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("list screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        //메인액티비티로부터 인텐트 얻기
        Intent intent = getIntent();
        //자전거 종류 구분 변수 전달
        bicycleSeries = intent.getStringExtra("bicycleSeries");
        //검색 변수 전달
        for(int i=0; i < brand.length; i++) {brand[i] = intent.getStringExtra("brand" + (i+1));}
        for(int i=0; i < frame.length; i++) {frame[i] = intent.getStringExtra("frame" + (i+1));}
        for(int i=0; i < year.length; i++) {year[i] = intent.getIntExtra("year" + (i + 1), 0);}
        minPrice = intent.getIntExtra("minPrice", 0); maxPrice = intent.getIntExtra("maxPrice", 0);

        //검색 진행
        doSearch();

        // 리스트 세부 아이템 클릭(클릭 시 상세페이지로 이동)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                //보낼 데이터 생성
                String bicycleName = list.get(position).txtBicycle;
                int bicycleYear = list.get(position).year;

                //화면 전환하는 객체 선언
                Intent intent = new Intent().setClass(ListActivity.this, DetailActivity.class);
                intent.putExtra("bicycleSeries", bicycleSeries);            //자전거 종류 구분
                intent.putExtra("bicycleName", bicycleName);              //자전거 구분(년식마다 이름이 동일한 게 있어서 이름+연식으로 구분)
                intent.putExtra("bicycleYear", bicycleYear);
                //화면 전환 메소드
                startActivity(intent);
            }
        });
    }

    public void doSearch() {

        //데이터베이스 생성 및 bicycleSeries에 맞춰 테이블 연결
        MainActivity_SQLiteHandler handler = MainActivity_SQLiteHandler.open(getApplicationContext());
        //초기값으로 로드 테이블에 커서를 설정
        Cursor c = handler.select_road();
        //bicycleSeries에 따라 테이블 값 설정
        switch(bicycleSeries) {
            case "road": c = handler.select_road(); break;
            case "mtb": c = handler.select_mtb(); break;
            default: break;
        }

        //자전거 리스트 검색 시 필요한 변수
        c.moveToLast();                        //데이터베이스 열 크기 확인을 위해 c를 마지막 행으로 이동
        final int length = c.getCount();            //데이터베이스 열의 크기 저장
        c.moveToFirst();                      //데이터 검색 시 c를 사용하기 위해 첫 행으로 이동
        NumberFormat nf = NumberFormat.getInstance();    //가격 "," 찍기 위한 객체 선언

        //자전거 리스트 검색 후 출력
        listView = (ListView) findViewById(R.id.layoutListView);    //리스트뷰 연결
        list = new ArrayList<ListActivity_CustomDTO>();    //검색 후의 값을 저장할 배열(리스트 세부 아이템 클릭용으로 final 지정해둠)
        list.clear();  //반복해서 넣는 것을 방지하기 위해 배열 클리어 진행

        for (int i = 0; i < length; i++) {    //로드 데이터베이스 조건검색 진행
            //년도 조건검색
            if (c.getInt(3) == year[0] || c.getInt(3) == year[1]) {
                //브랜드 조건검색
                if (c.getString(1).equals(brand[0]) || c.getString(1).equals(brand[1]) || c.getString(1).equals(brand[2]) || c.getString(1).equals(brand[3]) ||
                        c.getString(1).equals(brand[4]) || c.getString(1).equals(brand[5]) || c.getString(1).equals(brand[6]) || c.getString(1).equals(brand[7]) ||
                        c.getString(1).equals(brand[8]) || c.getString(1).equals(brand[9]) || c.getString(1).equals(brand[10]) || c.getString(1).equals(brand[11]) ||
                        c.getString(1).equals(brand[12]) || c.getString(1).equals(brand[13]) || c.getString(1).equals(brand[14]) || c.getString(1).equals(brand[15]) ||
                        c.getString(1).equals(brand[16]) || c.getString(1).equals(brand[17]) || c.getString(1).equals(brand[18]) ||c.getString(1).equals(brand[19]) ||
                        c.getString(1).equals(brand[20]) || c.getString(1).equals(brand[21]) || c.getString(1).equals(brand[22]) ||c.getString(1).equals(brand[23]) ||
                        c.getString(1).equals(brand[24]) || c.getString(1).equals(brand[25]) || c.getString(1).equals(brand[26]) ||c.getString(1).equals(brand[27]) ||
                        c.getString(1).equals(brand[28]) || c.getString(1).equals(brand[29]) || c.getString(1).equals(brand[30]) ||c.getString(1).equals(brand[31]) ||
                        c.getString(1).equals(brand[32]) || c.getString(1).equals(brand[33]) || c.getString(1).equals(brand[34])) {
                    //프레임 조건검색
                    if (c.getString(35).equals(frame[0]) || c.getString(35).equals(frame[1]) || c.getString(35).equals(frame[2]) || c.getString(35).equals(frame[3])) {
                        //가격 조건검색
                        if (c.getInt(34) >= minPrice && c.getInt(34) <= maxPrice) {
                            //조건검색이 모두 맞을 시에만 ListActivity_CustomDTO에 행 값 추가
                            String resName = "@mipmap/" + c.getString(57);    //res폴더에 있는 이미지명
                            list.add(new ListActivity_CustomDTO(
                                    //문자열로 res폴더의 이미지 가져오기
                                    getResources().getIdentifier(resName, "mipmap", "com.zla.android"),
                                    c.getString(4),                                //자전거명
                                    c.getString(1),                                //브랜드
                                    c.getInt(3),                                   //연식
                                    "프레임: " + c.getString(35),                    //프레임
                                    "구동계: " + c.getString(38),                   //구동계
                                    "정찰가: " + nf.format(c.getInt(34)) + "원"));    //가격
                        }
                    }
                }
            } c.moveToNext();    //다음 행으로 이동
        } handler.close();    //데이터베이스 및 테이블 close

        //데이터를 어뎁터에 세팅
        ListActivity_CustomAdapter adapter = new ListActivity_CustomAdapter(
                getApplicationContext(),
                R.layout.activity_list_row,
                list);
        listView.setAdapter(adapter);    //리스트 뷰에 어뎁터 세팅
    }

    //SearchActivity를 생성하며 검색 변수값을 전달하는 함수
    public void makeSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        //자전거 종류 선택 변수 전달
        intent.putExtra("bicycleSeries", bicycleSeries);
        //검색 변수 전달 - 자전거 종류 변수는 메인액티비티에서 받아왔으나 이는 다시 전달할 필요가 없음. 로드, MTB 선택으로 리스트 액티비티에 값을 전달하기 때문에 저장할 필요 X
        for(int i=0; i < brand.length; i++) {intent.putExtra("brand" + (i+1), brand[i]);}
        for(int i=0; i < frame.length; i++) {intent.putExtra("frame" + (i+1), frame[i]);}
        for(int i=0; i < year.length; i++) {intent.putExtra("year" + (i + 1), year[i]);}
        intent.putExtra("minPrice", minPrice); intent.putExtra("maxPrice", maxPrice);
        startActivityForResult(intent, 1);
    }

    //Search 액티비티에서 검색 변수값 받기
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode) {
            case 1://SearchBrandActivity에서 데이터 받기
                //자전거 종류 구분 변수 전달
                bicycleSeries = data.getStringExtra("bicycleSeries");
                //검색 변수 전달
                for(int i=0; i < brand.length; i++) {brand[i] = data.getStringExtra("brand" + (i+1));}
                for(int i=0; i < frame.length; i++) {frame[i] = data.getStringExtra("frame" + (i+1));}
                for(int i=0; i < year.length; i++) {year[i] = data.getIntExtra("year" + (i+1), 0);}
                minPrice = data.getIntExtra("minPrice", 0); maxPrice = data.getIntExtra("maxPrice", 0);

                //검색 데이터를 받은 후 재검색 진행
                doSearch();
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
        inflater.inflate(R.menu.menu_list, menu);

        //뒤로가기 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            //로드, MTB 리스트에서 메인화면으로 넘어오는 값
            case android.R.id.home:

                onBackPressed();

                return true;
            //검색 버튼
            case R.id.action_search:
                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("list screen(actionbar): search button").setLabel("key").build());

                //SearchActivity 생성
                makeSearchActivity();
                return true;
            //찜목록 버튼
            case R.id.action_wishlist:
                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("list screen(actionbar): wishlist button").setLabel("key").build());

                //make WishListActivity
                Intent intentWishList = new Intent(this, WishListActivity.class);
                //화면 전환 메소드
                startActivity(intentWishList);
                break;
            case R.id.action_information:
                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("list screen: information button").setLabel("key").build());

                Intent informationIntent = new Intent().setClass(ListActivity.this, InformationListActivity.class);
                //화면 전환 메소드
                startActivity(informationIntent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //MainActivity로 검색변수 전달. 메인페이지에서도 변수값 유지하기 위해 전달함
        Intent returnIntent = new Intent();
        //검색 변수 전달
        for(int i=0; i < brand.length; i++) {returnIntent.putExtra("brand" + (i+1), brand[i]);}
        for(int i=0; i < frame.length; i++) {returnIntent.putExtra("frame" + (i+1), frame[i]);}
        for(int i=0; i < year.length; i++) {returnIntent.putExtra("year" + (i+1), year[i]);}
        returnIntent.putExtra("minPrice", minPrice); returnIntent.putExtra("maxPrice", maxPrice);

        setResult(1, returnIntent);
        finish();
    }
}
