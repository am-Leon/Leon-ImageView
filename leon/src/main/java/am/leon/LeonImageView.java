package am.leon;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.List;

import static am.leon.Utils.getMediaPath;
import static am.leon.Utils.getStringPath;

public class LeonImageView extends TouchImageView {

    private OnImageClickListener onImageClickListener;
    private int reloadImageRes, defaultImageRes, videoPlayImageRes, placeHolderImageRes;


    public LeonImageView(Context context) {
        this(context, null);
    }


    public LeonImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public LeonImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        viewInit(context);
        setTypedArrayValues(context, attrs, defStyle);
    }


    private void viewInit(Context context) {
        onImageClickListener = new OnImageClickListener(context);
        setOnClickListener(onImageClickListener);
    }


    private void setTypedArrayValues(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LeonImageView, defStyle, 0);

        setReloadImageRes(typedArray.getResourceId(R.styleable.LeonImageView_leon_reload_icon, R.drawable.layer_reload));

        setDefaultImageRes(typedArray.getResourceId(R.styleable.LeonImageView_leon_default_icon, R.drawable.ic_default));

        setVideoPlayImageRes(typedArray.getResourceId(R.styleable.LeonImageView_leon_play_video_icon, R.drawable.ic_play_colored));

        setPlaceHolderImageRes(typedArray.getResourceId(R.styleable.LeonImageView_leon_place_holder_icon, R.drawable.layer_place_holder));
        typedArray.recycle();

    }


    private void setTypedArrayValues(Context context, int leonImageViewStyle) {
        // The attributes you want retrieved
        int[] attrs = {R.attr.leon_reload_icon, R.attr.leon_default_icon, R.attr.leon_play_video_icon, R.attr.leon_play_video_icon};

        // Parse LeonImageView, using Context.obtainStyledAttributes()
        TypedArray typedArray = context.obtainStyledAttributes(leonImageViewStyle, attrs);

        setReloadImageRes(typedArray.getResourceId(R.styleable.LeonImageView_leon_reload_icon, R.drawable.layer_reload));

        setDefaultImageRes(typedArray.getResourceId(R.styleable.LeonImageView_leon_default_icon, R.drawable.ic_default));

        setVideoPlayImageRes(typedArray.getResourceId(R.styleable.LeonImageView_leon_play_video_icon, R.drawable.ic_play_colored));

        setPlaceHolderImageRes(typedArray.getResourceId(R.styleable.LeonImageView_leon_place_holder_icon, R.drawable.layer_place_holder));
        typedArray.recycle();
    }


    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        if (l == null)
            super.setOnClickListener(null);
        else
            super.setOnClickListener(l);
    }


    //-----------------------------------------Setters & Getters------------------------------------


    public int getReloadImageRes() {
        return reloadImageRes;
    }

    public void setReloadImageRes(int reloadImageRes) {
        this.reloadImageRes = reloadImageRes;
        onImageClickListener.setReloadImageRes(reloadImageRes);
    }


    public int getDefaultImageRes() {
        return defaultImageRes;
    }

    public void setDefaultImageRes(int defaultImageRes) {
        this.defaultImageRes = defaultImageRes;
        onImageClickListener.setDefaultImageRes(defaultImageRes);
    }


    public int getVideoPlayImageRes() {
        return videoPlayImageRes;
    }

    public void setVideoPlayImageRes(int videoPlayImageRes) {
        this.videoPlayImageRes = videoPlayImageRes;
        onImageClickListener.setVideoPlayImageRes(videoPlayImageRes);
    }


    public int getPlaceHolderImageRes() {
        return placeHolderImageRes;
    }

    public void setPlaceHolderImageRes(int placeHolderImageRes) {
        this.placeHolderImageRes = placeHolderImageRes;
        onImageClickListener.setPlaceHolderImageRes(placeHolderImageRes);
    }


    //-----------------------------------------LeonImageMethods-------------------------------------

    public void loadImage(Object object) {
        try {
            executePicasso(LeonMedia.getLeonMedia(object));
            onImageClickListener.setImage(LeonMedia.getLeonMedia(object));
        } catch (Exception e) {
            this.setImageResource(getDefaultImageRes());
            e.printStackTrace();
        }
    }


    public void loadImage(Object object, Transformation transformation) {
        try {
            executePicasso(LeonMedia.getLeonMedia(object), transformation);
            onImageClickListener.setImage(LeonMedia.getLeonMedia(object));
        } catch (Exception e) {
            this.setImageResource(getDefaultImageRes());
            e.printStackTrace();
        }
    }


    //-----------------------------------------UriListMethods---------------------------------------


    public void loadUriImages(List<Uri> uriList) {
        onImageClickListener.setUriImages(uriList, 0, "en");
    }


    public void loadUriImages(List<Uri> uriList, int currentPosition) {
        onImageClickListener.setUriImages(uriList, currentPosition, "en");
    }


    public void loadUriImages(List<Uri> uriList, int currentPosition, String appLanguage) {
        onImageClickListener.setUriImages(uriList, currentPosition, appLanguage);
    }


    //-----------------------------------------MediaListMethods-------------------------------------


    public void loadMediaImages(List<Media> mediaList) {
        onImageClickListener.setMediaImages(mediaList, 0, "en");
    }


    public void loadMediaImages(List<Media> mediaList, int currentPosition) {
        onImageClickListener.setMediaImages(mediaList, currentPosition, "en");
    }


    public void loadMediaImages(List<Media> mediaList, int currentPosition, String appLanguage) {
        onImageClickListener.setMediaImages(mediaList, currentPosition, appLanguage);
    }


    //-----------------------------------------FileListMethods--------------------------------------


    public void loadFileImages(List<File> fileList) {
        onImageClickListener.setFileImages(fileList, 0, "en");
    }


    public void loadFileImages(List<File> fileList, int currentPosition) {
        onImageClickListener.setFileImages(fileList, currentPosition, "en");
    }


    public void loadFileImages(List<File> fileList, int currentPosition, String appLanguage) {
        onImageClickListener.setFileImages(fileList, currentPosition, appLanguage);
    }


    //-----------------------------------------StringListMethods------------------------------------


    public void loadImages(List<String> stringList) {
        onImageClickListener.setStringImages(stringList, 0, "en");
    }


    public void loadImages(List<String> stringList, int currentPosition) {
        onImageClickListener.setStringImages(stringList, currentPosition, "en");
    }


    public void loadImages(List<String> stringList, int currentPosition, String appLanguage) {
        onImageClickListener.setStringImages(stringList, currentPosition, appLanguage);
    }


    //-----------------------------------------ResListMethods---------------------------------------

    public void loadResImages(List<Integer> resList) {
        onImageClickListener.setResImages(resList, 0, "en");
    }


    public void loadResImages(List<Integer> resList, int currentPosition) {
        onImageClickListener.setResImages(resList, currentPosition, "en");
    }


    public void loadResImages(List<Integer> resList, int currentPosition, String appLanguage) {
        onImageClickListener.setResImages(resList, currentPosition, appLanguage);
    }


    //-----------------------------------------LeonImageMethods-------------------------------------

    private void executePicasso(LeonMedia leonMedia) {
        Picasso picasso = Picasso.get();
        switch (leonMedia.getType()) {
            case URI:
                picasso.load((Uri) leonMedia.getObject())
                        .placeholder(getPlaceHolderImageRes())
                        .error(getReloadImageRes())
                        .into(this, new PicassoCallback(this, leonMedia));
                break;

            case MEDIA:
                picasso.load(getMediaPath((Media) leonMedia.getObject()))
                        .placeholder(getPlaceHolderImageRes())
                        .error(getReloadImageRes())
                        .into(this, new PicassoCallback(this, leonMedia));
                break;

            case FILE:
                picasso.load((File) leonMedia.getObject())
                        .placeholder(getPlaceHolderImageRes())
                        .error(getReloadImageRes())
                        .into(this, new PicassoCallback(this, leonMedia));
                break;

            case STRING:
                picasso.load(getStringPath((String) leonMedia.getObject()))
                        .placeholder(getPlaceHolderImageRes())
                        .error(getReloadImageRes())
                        .into(this, new PicassoCallback(this, leonMedia));
                break;

            case RESOURCE:
                setImageResource((Integer) leonMedia.getObject());
                break;
        }

    }


    private void executePicasso(LeonMedia leonMedia, Transformation transformation) {
        Picasso picasso = Picasso.get();
        switch (leonMedia.getType()) {
            case URI:
                picasso.load((Uri) leonMedia.getObject())
                        .placeholder(getPlaceHolderImageRes())
                        .error(getReloadImageRes())
                        .transform(transformation)
                        .into(this, new PicassoCallback(this, leonMedia, transformation));
                break;

            case MEDIA:
                picasso.load(getMediaPath((Media) leonMedia.getObject()))
                        .placeholder(getPlaceHolderImageRes())
                        .error(getReloadImageRes())
                        .transform(transformation)
                        .into(this, new PicassoCallback(this, leonMedia, transformation));
                break;

            case FILE:
                picasso.load((File) leonMedia.getObject())
                        .placeholder(getPlaceHolderImageRes())
                        .error(getReloadImageRes())
                        .transform(transformation)
                        .into(this, new PicassoCallback(this, leonMedia, transformation));
                break;

            case STRING:
                picasso.load(getStringPath((String) leonMedia.getObject()))
                        .placeholder(getPlaceHolderImageRes())
                        .error(getReloadImageRes())
                        .transform(transformation)
                        .into(this, new PicassoCallback(this, leonMedia, transformation));
                break;

            case RESOURCE:
                setImageResource((Integer) leonMedia.getObject());
                break;
        }

    }


    public void show() {
        onImageClickListener.onClick(this);
    }

}