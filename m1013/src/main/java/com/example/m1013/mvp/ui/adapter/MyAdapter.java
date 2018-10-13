package com.example.m1013.mvp.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.m1013.R;
import com.example.m1013.bean.News;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.oneHolder>{
    private Context context;
    private List<News.BooksBean> list;
    public MyAdapter(Context context, List<News.BooksBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public oneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        oneHolder holder = new oneHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull oneHolder holder, int position) {

        holder.text_name.setText(list.get(position).getTitle());
        String image = list.get(position).getImage();
        holder.simp.setImageURI(image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class oneHolder extends RecyclerView.ViewHolder{

        private final TextView text_name;
        private final SimpleDraweeView simp;

        public oneHolder(View itemView) {
            super(itemView);
            text_name = itemView.findViewById(R.id.text_name);
            simp = itemView.findViewById(R.id.simp);
        }
    }
}
