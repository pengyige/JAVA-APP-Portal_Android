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
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import top.yigege.portal.R;
import top.yigege.portal.app.base.BaseActivity;
import top.yigege.portal.http.RequestParam;
import top.yigege.portal.util.CommonUtils;
import top.yigege.portal.util.ToolTipDialogUtils;

/**
 * 完善个人信息
 */
public class FillPersonInfoActivity extends BaseActivity {

    @BindView(R.id.fill_person_info_back)
    ImageView fillPersonInfoBack;
    @BindView(R.id.fill_person_info_name)
    EditText fillpersonInfoName;
    @BindView(R.id.fill_person_info_girl_btn)
    Button fillPersonInfoGirlBtn;
    @BindView(R.id.fill_person_info_man_btn)
    Button fillPersonInfoManBtn;
    @BindView(R.id.fill_person_info_passoword)
    EditText fillPersonPassword;
    @BindView(R.id.fill_person_info_next)
    Button fillPersonInfoNext;

    /**性别*/
    private int sex = 1;
    /**手机号*/
    private String phone;
    @Override
    protected int initContentView() {
        return R.layout.activity_fill_person_info;
    }

    @Override
    protected void initParms(Bundle bundle, Bundle savedInstanceState) {
        phone = (String) bundle.get("phone");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.fill_person_info_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fill_person_info_back:this.finish();
                overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
            break;
            case R.id.fill_person_info_man_btn:
                fillPersonInfoManBtn.setBackgroundResource(R.mipmap.fillprofile_man_press);
                fillPersonInfoGirlBtn.setBackgroundResource(R.mipmap.fillprofile_girl);
                sex = 1;
                break;
            case R.id.fill_person_info_girl_btn:
                fillPersonInfoGirlBtn.setBackgroundResource(R.mipmap.fillprofile_man);
                fillPersonInfoManBtn.setBackgroundResource(R.mipmap.fillprofile_girl_press);
                sex = 0;
                break;
            case R.id.fill_person_info_next:
                if (null == fillpersonInfoName.getText().toString() || fillpersonInfoName.getText().toString().trim().equals("")) {
                    ToolTipDialogUtils.showToolTip(FillPersonInfoActivity.this,"用户名不能为空",Toast.LENGTH_LONG);
                    return ;
                }

                if (CommonUtils.isBlack(fillPersonPassword.getText().toString())) {
                    ToolTipDialogUtils.showToolTip(FillPersonInfoActivity.this,"密码不能为空",Toast.LENGTH_LONG);
                    return;
                }

                if (!CommonUtils.isValidatePassword(fillPersonPassword.getText().toString())) {
                    ToolTipDialogUtils.showToolTip(FillPersonInfoActivity.this,"密码非法",Toast.LENGTH_LONG);
                    return;
                }

                sendRegisterUserReuqest(phone, fillpersonInfoName.getText().toString(),fillPersonPassword.getText().toString(),sex);

                break;


            default:break;
        }
     }


    /**
     * 发送注册用户请求
      * @param phone
     * @param username
     * @param password
     * @param sex
     */
    private void sendRegisterUserReuqest(String phone, String username, String password, int sex) {
        RequestParam requestParam = new RequestParam();
        requestParam.put("tel",phone);

    }
}
