package com.example.viewpagerscroll.activity;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpagerscroll.R;
import com.example.viewpagerscroll.adapter.ViewPagerAdapter;
import com.example.viewpagerscroll.model.Model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnTouchListener {
    private ViewPager mViewPager;
    private TextView mIntroTv;
    private LinearLayout mDotLayout;
    private ViewPagerAdapter mViewPagerAdapter;
    private boolean isM = true;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (isM){
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                mHandler.sendEmptyMessageDelayed(0, 3000);
            }else {
                mHandler.obtainMessage(0).sendToTarget();
            }
        }
    };
    private List<Model> list = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mIntroTv = (TextView) findViewById(R.id.tv_intro);
        mDotLayout = (LinearLayout) findViewById(R.id.dot_layout);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOnTouchListener(this);
    }

    private void initData() {
        list.add(new Model(R.mipmap.a, "有些人注定是等待别人的，有些人是注定被人等的"));
        list.add(new Model(R.mipmap.b, "人的一生，既不是想象中的那么好，也不是想象中的那么坏"));
        list.add(new Model(R.mipmap.c, "愚笨的女人打击男人；聪明的女人激励男人"));
        list.add(new Model(R.mipmap.d, "任何业绩的质变都来自于量变的积累"));
        list.add(new Model(R.mipmap.e, "人生伟业的建立，不在能知，乃在能行"));

        initDots();
        mViewPagerAdapter = new ViewPagerAdapter(list, MainActivity.this);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2) % list.size()));
        mHandler.sendEmptyMessageDelayed(0, 3000);
        updateIntroAndDot();
    }

    private void initDots() {
        for (int i = 0; i < list.size(); i++) {
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8, 8);
            if (i != 0) {
                params.leftMargin = 5;
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.selector_dot);
            mDotLayout.addView(view);
        }
    }

    private void updateIntroAndDot() {
        int currentPage = mViewPager.getCurrentItem() % list.size();
        mIntroTv.setText(list.get(currentPage).getTitle());
        for (int i = 0; i < mDotLayout.getChildCount(); i++) {
            mDotLayout.getChildAt(i).setEnabled(i == currentPage);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        updateIntroAndDot();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                isM = false;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                isM = true;
                break;
        }
        return false;
    }
}
