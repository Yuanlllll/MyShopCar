package com.bawei.myshopcar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bawei.myshopcar.R;
import com.bawei.myshopcar.tree.TreeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.gotoShop).setOnClickListener(this);
        findViewById(R.id.gotoShopType).setOnClickListener(this);
        findViewById(R.id.gotoTree).setOnClickListener(this);
    }

    public void gotoShop() {
        startActivity(new Intent(this, ShopCarActivity.class));
    }

    public void gotoShopType() {
        startActivity(new Intent(this, ShopTypeActivity.class));
    }

    public void gotoTree() {
        startActivity(new Intent(this, TreeActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gotoShop:
                gotoShop();
                break;
            case R.id.gotoShopType:
                gotoShopType();
                break;
            case R.id.gotoTree:
                gotoTree();
                break;
            default:
                break;
        }
    }
}
