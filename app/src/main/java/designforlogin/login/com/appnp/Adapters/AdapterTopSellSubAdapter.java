package designforlogin.login.com.appnp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import designforlogin.login.com.appnp.DataModel.AdapterTopSellSubData;
import designforlogin.login.com.appnp.DetailItemView.ItemDetailView;
import designforlogin.login.com.appnp.R;
import designforlogin.login.com.appnp.StaticVariable;

public class AdapterTopSellSubAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<AdapterTopSellSubData> adapterTopSellSubData;
    private static
    Context context;

    public AdapterTopSellSubAdapter(Context context, ArrayList<AdapterTopSellSubData> topSellsubText) {
        this.context = context;
        this.adapterTopSellSubData = topSellsubText;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        switch (i) {
            case R.layout.adapter_fr_top_sell_view_items:

                return new TopSellViewHolder(view);
            case R.layout.fragment_fr_sell_best_deals_sub_view:
                return new SellSubDeal(view);
            default:
                return new SellSubDeal(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        final AdapterTopSellSubData subData = adapterTopSellSubData.get(i);
        String imgUrlList = subData.getImgUrl();
        String imUrl = null;
        try {
            JSONArray a = new JSONArray(imgUrlList);
             imUrl = a.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (subData != null) {
            switch (subData.getType()) {

                case 1:
                    ((TopSellViewHolder) viewHolder).txtFrTopSellSubViewItem.setText(subData.getTopSellsubText());
                    Glide.with(context)
                            .load(StaticVariable.GetAddedDataImage+imUrl)
                            .placeholder(new ColorDrawable(Color.BLACK))
                            .into(((TopSellViewHolder) viewHolder).myItemDetailImage);

                    ((TopSellViewHolder) viewHolder).layOutSubRecyclerView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                          //  final AdapterTopSellSubData subDatas = adapterTopSellSubData.get(0);
//                            AdapterTopSellSubData subDatas = adapterTopSellSubData.get(0);
//                            ((TopSellViewHolder) viewHolder).
//                                    txtFrTopSellSubViewItem.setText(subDatas.getTopSellsubText());
//                            String item = String.valueOf(adapterTopSellSubData.get(viewHolder.getAdapterPosition()));


                            Intent intent = new Intent(context, ItemDetailView.class);
                            intent.putExtra("Item_Details", subData);
                            context.startActivity(intent);
                        }
                    });

                    break;
                case 2:
                    ((SellSubDeal) viewHolder).txtAdaptterSubDealData.setText(subData.getTopSellsubText());
                    break;
                case 3:

                    Glide.with(context)
                            .load(adapterTopSellSubData.get(i).getImgUrl())
                            .placeholder(new ColorDrawable(Color.BLACK))
                            .override(150, 150)
                            .into(((SellSubDeal) viewHolder).tabCategoryImage);
                    ((SellSubDeal) viewHolder).txtAdaptterSubDealData.setText(subData.getTopSellsubText());
                    break;
            }
        }


    }

    @Override
    public int getItemCount() {
        return adapterTopSellSubData.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (adapterTopSellSubData.get(position).getType()) {
            case 1:
                return R.layout.adapter_fr_top_sell_view_items;
            case 2:
                return R.layout.fragment_fr_sell_best_deals_sub_view;
            case 3:
                return R.layout.fragment_fr_sell_best_deals_sub_view;
            default:
                return -1;
        }


    }


    public static class TopSellViewHolder extends RecyclerView.ViewHolder {

        TextView txtFrTopSellSubViewItem;
        LinearLayout layOutSubRecyclerView;
        ImageView myItemDetailImage;


        public TopSellViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFrTopSellSubViewItem = itemView.findViewById(R.id.txtFrTopSellSubViewItemName);
            layOutSubRecyclerView = itemView.findViewById(R.id.layOutSubRecyclerView);
            myItemDetailImage = itemView.findViewById(R.id.myItemDetailImage);


        }
    }

    public class SellSubDeal extends RecyclerView.ViewHolder {
        TextView txtAdaptterSubDealData;
        ImageView tabCategoryImage;

        public SellSubDeal(@NonNull View itemView) {
            super(itemView);
            txtAdaptterSubDealData = itemView.findViewById(R.id.txtAdaptterSubDealData);
            tabCategoryImage = itemView.findViewById(R.id.tabCategoryImage);
        }

    }

    public class SellCategory extends RecyclerView.ViewHolder {

        public SellCategory(@NonNull View itemView) {
            super(itemView);
        }
    }
}


