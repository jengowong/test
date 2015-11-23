package com.github.jengo.b;

import java.io.Serializable;

/**
 * 一个POJO。用于处理普通表单域形如key=value对的数据
 */
public class FormFieldKeyValuePair implements Serializable {

    private static final long serialVersionUID = 1L;

    // The form field used for receiving user's input,
    // such as "username" in "<input type="text" name="username"/>"
    private String key;

    // The value entered by user in the corresponding form field,
    // such as "Patrick" the above mentioned form field "username"
    private String value;

    public FormFieldKeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("key=").append(key)
                .append("value=").append(value);
        return strBuilder.toString();
    }
}
