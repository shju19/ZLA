package com.zla.android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import net.daum.mf.map.api.MapLayout;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class StoreActivity extends AppCompatActivity
        implements /*MapView.OpenAPIKeyAuthenticationResultListener,*/ MapView.MapViewEventListener, MapView.POIItemEventListener { //extends AppCompatActivity

    //google analytics
    private Tracker mTracker;

    //알림창 설정
    AlertDialog.Builder ad;

    //information in map
    String storeNameInMap;

    //using daum map
    private MapView mMapView;
    private MapPOIItem mCustomMarker;
    //daum map point
    private static MapPoint CUSTOM_MARKER_POINT = MapPoint.mapPointWithGeoCoord(37.537229, 127.005515);
    private ScrollView scrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        //google analytics
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("store screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        if (isNetWork()) {
            //네트워크 연결 시 그대로 진행
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "데이터 요청에 실패 하였습니다.\n사용중인 네트워크 상태를 확인해주세요.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            finish();
        }

        // 알림창 설정
        ad = new AlertDialog.Builder(this);

        //기본정보를 xml의 값과 매칭
        TextView storeName = (TextView) findViewById(R.id.storeName);
        TextView adress1 = (TextView) findViewById(R.id.adress1);
        TextView adress2 = (TextView) findViewById(R.id.adress2);
        TextView contact1 = (TextView) findViewById(R.id.contact1);
        TextView contact2 = (TextView) findViewById(R.id.contact2);
        TextView brand1 = (TextView) findViewById(R.id.brand1);
        TextView brand2 = (TextView) findViewById(R.id.brand2);
        TextView fitting1 = (TextView) findViewById(R.id.fitting1);
        TextView fitting2 = (TextView) findViewById(R.id.fitting2);
        TextView repair1 = (TextView) findViewById(R.id.repair1);
        TextView repair2 = (TextView) findViewById(R.id.repair2);
        //영업시간 및 매장위치
        TextView businessHour = (TextView) findViewById(R.id.businessHour);
        TextView businessHour_weekly1 = (TextView) findViewById(R.id.businessHour_weekly1);
        TextView businessHour_weekly2 = (TextView) findViewById(R.id.businessHour_weekly2);
        TextView businessHour_saturday1 = (TextView) findViewById(R.id.businessHour_saturday1);
        TextView businessHour_saturday2 = (TextView) findViewById(R.id.businessHour_saturday2);
        TextView businessHour_holiday1 = (TextView) findViewById(R.id.businessHour_holiday1);
        TextView businessHour_holiday2 = (TextView) findViewById(R.id.businessHour_holiday2);
        TextView holiday1 = (TextView) findViewById(R.id.holiday1);
        TextView holiday2 = (TextView) findViewById(R.id.holiday2);
        TextView storeLocation = (TextView) findViewById(R.id.storeLocation);
        //재고확인 버튼
        Button callStore1 = (Button) findViewById(R.id.callStore1);
        Button callStore2 = (Button) findViewById(R.id.callStore2);

        //글꼴 설정
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/10X10.ttf");
        storeName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/10X10Bold.ttf"));
        adress1.setTypeface(face);
        adress2.setTypeface(face);
        contact1.setTypeface(face);
        contact2.setTypeface(face);
        brand1.setTypeface(face);
        brand2.setTypeface(face);
        fitting1.setTypeface(face);
        fitting2.setTypeface(face);
        repair1.setTypeface(face);
        repair2.setTypeface(face);
        businessHour.setTypeface(face);
        businessHour_weekly1.setTypeface(face);
        businessHour_weekly2.setTypeface(face);
        businessHour_saturday1.setTypeface(face);
        businessHour_saturday2.setTypeface(face);
        businessHour_holiday1.setTypeface(face);
        businessHour_holiday2.setTypeface(face);
        holiday1.setTypeface(face);
        holiday2.setTypeface(face);
        storeLocation.setTypeface(face);
        callStore1.setTypeface(face);
        callStore2.setTypeface(face);

        Intent intent = getIntent();                    //인텐트 얻기
        final String getStore = intent.getStringExtra("store");        // 매장명
        final String getLocation = intent.getStringExtra("location");  // 매장 지역구

        //데이터베이스 및 테이블 open
        final MainActivity_SQLiteHandler handler = MainActivity_SQLiteHandler.open(getApplicationContext());
        Cursor c = handler.select_store();
        c.moveToLast();                                //전체 테이블의 데이터를 세기 위해 커서 c를 테이블의 맨 끝으로 이동
        int Length = c.getCount();                    //현재 row 번호(=전체 row 개수)
        c.moveToFirst();

        //storeName과 동일한 값을 가진 sqlite 행 값 선택(corsor로 선택)
        for (int i = 0; i < Length; i++) {
            //ListActivity에서 넘어온 자전거 명과 sqlite의 자전거명 및 연식이 동일할 시 break;
            if ((c.getString(3)).equals(getStore)) {
                if (c.getString(2).equals(getLocation)) {
                    break;
                }
            }
            c.moveToNext();
        }

        // itemList 배열에 sqlite의 이미지 주소를 넣어 viewPager로 이미지 출력
        String itemList[] = {c.getString(55), c.getString(56), c.getString(57), c.getString(58), c.getString(59)};

        //image swipe 기능
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        StoreActivity_ViewPagerAdapter adapter = new StoreActivity_ViewPagerAdapter(this, itemList);
        viewPager.setAdapter(adapter);

        String[] brand = {"마지", "메리다", "보드만", "비앙키", "삼천리", "스캇", "스페셜라이즈드", "써벨로",
                "알톤", "엘파마", "윌리어", "자이언트", "첼로", "캐논데일", "트렉", "트리곤", "후지", "GT"};

        String fitting = "";
        String repair = "";
        //자전거 종류 별 취급브랜드 구분해주는 변수
        String brandSelect = "";
        int series = 0;
        series = 1; //기본값을 로드로 설정하여 series에 1을 넣음
        //17은 브랜드 개수를 의미함
        //if문에서 곱하기 2를 해주는 이유는 브랜드마다 로드, MTB로 구분돼 있기 때문에 2칸씩 건너뛰어 다음 브랜드로 넘어가기 위함
        for (int i = 0; i < 17; i++) {
            if (c.getString(17 + i * 2 + series).equals("o")) {
                brandSelect += brand[i] + "  ";
            } else {
            }
        }
        brandSelect += c.getString(54); //기타 브랜드 추가
        //취급 브랜드만 표기
        if (c.getString(11).equals("o")) {
            fitting += "일반피팅";
        }
        if (c.getString(12).equals("o")) {
            fitting += " | 전문피팅";
        }
        if (c.getString(13).equals("o")) {
            repair = "가능";
        } else {
            repair = "불가능";
        }

        //매장 연락하기 버튼을 넣기 위해 전화번호를 문자열로 빼놓음
        final String storeNumber = c.getString(9);

        //sqlite의 값을 activity_store 값에 매칭
        //기본정보
        storeName.setText(c.getString(3));
        adress2.setText(c.getString(4));
        contact2.setText(storeNumber);
        brand2.setText(brandSelect);
        fitting2.setText(fitting);
        repair2.setText(repair);
        businessHour_weekly2.setText(c.getString(14));
        businessHour_saturday2.setText(c.getString(15));
        businessHour_holiday2.setText(c.getString(16));
        holiday2.setText(c.getString(17));

        //set map location for setting the store's location and set store's name
        CUSTOM_MARKER_POINT = MapPoint.mapPointWithGeoCoord(c.getDouble(5), c.getDouble(6));
        storeNameInMap = c.getString(3);

        MapLayout mapLayout = new MapLayout(this);
        mMapView = mapLayout.getMapView();

        mMapView.setDaumMapApiKey(MapApiConst.DAUM_MAPS_ANDROID_APP_API_KEY);
//        mMapView.setOpenAPIKeyAuthenticationResultListener(this);
        mMapView.setMapViewEventListener(this);
        mMapView.setPOIItemEventListener(this);
        mMapView.setMapType(MapView.MapType.Standard);
        mMapView.setMapTilePersistentCacheEnabled(true);    //지도 데이터 다운로드
        mMapView.setMapCenterPointAndZoomLevel(CUSTOM_MARKER_POINT, 2, true);
        createCustomMarker(mMapView);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapLayout);

        //make the scrollView to stop when we control the map
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        // 전화 버튼
        callStore1.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ad.setTitle("전화").setMessage("즐라에서 보고 연락드렸다고 말씀하시면 문의가 빠릅니다").
                                setPositiveButton("전화하기", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //google analytics button
                                        mTracker.send(new HitBuilders.EventBuilder().setCategory("store screen: call button").setLabel("key").build());
                                        // 전화하기
                                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + storeNumber));
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                                // TODO: Consider calling
                                                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                                                // here to request the missing permissions, and then overriding
                                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                //                                          int[] grantResults)
                                                // to handle the case where the user grants the permission. See the documentation
                                                // for Activity#requestPermissions for more details.
                                                return;
                                            }
                                        }
                                        startActivity(intent);
                                    }
                                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).create().show();
                    }
                });

        // 방문예약 전화 버튼
        callStore2.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ad.setTitle("방문예약").setMessage("찾으시는 재고를 말씀해주신 후 방문하실 날짜와 시간을 말씀해주세요\n\n"+
                                "즐라에서 보고 연락드렸다고 말씀하시면 문의가 빠릅니다").
                                setPositiveButton("전화하기", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //google analytics button
                                        mTracker.send(new HitBuilders.EventBuilder().setCategory("store screen: visit reservation button").setLabel("key").build());
                                        // 전화하기
                                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + storeNumber));
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                                // TODO: Consider calling
                                                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                                                // here to request the missing permissions, and then overriding
                                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                //                                          int[] grantResults)
                                                // to handle the case where the user grants the permission. See the documentation
                                                // for Activity#requestPermissions for more details.
                                                return;
                                            }
                                        }
                                        startActivity(intent);
                                    }
                                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).create().show();
                    }
                });
        //데이터베이스 및 테이블 close
        handler.close();
    }

    //using the daum map's marker
    private void createCustomMarker(MapView mapView) {
        mCustomMarker = new MapPOIItem();
        mCustomMarker.setItemName(storeNameInMap);
        mCustomMarker.setTag(1);
        mCustomMarker.setMapPoint(CUSTOM_MARKER_POINT);
        mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mCustomMarker.setCustomImageResourceId(R.mipmap.map_marker);
        mCustomMarker.setCustomImageAutoscale(false);
        mCustomMarker.setShowDisclosureButtonOnCalloutBalloon(false);
        mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);

        mapView.addPOIItem(mCustomMarker);
        mapView.selectPOIItem(mCustomMarker, true);
        mapView.setMapCenterPoint(CUSTOM_MARKER_POINT, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        //액션바
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_activity_store, menu);

        // 뒤로가기 버튼 임시로 없애놓음. 재고확인 페이지에서 뒤로가기 버튼 생성 시 매장 상세 페이지에서도 뒤로가기 버튼 생성하기
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    //네트워크 연결 확인
    private Boolean isNetWork(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable();
        boolean isMobileConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        boolean isWifiAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable();
        boolean isWifiConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

        if ((isWifiAvailable && isWifiConnect) || (isMobileAvailable && isMobileConnect)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

        mapView.setOnTouchListener((new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        // enable touch on transparent view
                        return false;
                    case MotionEvent.ACTION_UP:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        return false;
                    default:
                        return false;
                }
            }
        }));
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

        mapView.setOnTouchListener((new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        // enable touch on transparent view
                        return false;
                    case MotionEvent.ACTION_UP:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        return false;
                    default:
                        return false;
                }
            }
        }));
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}