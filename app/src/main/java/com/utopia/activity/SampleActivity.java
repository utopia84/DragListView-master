package com.utopia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.utopia.activity.widget.DragListView;
import com.utopia.adapter.DragListAdapter;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        init();
    }

    /**
     * 初始化方法
     */
    private void init() {
        DragListView listView = (DragListView)findViewById(R.id.lv_content);
        DragListAdapter adapter = new DragListAdapter(this);
        listView.setAdapter(adapter);
    }
}
