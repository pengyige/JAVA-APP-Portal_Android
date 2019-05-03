package top.yigege.portal.ui.login;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import top.yigege.portal.R;
import top.yigege.portal.app.base.BaseActivity;
import top.yigege.portal.util.NavigationController;
import top.yigege.portal.util.ToolTipDialogUtils;

/**
 * 验证码
 */
public class FillCodeActivity extends BaseActivity {


    @BindView(R.id.fill_code_back)
    ImageView fillCodeBack;
    @BindView(R.id.fill_code_next)
    Button fillCodeNext;
    @BindView(R.id.fill_code)
    EditText fillCode;


    /**验证码*/
    private String registerCode;
    /**手机号*/
    private String registerPhone;
    @Override
    protected int initContentView() {
        return R.layout.activity_fill_code;
    }

    @Override
    protected void initParms(Bundle bundle, Bundle savedInstanceState) {
        registerCode = (String) bundle.get("registerCode");
        registerPhone = (String) bundle.get("registerPhone");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.fill_code_back,R.id.fill_code_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fill_code_back:this.finish();break;
            case R.id.fill_code_next:
                if (null == fillCode.getText().toString() || fillCode.getText().toString().trim().equals("")) {
                    ToolTipDialogUtils.showToolTip(FillCodeActivity.this,"验证码不能为空",2000);
                    return;
                }

                if (!fillCode.getText().toString().equals(registerCode)) {
                    ToolTipDialogUtils.showToolTip(FillCodeActivity.this,"您输入的验证码不正确",2000);
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("phone",registerPhone);

                NavigationController.getInstance().jumpTo(FillPersonInfoActivity.class,bundle,true);
                break;
            default:break;
        }
    }
}
