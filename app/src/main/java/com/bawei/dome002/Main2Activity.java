package com.bawei.dome002;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dome002.entry.FindShoppingCartEntry;
import com.bawei.dome002.entry.JsonEntry;
import com.bawei.dome002.http.DataCallBack;
import com.bawei.dome002.mvp.ShopContract;
import com.bawei.dome002.mvp.ShopPresenterImpl;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Main2Activity extends AppCompatActivity implements ShopContract.getShopView {
    private static final String TAG = "+++++++++";
    private ShopContract.getShopPresenter getShopPresenter;

    private Unbinder bind;
    @BindView(R.id.recy_buycar)
    RecyclerView recyclerView;
    private ShopCarAdapter shopCarAdapter;
    @BindView(R.id.text_price)
    TextView price;
    private int pic = 0;
    @BindView(R.id.cb_all)
    CheckBox checkBox;

    @OnClick(R.id.cb_all)
    public void setBind(View bind) {
        List<FindShoppingCartEntry.ResultBean> list = shopCarAdapter.getList();
        if (!checkBox.isChecked()) {
            for (int i = 0; i < list.size(); i++) {
                FindShoppingCartEntry.ResultBean resultBean = list.get(i);
                resultBean.setCb_home(false);
                for (int i1 = 0; i1 < resultBean.getShoppingCartList().size(); i1++) {
                    FindShoppingCartEntry.ResultBean.ShoppingCartListBean shoppingCartListBean = resultBean.getShoppingCartList().get(i1);
                    shoppingCartListBean.setCb_item(false);


                }
            }
            pic = 0;
            shopCarAdapter.notifyDataSetChanged();
            price.setText("$" + pic);
        } else {
            pic=0;
            for (int i = 0; i < list.size(); i++) {
                FindShoppingCartEntry.ResultBean resultBean = list.get(i);
                resultBean.setCb_home(true);
                for (int i1 = 0; i1 < resultBean.getShoppingCartList().size(); i1++) {
                    FindShoppingCartEntry.ResultBean.ShoppingCartListBean shoppingCartListBean = resultBean.getShoppingCartList().get(i1);
                    shoppingCartListBean.setCb_item(true);
                    pic += pic + shoppingCartListBean.getPrice() * shoppingCartListBean.getCount();
                }
            }
            shopCarAdapter.notifyDataSetChanged();
            price.setText("$" + pic);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

    }

    private void initView() {
        bind = ButterKnife.bind(this);
        getShopPresenter = new ShopPresenterImpl();
        getShopPresenter.bind(this);

        String sessionId = App.getMy().getString("sessionId", "");
        int userId = App.getMy().getInt("userId", 0);
        getShopPresenter.getShopCar(sessionId, userId);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shopCarAdapter = new ShopCarAdapter(this);
        recyclerView.setAdapter(shopCarAdapter);

//        if (!net){
//            List<JsonEntry> jsonEntries = App.getDaoSession().loadAll(JsonEntry.class);
//            JsonEntry jsonEntry = jsonEntries.get(0);
//            String json = jsonEntry.getJson();
//            FindShoppingCartEntry findShoppingCartEntry = new Gson().fromJson(json, FindShoppingCartEntry.class);
//            shopCarAdapter.setList(findShoppingCartEntry.getResult());
//        }


    }

    public boolean getNet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }

    @Override
    public void getShopListData(Object o) {

    }

    @Override
    public void getShopCarData(Object o) {
        FindShoppingCartEntry findShoppingCartEntry = (FindShoppingCartEntry) o;
        shopCarAdapter.setList(findShoppingCartEntry.getResult());

        initData();
        String s = new Gson().toJson(findShoppingCartEntry);
        JsonEntry jsonEntry = new JsonEntry(1, s);
        App.getDaoSession().insert(jsonEntry);
        List<JsonEntry> jsonEntries = App.getDaoSession().loadAll(JsonEntry.class);
        String json = jsonEntries.get(0).getJson();
        Log.i(TAG, "getShopCarData: "+json);

    }

    private void initData() {
        shopCarAdapter.setDataCallBack(new DataCallBack() {
            @Override
            public void getData(Object data) {
                int cont = 0;
                List<FindShoppingCartEntry.ResultBean> list = shopCarAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    FindShoppingCartEntry.ResultBean resultBean = list.get(i);
                    for (int i1 = 0; i1 < resultBean.getShoppingCartList().size(); i1++) {
                        FindShoppingCartEntry.ResultBean.ShoppingCartListBean shoppingCartListBean = resultBean.getShoppingCartList().get(i1);
                        if (shoppingCartListBean.isCb_item()) {
                            cont += cont + shoppingCartListBean.getCount() * shoppingCartListBean.getPrice();
                        }
                    }
                }
                price.setText("$" + cont);
            }

            @Override
            public void getError(String error) {

            }
        });
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
