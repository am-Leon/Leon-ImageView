package am.leon;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.List;

import static am.leon.Utils.DEFAULT_IMAGE_RESOURCE;
import static am.leon.Utils.PLACE_HOLDER_IMAGE_RESOURCE;
import static am.leon.Utils.YouTube_Thumb;

public class LeonImageView extends TouchImageView {

    private OnImageClickListener onImageClickListener;  // to setCustom OnClickListener
    private int defaultImageRes = DEFAULT_IMAGE_RESOURCE; // to get default image res
    private int placeHolderImageRes = PLACE_HOLDER_IMAGE_RESOURCE; // to get placeHolder image res


    public LeonImageView(Context context) {
        this(context, null);
    }


    public LeonImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeonImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        onImageClickListener = new OnImageClickListener(context);
        setOnClickListener(onImageClickListener);
        setZoomEnabled(false);
    }


    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        if (l == null)
            super.setOnClickListener(null);
        else
            super.setOnClickListener(l);
    }


    public int getDefaultImageRes() {
        return defaultImageRes;
    }


    public void setDefaultImageRes(int defaultImageRes) {
        this.defaultImageRes = defaultImageRes;
    }


    public int getPlaceHolderImageRes() {
        return placeHolderImageRes;
    }


    public void setPlaceHolderImageRes(int placeHolderImageRes) {
        this.placeHolderImageRes = placeHolderImageRes;
    }


    public void loadProfileImage(Media media, Transformation transformation) {
        try {
            Picasso.get().load(media.getPath()).placeholder(placeHolderImageRes).transform(transformation).into(this);
        } catch (NullPointerException ignored) {
            this.setImageResource(defaultImageRes);
        }
    }


    public void loadProfileImage(String url, Transformation transformation) {
        try {
            if (!url.isEmpty())
                Picasso.get().load(url).placeholder(placeHolderImageRes).transform(transformation).into(this);
            else
                this.setImageResource(defaultImageRes);
        } catch (NullPointerException ignored) {
            this.setImageResource(defaultImageRes);
        }
    }


    public void loadProfileImage(File file, Transformation transformation) {
        try {
            if (!file.getPath().isEmpty())
                Picasso.get().load(file).placeholder(placeHolderImageRes).transform(transformation).into(this);
            else
                this.setImageResource(defaultImageRes);
            Picasso.get().load(file).transform(transformation).into(this);
        } catch (NullPointerException ignored) {
            this.setImageResource(defaultImageRes);
        }
    }


    public void loadImage(Media media) {
        try {
            if (media.getType().equals(Media.TYPE_VIDEO))
                Picasso.get().load(YouTube_Thumb.concat(media.getPath().substring(media.getPath().indexOf("=") + 1)).concat("/0.jpg")).into(this);
            else {
                if (media.getPath().contains("http"))
                    Picasso.get().load(media.getPath()).placeholder(placeHolderImageRes).into(this);
                else
                    Picasso.get().load("file://" + media.getPath()).placeholder(placeHolderImageRes).into(this);
            }
        } catch (NullPointerException ignored) {
            this.setImageResource(defaultImageRes);
        }
    }


    public void setMedia(Media media) {
        onImageClickListener.setMedia(media);
    }


    public void setMedia(List<Media> mediaList, int currentPosition, String appLanguage) {
        onImageClickListener.setMedia(mediaList, currentPosition, appLanguage);
    }


    public void show() {
        onImageClickListener.onClick(this);
    }

}