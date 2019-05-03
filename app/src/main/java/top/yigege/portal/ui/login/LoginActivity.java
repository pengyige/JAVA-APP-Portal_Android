package top.yigege.portal.ui.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import top.yigege.portal.R;
import top.yigege.portal.app.base.BaseActivity;

/**
 * 登入活动
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_back)
    ImageView loginBack;

    @Override
    protected int initContentView() {
        return R.layout.activity_login;
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

    /**
     *
     * @param view
     */
    @OnClick({R.id.login_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_back:this.finish();break;
            default:break;
        }
    }
}
