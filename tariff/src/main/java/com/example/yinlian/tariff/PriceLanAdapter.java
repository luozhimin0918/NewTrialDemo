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
import java.text.DecimalFormat;
import java.util.List;
import android.support.v7.widget.RecyclerView;
import com.example.yinlian.tariff.model.PriceInfo;
import com.example.yinlian.tariff.model.TariffRespJson;

public class PriceLanAdapter extends RecyclerView.Adapter<PriceLanAdapter.ViewHolder> {

    private List<TariffRespJson.DataBean.TariffInfoListBean> mIconList;
    private boolean isCanOnClick=true;//是否可以点击选项

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setCanOnClick(boolean isCanOnClick){
        this.isCanOnClick=isCanOnClick;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView caozhiImage;
        LinearLayout bgStyle;
        TextView tariffDesc,presentPrice,originalPrice,discount;
        public ViewHolder(View view){
            super(view);
            caozhiImage =  view.findViewById(R.id.caozhiImage);
            bgStyle=view.findViewById(R.id.bgStyle);
            tariffDesc=view.findViewById(R.id.tariffDesc);
            presentPrice=view.findViewById(R.id.presentPrice);
            originalPrice=view.findViewById(R.id.originalPrice);
            discount=view.findViewById(R.id.discount);
        }
    }

    public PriceLanAdapter(List<TariffRespJson.DataBean.TariffInfoListBean> iconList){
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
        TariffRespJson.DataBean.TariffInfoListBean icon = mIconList.get(position);
        if(icon.getIsDefaulted()==1){
            holder.caozhiImage.setVisibility(View.VISIBLE);
            holder.bgStyle.setBackgroundResource(R.drawable.bg_jianbian_select);
        }else{
            if(isCanOnClick){
                holder.bgStyle.setBackgroundResource(R.drawable.bg_center_white);
            }else{
                holder.bgStyle.setBackgroundResource(R.drawable.bg_center_gray);
            }
            holder.caozhiImage.setVisibility(View.GONE);

        }
        holder.tariffDesc.setText("开通"+icon.getServiceTerm()+"个月");
        holder.presentPrice.setText("¥"+icon.getPresentPrice());
        holder.originalPrice.setText("¥"+icon.getOriginalPrice());
        double distan =icon.getOriginalPrice()-icon.getPresentPrice();
        holder.discount.setText("立省"+new DecimalFormat("#.##").format(distan)+"元");
        if(isCanOnClick){//可以点击添加点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifiUi(position);
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }else{
            holder.itemView.setOnClickListener(null);
        }


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
