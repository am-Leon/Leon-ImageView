package am.leon;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

class OnImageClickListener implements View.OnClickListener, FullScreenPhotoFragment.FullScreenStatus {

    private View view;
    private String media;
    private Context context;
    private String appLanguage;
    private int currentPosition;
    private List<String> mediaList;


    OnImageClickListener(Context context) {
        this.context = context;
        this.mediaList = new ArrayList<>();
    }


    void setMedia(String media) {
        this.mediaList.clear();
        this.media = media;
    }


    void setMedia(List<String> mediaList, int currentPosition, String appLanguage) {
        this.media = null;
        this.mediaList = mediaList;
        this.currentPosition = currentPosition;
        this.appLanguage = appLanguage;
    }


    @Override
    public void onClick(View view) {
        this.view = view;
        if (media != null)
            FullScreenPhotoFragment.getInstance(media, this).show(((AppCompatActivity) context).getSupportFragmentManager(), getClass().getName());
        else if (mediaList != null)
            FullScreenPhotoFragment.getInstance(mediaList, currentPosition, appLanguage, this).show(((AppCompatActivity) context).getSupportFragmentManager(), getClass().getName());
    }


    @Override
    public void fullScreenStatus(boolean status) {
        if (status)
            view.setEnabled(false);
        else
            view.setEnabled(true);
    }
}