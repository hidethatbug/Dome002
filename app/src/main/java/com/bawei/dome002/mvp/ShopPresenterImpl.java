package com.bawei.dome002.mvp;

import com.bawei.dome002.http.DataCallBack;

/**
 * Author:程金柱
 * Date:2019/6/29 9:40
 * Description：
 */

public class ShopPresenterImpl implements ShopContract.getShopPresenter {
    private ShopContract.getShopView getShopView;
    private ShopContract.getShopModel getShopModel;

    @Override
    public void getShopList(String phone, String pwd) {
        getShopModel.getShopList(phone, pwd, new DataCallBack() {
            @Override
            public void getData(Object data) {
                getShopView.getShopListData(data);
            }

            @Override
            public void getError(String error) {

            }
        });
    }

    @Override
    public void getShopCar(String sid, int uid) {
        getShopModel.getShopCar(sid, uid, new DataCallBack() {
            @Override
            public void getData(Object data) {
                getShopView.getShopCarData(data);
            }

            @Override
            public void getError(String error) {

            }
        });
    }

    @Override
    public void AddCar(String sid, int uid, String data) {
        getShopModel.AddCar(sid, uid, data, new DataCallBack() {
            @Override
            public void getData(Object data) {
                getShopView.getAddCarData(data);
            }

            @Override
            public void getError(String error) {

            }
        });
    }

    @Override
    public void bind(ShopContract.getShopView getShopView) {
        this.getShopView = getShopView;
        getShopModel = new ShopModelImpl();
    }

    @Override
    public void unbind() {
        if (getShopModel != null) {
            getShopModel = null;
        }
        if (getShopView != null) {
            getShopView = null;
        }
        System.gc();
    }
}
