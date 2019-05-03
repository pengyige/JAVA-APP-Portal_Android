package top.yigege.portal.ui.login;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import top.yigege.portal.R;
import top.yigege.portal.app.GlobalConfig;
import top.yigege.portal.app.base.BaseActivity;
import top.yigege.portal.http.BaseSubscriber;
import top.yigege.portal.http.HttpHelper;
import top.yigege.portal.http.RequestParam;
import top.yigege.portal.util.CommonUtils;
import top.yigege.portal.util.NavigationController;
import top.yigege.portal.util.ToolTipDialogUtils;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.register_back)
    ImageView registerBack;
    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_next)
    Button registerNext;

    @Override
    protected int initContentView() {
        return R.layout.activity_register;
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

    @OnClick({R.id.register_back,R.id.register_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_back:this.finish();break;
            case R.id.register_next:
                if (null != registerPhone.getText().toString() && CommonUtils.isPhoneNumber(registerPhone.getText().toString())) {
                    sendRegisterRequest(registerPhone.getText().toString());
                }else {
                    ToolTipDialogUtils.showToolTip(this,"手机号不正确",Toast.LENGTH_SHORT);
                }
                break;
            default:break;
        }
    }

    /**
     * 发送注册请求
     * @param phone
     */
    private void sendRegisterRequest(final String phone) {
        RequestParam requestParam = new RequestParam();
        requestParam.put("phone",phone);

        HttpHelper.post(GlobalConfig.REGISTER_CODE,requestParam).subscribe(new BaseSubscriber<JSONObject>(this) {
            @Override
            public void onNext(JSONObject o) {
                try {
                    Log.i("msg",o.getString("message").toString());
                    String registerCode = o.getString("message");
                    String registerPhone = phone;
                    Map param = new HashMap<String,Object>();
                    param.put("registerCode",registerCode);
                    param.put("registerPhone",registerPhone);
                    NavigationController.getInstance().jumpTo(FillCodeActivity.class,param);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (!this.bErrorExcuted(e)) {
                    ToolTipDialogUtils.showToolTip(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT);
                }

            }
        });
    }
}
