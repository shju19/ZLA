package com.zla.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity_SQLiteOpenHelper extends SQLiteOpenHelper {

        //생성자
        public MainActivity_SQLiteOpenHelper(Context context, String name,
                                             CursorFactory factory, int version) {
                super(context, name, factory, version);
        }

        //테이블 생성코드
        @Override
        public void onCreate(SQLiteDatabase db) {
                // TODO Auto-generated method stub

                //road table
                String sql_road = "create table bicycle_road ("
                        //고유값. 자동 증가
                        + "_id integer primary key autoincrement, "
                        //기본 정보
                        + "brand			text, "
                        + "type				text, "
                        + "year				integer, "
                        + "name				text, "
                        + "color1			text, "
                        + "color2			text, "
                        + "color3			text, "
                        + "color4			text, "
                        + "color5			text, "
                        + "size1			text, "
                        + "size2			text, "
                        + "size3			text, "
                        + "size4			text, "
                        + "size5			text, "
                        + "size6			text, "
                        + "size7			text, "
                        + "size8			text, "
                        + "size1_inner 	text, "
                        + "size2_inner		text, "
                        + "size3_inner		text, "
                        + "size4_inner		text, "
                        + "size5_inner		text, "
                        + "size6_inner		text, "
                        + "size7_inner		text, "
                        + "size8_inner		text, "
                        + "height1    		text, "
                        + "height2    		text, "
                        + "height3    		text, "
                        + "height4    		text, "
                        + "height5    		text, "
                        + "height6    		text, "
                        + "height7    		text, "
                        + "height8    		text, "
                        + "price			integer, "

                        //주요 사양
                        + "frame			text, "
                        + "frameName		text, "
                        + "fork				text, "

                        //구동계
                        + "groupset		text, "
                        + "shifters		text, "
                        + "frontDerailleur	text, "
                        + "rearDerailleur	text, "
                        + "brakeLever		text, "
                        + "brake			text, "
                        + "crankset		text, "
                        + "sprocket		text, "
                        + "chain			text, "

                        //상세 사양
                        + "stem				text, "
                        + "handlebar		text, "
                        + "wheelset		text, "
                        + "tires			text, "
                        + "saddle			text, "
                        + "seatPost		text, "
                        + "pedal			text, "
                        + "suspension		text, "
                        + "weight			text, "

                        //지오메트리
                        + "geometry		text, "

                        //이미지
                        + "image1			text, "
                        + "image2			text, "
                        + "image3			text, "
                        + "image4			text, "
                        + "image5			text, "

                        //즐겨찾기
                        + "wishList		text)";

                db.execSQL(sql_road);

                //mtb table
                String sql_mtb = "create table bicycle_mtb ("
                        //고유값. 자동 증가
                        + "_id integer primary key autoincrement, "

                        //기본 정보
                        + "brand			text, "
                        + "type				text, "
                        + "year				integer, "
                        + "name				text, "
                        + "color1			text, "
                        + "color2			text, "
                        + "color3			text, "
                        + "color4			text, "
                        + "color5			text, "
                        + "size1			text, "
                        + "size2			text, "
                        + "size3			text, "
                        + "size4			text, "
                        + "size5			text, "
                        + "size6			text, "
                        + "size7			text, "
                        + "size8			text, "
                        + "size1_inner 	text, "
                        + "size2_inner		text, "
                        + "size3_inner		text, "
                        + "size4_inner		text, "
                        + "size5_inner		text, "
                        + "size6_inner		text, "
                        + "size7_inner		text, "
                        + "size8_inner		text, "
                        + "height1    		text, "
                        + "height2    		text, "
                        + "height3    		text, "
                        + "height4    		text, "
                        + "height5    		text, "
                        + "height6    		text, "
                        + "height7    		text, "
                        + "height8    		text, "
                        + "price			integer, "

                        //주요 사양
                        + "frame			text, "
                        + "frameName		text, "
                        + "fork				text, "

                        //구동계
                        + "groupset		text, "
                        + "shifters		text, "
                        + "frontDerailleur	text, "
                        + "rearDerailleur	text, "
                        + "brakeLever		text, "
                        + "brake			text, "
                        + "crankset		text, "
                        + "sprocket		text, "
                        + "chain			text, "

                        //상세 사양
                        + "stem				text, "
                        + "handlebar		text, "
                        + "wheelset		text, "
                        + "tires			text, "
                        + "saddle			text, "
                        + "seatPost		text, "
                        + "pedal			text, "
                        + "suspension		text, "
                        + "weight			text, "

                        //지오메트리
                        + "geometry		text, "

                        //이미지
                        + "image1			text, "
                        + "image2			text, "
                        + "image3			text, "
                        + "image4			text, "
                        + "image5			text, "

                        //즐겨찾기
                        + "wishList		text)";

                db.execSQL(sql_mtb);

                //wishList table
                String sql_wishList = "create table bicycle_wishList ("
                        //고유값. 자동 증가
                        + "_id integer primary key autoincrement, "

                        //기본 정보
                        + "brand			text, "
                        + "type				text, "
                        + "year				integer, "
                        + "name				text, "
                        + "color1			text, "
                        + "color2			text, "
                        + "color3			text, "
                        + "color4			text, "
                        + "color5			text, "
                        + "size1			text, "
                        + "size2			text, "
                        + "size3			text, "
                        + "size4			text, "
                        + "size5			text, "
                        + "size6			text, "
                        + "size7			text, "
                        + "size8			text, "
                        + "size1_inner 	text, "
                        + "size2_inner		text, "
                        + "size3_inner		text, "
                        + "size4_inner		text, "
                        + "size5_inner		text, "
                        + "size6_inner		text, "
                        + "size7_inner		text, "
                        + "size8_inner		text, "
                        + "height1    		text, "
                        + "height2    		text, "
                        + "height3    		text, "
                        + "height4    		text, "
                        + "height5    		text, "
                        + "height6    		text, "
                        + "height7    		text, "
                        + "height8    		text, "
                        + "price			integer, "

                        //주요 사양
                        + "frame			text, "
                        + "frameName		text, "
                        + "fork				text, "

                        //구동계
                        + "groupset		text, "
                        + "shifters		text, "
                        + "frontDerailleur	text, "
                        + "rearDerailleur	text, "
                        + "brakeLever		text, "
                        + "brake			text, "
                        + "crankset		text, "
                        + "sprocket		text, "
                        + "chain			text, "

                        //상세 사양
                        + "stem				text, "
                        + "handlebar		text, "
                        + "wheelset		text, "
                        + "tires			text, "
                        + "saddle			text, "
                        + "seatPost		text, "
                        + "pedal			text, "
                        + "suspension		text, "
                        + "weight			text, "

                        //지오메트리
                        + "geometry		text, "

                        //이미지
                        + "image1			text, "
                        + "image2			text, "
                        + "image3			text, "
                        + "image4			text, "
                        + "image5			text, "

                        //즐겨찾기
                        + "wishList		text)";

                db.execSQL(sql_wishList);

                //store table
                String sql_store = "create table bicycle_store ("
                        //고유값. 자동 증가
                        + "_id integer primary key autoincrement, "

                        //기본 정보
                        + "store_id             text, "
                        + "location             text, "
                        + "storeName            text, "
                        + "adress               text, "
                        + "latitude             double, "
                        + "longitude            double, "
                        + "manager              text, "
                        + "position             text, "
                        + "contact              text, "
                        + "mobilePhone          text, "

                        //세부내용
                        + "basicFitting         text, "
                        + "specialFitting       text, "
                        + "repair               text, "

                        //영업시간
                        + "businessHour_weekly          text, "
                        + "businessHour_saturday        text, "
                        + "businessHour_holiday         text, "
                        + "holiday                      text, "

                        //브랜드(로드, MTB 구분)
                        + "masi_road text, " + "masi_mtb text, "
                        + "merida_road text, " + "merida_mtb text, "
                        + "boardman_road text, " + "boardman_mtb text, "
                        + "bianchi_road text, " + "bianchi_mtb text, "
                        + "samchuly_road text, " + "samchuly_mtb text, "
                        + "scott_road text, " + "scott_mtb text, "
                        + "specialized_road text, " + "specialized_mtb text, "
                        + "cervelo_road text, " + "cervelo_mtb text, "
                        + "alton_road text, " + "alton_mtb text, "
                        + "elfama_road text, " + "elfama_mtb text, "
                        + "wilier_road text, " + "wilier_mtb text, "
                        + "giant_road text, " + "giant_mtb text, "
                        + "cello_road text, " + "cello_mtb text, "
                        + "cannondale_road text, " + "cannondale_mtb text, "
                        + "trek_road text, " + "trek_mtb text, "
                        + "trigon_road text, " + "trigon_mtb text, "
                        + "fuji_road text, " + "fuji_mtb text, "
                        + "gt_road text, " + "gt_mtb text, "
                        + "brand_extra text, "

                        //이미지
                        + "image1_address text, " + "image2_address text, " + "image3_address text, " + "image4_address text, " + "image5_address text)";

                db.execSQL(sql_store);

                //store table
                String sql_latestList = "create table bicycle_latestList ("
                        //고유값. 자동 증가
                        + "_id integer primary key autoincrement, "

                        //기본 정보
                        + "bicycleSeries        text, "
                        + "name                 text, "
                        + "year                 integer)";
                db.execSQL(sql_latestList);

                //personal_information table
                String sql_personal = "create table personal_information ("
                        //고유값. 자동 증가
                        + "_id integer primary key autoincrement, "

                        //기본 정보
                        + "confirmLocation        text, "
                        + "personalSize           text, "
                        + "mobilePhone           text)";
                db.execSQL(sql_personal);

        }
        //table upgrade
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // TODO Auto-generated method stub

                String sql_road = "drop table if exists bicycle_road";
                String sql_mtb = "drop table if exists bicycle_mtb";
                String sql_wishList = "drop table if exists bicycle_wishList";
                String sql_store = "drop table if exists bicycle_store";
                String sql_latestList = "drop table if exists bicycle_latestList";
                String sql_personal = "drop table if exists personal_information";

                db.execSQL(sql_road);
                db.execSQL(sql_mtb);
                db.execSQL(sql_wishList);
                db.execSQL(sql_store);
                db.execSQL(sql_latestList);
                db.execSQL(sql_personal);

                onCreate(db);
        }
}
