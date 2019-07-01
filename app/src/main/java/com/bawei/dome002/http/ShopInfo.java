package com.bawei.dome002.http;

import com.bawei.dome002.entry.FindCommodityByCategoryEntry;
import com.bawei.dome002.entry.FindFirstCategoryEntry;
import com.bawei.dome002.entry.FindSecondCategoryEntry;
import com.bawei.dome002.entry.FindShoppingCartEntry;
import com.bawei.dome002.entry.LoginEntry;
import com.bawei.dome002.entry.RegisterEntry;
import com.bawei.dome002.entry.SyncShoppingCartEntry;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Author:程金柱
 * Date:2019/6/29 9:05
 * Description：
 */

public interface ShopInfo {
    /**
     * 注册
     *
     * @param phone
     * @param pwd
     * @return
     */
    @POST("small/user/v1/register/")
    @FormUrlEncoded
    Observable<RegisterEntry> getRegister(
            @Field("phone") String phone,
            @Field("pwd") String pwd
    );

    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @return
     */
    @POST("small/user/v1/login/")
    @FormUrlEncoded
    Observable<LoginEntry> getLogin(
            @Field("phone") String phone,
            @Field("pwd") String pwd
    );

    /**
     * 查询一级商品名录
     *
     * @return
     */
    @GET("small/commodity/v1/findFirstCategory/")
    Observable<FindFirstCategoryEntry> getFindFirstCategory();

    /**
     * 查询二级商品名录
     *
     * @param firstCategoryId
     * @return
     */
    @GET("small/commodity/v1/findSecondCategory/")
    Observable<FindSecondCategoryEntry> getFindSecondCategory(
            @Query("firstCategoryId") String firstCategoryId
    );

    /**
     * 根据二级名录查询商品类表
     *
     * @param categoryId
     * @param page
     * @param count
     * @return
     */
    @GET("small/commodity/v1/findCommodityByCategory/")
    Observable<FindCommodityByCategoryEntry> getFindCommodityByCategory(
            @Query("categoryId") String categoryId,
            @Query("page") int page,
            @Query("count") int count
    );

    /**
     * 添加购物车数据
     *
     * @param sessionId
     * @param userId
     * @param data
     * @return
     */
    @PUT("small/order/verify/v1/syncShoppingCart/")
    @FormUrlEncoded
    Observable<SyncShoppingCartEntry> getSyncShoppingCart(
            @Header("sessionId") String sessionId,
            @Header("userId") String userId,
            @Field("data") String data
    );

    /**
     * 查询购物车
     *
     * @param sessionId
     * @param userId
     * @return
     */
    @GET("small/order/verify/v1/findShoppingCart/")
    Observable<FindShoppingCartEntry> getFindShoppingCart(
            @Header("sessionId") String sessionId,
            @Header("userId") String userId
    );

}
