package com.github.jengo.a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jengowang on 15/11/20.
 */
public class PostRequestEmulator {
    private static final Logger LOG = LoggerFactory.getLogger(PostRequestEmulator.class);

    public static void main(String[] args) throws Exception {
        // 服务地址
        URL url = new URL("http://127.0.0.1:8080/test/upload");
        // 设定连接的相关参数
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

        // 向服务端发送key = value对
        out.write("username=kevin&password=pass");
        out.flush();
        out.close();

        // 获取服务端的反馈
        String strLine = "";
        String strResponse = "";
        InputStream in = connection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while ((strLine = reader.readLine()) != null) {
            strResponse += strLine + "\n";
        }

        LOG.info(strResponse);
    }

}
