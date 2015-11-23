package com.github.jengo.b;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class PostRequestEmulator {
    private static final Logger LOG = LoggerFactory.getLogger(PostRequestEmulator.class);

    public static void main(String[] args) throws Exception {

        // 设定服务地址
        String serverUrl = "http://127.0.0.1:8080/test/upload_b";

        // 设定要上传的普通Form Field及其对应的value
        // 类FormFieldKeyValuePair的定义见后面的代码
        ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
        ffkvp.add(new FormFieldKeyValuePair("username", "Patrick"));
        ffkvp.add(new FormFieldKeyValuePair("password", "HELLOPATRICK"));
        ffkvp.add(new FormFieldKeyValuePair("hobby", "Computer programming"));

        // 设定要上传的文件。
        // UploadFileItem见后面的代码
        ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();
        ufi.add(new UploadFileItem("upload1", "/Users/jengowang/Downloads/Exception.log"));
        ufi.add(new UploadFileItem("upload2", "/Users/jengowang/Downloads/logback_manual_chs.pdf"));
        ufi.add(new UploadFileItem("upload3", "/Users/jengowang/Downloads/主流OTA移动端应用竞品分析报告V1.0.pdf"));

        // 类HttpPostEmulator的定义，见后面的代码
        HttpPostEmulator hpe = new HttpPostEmulator();
        String response = hpe.sendHttpPostRequest(serverUrl, ffkvp, ufi);
        LOG.info("Response from server is: {}", response);
    }
}
