package am.leon;

import android.view.View;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Transformation;

public class PicassoCallback implements Callback, View.OnClickListener {

    private LeonMedia leonMedia;
    private LeonImageView imageView;
    private Transformation transformation;


    PicassoCallback(LeonImageView imageView, LeonMedia leonMedia) {
        this(imageView, leonMedia, null);
    }


    PicassoCallback(LeonImageView imageView, LeonMedia leonMedia, Transformation transformation) {
        this.transformation = transformation;
        this.imageView = imageView;
        this.leonMedia = leonMedia;
        viewInit();
    }


    private void viewInit() {
        imageView.setScaleX(.2f);
        imageView.setScaleY(.2f);

    }


    @Override
    public void onSuccess() {
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
            imageView.loadImage(leonMedia.getObject(), transformation);
        else
            imageView.loadImage(leonMedia.getObject());
    }

}