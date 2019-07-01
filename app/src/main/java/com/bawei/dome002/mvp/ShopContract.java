package com.bawei.dome002.mvp;

import com.bawei.dome002.http.DataCallBack;

/**
 * Author:程金柱
 * Date:2019/6/29 8:51
 * Description：
 */

public interface ShopContract {
    interface getShopModel {
        //获取数据类表显示
        void getShopList(String phone, String pwd, DataCallBack dataCallBack);

        //获取购物车数据
        void getShopCar(String sid, int uid, DataCallBack dataCallBack);

        //添加购物车
        void AddCar(String sid, int uid, String data, DataCallBack dataCallBack);
    }

    interface getShopView {
        void getShopListData(Object o);

        void getShopCarData(Object o);

        void getAddCarData(Object o);
    }

    interface getShopPresenter {
        //获取数据类表显示
        void getShopList(String phone, String pwd);

        //获取购物车数据
        void getShopCar(String sid, int uid);

        //添加购物车
        void AddCar(String sid, int uid, String data);
        //绑定
        void bind(getShopView getShopView);
        //销毁解绑
        void unbind();
    }
}
