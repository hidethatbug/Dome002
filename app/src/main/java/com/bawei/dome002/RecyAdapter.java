package com.bawei.dome002;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.dome002.entry.FindCommodityByCategoryEntry;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:程金柱
 * Date:2019/6/29 9:53
 * Description：
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.RecyHandler> {
    private Context context;

    public RecyAdapter(Context context) {
        this.context = context;
    }

    private List<FindCommodityByCategoryEntry.ResultBean> list;

    public void setList(List<FindCommodityByCategoryEntry.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyHandler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_item, viewGroup, false);
        return new RecyHandler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyHandler recyHandler, int i) {
        final FindCommodityByCategoryEntry.ResultBean resultBean = list.get(i);
        recyHandler.textView.setText(resultBean.getCommodityName());
        Glide.with(context).load(resultBean.getMasterPic()).into(recyHandler.imageView);
        recyHandler.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getId.getid(resultBean.getCommodityId());
            }
        });

    }


    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    public class RecyHandler extends RecyclerView.ViewHolder {
        @BindView(R.id.text_recy_item)
        TextView textView;
        @BindView(R.id.img_recy_item)
        ImageView imageView;
        @BindView(R.id.card_add_shop)
        CardView cardView;

        public RecyHandler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private getId getId;

    public void setGetId(RecyAdapter.getId getId) {
        this.getId = getId;
    }

    public interface getId {
        void getid(int id);
    }
}
