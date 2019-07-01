package com.bawei.dome002.mvp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.bawei.dome002.App;
import com.bawei.dome002.entry.FindCommodityByCategoryEntry;
import com.bawei.dome002.entry.FindFirstCategoryEntry;
import com.bawei.dome002.entry.FindSecondCategoryEntry;
import com.bawei.dome002.entry.FindShoppingCartEntry;
import com.bawei.dome002.entry.LoginEntry;
import com.bawei.dome002.entry.RegisterEntry;
import com.bawei.dome002.entry.SyncShoppingCartEntry;
import com.bawei.dome002.http.DataCallBack;
import com.bawei.dome002.http.HttpUtile;
import com.bawei.dome002.http.ShopInfo;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:程金柱
 * Date:2019/6/29 9:04
 * Description：
 */

public class ShopModelImpl implements ShopContract.getShopModel {

    private LoginEntry login;
    private FindFirstCategoryEntry firstCategoryEntry;
    private FindSecondCategoryEntry secondCategoryEntry;

    @SuppressLint("CheckResult")
    @Override
    public void getShopList(final String phone, final String pwd, final DataCallBack dataCallBack) {
        final ShopInfo rxjava = HttpUtile.httpUtile().getRxjava(ShopInfo.class);
        rxjava.getRegister(phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<RegisterEntry>() {
                    @Override
                    public void accept(RegisterEntry registerEntry) throws Exception {

                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<RegisterEntry, ObservableSource<LoginEntry>>() {
                    @Override
                    public ObservableSource<LoginEntry> apply(RegisterEntry registerEntry) throws Exception {
                        return rxjava.getLogin(phone, pwd);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<LoginEntry>() {
                    @Override
                    public void accept(LoginEntry loginEntry) throws Exception {
                        login = loginEntry;
                        SharedPreferences.Editor edit = App.getMy().edit();
                        edit.putString("sessionId", login.getResult().getSessionId());
                        edit.putInt("userId", login.getResult().getUserId());
                        edit.commit();
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<LoginEntry, ObservableSource<FindFirstCategoryEntry>>() {
                    @Override
                    public ObservableSource<FindFirstCategoryEntry> apply(LoginEntry loginEntry) throws Exception {
                        return rxjava.getFindFirstCategory();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<FindFirstCategoryEntry>() {
                    @Override
                    public void accept(FindFirstCategoryEntry findFirstCategoryEntry) throws Exception {
                        firstCategoryEntry = findFirstCategoryEntry;
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<FindFirstCategoryEntry, ObservableSource<FindSecondCategoryEntry>>() {
                    @Override
                    public ObservableSource<FindSecondCategoryEntry> apply(FindFirstCategoryEntry findFirstCategoryEntry) throws Exception {
                        return rxjava.getFindSecondCategory(firstCategoryEntry.getResult().get(2).getId());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<FindSecondCategoryEntry>() {
                    @Override
                    public void accept(FindSecondCategoryEntry findSecondCategoryEntry) throws Exception {
                        secondCategoryEntry = findSecondCategoryEntry;
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<FindSecondCategoryEntry, ObservableSource<FindCommodityByCategoryEntry>>() {
                    @Override
                    public ObservableSource<FindCommodityByCategoryEntry> apply(FindSecondCategoryEntry findSecondCategoryEntry) throws Exception {
                        return rxjava.getFindCommodityByCategory(
                                secondCategoryEntry.getResult().get(1).getId(),
                                1, 10);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FindCommodityByCategoryEntry>() {
                    @Override
                    public void accept(FindCommodityByCategoryEntry findCommodityByCategoryEntry) throws Exception {
                        dataCallBack.getData(findCommodityByCategoryEntry);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void getShopCar(String sid, int uid, final DataCallBack dataCallBack) {
        ShopInfo rxjava = HttpUtile.httpUtile().getRxjava(ShopInfo.class);
        rxjava.getFindShoppingCart(sid, String.valueOf(uid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FindShoppingCartEntry>() {
                    @Override
                    public void accept(FindShoppingCartEntry findShoppingCartEntry) throws Exception {
                        dataCallBack.getData(findShoppingCartEntry);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void AddCar(String sid, int uid, String data, final DataCallBack dataCallBack) {
        ShopInfo rxjava = HttpUtile.httpUtile().getRxjava(ShopInfo.class);
        /**
         * 同步购物车
         */
        rxjava.getSyncShoppingCart(sid, String.valueOf(uid), data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SyncShoppingCartEntry>() {
                    @Override
                    public void accept(SyncShoppingCartEntry syncShoppingCartEntry) throws Exception {
                        dataCallBack.getData(syncShoppingCartEntry);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }
}
