package am.leon;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Media implements Parcelable {

    public static final String TYPE_PHOTO = "photo";
    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_FILE = "file";

    private int id;
    private String path;
    private String type;
    private String title;
    private String description;
    private boolean main;

    public Media(String path, String type) {
        this.path = path;
        this.type = type;
    }

    public Media(String path, String title, String type) {
        this.path = path;
        this.title = title;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return type.equals(media.type) &&
                Objects.equals(title, media.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title);
    }

    private Media(Parcel in) {
        id = in.readInt();
        path = in.readString();
        type = in.readString();
        title = in.readString();
        description = in.readString();
        main = in.readByte() != 0;
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(path);
        dest.writeString(type);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeByte((byte) (main ? 1 : 0));
    }

    @NonNull
    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", main=" + main +
                '}';
    }

}