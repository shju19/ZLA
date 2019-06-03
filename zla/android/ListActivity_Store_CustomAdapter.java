package com.zla.android;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity_Store_CustomAdapter extends BaseAdapter {     //extends BaseAdapter

    static ViewHolder holder;

    ListActivity_Store_CustomDTO dto;

    private Context ctx;
    int layout;
    ArrayList<ListActivity_Store_CustomDTO> list;
    LayoutInflater inf;

    public ListActivity_Store_CustomAdapter(Context ctx, int layout, ArrayList<ListActivity_Store_CustomDTO> list) {

        this.ctx = ctx;
        this.layout = layout;
        this.list = list;

        inf = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //ListView에서 보여줄 데이터의 개수 지정
    @Override
    public int getCount() {
        return list.size();
    }

    //ListView에서 보여줄 객체 지정. getCount()값에 의해서 position 값이 동적으로 변경
    //ex. getCount() 값이 3이면 position 값은 0, 1, 2로 설정되어 getItem 메소드가 3번 수행됨
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    //getItem(position) 메소드가 리턴한 객체의 고유 식별값
    //일반적으로 position 값도 고유값이기 때문에 position으로 지정
    @Override
    public long getItemId(int position) {
        return position;
    }

    //실제 행(row)을 보여주는 뷰를 생성하는 메소드
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //ViewHolder 정의. 속도향상을 위한 구문
        //ViewHolder holder;

        //inflate 메소드를 사용하여 list_row.xml을 View 객체로 변경
        if(convertView == null) {
            convertView = inf.inflate(layout, null);

            holder = new ViewHolder();

            //converView 변수를 이용해서 list_row.xml에 지정한 위젯 참조
            holder.storeImage = (WebView)convertView.findViewById(R.id.storeImage);
            holder.storeName = (TextView)convertView.findViewById(R.id.storeName);
            holder.address	= (TextView)convertView.findViewById(R.id.address);
            holder.contact = (TextView)convertView.findViewById(R.id.contact);

            //webview에서 터치 가능하게 하는 구문
            holder.storeImage.setFocusable(false);
            holder.storeImage.setClickable(false);
            holder.storeImage.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

            //매장목록 페이지 글꼴 설정
            Typeface face = Typeface.createFromAsset(ctx.getAssets(), "fonts/10X10.ttf");    //글꼴 변수 설정
            holder.storeName.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/10X10Bold.ttf"));
            holder.address.setTypeface(face);
            holder.contact.setTypeface(face);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        dto = list.get(position);

        //목록 페이지에 매장 관련 변수 매칭시킴
        holder.storeImage.loadDataWithBaseURL(null,creHtmlBody(dto.storeImage), "text/html","utf-8", null);
        holder.storeName.setText(dto.getStoreName());
        holder.address.setText(dto.getAddress());
        holder.contact.setText(dto.getContact());

        return convertView;
    }

    //가로, 세로 이미지 맞추기
    public  String creHtmlBody(String imagUrl){
        StringBuffer sb = new StringBuffer("<HTML>");
        sb.append("<HEAD>");
        sb.append("</HEAD>");
        sb.append("<BODY style='margin:0; padding:0; text-align:left;'>");    //중앙정렬
        sb.append("<img width='100%' height='100%' src=\"" + imagUrl+"\">"); //가득차게 나옴
        sb.append("</BODY>");
        sb.append("</HTML>");
        return sb.toString();
    }

    //getView 내의 ViewHolder을 사용하기 위함. ViewHolder를 사용하여 리스트뷰의 속도를 향상시킴
    static class ViewHolder {
        public WebView storeImage;
        public TextView storeName;
        public TextView address;
        public TextView contact;
    }
}

