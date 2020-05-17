package am.leon;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import java.io.File;
import java.util.List;

import static am.leon.Utils.unwrap;

class OnImageClickListener implements View.OnClickListener, FullScreenPhotoFragment.FullScreenStatus {

    private View view;
    private Context context;
    private LeonObject leonObject;


    OnImageClickListener(Context context) {
        this.context = context;
        this.leonObject = new LeonObject();
    }

    void setImage(LeonMedia leonMedia) {
        switch (leonMedia.getType()) {
            case URI:
                setImage((Uri) leonMedia.getObject());
                break;

            case MEDIA:
                setImage((Media) leonMedia.getObject());
                break;

            case FILE:
                setImage((File) leonMedia.getObject());
                break;

            case STRING:
                setImage((String) leonMedia.getObject());
                break;

            case RESOURCE:
                setImage((Integer) leonMedia.getObject());
                break;
        }
    }


    private void setImage(Uri uri) {
        leonObject.getUriList().clear();
        leonObject.setImage(uri);
    }


    private void setImage(Media media) {
        leonObject.getMediaList().clear();
        leonObject.setImage(media);
    }


    private void setImage(File file) {
        leonObject.getFileList().clear();
        leonObject.setImage(file);
    }


    private void setImage(String path) {
        leonObject.getStringList().clear();
        leonObject.setImage(path);
    }


    private void setImage(int res) {
        leonObject.getResList().clear();
        leonObject.setImage(res);
    }


    void setUriImages(List<Uri> uriList, int currentPosition, String appLanguage) {
        leonObject.setUriList(uriList);
        leonObject.setCurrentPosition(currentPosition);
        leonObject.setAppLanguage(appLanguage);
    }


    void setMediaImages(List<Media> mediaList, int currentPosition, String appLanguage) {
        leonObject.setMediaList(mediaList);
        leonObject.setCurrentPosition(currentPosition);
        leonObject.setAppLanguage(appLanguage);
    }


    void setFileImages(List<File> fileList, int currentPosition, String appLanguage) {
        leonObject.setFileList(fileList);
        leonObject.setCurrentPosition(currentPosition);
        leonObject.setAppLanguage(appLanguage);
    }


    void setStringImages(List<String> stringList, int currentPosition, String appLanguage) {
        leonObject.setStringList(stringList);
        leonObject.setCurrentPosition(currentPosition);
        leonObject.setAppLanguage(appLanguage);
    }


    void setResImages(List<Integer> resList, int currentPosition, String appLanguage) {
        leonObject.setResList(resList);
        leonObject.setCurrentPosition(currentPosition);
        leonObject.setAppLanguage(appLanguage);
    }


    @Override
    public void onClick(View view) {
        this.view = view;
        FullScreenPhotoFragment.getInstance(leonObject, this).show(unwrap(context).getSupportFragmentManager(), getClass().getName());
    }


    @Override
    public void fullScreenStatus(boolean status) {
        if (status)
            view.setEnabled(false);
        else
            view.setEnabled(true);
    }


    void setReloadImageRes(int reloadImageRes) {
        leonObject.setReloadImageRes(reloadImageRes);
    }


    void setDefaultImageRes(int defaultImageRes) {
        leonObject.setDefaultImageRes(defaultImageRes);
    }


    void setVideoPlayImageRes(int videoPlayImageRes) {
        leonObject.setVideoPlayImageRes(videoPlayImageRes);
    }


    void setPlaceHolderImageRes(int placeHolderImageRes) {
        leonObject.setPlaceHolderImageRes(placeHolderImageRes);
    }

}