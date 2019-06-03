package com.zla.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class InformationListActivity extends AppCompatActivity {

    //google analytics
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_list);

        //google analytics
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("informationList screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        TextView informationBicycleSeries = (TextView) findViewById(R.id.informationBicycleSeries);
        Button informationBicycle1 = (Button) findViewById(R.id.informationBicycle1);
        Button informationBicycle2 = (Button) findViewById(R.id.informationBicycle2);
        Button informationBicycle3 = (Button) findViewById(R.id.informationBicycle3);
        Button informationBicycle4 = (Button) findViewById(R.id.informationBicycle4);
        Button informationBicycle5 = (Button) findViewById(R.id.informationBicycle5);
        TextView informationComponent = (TextView) findViewById(R.id.informationComponent);
        Button informationConstructure = (Button) findViewById(R.id.informationConstructure);
        Button informationComponent1 = (Button) findViewById(R.id.informationComponent1);
        Button informationComponent2 = (Button) findViewById(R.id.informationComponent2);
        TextView informationGroupset = (TextView) findViewById(R.id.informationGroupset);
        Button informationComponent3 = (Button) findViewById(R.id.informationComponent3);
        Button informationComponent4 = (Button) findViewById(R.id.informationComponent4);
        Button informationComponent5 = (Button) findViewById(R.id.informationComponent5);
        Button informationComponent6 = (Button) findViewById(R.id.informationComponent6);
        Button informationComponent7 = (Button) findViewById(R.id.informationComponent7);
        Button informationComponent8 = (Button) findViewById(R.id.informationComponent8);
        Button informationComponent9 = (Button) findViewById(R.id.informationComponent9);
        Button informationComponent10 = (Button) findViewById(R.id.informationComponent10);
        TextView informationDetail = (TextView) findViewById(R.id.informationDetail);
        Button informationComponent11 = (Button) findViewById(R.id.informationComponent11);
        Button informationComponent12 = (Button) findViewById(R.id.informationComponent12);
        Button informationComponent13 = (Button) findViewById(R.id.informationComponent13);
        Button informationComponent14 = (Button) findViewById(R.id.informationComponent14);
        Button informationComponent15 = (Button) findViewById(R.id.informationComponent15);
        Button informationComponent16 = (Button) findViewById(R.id.informationComponent16);
        Button informationComponent17 = (Button) findViewById(R.id.informationComponent17);
        Button informationComponent18 = (Button) findViewById(R.id.informationComponent18);
        Button informationComponent19 = (Button) findViewById(R.id.informationComponent19);

        final Intent intent = new Intent().setClass(InformationListActivity.this, InformationActivity.class);
        //자전거 종류 클릭
        informationBicycle1.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationBicycle1"); startActivity(intent);}});
        informationBicycle2.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationBicycle2"); startActivity(intent);}});
        informationBicycle3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationBicycle3"); startActivity(intent);}});
        informationBicycle4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationBicycle4"); startActivity(intent);}});
        informationBicycle5.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationBicycle5"); startActivity(intent);}});
        //자전거 구조 및 부품 클릭
        informationConstructure.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationConstructure"); startActivity(intent);}});
        informationComponent1.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent1"); startActivity(intent);}});
        informationComponent2.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent2"); startActivity(intent);}});
        informationComponent3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent3"); startActivity(intent);}});
        informationComponent4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent4"); startActivity(intent);}});
        informationComponent5.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent5"); startActivity(intent);}});
        informationComponent6.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent6"); startActivity(intent);}});
        informationComponent7.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent7"); startActivity(intent);}});
        informationComponent8.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent8"); startActivity(intent);}});
        informationComponent9.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent9"); startActivity(intent);}});
        informationComponent10.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent10"); startActivity(intent);}});
        informationComponent11.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent11"); startActivity(intent);}});
        informationComponent12.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent12"); startActivity(intent);}});
        informationComponent13.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent13"); startActivity(intent);}});
        informationComponent14.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent14"); startActivity(intent);}});
        informationComponent15.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent15"); startActivity(intent);}});
        informationComponent16.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent16"); startActivity(intent);}});
        informationComponent17.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent17"); startActivity(intent);}});
        informationComponent18.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent18"); startActivity(intent);}});
        informationComponent19.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {intent.putExtra("information", "informationComponent19"); startActivity(intent);}});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        //액션바
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_activity_information, menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
