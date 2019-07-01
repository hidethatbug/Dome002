package com.bawei.dome002.entry;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:程金柱
 * Date:2019/6/29 11:02
 * Description：
 */
@Entity
public class ListEntry {

    /**
     * commodityId : 5
     * count : 3
     */

    private int commodityId;
    private int count;

    @Generated(hash = 823072596)
    public ListEntry(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    @Generated(hash = 50726409)
    public ListEntry() {
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
