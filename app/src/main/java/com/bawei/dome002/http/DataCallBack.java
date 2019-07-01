package com.bawei.dome002.http;

/**
 * Author:程金柱
 * Date:2019/6/29 8:58
 * Description：
 */

public interface DataCallBack {
    void getData(Object data);

    void getError(String error);
}
