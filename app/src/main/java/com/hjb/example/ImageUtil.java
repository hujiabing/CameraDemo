package com.hjb.example;


import android.graphics.Bitmap.Config;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * @author wang
 * @version V1.0
 * @Description: 图片加载工具类
 * @date 2015-7-19 下午6:34:42
 */
public class ImageUtil {

    /**
     * sd卡文件
     */
    public static final String IMAGE_FILE = "file://";
    /**
     * assert 文件
     */
    public static final String IMAGE_ASSERTS = "assert:///";
    /**
     * drawable文件
     */
    public static final String IMAGE_DRAWABLE = "drawable://";


    /**
     * 加载图片 需要自己拼接
     *
     * @param url
     * @param img
     */
    public static void LoadPicture(String url, ImageView img) {
        LoadPicture(url, img, R.mipmap.ic_launcher);
    }
    /**
     * 加载头像图片 需要自己拼接
     *
     * @param url
     * @param img
     */
    public static void LoadHeadPicture(String url, ImageView img) {
        LoadPicture(url, img, R.mipmap.ic_launcher);
    }

    /**
     * @param url
     * @param imageview
     * @param imageRes  默认图片
     */
    public static void LoadPicture(String url, ImageView imageview, int imageRes) {
        DisplayImageOptions dio = getDio(imageRes, imageRes, Config.RGB_565, true, 0);
        if (imageview != null)
            ImageLoader.getInstance().displayImage(url, imageview, dio);
    }


    /**
     * 这个方法 更LoadPicture一模一样
     * 但是加载网络图片的时候最好用这个方法
     * 万一服务器存放的不是完整路径
     * 可以统一处理
     *
     * @param url
     * @param imageview
     */
    public static void loadPicNet(String url, ImageView imageview) {
        LoadPicture(url, imageview);
    }


    /**
     * 加载网络头像 圆角20
     *
     * @param url
     * @param imageview
     */
    public static void loadHeadImgNetCorner(String url, ImageView imageview) {
        ImageLoader.getInstance().displayImage(url, imageview, getDio(R.mipmap.ic_launcher, R.mipmap.ic_launcher, Config.RGB_565, true, 20));
    }
    public static void loadHeadImgNetCorner_(String url, ImageView imageview) {
        ImageLoader.getInstance().displayImage(url, imageview, getDio(R.mipmap.ic_launcher, R.mipmap.ic_launcher, Config.RGB_565, true, 1000));
    }
    /**
     * 加载网络头像
     *
     * @param url
     * @param imageview
     */
    public static void loadHeadImgNet(String url, ImageView imageview) {
        LoadHeadPicture(url, imageview);
    }

    public static DisplayImageOptions getDio(int loadingRes, int otherRes, Config config, boolean cacheInMemory, int cornor) {
        if (cornor != 0) {
            return new DisplayImageOptions.Builder().
                    bitmapConfig(config)
                    .cacheInMemory(cacheInMemory)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                    .resetViewBeforeLoading(false)
                    .showImageForEmptyUri(otherRes)
                    .showImageOnFail(otherRes)
                    .showImageOnLoading(loadingRes)
                    .displayer(new RoundedBitmapDisplayer(cornor))// 是否设置为圆角，弧度为多少
                    .build();
        } else {
            return new DisplayImageOptions.Builder().
                    bitmapConfig(config)
                    .cacheInMemory(cacheInMemory)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                    .resetViewBeforeLoading(false)
                    .showImageForEmptyUri(otherRes)
                    .showImageOnFail(otherRes)
                    .showImageOnLoading(loadingRes)//不能加圆角 不然手势不能用
                    .build();
        }

    }

    public static void cleanCache() {
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();
    }


}
