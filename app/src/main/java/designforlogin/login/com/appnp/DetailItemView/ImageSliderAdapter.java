package designforlogin.login.com.appnp.DetailItemView;


import android.content.Context;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
import java.util.logging.Handler;

import designforlogin.login.com.appnp.InterfaceProject.Interface;
import designforlogin.login.com.appnp.R;
import designforlogin.login.com.appnp.StaticVariable;

public class ImageSliderAdapter extends PagerAdapter implements Interface {

    Context mContext;
    private LayoutInflater layoutInflater;
    ArrayList<String> imglist;

    public ImageSliderAdapter(Context mContext,ArrayList<String> imglist) {
        this.imglist= imglist;
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;

    }




    @Override
    public void onImageReceive(ArrayList arrayList) {

        Log.i("dddddddd", "onImageReceive: " + arrayList);

    }

        @Override
        public int getCount () {
            Log.i("sss", "getCount: " + imglist.get(0));
            return imglist.size();


        }

        @Override
        public boolean isViewFromObject (View view, Object object){
            return view == object;
        }

        @Override
        public Object instantiateItem (ViewGroup container,int position){
            Log.d("lll",
                    "instantiateItem() called with: " + "container = [" + container + "], position = [" + position + "]");

            View itemView = layoutInflater.inflate(R.layout.image_slider, container, false);

            final ImageView ivPhoto = itemView.findViewById(R.id.imgImageSlider);

            if (!imglist.get(position).equals("")) {
                Glide.with(mContext)
                        .load(StaticVariable.AddItemDetailUrl+imglist.get(position))
                        .into(ivPhoto);
            }

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem (ViewGroup container,int position, Object object){
            Log.d("llll", "destroyItem() called with: " + "container = [" + container + "], position = [" + position
                    + "], object = [" + object + "]");
            container.removeView((LinearLayout) object);
        }

}
