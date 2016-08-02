
package com.travel.liuyun.network;

import android.util.Log;

import com.travel.liuyun.Constants;
import com.travel.liuyun.bean.Image;
import com.travel.liuyun.bean.Result;
import com.travel.liuyun.bean.UserInfo;
import com.travel.liuyun.parse.JSONUtil;
import com.travel.liuyun.utils.InternetUtil;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.conn.params.ConnManagerParams;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by Joe Date: 12-12-6 下午10:33 Email: joesupper@vip.qq.com
 */
public final class NetAccess {
    public final static int HTTP_TIMEOUT = 30000;
    private final static String STATUS = "status";
    private final static String MESSAGE = "message";
    private final static String DATA = "data";

    public static Result<UserInfo> saveUserIntroduce(String uid, String introduce) {
        List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
        data.add(new BasicNameValuePair("ID", uid));
        data.add(new BasicNameValuePair("Introduce", introduce));
        return apiRequest(Method.modifyUserInfo, data, new DataListener() {
            @Override
            public void bind(Result result, JSONObject jsonObject) {
                //final JSONObject object = jsonObject.optJSONObject(DATA);
            }
        });
    }

    // 需要上传图片的接口的方法
    public static Result<Image> uploadPicture(String pictureKey, String avatarUrl) {
        try {
            FormFile formFile = new FormFile(pictureKey, avatarUrl);
            List<BasicNameValuePair> data = new ArrayList<>();
            data.add(new BasicNameValuePair("version", "1.0"));
            data.add(new BasicNameValuePair("platform", "android"));
            data.add(new BasicNameValuePair("key", "123456"));
            final String result = uploadFile(Constants.UPLOAD_PICTURE, data, new FormFile[] {
                    formFile
            });
            if (null == result)
                return Result.error();
            JSONObject jsonObject = new JSONObject(result);
            Result r = new Result();
            r.setStatus(jsonObject.optInt(STATUS));
            r.setMessage(jsonObject.optString(MESSAGE));
            if (jsonObject.has(DATA) && null != jsonObject.get(DATA) && r.isSuccess()) {
                final JSONObject imageData = jsonObject.getJSONObject(DATA);
                r.setData(new Image(imageData.getString("img"), imageData.getString("img_thum")));
            }
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    public static Result<UserInfo> uploadUserAvatarOrBackground(String pictureKey, String avatarUrl, String id) {
        try {
            FormFile formFile = new FormFile(pictureKey, avatarUrl);
            List<BasicNameValuePair> data = new ArrayList<>();
            data.add(new BasicNameValuePair("version","1.0"));
            data.add(new BasicNameValuePair("platform", "android"));
            data.add(new BasicNameValuePair("key", "123456"));
            data.add(new BasicNameValuePair("ID", id));
            final String result = uploadFile(Constants.MODIFY_USERINFO_MSG, data, new FormFile[] {
                    formFile
            });
            if (null == result)
                return Result.error();
            JSONObject jsonObject = new JSONObject(result);
            Result r = new Result();
            r.setStatus(jsonObject.optInt(STATUS));
            r.setMessage(jsonObject.optString(MESSAGE));
            if (jsonObject.has(DATA) && null != jsonObject.get(DATA) && r.isSuccess()) {
                final Map<String,Object> loginResult = JSONUtil.getLoginResult(result);
                r.setData(loginResult.get("data"));
            }
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    public static DefaultHttpClient getHttpClient(int timeout) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), timeout);
        HttpConnectionParams.setSoTimeout(httpClient.getParams(), timeout);
        ConnManagerParams.setTimeout(httpClient.getParams(), timeout);
        return httpClient;
    }

    public static DefaultHttpClient getHttpClient() {
        return getHttpClient(HTTP_TIMEOUT);
    }

    public static String uploadFile(String actionUrl, List<BasicNameValuePair> data,
            FormFile[] files) {
        HttpURLConnection conn = null;
        DataOutputStream outStream = null;
        try {
            String BOUNDARY = "-----------------------------"
                    + UUID.randomUUID().toString().replace("-", ""); // 数据分隔线
            String MULTIPART_FORM_DATA = "multipart/form-data";
            URL url = new URL(actionUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            conn.setDoInput(true);// 允许输入
            conn.setDoOutput(true);// 允许输出
            conn.setUseCaches(false);// 不使用Cache
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA + "; boundary=" + BOUNDARY);
            StringBuilder sb = new StringBuilder();
            // 上传的表单参数部分，格式 请参考文章
            for (BasicNameValuePair nvp : data) {// 构建表单字段内容
                sb.append("--");
                sb.append(BOUNDARY);
                sb.append("\r\n");
                sb.append("Content-Disposition: form-data; name=\"").append(nvp.getName())
                        .append("\"\r\n\r\n");
                sb.append(nvp.getValue());
                sb.append("\r\n");
            }
            outStream = new DataOutputStream(conn.getOutputStream());
            outStream.write(sb.toString().getBytes());// 发送表单字段数据
            // 上传的文件部分，格式请参考文章
            for (FormFile file : files) {
                StringBuilder split = new StringBuilder();
                split.append("--");
                split.append(BOUNDARY);
                split.append("\r\n");
                split.append("Content-Disposition: form-data;name=\"").append(file.getName())
                        .append("\";filename=\"").append(file.getFileName()).append("\"\r\n");
                split.append("Content-Type: ").append(file.getContentType()).append("\r\n\r\n");
                outStream.write(split.toString().getBytes());
                InputStream is = null;
                BufferedInputStream bis = null;
                try {
                    is = file.getInputStream();
                    bis = new BufferedInputStream(is);
                    int d;
                    while (-1 != (d = bis.read())) {
                        outStream.write(d);
                    }
                    outStream.write("\r\n".getBytes());
                } finally {
                    if (null != is)
                        is.close();
                    if (null != bis)
                        bis.close();
                }
            }
            byte[] end_data = ("--" + BOUNDARY + "--\r\n").getBytes();// 数据结束标志
            outStream.write(end_data);
            outStream.flush();
            int cah = conn.getResponseCode();
            if (cah != 200)
                throw new RuntimeException("请求url失败");
            InputStream is = conn.getInputStream();
            int ch;
            StringBuilder b = new StringBuilder();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
            String reslut = b.toString();
            Log.w("upload", reslut);
            return reslut;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (outStream != null)
                    outStream.close();
            } catch (IOException e) {

            }
            if (conn != null)
                conn.disconnect();
        }
    }

    public static String request(HttpClient client, String url, List<BasicNameValuePair> data)
            throws IOException {
        HttpUriRequest req;
        if (null != data && data.size() > 0) {
            HttpPost post = new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(data, HTTP.UTF_8));
            req = post;
        } else {
            req = new HttpGet(url);
        }
        return EntityUtils.toString(client.execute(req).getEntity());
    }

    private static Result apiRequest(Method action, List<BasicNameValuePair> data) {
        return apiRequest(action, data, null);
    }

    private static Result apiRequest(Method action, List<BasicNameValuePair> data, DataListener listener) {
        if (!InternetUtil.hasInternet()) return Result.error("请检查网络!");
        try {
            final String result = api(action, data);
            if (null == result) return Result.error();
            JSONObject jsonObject = new JSONObject(result);
            Result r = new Result();
            r.setStatus(jsonObject.getInt(STATUS));
            r.setMessage(jsonObject.getString(MESSAGE));
            if (jsonObject.has(DATA) && null != jsonObject.get(DATA)/* && r.isSuccess()*/) {
                if (null != listener) listener.bind(r, jsonObject);
            }
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    private static String api(Method action, List<BasicNameValuePair> data) throws Exception {
        if (null == data) data = new ArrayList<BasicNameValuePair>();
        data.add(new BasicNameValuePair("version", "1.0"));
        data.add(new BasicNameValuePair("platform", "android"));
        data.add(new BasicNameValuePair("key", "123456"));
        String result = request(getHttpClient(), action.getUrl(), data);
        return result;
    }


    interface DataListener {
        void bind(Result result, JSONObject jsonObject);
    }
}
