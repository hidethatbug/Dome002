package com.bawei.dome002.entry;

import java.util.List;

/**
 * Author:程金柱
 * Date:2019/6/29 9:09
 * Description：
 */

public class FindCommodityByCategoryEntry {

    /**
     * result : [{"commodityId":81,"commodityName":"冬季新款 松紧腰灯笼袖蕾丝裙假两件连衣裙","masterPic":"http://172.17.8.100/images/small/commodity/nz/qz/3/1.jpg","price":418,"saleNum":0},{"commodityId":83,"commodityName":"秋季新款韩版女装淑女风荷叶边短款长袖连衣裙","masterPic":"http://172.17.8.100/images/small/commodity/nz/qz/5/1.jpg","price":168,"saleNum":0},{"commodityId":80,"commodityName":"秋季新款韩版女装连衣裙春秋冬学院风气质休闲时尚显瘦字母印花拼接荷叶边短裙子长袖连衣裙","masterPic":"http://172.17.8.100/images/small/commodity/nz/qz/2/1.jpg","price":178,"saleNum":0},{"commodityId":85,"commodityName":"秋冬女装新款长袖连衣裙女中长款气质打底毛衣裙鱼尾显瘦内搭裙韩版时尚宽松针织裙","masterPic":"http://172.17.8.100/images/small/commodity/nz/qz/7/1.jpg","price":99,"saleNum":0},{"commodityId":82,"commodityName":"三彩冬季新款 高腰修身包臀裙假两件开叉半身裙","masterPic":"http://172.17.8.100/images/small/commodity/nz/qz/4/1.jpg","price":68,"saleNum":0},{"commodityId":79,"commodityName":"冬新款女款连衣裙 V领纯色中长裙时尚百搭不对称连衣裙","masterPic":"http://172.17.8.100/images/small/commodity/nz/qz/1/1.jpg","price":148,"saleNum":0},{"commodityId":84,"commodityName":"秋冬装新款高腰半截裙女时尚灯芯绒短裙韩版学生a字裙子","masterPic":"http://172.17.8.100/images/small/commodity/nz/qz/6/1.jpg","price":129,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 81
         * commodityName : 冬季新款 松紧腰灯笼袖蕾丝裙假两件连衣裙
         * masterPic : http://172.17.8.100/images/small/commodity/nz/qz/3/1.jpg
         * price : 418
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
