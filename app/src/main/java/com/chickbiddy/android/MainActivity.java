package com.chickbiddy.android;

import android.os.SystemClock;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import com.chickbiddy.android.base.BaseActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.chickbiddy.android.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    ActivityMainBinding mBinding;

    private Button diary;
    private Button pictures;
    private Button clkControl;
    private Chronometer clk;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        initId();
        initListener();
    }

    private void initId() {
        diary = mBinding.diary;
        pictures=mBinding.pictures;
        clkControl=mBinding.clkControl;
        clk=mBinding.clk;

        clk.setVisibility(View.GONE);
    }
    private void initListener() {
        mBinding.diary.setOnClickListener(this);
        mBinding.pictures.setOnClickListener(this);
        mBinding.clkControl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.diary://打开日记
                break;
            case R.id.pictures://打开图集
                break;
            case R.id.clkControl:
                if(clkControl.getText()=="开始学习") {
                    clkControl.setText("学累了~");
                    clk.setBase(SystemClock.elapsedRealtime());//计时器清零
                    int hour = (int) ((SystemClock.elapsedRealtime() - clk.getBase()) / 1000 / 60);
                    clk.setFormat("0" + String.valueOf(hour) + ":%s");
                    clk.setVisibility(View.VISIBLE);
                    clk.start();
                }
                else {
                    clkControl.setText("开始学习");
                    clk.setVisibility(View.GONE);
                    clk.stop();
                }
                break;

            default:
                break;
        }
    }
}
