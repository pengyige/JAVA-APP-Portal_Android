package top.yigege.portal.http;

import java.io.File;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author: yigege
 * created on: 2019/4/15 20:19
 * description:
 */
public class RequestParam extends HashMap<String,Object> {

    /**
     * 将map装换成okhttp请求body
     * @return
     */
    public RequestBody prase() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (String key : keySet()) {
            Object object = get(key);
            if (!(object instanceof File)) {
                String value = object.toString();
                builder.addFormDataPart(key, value);
            } else {
                File file = (File) object;
                builder.addFormDataPart("file", file.getName(), RequestBody.create(null, file));
            }
        }
        return builder.build();
    }
}
