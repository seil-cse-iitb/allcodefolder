package com.example.sid.aravali_v_21;

/**
 * Created by sid on 6/12/16.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] web;


    public GridAdapter(Context c, String[] web) {
        mContext = c;

        this.web = web;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return web.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.control_grid_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.room_name_tv);

            textView.setText(web[position]);

        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}