package top.yigege.portal.ui.common;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import top.yigege.portal.R;
import top.yigege.portal.app.base.BaseActivity;

/**
 * 协议
 */
public class ProtocolActivity extends BaseActivity {

    @BindView(R.id.protocol_back)
    ImageView protocolBack;

    @Override
    protected int initContentView() {
        return R.layout.activity_protocol;
    }

    @Override
    protected void initParms(Bundle bundle, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.protocol_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.protocol_back:this.finish();break;
            default:break;
        }
    }
}
