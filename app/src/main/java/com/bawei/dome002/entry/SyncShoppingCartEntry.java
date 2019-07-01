package com.bawei.dome002.entry;

/**
 * Author:程金柱
 * Date:2019/6/29 9:11
 * Description：
 */

public class SyncShoppingCartEntry {

    /**
     * message : 同步成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
