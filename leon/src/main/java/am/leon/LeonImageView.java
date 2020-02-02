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
    }


    private void setTypedArrayValues(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LeonImageView, defStyle, 0);

        reloadImageRes = (typedArray.getResourceId(R.styleable.LeonImageView_reload_icon, R.drawable.layer_reload));

        defaultImageRes = (typedArray.getResourceId(R.styleable.LeonImageView_default_icon, R.drawable.ic_default));

        videoPlayImageRes = (typedArray.getResourceId(R.styleable.LeonImageView_video_play_icon, R.drawable.ic_play_colored));

        placeHolderImageRes = (typedArray.getResourceId(R.styleable.LeonImageView_place_holder_icon, R.drawable.layer_place_holder));
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


    //-----------------------------------------ListObjectsMethods-----------------------------------


    public void loadObjectImages(List<Object> objectList) {
        setMedia(getObjectList(objectList), 0, "en");
    }


    public void loadObjectImages(List<Object> objectList, int currentPosition) {
        setMedia(getObjectList(objectList), currentPosition, "en");
    }


    public void loadObjectImages(List<Object> objectList, int currentPosition, String appLanguage) {
        setMedia(getObjectList(objectList), currentPosition, appLanguage);
    }


    private List<String> getObjectList(List<Object> objectList) {
        List<String> stringList = new ArrayList<>();
        for (Object o : objectList) {
            stringList.add(handleObject(o));
        }
        return stringList;
    }

    //-----------------------------------------ListMediaMethods-------------------------------------


    public void loadMediaImages(List<Media> mediaList) {
        setMedia(getMediaList(mediaList), 0, "en");
    }


    public void loadMediaImages(List<Media> mediaList, int currentPosition) {
        setMedia(getMediaList(mediaList), currentPosition, "en");
    }


    public void loadMediaImages(List<Media> mediaList, int currentPosition, String appLanguage) {
        setMedia(getMediaList(mediaList), currentPosition, appLanguage);
    }


    private List<String> getMediaList(List<Media> objectList) {
        List<String> stringList = new ArrayList<>();
        for (Media o : objectList) {
            stringList.add(handleObject(o));
        }
        return stringList;
    }

    //-----------------------------------------ListFilesMethods-------------------------------------


    public void loadFileImages(List<File> fileList) {
        setMedia(getFileList(fileList), 0, "en");
    }


    public void loadFileImages(List<File> fileList, int currentPosition) {
        setMedia(getFileList(fileList), currentPosition, "en");
    }


    public void loadFileImages(List<File> fileList, int currentPosition, String appLanguage) {
        setMedia(getFileList(fileList), currentPosition, appLanguage);
    }


    private List<String> getFileList(List<File> objectList) {
        List<String> stringList = new ArrayList<>();
        for (File o : objectList) {
            stringList.add(handleObject(o));
        }
        return stringList;
    }

    //-----------------------------------------LeonImageMethods-------------------------------------


    public void loadImages(List<String> stringList) {
        setMedia(getStringList(stringList), 0, "en");
    }


    public void loadImages(List<String> stringList, int currentPosition) {
        setMedia(getStringList(stringList), currentPosition, "en");
    }


    public void loadImages(List<String> stringList, int currentPosition, String appLanguage) {
        setMedia(getStringList(stringList), currentPosition, appLanguage);
    }


    private List<String> getStringList(List<String> objectList) {
        List<String> stringList = new ArrayList<>();
        for (String o : objectList) {
            stringList.add(handleObject(o));
        }
        return stringList;
    }

    //-----------------------------------------LeonImageMethods-------------------------------------


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


    private void executePicasso(String urlPath, Transformation transformation) {
        if (transformation != null)
            Picasso.get().load(urlPath)
                    .placeholder(getPlaceHolderImageRes())
                    .error(getReloadImageRes())
                    .transform(transformation)
                    .into(this, new PicassoCallback(this, urlPath, transformation));
        else
            Picasso.get().load(urlPath)
                    .placeholder(getPlaceHolderImageRes())
                    .error(getReloadImageRes())
                    .into(this, new PicassoCallback(this, urlPath));
    }


    protected void executePicasso(String urlPath) {
        Picasso.get().load(urlPath)
                .placeholder(getPlaceHolderImageRes())
                .error(getReloadImageRes())
                .into(this, new PicassoCallback(this, urlPath, true));
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