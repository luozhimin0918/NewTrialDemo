package com.example.yinlian.tariff;

/**
 * Created by Luozhimin on 2019/3/22.15:14
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import android.support.v7.widget.RecyclerView;
import com.example.yinlian.tariff.model.PriceInfo;

public class PriceLanAdapter extends RecyclerView.Adapter<PriceLanAdapter.ViewHolder> {

    private List<PriceInfo> mIconList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView caozhiImage;
        TextView iconName;

        public ViewHolder(View view){
            super(view);
            caozhiImage = (ImageView) view.findViewById(R.id.caozhiImage);
            iconName = (TextView) view.findViewById(R.id.icon_name);
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        PriceInfo icon = mIconList.get(position);
        if(position==1){
            holder.caozhiImage.setVisibility(View.VISIBLE);
        }else{
            holder.caozhiImage.setVisibility(View.GONE);
        }

        holder.iconName.setText(icon.getTariffDesc());
    }

    @Override
    public int getItemCount() {
        return mIconList.size();
    }
}
