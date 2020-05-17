package am.leon;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class LeonObject implements Parcelable {

    private String appLanguage;
    private int currentPosition;
    private List<Uri> uriList;
    private List<Media> mediaList;
    private List<File> fileList;
    private List<String> stringList;
    private List<Integer> resList;
    private int reloadImageRes, defaultImageRes, videoPlayImageRes, placeHolderImageRes;

    LeonObject() {
        uriList = new ArrayList<>();
        mediaList = new ArrayList<>();
        fileList = new ArrayList<>();
        stringList = new ArrayList<>();
        resList = new ArrayList<>();
    }

    //----------------------------------------------------------------------------------------------

    public void clear() {
        uriList.clear();
        mediaList.clear();
        fileList.clear();
        stringList.clear();
        resList.clear();
        reloadImageRes = defaultImageRes =
                videoPlayImageRes = placeHolderImageRes = 0;
    }

    public void clearImages() {
        uriList.clear();
        mediaList.clear();
        fileList.clear();
        stringList.clear();
        resList.clear();
    }

    List<LeonMedia> getLeonMedia() {
        List<LeonMedia> list = new ArrayList<>();
        try {
            if (getUriList().size() != 0) {
                for (Uri uri : getUriList())
                    list.add(LeonMedia.getLeonMedia(uri));

            } else if (getMediaList().size() != 0) {
                for (Media media : getMediaList())
                    list.add(LeonMedia.getLeonMedia(media));

            } else if (getFileList().size() != 0) {
                for (File file : getFileList())
                    list.add(LeonMedia.getLeonMedia(file));

            } else if (getStringList().size() != 0) {
                for (String path : getStringList())
                    list.add(LeonMedia.getLeonMedia(path));

            } else if (getResList().size() != 0) {
                for (Integer res : getResList())
                    list.add(LeonMedia.getLeonMedia(res));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    //----------------------------------------------------------------------------------------------


    private LeonObject(Parcel in) {
        appLanguage = in.readString();
        currentPosition = in.readInt();
        uriList = in.createTypedArrayList(Uri.CREATOR);
        mediaList = in.createTypedArrayList(Media.CREATOR);
        stringList = in.createStringArrayList();
        reloadImageRes = in.readInt();
        defaultImageRes = in.readInt();
        videoPlayImageRes = in.readInt();
        placeHolderImageRes = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(appLanguage);
        dest.writeInt(currentPosition);
        dest.writeTypedList(uriList);
        dest.writeTypedList(mediaList);
        dest.writeStringList(stringList);
        dest.writeInt(reloadImageRes);
        dest.writeInt(defaultImageRes);
        dest.writeInt(videoPlayImageRes);
        dest.writeInt(placeHolderImageRes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LeonObject> CREATOR = new Creator<LeonObject>() {
        @Override
        public LeonObject createFromParcel(Parcel in) {
            return new LeonObject(in);
        }

        @Override
        public LeonObject[] newArray(int size) {
            return new LeonObject[size];
        }
    };


    String getAppLanguage() {
        return appLanguage;
    }

    void setAppLanguage(String appLanguage) {
        this.appLanguage = appLanguage;
    }

    int getCurrentPosition() {
        return currentPosition;
    }

    void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    void setImage(Uri uri) {
        uriList.add(uri);
    }

    List<Uri> getUriList() {
        return uriList;
    }

    void setUriList(List<Uri> uriList) {
        this.uriList = uriList;
    }

    void setImage(Media media) {
        mediaList.add(media);
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    void setImage(File file) {
        fileList.add(file);
    }

    List<File> getFileList() {
        return fileList;
    }

    void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    void setImage(String path) {
        stringList.add(path);
    }

    List<String> getStringList() {
        return stringList;
    }

    void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    void setImage(int res) {
        resList.add(res);
    }

    List<Integer> getResList() {
        return resList;
    }

    void setResList(List<Integer> resList) {
        this.resList = resList;
    }

    int getReloadImageRes() {
        return reloadImageRes;
    }

    void setReloadImageRes(int reloadImageRes) {
        this.reloadImageRes = reloadImageRes;
    }

    int getDefaultImageRes() {
        return defaultImageRes;
    }

    void setDefaultImageRes(int defaultImageRes) {
        this.defaultImageRes = defaultImageRes;
    }

    int getVideoPlayImageRes() {
        return videoPlayImageRes;
    }

    void setVideoPlayImageRes(int videoPlayImageRes) {
        this.videoPlayImageRes = videoPlayImageRes;
    }

    int getPlaceHolderImageRes() {
        return placeHolderImageRes;
    }

    void setPlaceHolderImageRes(int placeHolderImageRes) {
        this.placeHolderImageRes = placeHolderImageRes;
    }
}