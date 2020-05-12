package am.leon.old;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.picasso.Transformation;

import am.leon.LeonImageView;

class CallbackModel implements Parcelable {

    private String urlPath;
    private LeonImageView imageView;
    private Transformation transformation;

    public CallbackModel() {
    }

    private CallbackModel(Parcel in) {
        urlPath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(urlPath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CallbackModel> CREATOR = new Creator<CallbackModel>() {
        @Override
        public CallbackModel createFromParcel(Parcel in) {
            return new CallbackModel(in);
        }

        @Override
        public CallbackModel[] newArray(int size) {
            return new CallbackModel[size];
        }
    };

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public LeonImageView getImageView() {
        return imageView;
    }

    public void setImageView(LeonImageView imageView) {
        this.imageView = imageView;
    }

    public Transformation getTransformation() {
        return transformation;
    }

    public void setTransformation(Transformation transformation) {
        this.transformation = transformation;
    }
}
