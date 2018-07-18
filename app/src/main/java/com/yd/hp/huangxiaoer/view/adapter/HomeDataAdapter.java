package com.yd.hp.huangxiaoer.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yd.hp.huangxiaoer.R;
import com.yd.hp.huangxiaoer.model.bean.HomeBean;

import java.util.List;

public class HomeDataAdapter extends RecyclerView.Adapter {

    private List<HomeBean.DataBean.TuijianBean.ListBeanX> list;
    private Context context;

    public HomeDataAdapter(List<HomeBean.DataBean.TuijianBean.ListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.homedata_item_layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ((ViewHolder) holder).homedata_img.setImageURI(Uri.parse(list.get(position).getImages().split("\\|")[0]));
        ((ViewHolder) holder).homedata_price.setText("￥ "+(int) list.get(position).getPrice());
        ((ViewHolder) holder).homedata_name.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView homedata_img;
        TextView homedata_name,homedata_price;
        public ViewHolder(View itemView) {
            super(itemView);
            homedata_img = itemView.findViewById(R.id.homedata_img);
            homedata_name = itemView.findViewById(R.id.homedata_name);
            homedata_price = itemView.findViewById(R.id.homedata_price);
        }
    }

}
