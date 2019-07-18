package designforlogin.login.com.appnp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import designforlogin.login.com.appnp.DataModel.AdapterDataHolder;
import designforlogin.login.com.appnp.DataModel.AdapterTopSellSubData;
import designforlogin.login.com.appnp.MainActivity;
import designforlogin.login.com.appnp.R;
import designforlogin.login.com.appnp.TabLayout.Home.TopSell.SeeMoreFragment;

public class AdapterSellTopSell extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<AdapterDataHolder> adapterSellTopSells;
    String next_view;
    Context context;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    public AdapterSellTopSell() {

    }

    public AdapterSellTopSell(Context context, ArrayList<AdapterDataHolder> adapterDataHolders) {
        this.context = context;
        this.adapterSellTopSells = adapterDataHolders;

    }

    int viewHolderPosition;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        switch (i) {
            case R.layout.adpter_topsell_view:
                return new MainViewSellTopSell(view);
            case R.layout.fragment_fr_sell_best_deal_view:
                return new MainViewSellBestDeals(view);
            default:
                return new MainViewSellBestDeals(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        final AdapterDataHolder adpViewHolder = adapterSellTopSells.get(i);
        if (adpViewHolder != null) {
            switch (adpViewHolder.getTypeMain()) {
                case 1:

                    final ArrayList<AdapterTopSellSubData> mynewSubData = adpViewHolder.getAdapterTopSellSubData();

                    AdapterTopSellSubAdapter n = new AdapterTopSellSubAdapter(context, mynewSubData);
                    ((MainViewSellTopSell) viewHolder).adp_ViewText.setText(adpViewHolder.getMyText());
                    ((MainViewSellTopSell) viewHolder).fr_top_Sell_sub_recycler.hasFixedSize();
                    ((MainViewSellTopSell) viewHolder).fr_top_Sell_sub_recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                    ((MainViewSellTopSell) viewHolder).fr_top_Sell_sub_recycler.setAdapter(n);

                    ((MainViewSellTopSell) viewHolder).adt_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            String a = adpViewHolder.getMyText();
                            SeeMoreFragment seemore = new SeeMoreFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("viewName", a);
                            seemore.setArguments(bundle);
                            fragmentManager=((MainActivity)context).getSupportFragmentManager();
                            fragmentTransaction = fragmentManager.beginTransaction();
                            Fragment seemoreFr = fragmentManager.findFragmentById(R.id.fr_addMore);
                            fragmentTransaction.addToBackStack("your");
                            if (seemoreFr != null) {
                                fragmentTransaction.replace(R.id.fr_addMore, seemore);
                                fragmentTransaction.commit();
                            } else {
                                fragmentTransaction.add(R.id.fr_addMore, seemore);
                                fragmentTransaction.commit();
                            }
//                            viewHolderPosition=i;

//                            ArrayList<AdapterTopSellSubData> mynewSubData = adpViewHolder.getAdapterTopSellSubData();
//                            AdapterTopSellSubAdapter n = new AdapterTopSellSubAdapter(context,mynewSubData);
//                            ((MainViewSellTopSell)viewHolder).fr_top_Sell_sub_recycler.hasFixedSize();
//                            ((MainViewSellTopSell) viewHolder).fr_top_Sell_sub_recycler.setLayoutManager(new GridLayoutManager(context,3));
//                            ((MainViewSellTopSell) viewHolder).fr_top_Sell_sub_recycler.setAdapter(n);


//                            ((MainViewSellTopSell) viewHolder).adt_back.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    ArrayList<AdapterTopSellSubData> mynewSubData = adpViewHolder.getAdapterTopSellSubData();
//
//                                    AdapterTopSellSubAdapter n = new AdapterTopSellSubAdapter(context,mynewSubData);
//                                    ((MainViewSellTopSell) viewHolder).adp_ViewText.setText(adpViewHolder.getMyText());
//                                    ((MainViewSellTopSell) viewHolder).adt_back.setVisibility(View.GONE);
//                                    ((MainViewSellTopSell)viewHolder).fr_top_Sell_sub_recycler.hasFixedSize();
//                                    ((MainViewSellTopSell) viewHolder).fr_top_Sell_sub_recycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
//                                    ((MainViewSellTopSell) viewHolder).fr_top_Sell_sub_recycler.setAdapter(n);
//
//                                }
//                            });

                        }
                    });


                    break;
                case 2:
                    ArrayList<AdapterTopSellSubData> newDealData = adpViewHolder.getAdapterTopSellSubData();
                    AdapterTopSellSubAdapter newData = new AdapterTopSellSubAdapter(context, newDealData);
                    ((MainViewSellBestDeals) viewHolder).viewSellSubBestDeal.hasFixedSize();
                    ((MainViewSellBestDeals) viewHolder).viewSellSubBestDeal.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                    ((MainViewSellBestDeals) viewHolder).viewSellSubBestDeal.setAdapter(newData);
                    break;

            }

        }


    }

    @Override
    public int getItemCount() {
        return adapterSellTopSells.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (adapterSellTopSells.get(position).getTypeMain()) {
            case 1:
                return R.layout.adpter_topsell_view;
            case 2:
                return R.layout.fragment_fr_sell_best_deal_view;

            default:
                return R.layout.fragment_fr_sell_best_deals_sub_view;

        }
    }

    public static class MainViewSellTopSell extends RecyclerView.ViewHolder {
        TextView adp_ViewText, adt_back;
        RecyclerView fr_top_Sell_sub_recycler;

        public MainViewSellTopSell(@NonNull View itemView) {
            super(itemView);
            adp_ViewText = itemView.findViewById(R.id.adp_ViewText);
            adt_back = itemView.findViewById(R.id.adt_All);
            fr_top_Sell_sub_recycler = itemView.findViewById(R.id.fr_top_Sell_sub_recycler);

        }
    }

    public static class MainViewSellBestDeals extends RecyclerView.ViewHolder {
        RecyclerView viewSellSubBestDeal;

        public MainViewSellBestDeals(@NonNull View itemView) {
            super(itemView);
            viewSellSubBestDeal = itemView.findViewById(R.id.viewSellSubBestDeal);
        }
    }

    public int viewPosition() {
        return viewHolderPosition;
    }
}


