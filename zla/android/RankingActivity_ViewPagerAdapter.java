package com.zla.android;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class RankingActivity_ViewPagerAdapter extends PagerAdapter {

    Context context;

    private int[] GalImages = new int[] {
            R.mipmap.test_image,
            R.mipmap.test_image,
            R.mipmap.test_image,
            R.mipmap.test_image,
            R.mipmap.test_image,
            R.mipmap.test_image,
            R.mipmap.test_image,
            R.mipmap.test_image
    };

    RankingActivity_ViewPagerAdapter(Context context, int[] itemList) {
        this.context = context;
        this.GalImages = itemList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return GalImages.length;            //해당 길이만큼 반복하여 진행
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == ((ImageView)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        imageView.setPadding(0, 0, 0, 0);
        imageView.setScaleType(ImageView.ScaleType.FIT_START); //CENTER_INSIDE
        imageView.setImageResource(GalImages[position]);

        ((ViewPager)container).addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((ImageView)object);
    }
}