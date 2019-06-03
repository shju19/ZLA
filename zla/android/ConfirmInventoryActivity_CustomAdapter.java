package com.zla.android;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ConfirmInventoryActivity_CustomAdapter extends BaseAdapter {

    ConfirmInventoryActivity_CustomDTO dto;

    Context ctx;
    int layout;
    ArrayList<ConfirmInventoryActivity_CustomDTO> list;
    LayoutInflater inf;

    public ConfirmInventoryActivity_CustomAdapter(Context ctx, int layout, ArrayList<ConfirmInventoryActivity_CustomDTO> list) {

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
            holder.store = (TextView)convertView.findViewById(R.id.inventory_store);
            holder.location = (TextView)convertView.findViewById(R.id.inventory_location);
            holder.color[0] = (TextView)convertView.findViewById(R.id.inventory_color1);
            holder.color[1] = (TextView)convertView.findViewById(R.id.inventory_color2);
            holder.color[2] = (TextView)convertView.findViewById(R.id.inventory_color3);
            holder.color[3] = (TextView)convertView.findViewById(R.id.inventory_color4);
            holder.color[4] = (TextView)convertView.findViewById(R.id.inventory_color5);
            holder.color_size[0][0] = (Button)convertView.findViewById(R.id.inventory_color1_size1);
            holder.color_size[0][1] = (Button)convertView.findViewById(R.id.inventory_color1_size2);
            holder.color_size[0][2] = (Button)convertView.findViewById(R.id.inventory_color1_size3);
            holder.color_size[0][3] = (Button)convertView.findViewById(R.id.inventory_color1_size4);
            holder.color_size[0][4] = (Button)convertView.findViewById(R.id.inventory_color1_size5);
            holder.color_size[0][5] = (Button)convertView.findViewById(R.id.inventory_color1_size6);
            holder.color_size[0][6] = (Button)convertView.findViewById(R.id.inventory_color1_size7);
            holder.color_size[0][7] = (Button)convertView.findViewById(R.id.inventory_color1_size8);
            holder.color_size[1][0] = (Button)convertView.findViewById(R.id.inventory_color2_size1);
            holder.color_size[1][1] = (Button)convertView.findViewById(R.id.inventory_color2_size2);
            holder.color_size[1][2] = (Button)convertView.findViewById(R.id.inventory_color2_size3);
            holder.color_size[1][3] = (Button)convertView.findViewById(R.id.inventory_color2_size4);
            holder.color_size[1][4] = (Button)convertView.findViewById(R.id.inventory_color2_size5);
            holder.color_size[1][5] = (Button)convertView.findViewById(R.id.inventory_color2_size6);
            holder.color_size[1][6] = (Button)convertView.findViewById(R.id.inventory_color2_size7);
            holder.color_size[1][7] = (Button)convertView.findViewById(R.id.inventory_color2_size8);
            holder.color_size[2][0] = (Button)convertView.findViewById(R.id.inventory_color3_size1);
            holder.color_size[2][1] = (Button)convertView.findViewById(R.id.inventory_color3_size2);
            holder.color_size[2][2] = (Button)convertView.findViewById(R.id.inventory_color3_size3);
            holder.color_size[2][3] = (Button)convertView.findViewById(R.id.inventory_color3_size4);
            holder.color_size[2][4] = (Button)convertView.findViewById(R.id.inventory_color3_size5);
            holder.color_size[2][5] = (Button)convertView.findViewById(R.id.inventory_color3_size6);
            holder.color_size[2][6] = (Button)convertView.findViewById(R.id.inventory_color3_size7);
            holder.color_size[2][7] = (Button)convertView.findViewById(R.id.inventory_color3_size8);
            holder.color_size[3][0] = (Button)convertView.findViewById(R.id.inventory_color4_size1);
            holder.color_size[3][1] = (Button)convertView.findViewById(R.id.inventory_color4_size2);
            holder.color_size[3][2] = (Button)convertView.findViewById(R.id.inventory_color4_size3);
            holder.color_size[3][3] = (Button)convertView.findViewById(R.id.inventory_color4_size4);
            holder.color_size[3][4] = (Button)convertView.findViewById(R.id.inventory_color4_size5);
            holder.color_size[3][5] = (Button)convertView.findViewById(R.id.inventory_color4_size6);
            holder.color_size[3][6] = (Button)convertView.findViewById(R.id.inventory_color4_size7);
            holder.color_size[3][7] = (Button)convertView.findViewById(R.id.inventory_color4_size8);
            holder.color_size[4][0] = (Button)convertView.findViewById(R.id.inventory_color5_size1);
            holder.color_size[4][1] = (Button)convertView.findViewById(R.id.inventory_color5_size2);
            holder.color_size[4][2] = (Button)convertView.findViewById(R.id.inventory_color5_size3);
            holder.color_size[4][3] = (Button)convertView.findViewById(R.id.inventory_color5_size4);
            holder.color_size[4][4] = (Button)convertView.findViewById(R.id.inventory_color5_size5);
            holder.color_size[4][5] = (Button)convertView.findViewById(R.id.inventory_color5_size6);
            holder.color_size[4][6] = (Button)convertView.findViewById(R.id.inventory_color5_size7);
            holder.color_size[4][7] = (Button)convertView.findViewById(R.id.inventory_color5_size8);

            //색상 및 재고를 숨기기 위한 레이아웃 연결
            holder.layout_inventory_color[0] = (LinearLayout)convertView.findViewById(R.id.layout_inventory_color1);
            holder.layout_inventory_color_size[0] = (LinearLayout)convertView.findViewById(R.id.layout_inventory_color1_size);
            holder.layout_inventory_color[1] = (LinearLayout)convertView.findViewById(R.id.layout_inventory_color2);
            holder.layout_inventory_color_size[1] = (LinearLayout)convertView.findViewById(R.id.layout_inventory_color2_size);
            holder.layout_inventory_color[2] = (LinearLayout)convertView.findViewById(R.id.layout_inventory_color3);
            holder.layout_inventory_color_size[2] = (LinearLayout)convertView.findViewById(R.id.layout_inventory_color3_size);
            holder.layout_inventory_color[3] = (LinearLayout)convertView.findViewById(R.id.layout_inventory_color4);
            holder.layout_inventory_color_size[3] = (LinearLayout)convertView.findViewById(R.id.layout_inventory_color4_size);
            holder.layout_inventory_color[4] = (LinearLayout)convertView.findViewById(R.id.layout_inventory_color5);
            holder.layout_inventory_color_size[4] = (LinearLayout)convertView.findViewById(R.id.layout_inventory_color5_size);

            //재고확인 페이지 글꼴 설정
            Typeface face = Typeface.createFromAsset(ctx.getAssets(), "fonts/10X10.ttf");    //글꼴 변수 설정
            holder.store.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/10X10Bold.ttf"));
            holder.location.setTypeface(face);
            for(int i=0; i<5; i++) {holder.color[i].setTypeface(face);}
            /** 숫자는 10X10로 표현 하는 것보다 기본 값이 더 나아서 폰트 변경 안함
            *for(int c=0; c<5; c++) {
            *    for(int s=0; s<8; s++) {
            *        holder.color_size[c][s].setTypeface(face);
            *    }
            *}
            */

            //뷰 보이게하는 것 설정
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        //데이터를 위치에 맞게 설정
        dto = list.get(position);

        //색상, 사이즈 노출 개수에 맞춰 색상 및 사이즈의 레이아웃 숨기기 진행
        //색상 값이 공백일 경우 색상, 재고의 레이아웃 숨기기
        if(dto.getColor1().equals("")) { holder.layout_inventory_color[0].setVisibility(View.GONE); holder.layout_inventory_color_size[0].setVisibility(View.GONE);}
        if(dto.getColor2().equals("")) { holder.layout_inventory_color[1].setVisibility(View.GONE); holder.layout_inventory_color_size[1].setVisibility(View.GONE);}
        if(dto.getColor3().equals("")) { holder.layout_inventory_color[2].setVisibility(View.GONE); holder.layout_inventory_color_size[2].setVisibility(View.GONE);}
        if(dto.getColor4().equals("")) { holder.layout_inventory_color[3].setVisibility(View.GONE); holder.layout_inventory_color_size[3].setVisibility(View.GONE);}
        if(dto.getColor5().equals("")) { holder.layout_inventory_color[4].setVisibility(View.GONE); holder.layout_inventory_color_size[4].setVisibility(View.GONE);}
        //사이즈 값이 공백일 경우 사이즈 버튼 숨기기
        if(dto.getSize1().equals("")) {holder.color_size[0][0].setVisibility(View.GONE);holder.color_size[1][0].setVisibility(View.GONE);holder.color_size[2][0].setVisibility(View.GONE);holder.color_size[3][0].setVisibility(View.GONE);holder.color_size[4][0].setVisibility(View.GONE);}
        if(dto.getSize2().equals("")) {holder.color_size[0][1].setVisibility(View.GONE);holder.color_size[1][1].setVisibility(View.GONE);holder.color_size[2][1].setVisibility(View.GONE);holder.color_size[3][1].setVisibility(View.GONE);holder.color_size[4][1].setVisibility(View.GONE);}
        if(dto.getSize3().equals("")) {holder.color_size[0][2].setVisibility(View.GONE);holder.color_size[1][2].setVisibility(View.GONE);holder.color_size[2][2].setVisibility(View.GONE);holder.color_size[3][2].setVisibility(View.GONE);holder.color_size[4][2].setVisibility(View.GONE);}
        if(dto.getSize4().equals("")) {holder.color_size[0][3].setVisibility(View.GONE);holder.color_size[1][3].setVisibility(View.GONE);holder.color_size[2][3].setVisibility(View.GONE);holder.color_size[3][3].setVisibility(View.GONE);holder.color_size[4][3].setVisibility(View.GONE);}
        if(dto.getSize5().equals("")) {holder.color_size[0][4].setVisibility(View.GONE);holder.color_size[1][4].setVisibility(View.GONE);holder.color_size[2][4].setVisibility(View.GONE);holder.color_size[3][4].setVisibility(View.GONE);holder.color_size[4][4].setVisibility(View.GONE);}
        if(dto.getSize6().equals("")) {holder.color_size[0][5].setVisibility(View.GONE);holder.color_size[1][5].setVisibility(View.GONE);holder.color_size[2][5].setVisibility(View.GONE);holder.color_size[3][5].setVisibility(View.GONE);holder.color_size[4][5].setVisibility(View.GONE);}
        if(dto.getSize7().equals("")) {holder.color_size[0][6].setVisibility(View.GONE);holder.color_size[1][6].setVisibility(View.GONE);holder.color_size[2][6].setVisibility(View.GONE);holder.color_size[3][6].setVisibility(View.GONE);holder.color_size[4][6].setVisibility(View.GONE);}
        if(dto.getSize8().equals("")) {holder.color_size[0][7].setVisibility(View.GONE);holder.color_size[1][7].setVisibility(View.GONE);holder.color_size[2][7].setVisibility(View.GONE);holder.color_size[3][7].setVisibility(View.GONE);holder.color_size[4][7].setVisibility(View.GONE);}

        //리스트가 달라질 때마다 신규 리스트의 매장명, 지역구를 노출
        holder.store.setText(dto.getStore());
        holder.location.setText(dto.getLocation());

        //색상값 설정
        holder.color[0].setText(dto.getColor1());
        holder.color[1].setText(dto.getColor2());
        holder.color[2].setText(dto.getColor3());
        holder.color[3].setText(dto.getColor4());
        holder.color[4].setText(dto.getColor5());
        //사이즈값 설정
        for(int i=0; i<5; i++) {
            holder.color_size[i][0].setText(dto.getSize1());
            holder.color_size[i][1].setText(dto.getSize2());
            holder.color_size[i][2].setText(dto.getSize3());
            holder.color_size[i][3].setText(dto.getSize4());
            holder.color_size[i][4].setText(dto.getSize5());
            holder.color_size[i][5].setText(dto.getSize6());
            holder.color_size[i][6].setText(dto.getSize7());
            holder.color_size[i][7].setText(dto.getSize8());
        }

        //재고 값이 있는 항목에만 배경색을 칠해줌
        if(dto.getColor1_size1() != 0) {holder.color_size[0][0].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[0][0].setTextColor(Color.WHITE);}
        if(dto.getColor1_size2() != 0) {holder.color_size[0][1].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[0][1].setTextColor(Color.WHITE);}
        if(dto.getColor1_size3() != 0) {holder.color_size[0][2].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[0][2].setTextColor(Color.WHITE);}
        if(dto.getColor1_size4() != 0) {holder.color_size[0][3].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[0][3].setTextColor(Color.WHITE);}
        if(dto.getColor1_size5() != 0) {holder.color_size[0][4].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[0][4].setTextColor(Color.WHITE);}
        if(dto.getColor1_size6() != 0) {holder.color_size[0][5].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[0][5].setTextColor(Color.WHITE);}
        if(dto.getColor1_size7() != 0) {holder.color_size[0][6].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[0][6].setTextColor(Color.WHITE);}
        if(dto.getColor1_size8() != 0) {holder.color_size[0][7].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[0][7].setTextColor(Color.WHITE);}
        if(dto.getColor2_size1() != 0) {holder.color_size[1][0].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[1][0].setTextColor(Color.WHITE);}
        if(dto.getColor2_size2() != 0) {holder.color_size[1][1].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[1][1].setTextColor(Color.WHITE);}
        if(dto.getColor2_size3() != 0) {holder.color_size[1][2].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[1][2].setTextColor(Color.WHITE);}
        if(dto.getColor2_size4() != 0) {holder.color_size[1][3].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[1][3].setTextColor(Color.WHITE);}
        if(dto.getColor2_size5() != 0) {holder.color_size[1][4].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[1][4].setTextColor(Color.WHITE);}
        if(dto.getColor2_size6() != 0) {holder.color_size[1][5].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[1][5].setTextColor(Color.WHITE);}
        if(dto.getColor2_size7() != 0) {holder.color_size[1][6].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[1][6].setTextColor(Color.WHITE);}
        if(dto.getColor2_size8() != 0) {holder.color_size[1][7].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[1][7].setTextColor(Color.WHITE);}
        if (dto.getColor3_size1() != 0) {holder.color_size[2][0].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[2][0].setTextColor(Color.WHITE);}
        if (dto.getColor3_size2() != 0) {holder.color_size[2][1].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[2][1].setTextColor(Color.WHITE);}
        if (dto.getColor3_size3() != 0) {holder.color_size[2][2].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[2][2].setTextColor(Color.WHITE);}
        if (dto.getColor3_size4() != 0) {holder.color_size[2][3].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[2][3].setTextColor(Color.WHITE);}
        if (dto.getColor3_size5() != 0) {holder.color_size[2][4].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[2][4].setTextColor(Color.WHITE);}
        if (dto.getColor3_size6() != 0) {holder.color_size[2][5].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[2][5].setTextColor(Color.WHITE);}
        if (dto.getColor3_size7() != 0) {holder.color_size[2][6].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[2][6].setTextColor(Color.WHITE);}
        if (dto.getColor3_size8() != 0) {holder.color_size[2][7].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[2][7].setTextColor(Color.WHITE);}
        if (dto.getColor4_size1() != 0) {holder.color_size[3][0].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[3][0].setTextColor(Color.WHITE);}
        if (dto.getColor4_size2() != 0) {holder.color_size[3][1].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[3][1].setTextColor(Color.WHITE);}
        if (dto.getColor4_size3() != 0) {holder.color_size[3][2].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[3][2].setTextColor(Color.WHITE);}
        if (dto.getColor4_size4() != 0) {holder.color_size[3][3].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[3][3].setTextColor(Color.WHITE);}
        if (dto.getColor4_size5() != 0) {holder.color_size[3][4].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[3][4].setTextColor(Color.WHITE);}
        if (dto.getColor4_size6() != 0) {holder.color_size[3][5].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[3][5].setTextColor(Color.WHITE);}
        if (dto.getColor4_size7() != 0) {holder.color_size[3][6].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[3][6].setTextColor(Color.WHITE);}
        if (dto.getColor4_size8() != 0) {holder.color_size[3][7].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[3][7].setTextColor(Color.WHITE);}
        if (dto.getColor5_size1() != 0) {holder.color_size[4][0].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[4][0].setTextColor(Color.WHITE);}
        if (dto.getColor5_size2() != 0) {holder.color_size[4][1].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[4][1].setTextColor(Color.WHITE);}
        if (dto.getColor5_size3() != 0) {holder.color_size[4][2].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[4][2].setTextColor(Color.WHITE);}
        if (dto.getColor5_size4() != 0) {holder.color_size[4][3].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[4][3].setTextColor(Color.WHITE);}
        if (dto.getColor5_size5() != 0) {holder.color_size[4][4].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[4][4].setTextColor(Color.WHITE);}
        if (dto.getColor5_size6() != 0) {holder.color_size[4][5].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[4][5].setTextColor(Color.WHITE);}
        if (dto.getColor5_size7() != 0) {holder.color_size[4][6].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[4][6].setTextColor(Color.WHITE);}
        if (dto.getColor5_size8() != 0) {holder.color_size[4][7].setBackgroundColor(Color.parseColor("#283c45")); holder.color_size[4][7].setTextColor(Color.WHITE);}
        return convertView;
    }

    //getView 내의 ViewHolder을 사용하기 위함. ViewHolder를 사용하여 리스트뷰의 속도를 향상시킴
    static class ViewHolder {
        //변수를 저장할 뷰 설정
        public TextView store;
        public TextView location;
        public TextView[] color = new TextView[5];
        public Button[][] color_size = new Button[5][8];

        //색상 및 재고를 숨기기 위한 레이아웃 연결
        public LinearLayout[] layout_inventory_color = new LinearLayout[5];
        public LinearLayout[] layout_inventory_color_size = new LinearLayout[5];
    }
}