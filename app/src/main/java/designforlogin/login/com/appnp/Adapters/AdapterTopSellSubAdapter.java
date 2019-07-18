package designforlogin.login.com.appnp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import designforlogin.login.com.appnp.DetailItemView.ImageSliderAdapter;
import designforlogin.login.com.appnp.DetailItemView.ItemDetailView;
import designforlogin.login.com.appnp.MainActivity;
import designforlogin.login.com.appnp.R;
import designforlogin.login.com.appnp.StaticVariable;
import designforlogin.login.com.appnp.Transition.DetailTransition;

public class AdapterTopSellSubAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<AdapterTopSellSubData> adapterTopSellSubData;
    private static
    Context context;

    public AdapterTopSellSubAdapter(Context context, ArrayList<AdapterTopSellSubData> topSellsubText) {
        this.context = context;
        this.adapterTopSellSubData = topSellsubText;

    }
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        switch (i) {
            case R.layout.adapter_fr_top_sell_view_items:
                return new TopSellViewHolder(view);
            case R.layout.fragment_fr_sell_best_deals_sub_view:
                return new SellSubDeal(view);
            case R.layout.my_all_ads_view:
                return new AdsDetails(view);
            case R.layout.my_all_trending_view:
                return new TrendingViewHolder(view);

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
                            .load(StaticVariable.GetAddedDataImage + imUrl)
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

//                            Log.i("clicker", "onClick: starting intent");
//                            Intent intent = new Intent(context, ItemDetailView.class);
//                            intent.putExtra("Item_Details", subData);
//                            context.startActivity(intent);


// Note that we need the API version check here because the actual transition classes (e.g. Fade)
// are not in the support library and are only available in API 21+. The methods we are calling on the Fragment
// ARE available in the support library (though they don't do anything on API < 21)


                            Bundle bundle=new Bundle();
                            bundle.putSerializable("Item_Details",subData);
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                fragment.setSharedElementEnterTransition(new DetailTransition());
//                                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
//                                        android.R.anim.slide_out_right);
//                                fragment.setSharedElementReturnTransition(new DetailTransition());
//                            }

                            ItemDetailView fragment=new ItemDetailView();

                            fragment.setArguments(bundle);
                            fragmentManager = ((MainActivity) context). getSupportFragmentManager();
                            Fragment seemoreFr = fragmentManager.findFragmentById(R.id.fr_addMore);
                            fragmentTransaction = fragmentManager .beginTransaction();
                            if(seemoreFr!=null) {
                                fragmentTransaction.add(R.id.fr_addMore, fragment);
                                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                                        android.R.anim.slide_out_right);
                                fragmentTransaction.commit();

                            }else{
                                fragmentTransaction.replace(R.id.fr_addMore, fragment);
                                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                                        android.R.anim.slide_out_right);
                                fragmentTransaction.commit();

                            }

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
                case 4:
                    ((AdsDetails) viewHolder).adsAddedName.setText(subData.getTopSellsubText());
                    String imgJson = subData.getImgUrl();

                    try {
                        JSONArray im = new JSONArray(imgJson);
                        String imgName = im.getString(0);
                        Glide.with(context)
                                .load(StaticVariable.GetAddedDataImage + imgName)
                                .placeholder(new ColorDrawable(Color.BLACK))
                                .override(150, 150)
                                .into(((AdsDetails) viewHolder).adsAddedImage);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ((AdsDetails) viewHolder).card_myAdsList.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("clicker", "onClick: starting intent");
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("Item_Details",subData);


                            ItemDetailView fragment=new ItemDetailView();
                            fragment.setArguments(bundle);
                            fragmentManager = ((MainActivity) context). getSupportFragmentManager();
                            Fragment seemoreFr = fragmentManager.findFragmentById(R.id.fr_addMore);
                            fragmentTransaction = fragmentManager .beginTransaction();
                            if(seemoreFr!=null) {
                                fragmentTransaction.add(R.id.fr_addMore, fragment);
                                fragmentTransaction.commit();

                            }else{
                                fragmentTransaction.replace(R.id.fr_addMore, fragment);
                                fragmentTransaction.commit();

                            }

                        }
                    });
                    break;
                case 5:
                    ((TrendingViewHolder) viewHolder).trendingAddedName.setText(subData.getTopSellsubText());
                    String myJson = subData.getImgUrl();

                    try {
                        JSONArray im = new JSONArray(myJson);
                        String imgName = im.getString(0);
                        Glide.with(context)
                                .load(StaticVariable.GetAddedDataImage + imgName)
                                .placeholder(new ColorDrawable(Color.BLACK))
                                .override(150, 150)
                                .into(((TrendingViewHolder) viewHolder).trendingAddedImage);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ((TrendingViewHolder) viewHolder).card_Trending_myAdsList.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("clicker", "onClick: starting intent");
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("Item_Details",subData);
                            ItemDetailView fragment=new ItemDetailView();

                            fragment.setArguments(bundle);
                            fragmentManager = ((MainActivity) context). getSupportFragmentManager();
                            Fragment seemoreFr = fragmentManager.findFragmentById(R.id.fr_addMore);
                            fragmentTransaction = fragmentManager .beginTransaction();
                            if(seemoreFr!=null) {
                                fragmentTransaction.add(R.id.fr_addMore, fragment);
                                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                                        android.R.anim.slide_out_right);
                                fragmentTransaction.commit();

                            }else{
                                fragmentTransaction.replace(R.id.fr_addMore, fragment);
                                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                                        android.R.anim.slide_out_right);
                                fragmentTransaction.commit();

                            }

                        }
                    });
                    break;




//                    ((AdsDetails) viewHolder).txtAdaptterSubDealData.setText(subData.getTopSellsubText());
//                    ((AdsDetails) viewHolder).mtoolbar.setVisibility(View.GONE);
//                    ((AdsDetails) viewHolder).viewSellerDetail.setVisibility(View.GONE);
//                    ((AdsDetails) viewHolder).abtseller.setVisibility(View.GONE);
//                    ArrayList<String> imgList = new ArrayList<>();
//                    String imgJson = subData.getImgUrl();
//                    Log.i("hhhhhhhhh", "onCreate: " + imgJson);
//                    try {
//                        JSONArray im = new JSONArray(imgJson);
//                        for (int j = 0; j < im.length(); j++) {
//                            imgList.add(im.getString(j));
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    if (((AdsDetails) viewHolder).viewPager != null) {
//                        ((AdsDetails) viewHolder).viewPager.setAdapter(new ImageSliderAdapter(context, imgList));
//                    }
//                    ((AdsDetails) viewHolder).tabDetailsDots.setupWithViewPager(((AdsDetails) viewHolder).viewPager, true);
//        }

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
            case 4:
                return R.layout.my_all_ads_view;
            case 5:
                return R.layout.my_all_trending_view;
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

    public class AdsDetails extends RecyclerView.ViewHolder {
        TextView adsAddedName;
        ImageView adsAddedImage;
        CardView card_myAdsList;
//        Toolbar mtoolbar;
//        ViewPager viewPager;
//        TabLayout tabDetailsDots;
//        CardView viewSellerDetail;


        public AdsDetails(@NonNull View itemView) {
            super(itemView);
            adsAddedName = itemView.findViewById(R.id.adsAddedName);
            adsAddedImage = itemView.findViewById(R.id.adsAddedImage);
            card_myAdsList = itemView.findViewById(R.id.card_myAdsList);
//            mtoolbar = itemView.findViewById(R.id.fr_AddDetails_toolbar);
//            viewPager = itemView.findViewById(R.id.itemImageSlider);
//            tabDetailsDots = itemView.findViewById(R.id.tabDetailsDots);
//            viewSellerDetail=itemView.findViewById(R.id.viewSellerDetail);
//            abtseller=itemView.findViewById(R.id.abtseller);

        }
    }

    public class TrendingViewHolder extends RecyclerView.ViewHolder {
        TextView trendingAddedName;
        ImageView trendingAddedImage;
        CardView card_Trending_myAdsList;

        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);
//            trendingAddedName = itemView.findViewById(R.id.trendingAddedImage);
//            trendingAddedImage = itemView.findViewById(R.id.trendingAddedImage);
            trendingAddedImage = itemView.findViewById(R.id.trendingAddedImage);
            trendingAddedName = itemView.findViewById(R.id.trendingAddedName);
            card_Trending_myAdsList = itemView.findViewById(R.id.card_Trending_myAdsList);

        }
    }
}


