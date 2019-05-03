package top.yigege.portal.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.yigege.portal.R;
import top.yigege.portal.app.base.BaseActivity;
import top.yigege.portal.ui.common.ProtocolActivity;
import top.yigege.portal.util.NavigationController;

/**
 * author: yigege
 * created on: 2019/5/2 15:31
 * description:欢迎页面
 */
public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.welcome_login)
    Button welcomeLogin;
    @BindView(R.id.welcome_register)
    Button welcomeRegister;
    @BindView(R.id.welcome_protocol)
    TextView welcomeProtocol;

    @Override
    protected int initContentView() {
        return R.layout.acitivity_welcome;
    }

    @Override
    protected void initParms(Bundle bundle, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        Log.i("test","ok");
    }

    @OnClick({R.id.welcome_login,R.id.welcome_register,R.id.welcome_protocol})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.welcome_login:
                NavigationController.getInstance().jumpTo(LoginActivity.class,null,false);
                break;
            case R.id.welcome_register:
                NavigationController.getInstance().jumpTo(RegisterActivity.class,null,false);
                break;
            case R.id.welcome_protocol:
                NavigationController.getInstance().jumpTo(ProtocolActivity.class,null,false);

                break;
            default:break;
        }
    }
}
