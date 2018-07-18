package com.yd.hp.huangxiaoer.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yd.hp.huangxiaoer.R;
import com.yd.hp.huangxiaoer.model.bean.YuYueBean;
import com.yd.hp.huangxiaoer.view.interfaces.ItemClicked;

import java.util.List;

public class YuYueAdapter extends RecyclerView.Adapter {

    private List<YuYueBean.DataBean> list;
    private Context context;
    private ItemClicked itemClicked;
    private boolean flag;

    public void setItemClicked(ItemClicked itemClicked) {
        this.itemClicked = itemClicked;
    }

    public YuYueAdapter(List<YuYueBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.yuyue_item_layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ((ViewHolder) holder).yuye_table_tv.setText(list.get(position).getName());
        ((ViewHolder) holder).yuye_img.setImageURI(Uri.parse(list.get(position).getPic_url()));
        ((ViewHolder) holder).xinxi_tv.setText(list.get(position).getShipping_fee_tip()+" | "+list.get(position).getMin_price_tip()+" | "+list.get(position).getAverage_price_tip());
        List<YuYueBean.DataBean.Discounts2Bean> discounts2 = list.get(position).getDiscounts2();
        ((ViewHolder) holder).jian_tv.setText(discounts2.get(0).getInfo());
        ((ViewHolder) holder).zhe_tv.setText(discounts2.get(1).getInfo());
        ((ViewHolder) holder).relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClicked.onItemClicked(view,position);
            }
        });
        ((ViewHolder) holder).xin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag){
                    ((ViewHolder) holder).xin.setImageResource(R.mipmap.xin_true);
                    flag = true;
                }else {
                    ((ViewHolder) holder).xin.setImageResource(R.mipmap.xin_false);
                    flag = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView yuye_img;
        ImageView xin;
        RelativeLayout relative;
        TextView jian_tv,zhe_tv,yuye_table_tv,xinxi_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            yuye_img = itemView.findViewById(R.id.yuyue_img);
            relative = itemView.findViewById(R.id.relative);
            yuye_table_tv = itemView.findViewById(R.id.yuyue_table_tv);
            jian_tv = itemView.findViewById(R.id.jian_tv);
            zhe_tv = itemView.findViewById(R.id.zhe_tv);
            xinxi_tv = itemView.findViewById(R.id.xinxi_tv);
            xin = itemView.findViewById(R.id.guanzhu_img);
        }
    }

}
