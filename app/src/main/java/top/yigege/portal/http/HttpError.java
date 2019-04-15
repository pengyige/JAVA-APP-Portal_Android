package top.yigege.portal.http;

/**
 * author: yigege
 * created on: 2019/4/15 20:05
 * description:网络请求异常
 */
public class HttpError extends RuntimeException{

    /**参数错误*/
    public static final int ERROR_PARAM = 400;

    /**操作失败*/
    public static final int ERROR_OPERATE = 501;

    private int code;
    private String msg;

    public HttpError(int rc, String msg) {
        this.code = rc;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
