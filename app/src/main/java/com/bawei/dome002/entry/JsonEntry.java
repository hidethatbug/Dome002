package com.bawei.dome002.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:程金柱
 * Date:2019/6/29 19:04
 * Description：
 */
@Entity
public class JsonEntry {
   
    private long id;
    private String json;
   @Generated(hash = 837626670)
   public JsonEntry(long id, String json) {
       this.id = id;
       this.json = json;
   }
   @Generated(hash = 1159953021)
   public JsonEntry() {
   }
   public long getId() {
       return this.id;
   }
   public void setId(long id) {
       this.id = id;
   }
   public String getJson() {
       return this.json;
   }
   public void setJson(String json) {
       this.json = json;
   }
}
