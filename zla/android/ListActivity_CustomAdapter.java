package com.zla.android;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity_CustomAdapter extends BaseAdapter {

    ListActivity_CustomDTO dto;

    Context ctx;
    int layout;
    ArrayList<ListActivity_CustomDTO> list;
    LayoutInflater inf;

    public ListActivity_CustomAdapter(Context ctx, int layout, ArrayList<ListActivity_CustomDTO> list) {

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

    //실제 행(row)을 보여주는 뷰를 생성하는 메소드. 리스트가 보여질 때 getView를 실행
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //ViewHolder 정의. 속도향상을 위한 구문
        ViewHolder holder;

        //inflate 메소드를 사용하여 list_row.xml을 View 객체로 변경
        if(convertView == null) {
            convertView = inf.inflate(layout, null);

            holder = new ViewHolder();

            //ViewHolder의 객체인 holder에 findViewById를 연결. 처음 연결 시 convertView가 null일 때 한 번만 연결하고
            //이후 이를 재활용함. 이를 사용함으로써 findViewById를 한번만 사용하여 getView의 속도를 개선함
            //converView 변수를 이용해서 list_row.xml에 지정한 위젯 참조
            holder.imgIcon  = (ImageView)convertView.findViewById(R.id.imgIcon);
            holder.txtBicycle = (TextView)convertView.findViewById(R.id.txtBicycle);
            holder.txtBrand	 = (TextView)convertView.findViewById(R.id.txtBrand);
            holder.txtFrame	 = (TextView)convertView.findViewById(R.id.txtFrame);
            holder.txtGroupset = (TextView)convertView.findViewById(R.id.txtGroupset);
            holder.txtPrice	 = (TextView)convertView.findViewById(R.id.txtPrice);

            //목록 페이지 글꼴 설정
            Typeface face = Typeface.createFromAsset(ctx.getAssets(), "fonts/10X10.ttf");    //글꼴 변수 설정
            holder.txtBicycle.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/10X10Bold.ttf"));
            holder.txtBrand.setTypeface(face);
            holder.txtFrame.setTypeface(face);
            holder.txtGroupset.setTypeface(face);
            holder.txtPrice.setTypeface(face);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        dto = list.get(position);

        //리스트가 달라질 때마다 신규 리스트의 값을 넣어서 보여줌
        holder.imgIcon.setImageResource(dto.getImgIcon());
        holder.txtBicycle.setText(dto.getBicycle());
        holder.txtBrand.setText(dto.getBrand() + " " + dto.getYear());
        holder.txtFrame.setText(dto.getFrame());
        holder.txtGroupset.setText(dto.getGroupset());
        holder.txtPrice.setText(dto.getPrice());

        return convertView;
    }

    //getView 내의 ViewHolder을 사용하기 위함. ViewHolder를 사용하여 리스트뷰의 속도를 향상시킴
    static class ViewHolder {
        public ImageView imgIcon;
        public TextView txtBicycle;
        public TextView txtBrand;
        public TextView txtFrame;
        public TextView txtGroupset;
        public TextView txtPrice;
    }
}