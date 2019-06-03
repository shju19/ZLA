package com.zla.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class StoreActivity_ViewPagerAdapter extends PagerAdapter {

    Context context;

    WebView webview;

    private String[] ImagesAddress = new String[] {
            "image1_address", "image2_address", "image3_address", "image4_address", "image5_address"
    };

    StoreActivity_ViewPagerAdapter(Context context, String[] itemList) {
        this.context = context;
        this.ImagesAddress = itemList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return ImagesAddress.length;    //GalImages.length로 길이를 정한 후 이만큼 반복 진행
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == ((WebView)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        webview = new WebView(context);
        //set javaScript enabled
        webview.getSettings().setJavaScriptEnabled(true);
        //show webview in android app not loading new webpage
        webview.setWebViewClient(new WebViewClient());
        webview.setVerticalScrollBarEnabled(false);
        webview.setVerticalScrollbarOverlay(false);
        webview.setHorizontalScrollBarEnabled(false);
        webview.setHorizontalScrollbarOverlay(false);
        webview.setInitialScale(100);

        //load webview url
        //webview.loadUrl(ImagesAddress[position]);
        webview.loadDataWithBaseURL(null, creHtmlBody(ImagesAddress[position]), "text/html", "utf-8", null);
        ((ViewPager)container).addView(webview, 0);

        return webview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((WebView) object);
    }

    //가로, 세로 이미지 맞추기
    public  String creHtmlBody(String imagUrl){
        StringBuffer sb = new StringBuffer("<HTML>");
        sb.append("<HEAD>");
        sb.append("</HEAD>");
        sb.append("<BODY style='margin:0; padding:0; text-align:center;'>");    //중앙정렬
        sb.append("<img width='100%' height: auto; src=\"" + imagUrl+"\">"); //가득차게 나옴
        sb.append("</BODY>");
        sb.append("</HTML>");
        return sb.toString();
    }
}