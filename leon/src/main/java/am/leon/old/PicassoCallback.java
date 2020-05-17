//package am.leon.old;
//
//import android.view.View;
//
//import com.squareup.picasso.Callback;
//import com.squareup.picasso.Transformation;
//
//import am.leon.LeonImageView;
//
//public class PicassoCallback implements Callback, View.OnClickListener {
//
//    private String urlPath;
//    private LeonImageView imageView;
//    private Transformation transformation;
//
//
//    PicassoCallback(LeonImageView imageView, String urlPath) {
//        this(imageView, urlPath, null);
//    }
//
//
//    PicassoCallback(LeonImageView imageView, String urlPath, Transformation transformation) {
//        this.transformation = transformation;
//        this.imageView = imageView;
//        this.urlPath = urlPath;
//        viewInit();
//    }
//
//
//    private void viewInit() {
//        imageView.setScaleX(.2f);
//        imageView.setScaleY(.2f);
//
//    }
//
//
//    @Override
//    public void onSuccess() {
//        imageView.setScaleX(1);
//        imageView.setScaleY(1);
//    }
//
//
//    @Override
//    public void onError(Exception e) {
//        imageView.setZoomEnabled(false);
//        imageView.setOnClickListener(this);
//    }
//
//
//    @Override
//    public void onClick(View view) {
//        if (transformation != null)
//            imageView.loadImage(urlPath, transformation);
//        else
//            imageView.loadImage(urlPath);
//    }
//
//}