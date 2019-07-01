package com.bawei.dome002.entry;

/**
 * Author:程金柱
 * Date:2019/6/29 10:36
 * Description：
 */

public class ShopList {

    /**
     * commodityId : 5
     * count : 3
     */

    private int commodityId;
    private int count;

    public ShopList(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public ShopList() {
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
