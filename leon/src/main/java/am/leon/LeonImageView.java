package am.leon;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static am.leon.Utils.YouTube_Thumb;

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
        setZoomEnabled(false);
    }


    private void setTypedArrayValues(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LeonImageView, defStyle, 0);

        try {
            reloadImageRes = (typedArray.getResourceId(R.styleable.LeonImageView_reload_icon, R.drawable.ic_reload));

            defaultImageRes = (typedArray.getResourceId(R.styleable.LeonImageView_default_icon, R.drawable.ic_default));

            videoPlayImageRes = (typedArray.getResourceId(R.styleable.LeonImageView_video_play_icon, R.drawable.ic_play_colored));

            placeHolderImageRes = (typedArray.getResourceId(R.styleable.LeonImageView_place_holder_icon, R.drawable.layer_place_holder));
        } finally {
            typedArray.recycle();
        }
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
    }


    public int getDefaultImageRes() {
        return defaultImageRes;
    }

    public void setDefaultImageRes(int defaultImageRes) {
        this.defaultImageRes = defaultImageRes;
    }


    public int getVideoPlayImageRes() {
        return videoPlayImageRes;
    }

    public void setVideoPlayImageRes(int videoPlayImageRes) {
        this.videoPlayImageRes = videoPlayImageRes;
    }


    public int getPlaceHolderImageRes() {
        return placeHolderImageRes;
    }

    public void setPlaceHolderImageRes(int placeHolderImageRes) {
        this.placeHolderImageRes = placeHolderImageRes;
    }


    //-----------------------------------------LeonImageMethods-------------------------------------


    public void loadImage(Object object) {
        executePicasso(handleObject(object), null);
        setMedia(handleObject(object));
    }


    public void loadImage(Object object, Transformation transformation) {
        executePicasso(handleObject(object), transformation);
        setMedia(handleObject(object));
    }


    public void loadImages(List<Object> objectList) {
        setMedia(getMediaList(objectList), 0, "en");
    }


    public void loadImages(List<Object> objectList, int currentPosition) {
        setMedia(getMediaList(objectList), currentPosition, "en");
    }


    public void loadImages(List<Object> objectList, int currentPosition, String appLanguage) {
        setMedia(getMediaList(objectList), currentPosition, appLanguage);
    }


    private List<String> getMediaList(List<Object> objectList) {
        List<String> stringList = new ArrayList<>();
        for (Object o : objectList) {
            stringList.add(handleObject(o));
        }
        return stringList;
    }


    private String handleObject(Object object) {
        String urlPath = null;
        try {
            if (object != null) {
                if (object instanceof String) {
                    String s = (String) object;
                    if (!s.contains("http"))
                        urlPath = "file://" + s;
                    else
                        urlPath = s;

                } else if (object instanceof File) {
                    File file = (File) object;
                    urlPath = file.getPath();

                } else if (object instanceof Media) {
                    Media media = (Media) object;
                    if (media.getType().equals(Media.TYPE_VIDEO))
                        urlPath = YouTube_Thumb.concat(media.getPath().substring(media.getPath().indexOf("=") + 1)).concat("/0.jpg");
                    else {
                        if (!media.getPath().contains("http"))
                            urlPath = "file://" + media.getPath();
                        else
                            urlPath = media.getPath();
                    }
                }
            } else
                this.setImageResource(defaultImageRes);

        } catch (NullPointerException ignored) {
            this.setImageResource(defaultImageRes);
        }

        return urlPath;
    }


    protected void executePicasso(String urlPath, Transformation transformation) {
        if (transformation != null)
            Picasso.get().load(urlPath).transform(transformation).into(new PicassoTarget(this, urlPath, transformation));
        else
            Picasso.get().load(urlPath).into(new PicassoTarget(this, urlPath));
    }


    private void setMedia(String media) {
        onImageClickListener.setMedia(media);
    }


    private void setMedia(List<String> mediaList, int currentPosition, String appLanguage) {
        onImageClickListener.setMedia(mediaList, currentPosition, appLanguage);
    }


    public void show() {
        onImageClickListener.onClick(this);
    }

}