package am.leon;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

class LeonMedia implements Parcelable {

    enum LeonMediaType {URI, MEDIA, FILE, STRING, RESOURCE}

    private Object object;
    private LeonMediaType type;

    LeonMedia() {
    }

    LeonMedia(Object object, LeonMediaType type) {
        this.object = object;
        this.type = type;
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