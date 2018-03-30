package com.example.scs.rxretrofitmvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.scs.rxretrofitmvp.R;
import com.example.scs.rxretrofitmvp.base.AActivity;
import com.example.scs.rxretrofitmvp.p.MainPresenter;
import com.example.scs.rxretrofitmvp.v.MainView;

public class RxDemoActivity extends AActivity<MainPresenter, MainView> implements MainView {

    TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        tv_show = findViewById(R.id.tv_show);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public int setlayout() {
        return R.layout.activity_rx_demo;
    }

    @Override
    public int getLoaderid() {
        return RxDemoActivity.this.hashCode();
    }

    public void onMainbtn(View view) {
        presenter.getMainData();
    }

    @Override
    public void ShowLoading() {
        showProgressDialog();
    }

    @Override
    public void CencleLoading() {
        dismissProgressDialog();
    }

    @Override
    public <T> void MainSuccess(T msg) {
        tv_show.setText(msg.toString());
    }

    @Override
    public void MainFail() {
        show_Toast("服务器跑走了");
    }


    public void onRxbtn(View view) {

    }
}
