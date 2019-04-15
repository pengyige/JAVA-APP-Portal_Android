package top.yigege.portal.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: yigege
 * created on: 2019/4/15 19:46
 * description:
 */
public abstract class BaseFragment extends Fragment {

    /**绑定对象*/
    protected Unbinder unbinder;

    /**布局对象*/
    protected View fragmentView;

    /**上下文*/
    protected Context mContext;

    /**布局加载对象*/
    protected LayoutInflater mInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mContext = getActivity().getApplicationContext();
        fragmentView = inflater.inflate(initContentView(), container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        initParms(savedInstanceState);
        initData();
        initListener();
        return fragmentView;
    }

    /**
     * 初始化布局
     * @return
     */
    protected abstract int initContentView();

    /**
     * 初始化参数
     * @param savedInstanceState
     */
    protected abstract void initParms(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();
}
