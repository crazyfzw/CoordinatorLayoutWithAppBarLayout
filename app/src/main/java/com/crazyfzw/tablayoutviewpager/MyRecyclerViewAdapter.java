package com.crazyfzw.tablayoutviewpager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Crazyfzw on 2016/4/10.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    //数据集
    public List<String> mdatas;

    //1.在构造函数中取得数据集
    public MyRecyclerViewAdapter(List<String> datas){
        super();
        this.mdatas = datas;
    }

   //定义一个接口
    public static interface  OnItemClickListener{
        void onItemClick(View view, int positon);
    }


    private OnItemClickListener mOnItemClickListener;
    //添加接口和设置Adapter的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    ///3.创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //创建一个ViewHolder
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    //4.将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.textView.setText(mdatas.get(position));
        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    //5.返回数据的长度
    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    //2.自定义的ViewHolder，取得每个Item的的所有界面元素
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
