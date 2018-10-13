package com.example.m1013.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.m1013.R;
import com.example.m1013.bean.News;
import com.example.m1013.di.component.DaggerShowComponent;
import com.example.m1013.di.module.ShowModule;
import com.example.m1013.mvp.contract.ShowContract;
import com.example.m1013.mvp.presenter.ShowPresenter;
import com.example.m1013.mvp.ui.adapter.MyAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ShowActivity extends BaseActivity<ShowPresenter> implements ShowContract.View {

    @BindView(R.id.recy_view)
    RecyclerView recyView;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerShowComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .showModule(new ShowModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_show; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.getshowdata();

        //刷新
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smart.finishRefresh(2000);
            }
        });
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smart.finishLoadMore(2000);
            }
        });
        //设置 Header 为 贝塞尔雷达 样式
        smart.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        smart.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
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
    public void data(News news) {
        List<News.BooksBean> books = news.getBooks();
        Toast.makeText(this, "books:" + books, Toast.LENGTH_SHORT).show();
        LinearLayoutManager manager = new LinearLayoutManager(ShowActivity.this, LinearLayoutManager.VERTICAL, false);
        recyView.setLayoutManager(manager);
        MyAdapter adapter = new MyAdapter(ShowActivity.this, books);
        recyView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
