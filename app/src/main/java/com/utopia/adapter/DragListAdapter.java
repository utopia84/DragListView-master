package com.utopia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.utopia.activity.R;
import com.utopia.activity.widget.DragItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */

public class DragListAdapter extends BaseAdapter {

    private List<String> testData = new ArrayList<>();
    protected LayoutInflater mInflater;

    public DragListAdapter(Context context) {
        testData.add("第一条数据");
        testData.add("第二条数据");
        testData.add("第三条数据");
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return testData.size();
    }

    @Override
    public Object getItem(int i) {
        return testData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_item, parent, false);
            View menuView = mInflater.inflate(R.layout.swipemenu, parent, false);
            //合成内容与菜单
            convertView = new DragItemView(convertView, menuView);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvContent.setText(testData.get(position));
        return convertView;
    }

    class ViewHolder {
        public TextView tvContent;
        public TextView tvClose;

        public ViewHolder(View convertView) {
            this.tvContent = (TextView)convertView.findViewById(R.id.tv_content);
            this.tvClose =  (TextView)convertView.findViewById(R.id.tv_close);
        }
    }
}
