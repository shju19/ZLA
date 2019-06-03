package com.zla.android;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;

public class WishListDetailActivity extends AppCompatActivity {

    //google analytics
    private Tracker mTracker;

    //액션바 연결 변수
    Menu menu;

    //찜하기 변수
    String wishList;

    //인텐트 받아오는 변수
    String bicycleSeries; //찜하기 변수도 됨
    String bicycleName; //찜하기 변수도 됨

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //google analytics
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("wishlist detail screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        //activity_detail의 값을 받는 변수 값에 activity_detail의 id값 매칭
        //기본정보
        TextView detailName = (TextView) findViewById(R.id.detailName);
        TextView detailBrand1 = (TextView) findViewById(R.id.detailBrand1); TextView detailBrand2  = (TextView) findViewById(R.id.detailBrand2);
        TextView detailColor1 = (TextView) findViewById(R.id.detailColor1); TextView detailColor2  = (TextView) findViewById(R.id.detailColor2);
        TextView detailSize1 = (TextView) findViewById(R.id.detailSize1); TextView detailSize2  = (TextView) findViewById(R.id.detailSize2);
        TextView detailPrice1 = (TextView) findViewById(R.id.detailPrice1); TextView detailPrice2  = (TextView) findViewById(R.id.detailPrice2);
        //메인스펙
        TextView mainSpec = (TextView) findViewById(R.id.mainSpec);
        TextView mainFrame1 = (TextView) findViewById(R.id.mainFrame1); TextView mainFrame2  = (TextView) findViewById(R.id.mainFrame2);
        ImageView frame_graph = (ImageView) findViewById(R.id.frame_graph);
        TextView mainGroupset1 = (TextView) findViewById(R.id.mainGroupset1); TextView mainGroupset2  = (TextView) findViewById(R.id.mainGroupset2);
        ImageView groupset_graph = (ImageView) findViewById(R.id.groupset_graph);
        //구동계
        TextView groupset = (TextView) findViewById(R.id.groupset);
        TextView detailShifters1 = (TextView) findViewById(R.id.detailShifters1); TextView detailShifters2 = (TextView) findViewById(R.id.detailShifters2);
        TextView detailFrontDerailleur1 = (TextView) findViewById(R.id.detailFrontDerailleur1); TextView detailFrontDerailleur2 = (TextView) findViewById(R.id.detailFrontDerailleur2);
        TextView detailRearDerailleur1 = (TextView) findViewById(R.id.detailRearDerailleur1); TextView detailRearDerailleur2 = (TextView) findViewById(R.id.detailRearDerailleur2);
        TextView detailBrake1 = (TextView) findViewById(R.id.detailBrake1); TextView detailBrake2 = (TextView) findViewById(R.id.detailBrake2);
        TextView detailCrankset1 = (TextView) findViewById(R.id.detailCrankset1); TextView detailCrankset2 = (TextView) findViewById(R.id.detailCrankset2);
        TextView detailSprocket1 = (TextView) findViewById(R.id.detailSprocket1); TextView detailSprocket2 = (TextView) findViewById(R.id.detailSprocket2);
        TextView detailChain1 = (TextView) findViewById(R.id.detailChain1); TextView detailChain2 = (TextView) findViewById(R.id.detailChain2);
        //상세스펙
        TextView detailSpec = (TextView) findViewById(R.id.detailSpec);
        TextView detailFork1 = (TextView) findViewById(R.id.detailFork1); TextView detailFork2 = (TextView) findViewById(R.id.detailFork2);
        TextView detailStem1 = (TextView) findViewById(R.id.detailStem1); TextView detailStem2 = (TextView) findViewById(R.id.detailStem2);
        TextView detailHandlebar1 = (TextView) findViewById(R.id.detailHandlebar1); TextView detailHandlebar2 = (TextView) findViewById(R.id.detailHandlebar2);
        TextView detailWheelset1 = (TextView) findViewById(R.id.detailWheelset1); TextView detailWheelset2 = (TextView) findViewById(R.id.detailWheelset2);
        TextView detailTires1 = (TextView) findViewById(R.id.detailTires1); TextView detailTires2 = (TextView) findViewById(R.id.detailTires2);
        TextView detailSaddle1 = (TextView) findViewById(R.id.detailSaddle1); TextView detailSaddle2 = (TextView) findViewById(R.id.detailSaddle2);
        TextView detailSeatPost1 = (TextView) findViewById(R.id.detailSeatPost1); TextView detailSeatPost2 = (TextView) findViewById(R.id.detailSeatPost2);
        TextView detailWeight1 = (TextView) findViewById(R.id.detailWeight1); TextView detailWeight2 = (TextView) findViewById(R.id.detailWeight2);
        TextView detailYear1 = (TextView) findViewById(R.id.detailYear1); TextView detailYear2 = (TextView) findViewById(R.id.detailYear2);
        TextView detailType1 = (TextView) findViewById(R.id.detailType1); TextView detailType2 = (TextView) findViewById(R.id.detailType2);
        //지오메트리
        TextView detailGeometry = (TextView) findViewById(R.id.detailGeometry);
        ImageView detailGeometryImage = (ImageView) findViewById(R.id.detailGeometryImage);
        //매장확인, 재고확인 버튼
        Button confirmStore = (Button) findViewById(R.id.confirmStore);

        //글꼴 설정
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/10X10.ttf");
        //기본정보
        detailName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/10X10Bold.ttf"));
        detailBrand1.setTypeface(face); detailBrand2.setTypeface(face);
        detailColor1.setTypeface(face); detailColor2.setTypeface(face);
        detailSize1.setTypeface(face); detailSize2.setTypeface(face);
        detailPrice1.setTypeface(face); detailPrice2.setTypeface(face);
        //메인스펙
        mainSpec.setTypeface(face);
        mainFrame1.setTypeface(face); mainFrame2.setTypeface(face);
        mainGroupset1.setTypeface(face); mainGroupset2.setTypeface(face);
        //구동계
        groupset.setTypeface(face);
        detailShifters1.setTypeface(face); detailShifters2.setTypeface(face);
        detailFrontDerailleur1.setTypeface(face); detailFrontDerailleur2.setTypeface(face);
        detailRearDerailleur1.setTypeface(face); detailRearDerailleur2.setTypeface(face);
        detailBrake1.setTypeface(face); detailBrake2.setTypeface(face);
        detailCrankset1.setTypeface(face); detailCrankset2.setTypeface(face);
        detailSprocket1.setTypeface(face); detailSprocket2.setTypeface(face);
        detailChain1.setTypeface(face); detailChain2.setTypeface(face);
        //상세스펙
        detailSpec.setTypeface(face);
        detailFork1.setTypeface(face); detailFork2.setTypeface(face);
        detailStem1.setTypeface(face); detailStem2.setTypeface(face);
        detailHandlebar1.setTypeface(face); detailHandlebar2.setTypeface(face);
        detailWheelset1.setTypeface(face); detailWheelset2.setTypeface(face);
        detailTires1.setTypeface(face); detailTires2.setTypeface(face);
        detailSaddle1.setTypeface(face); detailSaddle2.setTypeface(face);
        detailSeatPost1.setTypeface(face); detailSeatPost2.setTypeface(face);
        detailWeight1.setTypeface(face); detailWeight2.setTypeface(face);
        detailYear1.setTypeface(face); detailYear2.setTypeface(face);
        detailType1.setTypeface(face); detailType2.setTypeface(face);
        //지오메트리
        detailGeometry.setTypeface(face);
        //판매매장, 재고확인
        confirmStore.setTypeface(face);

        //리스트 페이지에서 데이터 받기
        Intent intent = getIntent();                    //인텐트 얻기
        bicycleSeries = intent.getStringExtra("bicycleSeries");  //자전거 종류 구분
        bicycleName = intent.getStringExtra("bicycleName");    //ListActivity에서 데이터 받기
        final int bicycleYear = intent.getIntExtra("bicycleYear", 0);

        //자전거 데이터 선택
        //데이터베이스 생성 및 bicycleSeries에 맞춰 테이블 연결
        MainActivity_SQLiteHandler handler = MainActivity_SQLiteHandler.open(getApplicationContext());
        //초기값으로 로드 테이블에 커서를 설정
        Cursor c = handler.select_road();

        //자전거 리스트 검색 시 필요한 변수
        c.moveToLast();                        //데이터베이스 열 크기 확인을 위해 c를 마지막 행으로 이동
        int length = c.getCount();            //데이터베이스 열의 크기 저장
        c.moveToFirst();                      //데이터 검색 시 c를 사용하기 위해 첫 행으로 이동
        NumberFormat nf = NumberFormat.getInstance();    //가격 "," 찍기 위한 객체 선언

        bicycleSeries = "mtb"; //기본값을 mtb로 설정
        //로드, MTB 종류 선택 구문. bicycleName 및 bicycleYear와 동일한 값을 가진 sqlite 행 값 선택(corsor로 선택)
        for(int i=0; i < length; i++) {
            if(c.getString(4).equals(bicycleName)) {
                if(c.getInt(3) == bicycleYear) {
                    //자전거명과 연식이 동일할 경우 해당 행에서 for을 종료
                    bicycleSeries = "road";
                    break;
                }
            }
            c.moveToNext();
        }

        //bicycleSeries에 따라 테이블 값 설정
        switch(bicycleSeries) {
            case "road": c = handler.select_road(); break;
            case "mtb": c = handler.select_mtb(); break;
            default: break;
        }

        //자전거 리스트 검색 시 필요한 변수
        c.moveToLast();                        //데이터베이스 열 크기 확인을 위해 c를 마지막 행으로 이동
        int length2 = c.getCount();            //데이터베이스 열의 크기 저장
        c.moveToFirst();                      //데이터 검색 시 c를 사용하기 위해 첫 행으로 이동

        //bicycleName 및 bicycleYear와 동일한 값을 가진 sqlite 행 값 선택(corsor로 선택)
        for(int i=0; i < length2; i++) {
            if(c.getString(4).equals(bicycleName)) {
                if(c.getInt(3) == bicycleYear) {
                    //자전거명과 연식이 동일할 경우 해당 행에서 for을 종료
                    break;
                }
            }
            c.moveToNext();
        }

        // itemList 배열에 sqlite의 이미지 주소를 넣어 viewPager로 이미지 출력
        int itemList[] = {getResources().getIdentifier("@mipmap/" + c.getString(57), "mipmap", "com.zla.android"),
                getResources().getIdentifier("@mipmap/" + c.getString(58), "mipmap", "com.zla.android"),
                getResources().getIdentifier("@mipmap/" + c.getString(59), "mipmap", "com.zla.android"),
                getResources().getIdentifier("@mipmap/" + c.getString(60), "mipmap", "com.zla.android"),
                getResources().getIdentifier("@mipmap/" + c.getString(61), "mipmap", "com.zla.android")};

        //image swipe 기능
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        DetailActivity_ViewPagerAdapter adapter = new DetailActivity_ViewPagerAdapter(this, itemList);
        viewPager.setAdapter(adapter);

        //sqlite의 값을 activity_detail 값에 매칭
        //기본정보
        detailName.setText(c.getString(4));
        detailBrand2.setText(c.getString(1));
        final String color1=c.getString(5), color2=c.getString(6), color3=c.getString(7), color4=c.getString(8), color5=c.getString(9);
        String colorSelect = "";    //DetailActivity에 노출할 색상 변수값
        if(color1!="") {colorSelect += color1;} if(color2!="") {colorSelect += "  " + color2;} if(color3!="") {colorSelect += "  " + color3;}
        if(color4!="") {colorSelect += "  " + color4;} if(color5!="") {colorSelect += "  " + color5;}
        detailColor2.setText(colorSelect);                                //colorSelect에 저장한 색깔을 setText로 표현
        final String size1=c.getString(10), size2=c.getString(11), size3=c.getString(12), size4=c.getString(13);
        final String size5=c.getString(14), size6=c.getString(15), size7=c.getString(16), size8=c.getString(17);
        String sizeSelect = "";    //DetailActivity에 노출할 사이즈 변수값
        if(size1!="") {sizeSelect += size1;} if(size2!="") {sizeSelect += "  " + size2;} if(size3!="") {sizeSelect += "  " + size3;}
        if(size4!="") {sizeSelect += "  " + size4;} if(size5!="") {sizeSelect += "  " + size5;} if(size6!="") {sizeSelect += "  " + size6;}
        if(size7!="") {sizeSelect += "  " + size7;} if(size8!="") {sizeSelect += "  " + size8;}
        detailSize2.setText(sizeSelect);
        detailPrice2.setText(nf.format(c.getInt(34)) + "원");            //가격 ","찍고 "원" 표기

        //주요 사양 - 프레임
        mainFrame2.setText(""); // 삭제예정
        // 프레임 이름으로 불러올 이미지 이름 저장
        String frame_image = "";
        switch(c.getString(35)) {
            case"스틸": frame_image = "frame/frame_1_steel.png";break;
            case"크로몰리": frame_image = "frame/frame_2_chromoly.png";break;
            case"알루미늄": frame_image = "frame/frame_3_aluminum.png";break;
            case"카본": frame_image = "frame/frame_4_carbon.png";break;
            case"티타늄": frame_image = "frame/frame_5_titanium.png";break;
            default:frame_image = "frame/frame_graph.png";break;
        }

        //Assets폴더의 이미지 가져오기
        AssetManager assetmanager1 = getResources().getAssets();
        Bitmap bitmap1 = null;
        //InputStream으로 사진 불러오며 초기값 설정을 해줌
        InputStream is1 = null;
        try {
            is1 = assetmanager1.open(frame_image, AssetManager.ACCESS_BUFFER);
            bitmap1 = BitmapFactory.decodeStream(is1);    //이미지를 비트맵에 저장
        } catch (IOException e) {
            e.printStackTrace();
        }
        //이미지 노출
        frame_graph.setImageBitmap(bitmap1);

        //주요사양 - 구동계
        mainGroupset2.setText("");  //삭제예정
        // 프레임 이름으로 불러올 이미지 이름 저장
        String groutset_image = "";
        switch(c.getString(38)) {
            case"시마노 듀라 에이스 Di2": groutset_image = "groupset/shimano_road_8_dura_ace_di2.png";break;
            case"시마노 듀라 에이스": groutset_image = "groupset/shimano_road_7_dura_ace.png";break;
            case"시마노 울테그라 Di2": groutset_image = "groupset/shimano_road_6_uultegra_di2.png";break;
            case"시마노 울테그라": groutset_image = "groupset/shimano_road_5_ultegra.png";break;
            case"시마노 105": groutset_image = "groupset/shimano_road_4_105.png";break;
            case"시마노 티아그라": groutset_image = "groupset/shimano_road_3_tiagra.png";break;
            case"시마노 소라": groutset_image = "groupset/shimano_road_2_sora.png";break;
            case"시마노 클라리스": groutset_image = "groupset/shimano_road_1_claris.png";break;
            case"시마노 XTR Di2": groutset_image = "groupset/shimano_mtb_9_xtr_di2.png";break;
            case"시마노 XTR": groutset_image = "groupset/shimano_mtb_9_xtr.png";break;
            case"시마노 세인트": groutset_image = "groupset/shimano_mtb_8_saint.png";break;
            case"시마노 데오레 XT": groutset_image = "groupset/shimano_mtb_7_deore_xt.png";break;
            case"시마노 SLX": groutset_image = "groupset/shimano_mtb_6_slx.png";break;
            case"시마노 데오레": groutset_image = "groupset/shimano_mtb_5_deore.png";break;
            case"시마노 ZEE": groutset_image = "groupset/shimano_mtb_4_zee.png";break;
            case"시마노 알리비오": groutset_image = "groupset/shimano_mtb_3_alivio.png";break;
            case"시마노 아세라": groutset_image = "groupset/shimano_mtb_2_acera.png";break;
            case"시마노 알투스": groutset_image = "groupset/shimano_mtb_1_altus.png";break;
            case"스램 레드 E.tap": groutset_image = "groupset/sram_road_5_red_etap.png";break;
            case"스램 레드": groutset_image = "groupset/sram_road_4_red.png";break;
            case"스램 포스": groutset_image = "groupset/sram_road_3_force.png";break;
            case"스램 라이벌": groutset_image = "groupset/sram_road_2_rival.png";break;
            case"스램 에이펙스": groutset_image = "groupset/sram_road_1_apex.png";break;
            case"스램 XX1": groutset_image = "groupset/sram_mtb_11_xx1.png";break;
            case"스램 XX": groutset_image = "groupset/sram_mtb_10_xx.png";break;
            case"스램 X01": groutset_image = "groupset/sram_mtb_9_x01.png";break;
            case"스램 X0": groutset_image = "groupset/sram_mtb_8_x0.png";break;
            case"스램 X1": groutset_image = "groupset/sram_mtb_7_x1.png";break;
            case"스램 GX": groutset_image = "groupset/sram_mtb_6_gx.png";break;
            case"스램 X9": groutset_image = "groupset/sram_mtb_5_x9.png";break;
            case"스램 X7": groutset_image = "groupset/sram_mtb_4_x7.png";break;
            case"스램 X5": groutset_image = "groupset/sram_mtb_3_x5.png";break;
            case"스램 X4": groutset_image = "groupset/sram_mtb_2_x4.png";break;
            case"스램 X3": groutset_image = "groupset/sram_mtb_1_x3.png";break;
            case"캄파놀로 슈퍼 레코드 EPS": groutset_image = "groupset/campagnolo_road_8_super_record_eps.png";break;
            case"캄파놀로 레코드 EPS": groutset_image = "groupset/campagnolo_road_7_record_eps.png";break;
            case"캄파놀로 코러스 EPS": groutset_image = "groupset/campagnolo_road_6_chorus_eps.png";break;
            case"캄파놀로 슈퍼 레코드": groutset_image = "groupset/campagnolo_road_5_super_record.png";break;
            case"캄파놀로 레코드": groutset_image = "groupset/campagnolo_road_4_record.png";break;
            case"캄파놀로 코러스": groutset_image = "groupset/campagnolo_road_3_chorus.png";break;
            case"캄파놀로 아테나": groutset_image = "groupset/campagnolo_road_2_athena.png";break;
            case"캄파놀로 벨로체": groutset_image = "groupset/campagnolo_road_1_veloce.png";break;
            default:groutset_image = "groupset/groupset_graph.png";break;
        }

        //Assets폴더의 이미지 가져오기
        AssetManager assetmanager2 = getResources().getAssets();
        Bitmap bitmap2 = null;
        //InputStream으로 사진 불러오며 초기값 설정을 해줌
        InputStream is2 = null;
        try {
            is2 = assetmanager2.open(groutset_image, AssetManager.ACCESS_BUFFER);
            bitmap2 = BitmapFactory.decodeStream(is2);    //이미지를 비트맵에 저장
        } catch (IOException e) {
            e.printStackTrace();
        }
        //이미지 노출
        groupset_graph.setImageBitmap(bitmap2);

        //구동계
        detailShifters2.setText(c.getString(39));
        detailFrontDerailleur2.setText(c.getString(40));
        detailRearDerailleur2.setText(c.getString(41));
//		detailBrakeLever2.setText(c.getString(42));						//브레이크레버는 브레이크와 거의 동일한 정보로 표기 안함
        detailBrake2.setText(c.getString(43));
        detailCrankset2.setText(c.getString(44));
        detailSprocket2.setText(c.getString(45));
        detailChain2.setText(c.getString(46));
        //상세 사양
        detailFork2.setText(c.getString(37));
        detailStem2.setText(c.getString(47));
        detailHandlebar2.setText(c.getString(48));
        detailWheelset2.setText(c.getString(49));
        detailTires2.setText(c.getString(50));
        detailSaddle2.setText(c.getString(51));
        detailSeatPost2.setText(c.getString(52));
//		detailPedal2.setText(c.getString(53));
//		detailSuspension2.setText(c.getString(54));
        detailWeight2.setText(c.getString(55));
        detailYear2.setText(c.getString(3) + "년식");
        detailType2.setText(c.getString(2));
        //지오메트리 이미지
        int geometryNumber = getResources().getIdentifier("@mipmap/" + c.getString(56), "mipmap", "com.zla.android");
        detailGeometryImage.setImageResource(geometryNumber);

        //찜 버튼 초기값 설정
        wishList = c.getString(62);

        //매장 검색용으로 전달할 브랜드 변수 설정
        final String bicycleBrand = c.getString(1);

        //매장확인 버튼
        confirmStore.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        //네트워크가 연결되어 있을 시에만 메인페이지 연결
                        if(isNetWork()) {
                            //판매매장 확인 페이지 생성(해당 페이지는 WebView로 페이지를 보여줌)
                            Intent intent = new Intent().setClass(WishListDetailActivity.this, ConfirmInventoryActivity.class);
                            intent.putExtra("bicycleName", bicycleName);
                            intent.putExtra("bicycleYear", bicycleYear);
                            intent.putExtra("color1", color1);
                            intent.putExtra("color2", color2);
                            intent.putExtra("color3", color3);
                            intent.putExtra("color4", color4);
                            intent.putExtra("color5", color5);
                            intent.putExtra("size1", size1);
                            intent.putExtra("size2", size2);
                            intent.putExtra("size3", size3);
                            intent.putExtra("size4", size4);
                            intent.putExtra("size5", size5);
                            intent.putExtra("size6", size6);
                            intent.putExtra("size7", size7);
                            intent.putExtra("size8", size8);

                            startActivity(intent);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "데이터 요청에 실패 하였습니다.\n사용중인 네트워크 상태를 확인해주세요.", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }
        );


        //데이터베이스 종료
        handler.close();
    }

    //액션바 내용
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);

        //뒤로가기 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //make the first wishList image setting
        if(wishList.equals("no")) { menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.wishlist_checkedoff)); }
        else if (wishList.equals("yes")) { menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.wishlist_checkedon)); }

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

            //찜 버튼
            case R.id.action_wishlist:
                MainActivity_SQLiteHandler handler = MainActivity_SQLiteHandler.open(getApplicationContext());
                Toast toast;
                switch(wishList) {
                    case "yes":
                        //google analytics button
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("wishlist detail screen(actionbar): wishlist button off").setLabel("key").build());

                        wishList = "no";
                        if(bicycleSeries.equals("road")) { handler.update_road(bicycleName, "no"); }
                        else if(bicycleSeries.equals("mtb")) { handler.update_mtb(bicycleName, "no"); }
                        //wishList image change
                        menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.wishlist_checkedoff));
                        toast = Toast.makeText(getApplicationContext(), "찜 취소 되었습니다.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        break;
                    case "no":
                        //google analytics button
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("wishlist detail screen(actionbar): wishlist button on").setLabel("key").build());

                        wishList = "yes";
                        if(bicycleSeries.equals("road")) { handler.update_road(bicycleName, "yes"); }
                        else if(bicycleSeries.equals("mtb")) { handler.update_mtb(bicycleName, "yes"); }
                        //wishList image change
                        menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.wishlist_checkedon));
                        toast = Toast.makeText(getApplicationContext(), "찜 되었습니다.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        break;
                }
                handler.close();

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
}
