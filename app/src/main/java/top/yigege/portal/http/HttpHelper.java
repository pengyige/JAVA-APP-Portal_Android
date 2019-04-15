package top.yigege.portal.http;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import top.yigege.portal.app.UserManager;
import top.yigege.portal.util.AppUtils;

/**
 * author: yigege
 * created on: 2019/4/15 20:18
 * description:网络请求
 */
public class HttpHelper {
    /**连接超时时间*/
    public static final int HTTP_CONNECT_TIME_OUT = 10;

    /**读取超时时间*/
    public static final int HTTP_READ_TIME_OUT = 10;

    /**服务端响应key*/
    private static final String SERVER_RESPONSE_CODE_KEY = "code";
    private static final String SERVER_RESPONSE_MESSAGE_KEY = "message";

    /**
     * 采用观察者模式post
     * @param url
     * @param params
     * @return
     */
    public static Observable<JSONObject> post(final String url, final RequestParam params) {

        return Observable.create(new Observable.OnSubscribe<JSONObject>() {
            @Override
            public void call(final Subscriber<? super JSONObject> subscriber) {
                try {

                    Response response = postHttp(url, fifterParam(params).prase());
                    if (response == null) {
                        subscriber.onError(new HttpError(HttpError.ERROR_OPERATE, "网络异常"));
                        return;
                    }
                    int code = response.code();
                    if (code == 200) {
                        String result = response.body().string();
                        Log.i("NR",result+"");

                        JSONObject object = new JSONObject(result);
                        int rc = object.optInt(SERVER_RESPONSE_CODE_KEY);
                        if (rc == 200) {
                            subscriber.onNext(object);
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new HttpError(rc, object.optString(SERVER_RESPONSE_MESSAGE_KEY)));
                        }
                    } else {
                        subscriber.onError(new HttpError(HttpError.ERROR_OPERATE, "网络异常"));
                    }
                } catch (Exception e) {
                    subscriber.onError(new HttpError(HttpError.ERROR_OPERATE, "网络异常"));
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    /**
     * 过滤补充 系统级别的参数
     * @param param
     * @return
     */
    private static RequestParam fifterParam(RequestParam param) {
        if (param == null) {
            param = new RequestParam();
        }

        if (UserManager.getUserManager().isLogin()) {
            param.put("userId", UserManager.getUserManager().getLoginUser().getUserId());
            param.put("token", UserManager.getUserManager().getLoginUser().getToken());
        }
        return param;
    }

    /**
     * 发送http请求
     * @param url
     * @param requestBody
     * @return
     */
    private static Response postHttp(final String url, final RequestBody requestBody) {

        OkHttpClient client = new OkHttpClient.Builder()
                //  getUnsafeOkHttpClient().newBuilder()
                .connectTimeout(HTTP_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(HTTP_READ_TIME_OUT, TimeUnit.SECONDS).writeTimeout(HTTP_READ_TIME_OUT * 2, TimeUnit.SECONDS)
                .build();


        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = null;
        String result = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }



}
