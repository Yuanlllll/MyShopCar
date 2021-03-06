package com.bawei.myshopcar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.myshopcar.Apis;
import com.bawei.myshopcar.Bean.ShopTypeBean;
import com.bawei.myshopcar.Bean.ShopTypeProductBean;
import com.bawei.myshopcar.Constants;
import com.bawei.myshopcar.R;
import com.bawei.myshopcar.adapter.ShopTypeAdapter;
import com.bawei.myshopcar.adapter.ShopTypeProductAdapter;
import com.bawei.myshopcar.presenter.IPresenterImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * 二级联动
 */
public class ShopTypeActivity extends AppCompatActivity implements IView {
    private IPresenterImpl mIPresenterImpl;
    private ShopTypeAdapter mShopTypeAdapter;
    private ShopTypeProductAdapter mShopTypeProductAdapter;
    private RecyclerView mRecyclerViewShopType, mRecyclerViewShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_type);

        mIPresenterImpl = new IPresenterImpl(this);
        initShopTypeView();
        initShopTypeProductView();
        getTypeData();
    }

    /**
     * 初始化左侧recyclerView,加载左侧adapter
     */
    private void initShopTypeView() {
        mRecyclerViewShop = findViewById(R.id.recyclerview_shop_type);
        LinearLayoutManager linearLayoutManagerLeft = new LinearLayoutManager(this);
        linearLayoutManagerLeft.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewShop.setLayoutManager(linearLayoutManagerLeft);
        mRecyclerViewShop.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mShopTypeAdapter = new ShopTypeAdapter(this);
        mRecyclerViewShop.setAdapter(mShopTypeAdapter);

        //添加接口回调，用来接收左侧recyclerView的cid
        mShopTypeAdapter.setOnClickListener(new ShopTypeAdapter.OnClickListener() {
            @Override
            public void onClick(int position, String cid) {
                //拿到cid之后，通过接口获得对应的数据，展示在右侧列表中
                getShopData(cid);
            }
        });
    }

    /**
     * 初始化右侧recyclerView,加载右侧adapter
     */
    private void initShopTypeProductView() {
        mRecyclerViewShopType = findViewById(R.id.recyclerview_shop);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewShopType.setLayoutManager(linearLayoutManager);
        mRecyclerViewShopType.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mShopTypeProductAdapter = new ShopTypeProductAdapter(this);
        mRecyclerViewShopType.setAdapter(mShopTypeProductAdapter);
    }

    /**
     * 获取左侧列表数据
     */
    private void getTypeData() {
        Map<String, String> map = new HashMap<>();
        mIPresenterImpl.startRequest(Apis.URL_PRODUCT_GET_CATAGORY, map, ShopTypeBean.class);
    }

    /**
     * 获取右侧列表数据
     * @param cid
     */
    private void getShopData(String cid) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.MAP_KEY_PRODUCT_GET_CATAGORY_CID, cid);
        mIPresenterImpl.startRequest(Apis.URL_PRODUCT_GET_PRODUCT_CATAGORY, map, ShopTypeProductBean.class);
    }

    @Override
    public void showResponseData(Object data) {
        if (data instanceof ShopTypeBean) {
            //获取数据后，展示左侧列表
            ShopTypeBean shopTypeBean = (ShopTypeBean) data;
            mShopTypeAdapter.setData(shopTypeBean.getData());
        } else if (data instanceof ShopTypeProductBean) {
            //获取数据后，展示右侧列表
            ShopTypeProductBean shopTypeProductBean = (ShopTypeProductBean) data;
            mShopTypeProductAdapter.setData(shopTypeProductBean.getData());

            //将右侧列表滚到顶部(这行不加也无所谓)
            mRecyclerViewShopType.scrollToPosition(0);
        }
    }

    @Override
    public void showResponseFail(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIPresenterImpl.onDetach();
    }
}
