package com.example.m1013.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.m1013.R;
import com.example.m1013.di.component.DaggerFrag1Component;
import com.example.m1013.di.module.Frag1Module;
import com.example.m1013.mvp.contract.Frag1Contract;
import com.example.m1013.mvp.presenter.Frag1Presenter;
import com.example.m1013.mvp.ui.activity.MainActivity;
import com.example.m1013.mvp.ui.activity.ShowActivity;
import com.example.m1013.mvp.ui.adapter.MyAdapter;
import com.fyales.tagcloud.library.TagBaseAdapter;
import com.fyales.tagcloud.library.TagCloudLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class Frag1Fragment extends BaseFragment<Frag1Presenter> implements Frag1Contract.View {
    @BindView(R.id.fyales)
    TagCloudLayout fyales;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.btn_sou)
    Button btnSou;
    Unbinder unbinder;
    @BindView(R.id.img)
    ImageView img;
    private ArrayList<String> mList;
    private TagBaseAdapter mAdapter;
    private int REQUEST_CODE=200;
    public static Frag1Fragment newInstance() {
        Frag1Fragment fragment = new Frag1Fragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerFrag1Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .frag1Module(new Frag1Module(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag1, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mList = new ArrayList<>();
        mList.add("中华人民共和国");
        mList.add("大韩民国");
        mList.add("日本");
        mList.add("朝鲜");
        mList.add("台湾");
        mList.add("香港特别行政区");
        mList.add("澳门特别行政区");
        mList.add("越南");
        mList.add("老挝");
        mList.add("柬埔寨");
        mList.add("泰国");
        mList.add("缅甸");
        mList.add("马来西亚");
        mList.add("新加坡");
        mList.add("印度尼西亚");
        mList.add("文莱");
        mList.add("菲律宾");
        mAdapter = new TagBaseAdapter(getActivity(), mList);
        fyales.setAdapter(mAdapter);
        fyales.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                Toast.makeText(getActivity(), mList.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ShowActivity.class);
                startActivity(intent);
            }
        });
        btnSou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                mList.add(s);
                mAdapter.notifyDataSetChanged();
            }
        });




        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

    /*    btnSou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                list.add(s);
                TextView view = new TextView(getActivity());
                view.setText(s);
                myView.addView(view);
                myView.setPadding(5,5,5,5);
            }
        });*/
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
