package com.example.scs.rxretrofitmvp;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.scs.rxretrofitmvp.base.AActivity;
import com.example.scs.rxretrofitmvp.p.LoginPresenter;
import com.example.scs.rxretrofitmvp.ui.RxDemoActivity;
import com.example.scs.rxretrofitmvp.util.RetrofitUtils;
import com.example.scs.rxretrofitmvp.util.SharedPreferencesManager;
import com.example.scs.rxretrofitmvp.v.LoginView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AActivity<LoginPresenter, LoginView> implements LoginView {

    TextView tv_login;

    public void onlogin(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("username", "15706844102");
        map.put("pushType", "1");
        map.put("password", "e10adc3949ba59abbe56e057f20f883e");
        map.put("deviceToken", "AteDBO4bsJC9QiZTpQIFvinCzhdBE770CzIqbHuHFxrQ");
        presenter.login(map);
    }

    @Override
    public void init() {
        tv_login = findViewById(R.id.tv_login);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public int setlayout() {
        return R.layout.activity_main;
    }

    @Override
    public int getLoaderid() {
        return MainActivity.this.hashCode();
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
    public <T> void loginSuccess(T msg) {
        String token = "";
        try {
            JSONObject jsonObject = new JSONObject(msg.toString());
            token = jsonObject.getJSONObject("Data").getString("UserToken");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RetrofitUtils.removeRetrofit();

        SharedPreferencesManager.getInstance().setUserToken(token);

        Intent intent = new Intent(activity, RxDemoActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFail() {
        show_Toast("请求失败");
    }
}
