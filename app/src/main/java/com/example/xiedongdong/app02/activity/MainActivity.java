package com.example.xiedongdong.app02.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiedongdong.app02.R;
import com.example.xiedongdong.app02.fragment.CommunityFragment;
import com.example.xiedongdong.app02.fragment.HomeFragment;
import com.example.xiedongdong.app02.fragment.MeFragment;


/**
 * Created by xiedongdong on 16/5/24.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener{
    //底部三个导航布局
    private LinearLayout ll_home;
    private LinearLayout ll_community;
    private LinearLayout ll_me;
    //三个Textview
    private TextView tv_home;
    private TextView tv_community;
    private TextView tv_me;
    //初始化三个Fragment
    private Fragment tab01;
    private Fragment tab02;
    private Fragment tab03;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //发送延迟后2秒钟，改变isExit的值
            isExit = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //关闭横屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();   //初始化页面
        initEvents();  //初始化事件
        onSelect(0);  //默认启动页面（首页）
    }

    private void initEvents() {
        ll_home.setOnClickListener(this);
        ll_community.setOnClickListener(this);
        ll_me.setOnClickListener(this);
    }

    private void initView() {
        ll_home=(LinearLayout)findViewById(R.id.ll_home);
        ll_community=(LinearLayout)findViewById(R.id.ll_community);
        ll_me=(LinearLayout)findViewById(R.id.ll_me);

        tv_home=(TextView)findViewById(R.id.tv_home);
        tv_community=(TextView)findViewById(R.id.tv_community);
        tv_me=(TextView)findViewById(R.id.tv_me);
    }

    @Override
    public void onClick(View view) {
        resetText();
        switch (view.getId()){
            case R.id.ll_home:
                onSelect(0); //要启动的页面，调用onSelect方法
                break;
            case R.id.ll_community:
                onSelect(1);
                break;
            case R.id.ll_me:
                onSelect(2);
                break;
            default:
                break;

        }
    }

    private void onSelect(int i) {

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        //隐藏Fragment,不然会出现叠加显示效果
        hideFragment(fragmentTransaction);

        switch (i){
            case 0:
                if(tab01==null){
                    tab01=new HomeFragment();    //tab01指向HomeFragment
                    fragmentTransaction.add(R.id.fl_content,tab01);  //将HomeFragment添加到活动中
                }else{
                    fragmentTransaction.show(tab01);
                }
                tv_home.setTextColor(getResources().getColor(R.color.orange));
                tv_home.setTextSize(23);
                break;
            case 1:
                if (tab02==null){
                    tab02=new CommunityFragment();
                    fragmentTransaction.add(R.id.fl_content,tab02);
                }else{
                    fragmentTransaction.show(tab02);
                }
                tv_community.setTextColor(getResources().getColor(R.color.orange));
                tv_community.setTextSize(23);
                break;
            case 2:
                if (tab03==null){
                    tab03=new MeFragment();
                    fragmentTransaction.add(R.id.fl_content,tab03);
                }else{
                    fragmentTransaction.show(tab03);
                }
                tv_me.setTextColor(getResources().getColor(R.color.orange));
                tv_me.setTextSize(23);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();  //提交事务
    }
    /**
     * 隐藏所有的Fragment
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if(tab01!=null){
            fragmentTransaction.hide(tab01);
        }
        if(tab02!=null){
            fragmentTransaction.hide(tab02);
        }
        if(tab03!=null){
            fragmentTransaction.hide(tab03);
        }

    }

    /**
     *
     * 重置所有的颜色为白色（默认颜色）
     */

    private void resetText() {
        tv_home.setTextColor(Color.GRAY);
        tv_home.setTextSize(20);
        tv_community.setTextColor(Color.GRAY);
        tv_community.setTextSize(20);
        tv_me.setTextColor(Color.GRAY);
        tv_me.setTextSize(20);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }



}
