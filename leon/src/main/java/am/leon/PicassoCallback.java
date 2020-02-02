package am.leon;

import android.view.View;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Transformation;

public class PicassoCallback implements Callback, View.OnClickListener {

    private String urlPath;
    private boolean fromFullScreen;
    private LeonImageView imageView;
    private Transformation transformation;


    PicassoCallback(LeonImageView imageView, String urlPath) {
        this.imageView = imageView;
        this.urlPath = urlPath;
    }


    PicassoCallback(LeonImageView imageView, String urlPath, boolean fromFullScreen) {
        this.fromFullScreen = fromFullScreen;
        this.imageView = imageView;
        this.urlPath = urlPath;
    }


    PicassoCallback(LeonImageView imageView, String urlPath, Transformation transformation) {
        this.transformation = transformation;
        this.imageView = imageView;
        this.urlPath = urlPath;
    }


    @Override
    public void onSuccess() {
        if (fromFullScreen)
            imageView.setZoomEnabled(true);
    }

    @Override
    public void onError(Exception e) {
        imageView.setZoomEnabled(false);
        imageView.setOnClickListener(this);
        imageView.setImageResource(imageView.getReloadImageRes());
    }


    @Override
    public void onClick(View view) {
        if (transformation != null)
            imageView.loadImage(urlPath, transformation);
        else
            imageView.loadImage(urlPath);
    }

}