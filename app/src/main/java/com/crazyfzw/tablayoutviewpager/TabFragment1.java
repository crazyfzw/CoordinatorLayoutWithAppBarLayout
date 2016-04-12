package com.crazyfzw.tablayoutviewpager;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;



public class TabFragment1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String ARG_PARAM1 = "param1";
    private RecyclerView mrecyclerView;
    private MyRecyclerViewAdapter adapter;
    private List<String> datas;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View myView;

    public static TabFragment1 newInstance(String param1) {
        TabFragment1 fragment = new TabFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    public TabFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_tab1, container, false);
//        textView = (TextView) myview.findViewById(R.id.textView1);
//        textView.setText(mParam1);
        return myView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mrecyclerView = (RecyclerView) myView.findViewById(R.id.id_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) myView.findViewById(R.id.id_swiperefreshlayout);

        //显示RecyclerView
        initVerticalRecyclerView();

        //设置SwipeRefreshLayout实现下拉刷新效果
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    private void initVerticalRecyclerView() {

        //2.创建一个垂直的线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        //找到RecyclerView，并设置布局管理器
        mrecyclerView.setLayoutManager(layoutManager);
        mrecyclerView.setHasFixedSize(true);

        //3.取得数据集
        datas = new ArrayList<>();
        for (int i='A'; i<='Z'; i++){
            datas.add((char) i + "");
        }
        //4.创建adapter
        adapter = new MyRecyclerViewAdapter(datas);
        //将RecyclerView组件绑定adapter
        mrecyclerView.setAdapter(adapter);

        //5.在Adapter中添加好事件后，变可以在这里注册事件实现监听了
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positon) {
                Toast.makeText(getActivity().getApplicationContext(), "item" + positon, Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                for(int i=0; i<11; i++){
                    int temp = (int) (Math.random() * 10);
                    adapter.mdatas.add(i,"new"+temp);
                }

                adapter.notifyDataSetChanged();
            }
        },1000);
    }
}
