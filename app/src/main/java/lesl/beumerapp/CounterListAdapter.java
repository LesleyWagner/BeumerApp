package lesl.beumerapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lesl.beumerapp.databinding.CounterElementBinding;
import lesl.beumerapp.models.Area;

public class CounterListAdapter extends BaseAdapter {
    private final Activity context;
    private final LayoutInflater inflater;
    private List<Area> data;
    private AreaEventHandler callback;

    private static class ViewHolder {
        public CounterElementBinding binding;
    }

    public CounterListAdapter(Activity context, List<Area> data, AreaEventHandler callback) {
        this.context = context;
        this.inflater = context.getLayoutInflater();
        this.data = data;
        this.callback = callback;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.binding = CounterElementBinding.inflate(inflater, parent, false);
            viewHolder.binding.setCallback(callback);
            rowView = viewHolder.binding.getRoot();
            rowView.setTag(viewHolder);
        }
        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.binding.setArea(data.get(position));

        return rowView;
    }

    public void setData(List<Area> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
