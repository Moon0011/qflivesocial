package com.qingfeng.livesocial.live;

import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.util.SxbLog;
import com.tencent.ilivesdk.core.ILiveRoomManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.xutils.common.util.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 网络请求类
 */
public class UserServerHelper {
    private static final String TAG = UserServerHelper.class.getSimpleName();
    private static UserServerHelper instance = null;
    public static final String GET_LINK_SIG = "http://live.520cai.cn/index.php?svc=live&cmd=linksig";
    private boolean bDebug = false;

    public static UserServerHelper getInstance() {
        if (instance == null) {
            instance = new UserServerHelper();
        }
        return instance;
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build();


    public String post(String url, String json) throws IOException {
        if (bDebug) {
            SxbLog.d(TAG, "postReq->url:" + url);
            SxbLog.d(TAG, "postReq->data:" + json);
        }
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String rsp = response.body().string();
            if (bDebug) {
                SxbLog.d(TAG, "postRsp->rsp: " + rsp);
            }
            return rsp;
        } else {
            return "";
        }
    }

    public String getGetLinkSig(String id, String roomid) {
        try {
            JSONObject jasonPacket = new JSONObject();
            jasonPacket.put("token", QFApplication.getInstance().getLoginUser().getToken());
            jasonPacket.put("id", id);
            jasonPacket.put("current_roomnum", ILiveRoomManager.getInstance().getRoomId());
            jasonPacket.put("roomnum", Integer.valueOf(roomid));
            String json = jasonPacket.toString();
            LogUtil.e("getGetLinkSig->req:" + json);
            String response = post(GET_LINK_SIG, json);
            LogUtil.e("getGetLinkSig->rsp:" + response);

            JSONTokener jsonParser = new JSONTokener(response);
            JSONObject reg_response = (JSONObject) jsonParser.nextValue();
            int ret = reg_response.getInt("errorCode");
            if (ret == 0) {
                JSONObject data = reg_response.getJSONObject("data");
                String sign = data.getString("linksig");
                return sign;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
