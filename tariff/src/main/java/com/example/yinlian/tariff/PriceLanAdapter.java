package com.example.yinlian.tariff;

/**
 * Created by Luozhimin on 2019/3/22.15:14
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import android.support.v7.widget.RecyclerView;
import com.example.yinlian.tariff.model.PriceInfo;

public class PriceLanAdapter extends RecyclerView.Adapter<PriceLanAdapter.ViewHolder> {

    private List<PriceInfo> mIconList;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView caozhiImage;
        LinearLayout bgStyle;

        public ViewHolder(View view){
            super(view);
            caozhiImage =  view.findViewById(R.id.caozhiImage);
            bgStyle=view.findViewById(R.id.bgStyle);
        }
    }

    public PriceLanAdapter(List<PriceInfo> iconList){
        mIconList = iconList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycle_view,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        PriceInfo icon = mIconList.get(position);
        if(icon.getIsDefaulted()==1){
            holder.caozhiImage.setVisibility(View.VISIBLE);
            holder.bgStyle.setBackgroundResource(R.drawable.bg_jianbian_select);
        }else{
            holder.caozhiImage.setVisibility(View.GONE);
            holder.bgStyle.setBackgroundResource(R.drawable.bg_center_white);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifiUi(position);
                mOnItemClickListener.onItemClick(holder.itemView,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mIconList.size();
    }
    private void notifiUi(int selectPostion){
        for(int i=0;i<mIconList.size();i++){
            if(i==selectPostion){
                mIconList.get(i).setIsDefaulted(1);
            }else {
                mIconList.get(i).setIsDefaulted(0);
            }

        }

        notifyDataSetChanged();
    }
}
