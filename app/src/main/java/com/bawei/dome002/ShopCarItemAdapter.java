package com.bawei.dome002;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.dome002.entry.FindShoppingCartEntry;
import com.bawei.dome002.http.DataCallBack;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:程金柱
 * Date:2019/6/29 16:50
 * Description：
 */

public class ShopCarItemAdapter extends RecyclerView.Adapter<ShopCarItemAdapter.ShopCarHandler> {
    private Context context;
    private List<FindShoppingCartEntry.ResultBean.ShoppingCartListBean> list;

    public ShopCarItemAdapter(Context context, List<FindShoppingCartEntry.ResultBean.ShoppingCartListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShopCarHandler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_recy_item, viewGroup, false);
        return new ShopCarHandler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopCarHandler shopCarHandler, int i) {
        final FindShoppingCartEntry.ResultBean.ShoppingCartListBean shopping = list.get(i);

        shopCarHandler.checkBox.setChecked(shopping.isCb_item());
        shopCarHandler.name.setText(shopping.getCommodityName());
        shopCarHandler.name.setText("$:" + shopping.getPrice());
        Glide.with(context).load(shopping.getPic()).into(shopCarHandler.imageView);
        shopCarHandler.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = shopCarHandler.checkBox.isChecked();
                shopping.setCb_item(checked);
                dataCallBack.getData("");
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
        @BindView(R.id.text_price_item)
        TextView price;
        @BindView(R.id.text_name_item)
        TextView name;
        @BindView(R.id.cb_item)
        CheckBox checkBox;
        @BindView(R.id.img_item)
        ImageView imageView;

        public ShopCarHandler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
