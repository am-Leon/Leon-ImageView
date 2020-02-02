package am.leon;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

public class PicassoTarget implements Target, View.OnClickListener {

    private String urlPath;
    private LeonImageView imageView;
    private Transformation transformation;


    PicassoTarget(LeonImageView imageView, String urlPath) {
        this.imageView = imageView;
        this.urlPath = urlPath;
    }


    PicassoTarget(LeonImageView imageView, String urlPath, Transformation transformation) {
        this.transformation = transformation;
        this.imageView = imageView;
        this.urlPath = urlPath;
    }


    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        imageView.setLayoutParams(getNormalView());
        imageView.setImageBitmap(bitmap);
        imageView.setZoomEnabled(true);
    }


    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
        imageView.setImageResource(imageView.getReloadImageRes());
        imageView.setOnClickListener(this);
        viewInit();
    }


    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        imageView.setImageResource(imageView.getPlaceHolderImageRes());
        imageView.setOnClickListener(null);
        viewInit();
    }


    private ViewGroup.LayoutParams getSmallView() {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = Utils.convertDpToPixel(imageView.getContext(), 40);
        params.width = Utils.convertDpToPixel(imageView.getContext(), 40);
        return params;
    }


    private ViewGroup.LayoutParams getNormalView() {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        return params;
    }


    @Override
    public void onClick(View view) {
        if (transformation != null)
            imageView.loadImage(urlPath, transformation);
        else
            imageView.loadImage(urlPath);
    }


    private void viewInit() {
        imageView.setLayoutParams(getSmallView());
        imageView.setZoomEnabled(false);
    }

}