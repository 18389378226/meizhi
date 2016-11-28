package com.chensen.meizhi.common.http;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chensen.meizhi.R;

/**
 * author：chensen on 2016/11/25 14:12
 * desc：
 */

public class ImageLoader {


    public static void load(Context context, String url, ImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }

    }

    public static void load(Context context, String url, SimpleTarget target) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(target);

        }
    }

    public static void load(Context context, ImageView imageView) {
        Glide.with(context)
                .load(R.drawable.default_img)
                .placeholder(R.drawable.default_img)
                .into(imageView);
    }
}
