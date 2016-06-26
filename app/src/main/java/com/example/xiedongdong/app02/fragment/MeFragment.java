package com.example.xiedongdong.app02.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiedongdong.app02.Base.BaseFragment;
import com.example.xiedongdong.app02.R;
import com.example.xiedongdong.app02.activity.AboutSoftActivity;
import com.example.xiedongdong.app02.activity.ChangePasswordActivity;
import com.example.xiedongdong.app02.activity.LoginActivity;
import com.example.xiedongdong.app02.activity.MoreFunctionActivity;
import com.example.xiedongdong.app02.activity.UserInfo;
import com.example.xiedongdong.app02.bean.User;
import com.example.xiedongdong.app02.util.BitmapFileNet;

import java.io.File;
import java.io.IOException;

import cn.bmob.v3.BmobUser;

/**
 * Created by xiedongdong on 16/5/24.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private final String PATH= Environment.getExternalStorageDirectory()+"/Geek/head_image.jpg" ;

    private ImageView img_headImg;
    private LinearLayout ll_userInfo;
    private TextView tv_username;
    private Button btn_quitUsername;
    private LinearLayout ll_aboutSoft;
    private LinearLayout ll_changePassword;
    private LinearLayout ll_myPosts;
    private LinearLayout ll_myCollect;
    private LinearLayout ll_moreFunction;
    private TextView tv_currentUser;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_me,container,false);

        img_headImg=(ImageView)view.findViewById(R.id.img_headImg);

        ll_userInfo=(LinearLayout)view.findViewById(R.id.ll_userInfo);
        ll_userInfo.setOnClickListener(this);

        tv_username=(TextView)view.findViewById(R.id.tv_username);

        btn_quitUsername=(Button)view.findViewById(R.id.btn_quitUsername);
        btn_quitUsername.setOnClickListener(this);

        ll_aboutSoft=(LinearLayout) view.findViewById(R.id.ll_aboutSoft);
        ll_aboutSoft.setOnClickListener(this);

        ll_changePassword=(LinearLayout) view.findViewById(R.id.ll_changePassword);
        ll_changePassword.setOnClickListener(this);

        ll_myPosts=(LinearLayout)view.findViewById(R.id.ll_myPosts);
        ll_myPosts.setOnClickListener(this);

        ll_myCollect=(LinearLayout)view.findViewById(R.id.ll_myCollect);
        ll_myCollect.setOnClickListener(this);

        ll_moreFunction=(LinearLayout)view.findViewById(R.id.ll_moreFunction);
        ll_moreFunction.setOnClickListener(this);

        tv_currentUser=(TextView)view.findViewById(R.id.tv_currentUser);

        /**
         * 从ShardPerferences中获取用户名信息
         */
        initData();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ll_userInfo:
                if(isLogin()){
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }else{
                    startActivity(new Intent(getActivity(), UserInfo.class));
                }
                break;
            case R.id.ll_myPosts:
                showToast("正在开发中");
                break;
            case R.id.ll_myCollect:
                showToast("正在开发中");
                break;
            case R.id.ll_aboutSoft:
                startActivity(new Intent(getActivity(),AboutSoftActivity.class));
                break;
            case R.id.ll_changePassword:
                if(isLogin()){
                    showToast("未登录");
                }else{
                    startActivity(new Intent(getActivity(),ChangePasswordActivity.class));
                }
                break;
            case R.id.ll_moreFunction:
                if(isLogin()){
                    showToast("未登录");
                }else{
                    startActivity(new Intent(getActivity(),MoreFunctionActivity.class));
                }
                break;
            case R.id.btn_quitUsername:
                if(isLogin()){
                    showToast("未登录");
                }else{
                    logOut();
                    getActivity().finish();
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
                break;

            default:
                break;

        }

    }

    /**
     * 初始化数据
     */
    private void initData() {
        final User userInfo=BmobUser.getCurrentUser(getContext(),User.class);
        if(userInfo!=null){
            tv_username.setText(userInfo.getUsername());
        }

        initHeadImg();
    }

    /**
     * 初始化数据是获取头像
     */
    private void initHeadImg() {
        File headImgFile=new File(new String(PATH));
        if(headImgFile.exists()){
            Bitmap bitmap= BitmapFactory.decodeFile(PATH);
            img_headImg.setImageBitmap(bitmap);
        }else{
            final User user=BmobUser.getCurrentUser(getActivity().getApplication(),User.class);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Bitmap bitmap= BitmapFileNet.get(user.getHeadImgUrl());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                img_headImg.setImageBitmap(bitmap);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 退出用户
     */
    private void logOut() {

        BmobUser.logOut(getActivity());   //清除缓存用户
    }

    /**
     * 检测是否登录
     */
    public boolean isLogin() {
        User userInfo= BmobUser.getCurrentUser(getActivity(),User.class);
        if(userInfo==null){
            return true;
        }

        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        initHeadImg();
    }
}