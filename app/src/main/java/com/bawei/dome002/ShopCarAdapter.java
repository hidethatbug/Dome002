package com.bawei.dome002;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.dome002.entry.FindShoppingCartEntry;
import com.bawei.dome002.http.DataCallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.constraint.Constraints.TAG;

/**
 * Author:程金柱
 * Date:2019/6/29 16:50
 * Description：
 */

public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.ShopCarHandler> {
    private Context context;
    private List<FindShoppingCartEntry.ResultBean> list;

    public List<FindShoppingCartEntry.ResultBean> getList() {
        return list;
    }

    public ShopCarAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<FindShoppingCartEntry.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopCarHandler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_recy, viewGroup, false);
        return new ShopCarHandler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopCarHandler shopCarHandler, int i) {
        final FindShoppingCartEntry.ResultBean resultBean = list.get(i);
        shopCarHandler.checkBox.setChecked(resultBean.isCb_home());
        Log.i(TAG, "onBindViewHolder: " + resultBean.isCb_home());
        shopCarHandler.textView.setText(resultBean.getCategoryName());
        shopCarHandler.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ShopCarItemAdapter shopCarItemAdapter = new ShopCarItemAdapter(context, list.get(i).getShoppingCartList());
        shopCarItemAdapter.setDataCallBack(new DataCallBack() {
            @Override
            public void getData(Object data) {
                dataCallBack.getData("");

            }

            @Override
            public void getError(String error) {

            }
        });
        shopCarHandler.recyclerView.setAdapter(shopCarItemAdapter);
        shopCarHandler.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = shopCarHandler.checkBox.isChecked();
                resultBean.setCb_home(checked);
                Log.i(TAG, "onClick: +++++++++++++++++++++++++++" + checked);
                if (!checked) {
                    for (int i1 = 0; i1 < resultBean.getShoppingCartList().size(); i1++) {
                        FindShoppingCartEntry.ResultBean.ShoppingCartListBean shoppingCartListBean = resultBean.getShoppingCartList().get(i1);
                        shoppingCartListBean.setCb_item(false);
                    }

                } else {
                    for (int i1 = 0; i1 < resultBean.getShoppingCartList().size(); i1++) {
                        FindShoppingCartEntry.ResultBean.ShoppingCartListBean shoppingCartListBean = resultBean.getShoppingCartList().get(i1);
                        shoppingCartListBean.setCb_item(true);
                    }

                }
                dataCallBack.getData("");
                notifyDataSetChanged();
            }
        });
    }

    private DataCallBack dataCallBack;

    public void setDataCallBack(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }

        return 0;
    }

    public class ShopCarHandler extends RecyclerView.ViewHolder {
        @BindView(R.id.text_shopcar_item)
        TextView textView;
        @BindView(R.id.cb_shopcar_item)
        CheckBox checkBox;
        @BindView(R.id.recy_shopcar_item)
        RecyclerView recyclerView;

        public ShopCarHandler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
