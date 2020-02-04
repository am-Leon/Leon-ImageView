package am.leon;

import android.view.View;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Transformation;

public class PicassoCallback implements Callback, View.OnClickListener {

    private String urlPath;
    private boolean fromFullScreen = false;
    private LeonImageView imageView;
    private Transformation transformation;


    PicassoCallback(LeonImageView imageView, String urlPath) {
        this(imageView, urlPath, false);
    }


    PicassoCallback(LeonImageView imageView, String urlPath, boolean fromFullScreen) {
        this(imageView, urlPath, null);
        this.fromFullScreen = fromFullScreen;
        viewInit();
    }


    PicassoCallback(LeonImageView imageView, String urlPath, Transformation transformation) {
        this.transformation = transformation;
        this.imageView = imageView;
        this.urlPath = urlPath;
    }


    private void viewInit() {
        if (fromFullScreen) {
            imageView.setScaleX(.1f);
            imageView.setScaleY(.1f);
        } else {
            imageView.setScaleX(.5f);
            imageView.setScaleY(.5f);
        }
    }


    @Override
    public void onSuccess() {
        if (fromFullScreen)
            imageView.setZoomEnabled(true);
        imageView.setScaleX(1);
        imageView.setScaleY(1);
    }


    @Override
    public void onError(Exception e) {
        imageView.setZoomEnabled(false);
        imageView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (transformation != null)
            imageView.loadImage(urlPath, transformation);
        else
            imageView.loadImage(urlPath, fromFullScreen);
    }

}