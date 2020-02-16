package am.leon;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

public class SingleImageFragment extends Fragment implements View.OnClickListener, TouchImageView.OnTouchImageViewListener {

    private View view;
    private Context context;
    private String imagePath;
    private String appLanguage;

    private LeonImageView imageView;
    private AppCompatImageView ic_video;
    private static ImageZoomCallback imageZoomCallback;
    private static final String IMAGE_TAG = "IMAGE_TAG";
    private static final String APP_LANGUAGE = "APP_LANGUAGE";


    public SingleImageFragment() {
    }


    static SingleImageFragment getInstance(String imagePath, String appLanguage, ImageZoomCallback zoomCallback) {
        SingleImageFragment fragment = new SingleImageFragment();
        Bundle bundle = new Bundle();
        imageZoomCallback = zoomCallback;
        bundle.putString(IMAGE_TAG, imagePath);
        bundle.putString(APP_LANGUAGE, appLanguage);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imagePath = getArguments().getString(IMAGE_TAG);
            appLanguage = getArguments().getString(APP_LANGUAGE);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_media, container, false);
        viewInit();

        if (imagePath != null && imagePath.contains(Utils.YouTube_Thumb)) {
            ic_video.setVisibility(View.VISIBLE);
            imageView.setOnClickListener(this);
        } else {
            ic_video.setVisibility(View.GONE);
            imageView.setOnClickListener(null);
        }

        imageView.executePicasso(imagePath, true);

        return view;
    }


    private void viewInit() {
        context = getContext();
        imageView = view.findViewById(R.id.image);
        imageView.setOnTouchImageViewListener(this);
        ic_video = view.findViewById(R.id.videoImage);

        if (appLanguage != null) {
            if (appLanguage.equals("ar"))
                imageView.setRotationY(180);
        }
    }


    @Override
    public void onClick(View view) {
        Utils.youtubePlay(context, imagePath.substring(imagePath.indexOf("=") + 1));
    }


    @Override
    public void onMove() {
        if (imageZoomCallback != null)
            imageZoomCallback.isZoomed(imageView.isZoomed());
    }


    interface ImageZoomCallback {
        void isZoomed(boolean isZoomed);
    }

}