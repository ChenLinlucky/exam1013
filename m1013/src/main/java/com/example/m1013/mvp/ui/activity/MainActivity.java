package com.example.m1013.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.m1013.R;
import com.example.m1013.di.component.DaggerMainComponent;
import com.example.m1013.di.module.MainModule;
import com.example.m1013.mvp.contract.MainContract;
import com.example.m1013.mvp.presenter.MainPresenter;
import com.example.m1013.mvp.ui.fragment.Frag1Fragment;
import com.example.m1013.mvp.ui.fragment.Frag2Fragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.btn_1)
    RadioButton btn1;
    @BindView(R.id.btn_2)
    RadioButton btn2;
    @BindView(R.id.btn_3)
    RadioButton btn3;
    @BindView(R.id.btn_4)
    RadioButton btn4;
    @BindView(R.id.btn_5)
    RadioButton btn5;
    @BindView(R.id.btn_6)
    RadioButton btn6;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.frag_ment)
    FrameLayout fragMent;
    private FragmentManager manager;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ArrayList<Fragment> list = new ArrayList<>();
        Frag1Fragment frag1 = new Frag1Fragment();
        Frag2Fragment frag2 = new Frag2Fragment();
        list.add(frag1);
        list.add(frag2);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frag_ment,frag1).commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.btn_1:
                        transaction.replace(R.id.frag_ment,frag1);
                    break;
                    case R.id.btn_2:
                        transaction.replace(R.id.frag_ment,frag2);
                    break;

                }
                transaction.commit();
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);



    }
}
