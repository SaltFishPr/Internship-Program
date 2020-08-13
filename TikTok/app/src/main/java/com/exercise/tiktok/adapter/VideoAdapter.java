package com.exercise.tiktok.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exercise.tiktok.R;
import com.exercise.tiktok.UpPageActivity;
import com.exercise.tiktok.bean.VideoBean;


import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.RecyclerHolder> {
    protected Context context;
    protected List<VideoBean> datas;

    public VideoAdapter(Context context, List<VideoBean> datas){
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_video, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, final int position) {
        String uriStr = "android.resource://" + context.getPackageName() + "/"+datas.get(position).getImg_video();
        holder.video.setVideoPath(uriStr);
        holder.img_up.setImageResource(datas.get(position).getImg_up());
        holder.img_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(view.toString());
                Intent intent = new Intent(context, UpPageActivity.class);
                intent.putExtra("name",datas.get(position).getName());
                context.startActivity(intent);
            }
        });
        holder.name.setText(datas.get(position).getName());
        holder.miaoshu.setText(datas.get(position).getMiaoshu());
        holder.dianzan.setText(datas.get(position).getDianzan());
        holder.pingluen.setText(datas.get(position).getPingluen());
        holder.zhuanfa.setText(datas.get(position).getZhuanfa());
    }





    @Override
    public int getItemCount() {
        return datas.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        VideoView video;
        ImageView img_up;
        TextView name,miaoshu,dianzan,pingluen,zhuanfa;

        private RecyclerHolder(View itemView) {
            super(itemView);
            video = itemView.findViewById(R.id.vv);
            img_up = itemView.findViewById(R.id.img_up);
            name = itemView.findViewById(R.id.name);
            miaoshu = itemView.findViewById(R.id.miaoshu);
            dianzan = itemView.findViewById(R.id.dianzan);
            pingluen = itemView.findViewById(R.id.pingluen);
            zhuanfa = itemView.findViewById(R.id.zhuanfa);
        }
    }


}
