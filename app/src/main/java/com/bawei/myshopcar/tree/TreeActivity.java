package com.bawei.myshopcar.tree;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bawei.myshopcar.R;

public class TreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);

        initView();
    }

    private void initView() {
        final CustomTreeView customTreeView = findViewById(R.id.cfl_search);
        final EditText editText = findViewById(R.id.edit_search);
        findViewById(R.id.iv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = new TextView(TreeActivity.this);
                tv.setTextColor(Color.RED);
                tv.setText(editText.getText());
                tv.setBackgroundResource(R.drawable.search_history_bg);
                customTreeView.addView(tv);
            }
        });
    }
}
