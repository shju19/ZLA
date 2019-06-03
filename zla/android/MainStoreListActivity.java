package com.zla.android;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;

public class MainStoreListActivity extends AppCompatActivity {

    //google analytics
    private Tracker mTracker;

    //액션바 연결 변수
    Menu menu;

    //자전거 검색 리스트뷰 및 리스트 초기설정
    ListView listView;
    ArrayList<ListActivity_Store_CustomDTO> list;

    //자전거 종류 변수 - 임시로 로드로 진행. 추후 로드, MTB 구분 외에 all 구분을 두어 로드, MTB 중 하나라도 취급할 시 all에 체크하여 all을 불러오도록 진행
    String bicycleSeries;
    //검색 변수
    String[] brand = {"궤르쵸티", "라피에르", "룩", "리들리", "마린", "마지", "맥킨리", "메리다", "보드만", "비앙키", "삼천리",
            "세파스", "스캇", "스페셜라이즈드", "시포", "써벨로", "알톤", "엘파마", "예거", "오베아", "윌리어", "자이언트",
            "제이미스", "첼로", "캐논데일", "케스트렐", "코메트", "콜럼버스", "큐브", "타임", "트렉", "트리곤", "포커스", "후지", "GT"};
    String[] location = {"강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구",
            "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        //google analytics
        //Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("main storelist screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        //메인액티비티로부터 인텐트 얻기
        Intent intent = getIntent();
        //자전거 종류 구분 변수 전달
        //검색 변수 전달
        for(int i=0; i < location.length; i++) {location[i] = intent.getStringExtra("location" + (i+1));}
        for(int i=0; i < brand.length; i++) {brand[i] = intent.getStringExtra("brand" + (i+1));}

        //검색 진행
        doSearch();

        // 리스트 세부 아이템 클릭(클릭 시 상세페이지로 이동)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                //보낼 데이터 생성
                String storeName = list.get(position).storeName;
                String contact = list.get(position).contact;

                //화면 전환하는 객체 선언
                Intent intent = new Intent().setClass(MainStoreListActivity.this, MainStoreActivity.class);
                //매장 정보
                intent.putExtra("storeName", storeName);
                intent.putExtra("contact", contact);

                //화면 전환 메소드
                startActivity(intent);
            }
        });
    }

    public void doSearch() {

        //데이터베이스 생성 및 bicycleSeries에 맞춰 테이블 연결
        MainActivity_SQLiteHandler handler = MainActivity_SQLiteHandler.open(getApplicationContext());
        //초기값으로 로드 테이블에 커서를 설정
        Cursor c = handler.select_store();

        //자전거 리스트 검색 시 필요한 변수
        c.moveToLast();                        //데이터베이스 열 크기 확인을 위해 c를 마지막 행으로 이동
        final int length = c.getCount();            //데이터베이스 열의 크기 저장
        c.moveToFirst();                      //데이터 검색 시 c를 사용하기 위해 첫 행으로 이동

        //자전거 리스트 검색 후 출력
        listView = (ListView) findViewById(R.id.layout_store_list);
        list = new ArrayList<ListActivity_Store_CustomDTO>();    //검색 후의 값을 저장할 배열(리스트 세부 아이템 클릭용으로 final 지정해둠)
        list.clear();  //반복해서 넣는 것을 방지하기 위해 배열 클리어 진행

        for (int i = 0; i < length; i++) {    //로드 데이터베이스 조건검색 진행
            //브랜드 조건검색 - 임시로 제외
            //지역 조건검색
            if (c.getString(2).equals(location[0]) || c.getString(2).equals(location[1]) || c.getString(2).equals(location[2]) || c.getString(2).equals(location[3]) ||
                    c.getString(2).equals(location[4]) || c.getString(2).equals(location[5]) || c.getString(2).equals(location[6]) || c.getString(2).equals(location[7]) ||
                    c.getString(2).equals(location[8]) || c.getString(2).equals(location[9]) || c.getString(2).equals(location[10]) || c.getString(2).equals(location[11]) ||
                    c.getString(2).equals(location[12]) || c.getString(2).equals(location[13]) || c.getString(2).equals(location[14]) || c.getString(2).equals(location[15]) ||
                    c.getString(2).equals(location[16]) || c.getString(2).equals(location[17]) || c.getString(2).equals(location[18]) ||c.getString(2).equals(location[19]) ||
                    c.getString(2).equals(location[20]) || c.getString(2).equals(location[21]) || c.getString(2).equals(location[22]) ||c.getString(2).equals(location[23]) ||
                    c.getString(2).equals(location[24])) {
                //조건검색이 모두 맞을 시에만 ListActivity_Store_CustomDTO에 행 값 추가
                //String resName = "@mipmap/" + c.getString(54);    //res폴더에 있는 이미지명

                list.add(new ListActivity_Store_CustomDTO(
                        //문자열로 res폴더의 이미지 가져오기
                        c.getString(55),
                        c.getString(3),                                //매장명
                        c.getString(4),                                //위치
                        c.getString(9)));                              //매장연락처
            } c.moveToNext();    //다음 행으로 이동
        } handler.close();    //데이터베이스 및 테이블 close

        //데이터를 어뎁터에 세팅
        ListActivity_Store_CustomAdapter adapter = new ListActivity_Store_CustomAdapter(
                getApplicationContext(),
                R.layout.activity_store_list_low,
                list);
        listView.setAdapter(adapter);    //리스트 뷰에 어뎁터 세팅
    }

    //SearchActivity를 생성하며 검색 변수값을 전달하는 함수
    public void makeSearchActivity() {
        Intent intent = new Intent(this, MainStoreSearchActivity.class);
        //자전거 종류 선택 변수 전달
        //검색 변수 전달 - 자전거 종류 변수는 메인액티비티에서 받아왔으나 이는 다시 전달할 필요가 없음. 로드, MTB 선택으로 리스트 액티비티에 값을 전달하기 때문에 저장할 필요 X
        //for(int i=0; i < brand.length; i++) {intent.putExtra("brand" + (i+1), brand[i]);}
        for(int i=0; i < location.length; i++) {intent.putExtra("location" + (i+1), location[i]); Log.d("test1", location[i]);}
        startActivityForResult(intent, 1);
    }

    //Search 액티비티에서 검색 변수값 받기
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode) {
            case 1://SearchBrandActivity에서 데이터 받기
                //검색 변수 전달
                //for(int i=0; i < brand.length; i++) {brand[i] = data.getStringExtra("brand" + (i+1));}
                for(int i=0; i < location.length; i++) {location[i] = data.getStringExtra("location" + (i+1));}

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
                mTracker.send(new HitBuilders.EventBuilder().setCategory("main storelist screen(actionbar): search button").setLabel("key").build());

                //SearchActivity 생성
                makeSearchActivity();
                return true;
            //찜목록 버튼
            case R.id.action_wishlist:
                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("main storelist screen(actionbar): wishlist button").setLabel("key").build());

                //make WishListActivity
                Intent intentWishList = new Intent(this, WishListActivity.class);
                //화면 전환 메소드
                startActivity(intentWishList);
                break;
            case R.id.action_information:
                //google analytics button
                mTracker.send(new HitBuilders.EventBuilder().setCategory("main storelist screen: information button").setLabel("key").build());

                Intent informationIntent = new Intent().setClass(MainStoreListActivity.this, InformationListActivity.class);
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
        for(int i=0; i < brand.length; i++) {returnIntent.putExtra("brand" + (i + 1), brand[i]);}
        for(int i=0; i < location.length; i++) {returnIntent.putExtra("location" + (i + 1), location[i]);}

        setResult(2, returnIntent);
        finish();
    }
}
