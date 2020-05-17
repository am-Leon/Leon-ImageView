package am.leon;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.File;

class LeonMedia implements Parcelable {

    enum LeonMediaType {URI, MEDIA, FILE, STRING, RESOURCE}

    private Object object;
    private LeonMediaType type;

    private LeonMedia() {
    }

    private LeonMedia(Object object, LeonMediaType type) {
        this.object = object;
        this.type = type;
    }


    static LeonMedia getLeonMedia(Object o) throws Exception {
        LeonMedia leonMedia = new LeonMedia();
        if (o != null) {
            if (o instanceof Uri)
                leonMedia = new LeonMedia(o, LeonMedia.LeonMediaType.URI);

            else if (o instanceof Media)
                leonMedia = new LeonMedia(o, LeonMedia.LeonMediaType.MEDIA);

            else if (o instanceof File)
                leonMedia = new LeonMedia(o, LeonMedia.LeonMediaType.FILE);

            else if (o instanceof String)
                leonMedia = new LeonMedia(o, LeonMedia.LeonMediaType.STRING);

            else if (o instanceof Integer)
                leonMedia = new LeonMedia(o, LeonMedia.LeonMediaType.RESOURCE);

            else throw new Exception("type not supported");
        }
        return leonMedia;
    }


    private LeonMedia(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LeonMedia> CREATOR = new Creator<LeonMedia>() {
        @Override
        public LeonMedia createFromParcel(Parcel in) {
            return new LeonMedia(in);
        }

        @Override
        public LeonMedia[] newArray(int size) {
            return new LeonMedia[size];
        }
    };

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public LeonMediaType getType() {
        return type;
    }

    public void setType(LeonMediaType type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return "LeonMediaType " + getType() + " Object " + getObject();
    }

}