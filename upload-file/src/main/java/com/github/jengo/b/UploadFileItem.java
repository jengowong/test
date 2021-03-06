package com.github.jengo.b;

import java.io.Serializable;

/**
 * 一个POJO。用于保存上传文件的相关信息
 */
public class UploadFileItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // The form field name in a form used for uploading a file,
    // such as "upload1" in "<input type="file" name="upload1"/>"
    private String formFieldName;

    // File name to be uploaded, the fileName contains path,
    // such as "E:\\some_file.jpg"
    private String fileName;

    public UploadFileItem(String formFieldName, String fileName) {
        this.formFieldName = formFieldName;
        this.fileName = fileName;
    }

    public String getFormFieldName() {
        return formFieldName;
    }

    public void setFormFieldName(String formFieldName) {
        this.formFieldName = formFieldName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("formFieldName=").append(formFieldName)
                .append(", fileName=").append(fileName);
        return strBuilder.toString();
    }
}
