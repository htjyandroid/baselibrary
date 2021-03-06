package com.htjy.baselibrary.widget.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.htjy.baselibrary.widget.imageloader.listener.ImageDownloadListener;
import com.htjy.baselibrary.widget.imageloader.listener.ImageDrawableListener;
import com.htjy.baselibrary.widget.imageloader.listener.ImageLoadListener;
import com.htjy.baselibrary.widget.imageloader.listener.ImageSaveListener;


/**
 * Created by soulrelay on 2016/10/11 13:42.
 * Class Note:
 * use this class to load image,single instance
 */
public class ImageLoaderUtil {

    //图片默认加载类型 以后有可能有多种类型
    public static final int PIC_DEFAULT_TYPE = 0;

    //图片默认加载策略 以后有可能有多种图片加载策略
    public static final int LOAD_STRATEGY_DEFAULT = 0;

    private static ImageLoaderUtil mInstance;
    //本应该使用策略模式，用基类声明，但是因为Glide特殊问题
    //持续优化更新
    private BaseImageLoaderStrategy mStrategy;

    public ImageLoaderUtil() {
        mStrategy = new GlideImageLoaderStrategy();
    }

    //单例模式，节省资源
    public static ImageLoaderUtil getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderUtil.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderUtil();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * 统一使用App context
     * 可能带来的问题：http://stackoverflow.com/questions/31964737/glide-image-loading-with-application-context
     *
     * @param url
     * @param placeholder
     * @param imageView
     */
    public void loadImage(Object url, int placeholder, ImageView imageView) {
        mStrategy.loadImage(imageView.getContext(), url, placeholder, imageView);
    }
    public void loadImage(Object url, ImageView imageView) {
        mStrategy.loadImage(url, imageView);
    }
    public void loadImageWithAppCxt(Object url, ImageView imageView) {
        mStrategy.loadImageWithAppCxt(url, imageView);
    }

    public void loadImageWithListener(Object url, int placeholder, ImageView imageView, ImageLoadListener listener) {
        mStrategy.loadImageWithListener(url, placeholder, imageView, listener);
    }

//    public void loadGifImage(Object url, int placeholder, ImageView imageView) {
//        mStrategy.loadGifImage(url, placeholder, imageView);
//    }

    public void loadCircleImage(Object url, int placeholder, ImageView imageView) {
        mStrategy.loadCircleImage(url, placeholder, imageView);
    }

    public void loadCentercropCircleImage(Object url, int placeholder, ImageView imageView) {
        mStrategy.loadCentercropCircleImage(url, placeholder, imageView);
    }

    public void loadCircleImage(Bitmap bitmap, int placeholder, ImageView imageView) {
        mStrategy.loadCircleImage(bitmap, placeholder, imageView);
    }

    public void loadCircleImage(Bitmap bitmap, int placeholder, ImageView imageView, ImageLoadListener listener) {
        mStrategy.loadCircleImage(bitmap, placeholder, imageView, listener);
    }

    public void loadCircleImage(Object url, int placeholder, ImageView imageView, ImageLoadListener listener) {
        mStrategy.loadCircleImage(url, placeholder, imageView, listener);
    }

    public void loadCircleBorderImage(Object url, int placeholder, ImageView imageView, float borderWidth, int borderColor) {
        mStrategy.loadCircleBorderImage(url, placeholder, imageView, borderWidth, borderColor);
    }






//    public void loadImageWithProgress(Object url, ImageView imageView, ProgressLoadListener listener) {
//        mStrategy.loadImageWithProgress(url, imageView, listener);
//    }
//
//    public void loadGifWithPrepareCall(Object url, ImageView imageView, SourceReadyListener listener) {
//        mStrategy.loadGifWithPrepareCall(url, imageView, listener);
//    }
//
//    public void loadImageWithPrepareCall(Object url, ImageView imageView, int placeholder, SourceReadyListener listener) {
//        mStrategy.loadImageWithPrepareCall(url, imageView, placeholder, listener);
//    }

    public void loadCornerImg(Bitmap bitmap, ImageView imageView, int placeholder, int dp) {
        mStrategy.loadCornerImage(bitmap, placeholder, imageView, dp);
    }

    public void loadCornerImg(Object url, ImageView imageView, int placeholder, int dp) {
        mStrategy.loadCornerImage(url, placeholder, imageView, dp);
    }

    public void loadCornerImgWithListener(Object url, ImageView imageView, int placeholder, int dp, ImageLoadListener listener) {
        mStrategy.loadCornerImage(url, placeholder, imageView, dp, listener);
    }

    public void loadCenterCropWithCorner(Context context, Object model, ImageView imageView, int corner_px) {
        mStrategy.loadCenterCropWithCorner(context, model, imageView, corner_px);
    }

    public void loadCenterCropWithCorner(Context context, Object model, ImageView imageView, int placeholder, int corner_px) {
        mStrategy.loadCenterCropWithCorner(context, model, imageView,placeholder, corner_px);
    }

    /**
     * 策略模式的注入操作
     *
     * @param strategy
     */
    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        mStrategy = strategy;
    }

    /**
     * 需要展示图片加载进度的请参考 GalleryAdapter
     * 样例如下所示
     */

    /**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache(final Context context) {
        mStrategy.clearImageDiskCache(context);
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache(Context context) {
        mStrategy.clearImageMemoryCache(context);
    }

    /**
     * 根据不同的内存状态，来响应不同的内存释放策略
     *
     * @param context
     * @param level
     */
    public void trimMemory(Context context, int level) {
        mStrategy.trimMemory(context, level);
    }

    /**
     * 清除图片所有缓存
     */
    public void clearImageAllCache(Context context) {
        clearImageDiskCache(context.getApplicationContext());
        clearImageMemoryCache(context.getApplicationContext());
    }

    /**
     * 获取缓存大小
     *
     * @return CacheSize
     */
    public String getCacheSize(Context context) {
        return mStrategy.getCacheSize(context);
    }

    public void saveImage(Context context, Object url, String savePath, String saveFileName, ImageSaveListener listener) {
        mStrategy.saveImage(context, url, savePath, saveFileName, listener);
    }

    public void downloadOnly(Context context, String url, ImageDownloadListener listener) {
        mStrategy.downloadOnly(context, url, listener);
    }

    public void loadDrawable(Context context, Object url, ImageDrawableListener listener) {
        mStrategy.loadDrawable(context, url, listener);
    }

}
