package com.bawei.dome002;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.dome002.entry.FindCommodityByCategoryEntry;
import com.bawei.dome002.entry.ListEntry;
import com.bawei.dome002.entry.ShopList;
import com.bawei.dome002.gen.DaoSession;
import com.bawei.dome002.mvp.ShopContract;
import com.bawei.dome002.mvp.ShopPresenterImpl;
import com.google.gson.Gson;


import java.util.ArrayList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements ShopContract.getShopView {

    private static final String TAG = "MainActivity+++++++++";
    private ShopContract.getShopPresenter getShopPresenter;
    private Unbinder bind;
    @BindView(R.id.recy)
    RecyclerView recyclerView;
    private RecyAdapter recyAdapter;

    @BindView(R.id.jump)
    ImageView imageView;

    private ShopList shopList;
    private List<ShopList> list;


    @OnClick(R.id.jump)
    public void setc(View view){


        List<ListEntry> listEntries = App.getDaoSession().loadAll(ListEntry.class);
        String s1 = new Gson().toJson(listEntries);
        Log.i(TAG, "setc: " + s1);
        String sessionId = App.getMy().getString("sessionId", "");
        int userId = App.getMy().getInt("userId", 0);
        getShopPresenter.AddCar(sessionId, userId, s1);
        App.getDaoSession().deleteAll(ListEntry.class);

        startActivity(new Intent(MainActivity.this, Main2Activity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        getShopPresenter = new ShopPresenterImpl();
        getShopPresenter.bind(this);
        bind = ButterKnife.bind(this);
        getShopPresenter.getShopList("17500766820", "123");
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        recyAdapter = new RecyAdapter(this);
        recyclerView.setAdapter(recyAdapter);
        list = new ArrayList<>();


    }

    @Override
    public void getShopListData(Object o) {
        FindCommodityByCategoryEntry findCommodityByCategoryEntry = (FindCommodityByCategoryEntry) o;
        Log.i(TAG, "getShopListData: " + findCommodityByCategoryEntry.getMessage());
        recyAdapter.setList(findCommodityByCategoryEntry.getResult());
        iniData();
    }

    private void iniData() {

        recyAdapter.setGetId(new RecyAdapter.getId() {
            @Override
            public void getid(int id) {

                //   list.add(new ShopList(8,7));

                List<ListEntry> listEntries = App.getDaoSession().loadAll(ListEntry.class);
                if (listEntries.isEmpty()) {
                    ListEntry listEntry1 = new ListEntry(id, 1);
                    long insert = App.getDaoSession().insert(listEntry1);
                } else {
                    int count = 0;
                    for (int i = 0; i < listEntries.size(); i++) {
                        ListEntry listEntry = listEntries.get(i);
                        if (listEntry.getCommodityId() == id) {
                            count = listEntry.getCount();
                            ListEntry listEntry2 = new ListEntry(id, count);
                            count++;

                            ListEntry listEntry1 = new ListEntry(id, count);
                            App.getDaoSession().insertOrReplace(listEntry1);
                        }
                    }
                    if (count == 0) {
                        ListEntry listEntry1 = new ListEntry(id, 1);
                        long insert = App.getDaoSession().insert(listEntry1);
                    }
                }
//                shopList = new ShopList(id, 1);
//                ListEntry listEntry = new ListEntry(id, 1);
//                list.add(shopList);
//                DaoSession daoSession = App.getDaoSession();
//                long l = daoSession.insertOrReplace(listEntry);
//                ShopList shopList = new ShopList();
//                shopList.setCommodityId(id);
//                shopList.setCount(1);
//                list.add(shopList);

            }
        });
    }

    @Override
    public void getShopCarData(Object o) {

    }

    @Override
    public void getAddCarData(Object o) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getShopPresenter.unbind();
        bind.unbind();
    }
}
