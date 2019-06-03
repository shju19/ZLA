package com.zla.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;

public class IntroActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        TextView introDetail1 = (TextView)findViewById(R.id.introDetail1);
        TextView introDetail2 = (TextView)findViewById(R.id.introDetail2);

        //글꼴 객체 선언
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/10X10.ttf");
        //버튼 글꼴 변경 - 버튼은 굵은 폰트로 표시
        introDetail1.setTypeface(face);
        introDetail2.setTypeface(face);

        Handler handler_intro = new Handler();
        handler_intro.postDelayed(new Runnable() {
            public void run() {
                //네트워크가 연결되어 있을 시에만 메인페이지 연결
                if(isNetWork()) {
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);

                    //road 데이터베이스 생성 및 데이터 삽입
                    MainActivity_SQLiteHandler handler = MainActivity_SQLiteHandler.open(getApplicationContext());
                    handler.make_road_2016_1();
                    handler.make_road_2016_2();
                    handler.make_mtb_2016_1();
                    handler.make_mtb_2016_2();
                    handler.make_store();
                    handler.make_latestList();
                    handler.make_personal();
                    handler.close();

                    // 초기화면 진행한 후 뒤로가기 했을경우 intro 페이지 안나오도록 없애주기
                    finish();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "데이터 요청에 실패 하였습니다.\n사용중인 네트워크 상태를 확인해주세요.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }
        }, 1000);
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