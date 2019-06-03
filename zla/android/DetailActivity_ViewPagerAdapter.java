package com.zla.android;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DetailActivity_ViewPagerAdapter extends PagerAdapter {

    Context context;

    private int[] GalImages = new int[] {
            R.mipmap.test_image,
            R.mipmap.test_image,
            R.mipmap.test_image,
            R.mipmap.test_image,
            R.mipmap.test_image
    };

    DetailActivity_ViewPagerAdapter(Context context, int[] itemList) {
        this.context = context;
        this.GalImages = itemList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 1;					//GalImages.length;
    }								//원래 뷰페이저는 GalImages.length로 길이 정한 후 이만큼 반복해야 한다.
    //그러나 임시로 이미지 1개만 확보하여 임시로 return 1;값을 대입해놓는다.

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

