package com.example.viewpagerscroll.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.viewpagerscroll.R;
import com.example.viewpagerscroll.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyue on 2016/5/20.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<Model> models = new ArrayList<>();
    private Context mContext;
    public ViewPagerAdapter(List<Model> models,Context context) {
        super();
        this.models = models;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.adapter_ad, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        Model ad = models.get(position % models.size());
        imageView.setImageResource(ad.getIcon());

        container.addView(view);// 一定不能少，将view加入到viewPager中

        return view;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
