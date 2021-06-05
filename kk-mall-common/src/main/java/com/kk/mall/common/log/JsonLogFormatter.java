package com.kk.mall.common.log;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Slf4j
public class JsonLogFormatter {
    public static String[] format(String headMsg, String msg) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toJSONString(4);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toJSONString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        message = headMsg + System.getProperty("line.separator") + message;
        return message.split(System.getProperty("line.separator"));

    }
}
