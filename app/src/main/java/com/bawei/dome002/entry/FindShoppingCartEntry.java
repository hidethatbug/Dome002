package com.bawei.dome002.entry;

import java.util.List;

/**
 * Author:程金柱
 * Date:2019/6/29 9:12
 * Description：
 */

public class FindShoppingCartEntry {

    /**
     * result : [{"categoryName":"美妆护肤","shoppingCartList":[{"commodityId":5,"commodityName":"双头两用修容笔","count":3,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39},{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":4,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39}]},{"categoryName":"女装","shoppingCartList":[{"commodityId":80,"commodityName":"秋季新款韩版女装连衣裙春秋冬学院风气质休闲时尚显瘦字母印花拼接荷叶边短裙子长袖连衣裙","count":8,"pic":"http://172.17.8.100/images/small/commodity/nz/qz/2/1.jpg","price":178},{"commodityId":90,"commodityName":"女款气质女神斗篷披肩蝙蝠衫毛呢外套","count":8,"pic":"http://172.17.8.100/images/small/commodity/nz/wt/5/1.jpg","price":396},{"commodityId":89,"commodityName":"森马毛呢外套女士简约气质西装领短款呢子大衣韩版","count":8,"pic":"http://172.17.8.100/images/small/commodity/nz/wt/4/1.jpg","price":99}]},{"categoryName":"手机数码","shoppingCartList":[{"commodityId":100,"commodityName":"【壳膜线套餐】 苹果 iPhone XS 256G 全网通手机","count":8,"pic":"http://172.17.8.100/images/small/commodity/sjsm/sj/1/1.jpg","price":10069}]},{"categoryName":"女鞋","shoppingCartList":[{"commodityId":25,"commodityName":"秋冬季真皮兔毛女鞋韩版休闲平底毛毛鞋软底百搭浅口水钻加绒棉鞋毛毛鞋潮鞋","count":8,"pic":"http://172.17.8.100/images/small/commodity/nx/ddx/1/1.jpg","price":158}]}]
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
         * categoryName : 美妆护肤
         * shoppingCartList : [{"commodityId":5,"commodityName":"双头两用修容笔","count":3,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39},{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":4,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39}]
         */
        private boolean cb_home;
        private String categoryName;
        private List<ShoppingCartListBean> shoppingCartList;

        public boolean isCb_home() {
            return cb_home;
        }

        public void setCb_home(boolean cb_home) {
            this.cb_home = cb_home;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<ShoppingCartListBean> getShoppingCartList() {
            return shoppingCartList;
        }

        public void setShoppingCartList(List<ShoppingCartListBean> shoppingCartList) {
            this.shoppingCartList = shoppingCartList;
        }

        public static class ShoppingCartListBean {
            /**
             * commodityId : 5
             * commodityName : 双头两用修容笔
             * count : 3
             * pic : http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg
             * price : 39
             */

            private int commodityId;
            private String commodityName;
            private int count;
            private String pic;
            private int price;
            private boolean cb_item;


            public ShoppingCartListBean(boolean cb_item) {
                this.cb_item = cb_item;
            }

            public boolean isCb_item() {
                return cb_item;
            }

            public void setCb_item(boolean cb_item) {
                this.cb_item = cb_item;
            }

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

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }
}
