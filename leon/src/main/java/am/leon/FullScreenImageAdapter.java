package am.leon;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

class FullScreenImageAdapter extends RecyclerView.Adapter<FullScreenImageAdapter.ViewHolder> {

    private Context context;
    private LeonObject leonObject;
    private ImageZoomCallback zoomCallback;


    FullScreenImageAdapter(Context context, LeonObject leonObject, ImageZoomCallback zoomCallback) {
        this.context = context;
        this.leonObject = leonObject;
        this.zoomCallback = zoomCallback;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_media, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LeonMedia leonMedia = leonObject.getLeonMedia().get(position);

        holder.imageView.setScaleX(.1f);
        holder.imageView.setScaleY(.1f);
        holder.imageView.setZoomEnabled(false);

        holder.handleLeonMedia(leonMedia);

    }


    @Override
    public int getItemCount() {
        return leonObject.getLeonMedia().size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements TouchImageView.OnTouchImageViewListener, View.OnClickListener, Callback {

        private LeonImageView imageView;
        private AppCompatImageView ic_video;
        private boolean loadingError = false;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ic_video = itemView.findViewById(R.id.videoImage);
            imageView = itemView.findViewById(R.id.image);
            imageView.setOnTouchImageViewListener(this);
            imageView.setOnClickListener(null);

            imageView.setPlaceHolderImageRes(leonObject.getPlaceHolderImageRes());
            imageView.setDefaultImageRes(leonObject.getDefaultImageRes());
            imageView.setReloadImageRes(leonObject.getReloadImageRes());
            ic_video.setImageResource(leonObject.getVideoPlayImageRes());
        }


        @Override
        public void onMove() {
            if (zoomCallback != null)
                zoomCallback.isZoomed(imageView.isZoomed());
        }


        private void handleLeonMedia(LeonMedia leonMedia) {
            switch (leonMedia.getType()) {
                case URI:
                    executePicasso((Uri) leonMedia.getObject());
                    break;

                case MEDIA:
                    executePicasso(Utils.getMediaPath((Media) leonMedia.getObject()));
                    break;

                case FILE:
                    executePicasso((File) leonMedia.getObject());
                    break;

                case STRING:
                    String path = (String) leonMedia.getObject();
                    if (path.contains("http"))
                        executePicasso(path);
                    else
                        executePicasso("file://" + path);
                    break;

                case RESOURCE:
                    imageView.setImageResource((Integer) leonMedia.getObject());
                    break;
            }
        }


        private void handleMediaObject(Media media) {
            if (media.getType().equals(Media.TYPE_VIDEO)) {
                ic_video.setVisibility(View.VISIBLE);
                imageView.setOnClickListener(this);
                imageView.setZoomEnabled(false);
            } else
                ic_video.setVisibility(View.GONE);
        }


        private void executePicasso(Uri uri) {
            Picasso.get()
                    .load(uri)
                    .placeholder(leonObject.getPlaceHolderImageRes())
                    .error(leonObject.getReloadImageRes())
                    .into(imageView, this);
        }


        private void executePicasso(File file) {
            Picasso.get()
                    .load(file)
                    .placeholder(leonObject.getPlaceHolderImageRes())
                    .error(leonObject.getReloadImageRes())
                    .into(imageView, this);
        }


        private void executePicasso(String path) {
            Picasso.get()
                    .load(path)
                    .placeholder(leonObject.getPlaceHolderImageRes())
                    .error(leonObject.getReloadImageRes())
                    .into(imageView, this);
        }


        @Override
        public void onSuccess() {
            loadingError = false;
            imageView.setScaleX(1);
            imageView.setScaleY(1);
            imageView.setZoomEnabled(true);

            try {
                LeonMedia leonMedia = leonObject.getLeonMedia().get(getAdapterPosition());
                if (leonMedia.getType().equals(LeonMedia.LeonMediaType.MEDIA))
                    handleMediaObject((Media) leonMedia.getObject());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }


        @Override
        public void onError(Exception e) {
            loadingError = true;
            imageView.setScaleX(.1f);
            imageView.setScaleY(.1f);
            imageView.setZoomEnabled(false);
            imageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            LeonMedia leonMedia = leonObject.getLeonMedia().get(getAdapterPosition());
            if (loadingError)
                handleLeonMedia(leonMedia);
            else {
                if (leonMedia.getType().equals(LeonMedia.LeonMediaType.MEDIA)) {
                    Media media = (Media) leonMedia.getObject();
                    if (media.getPath() != null)
                        Utils.youtubePlay(context, media.getPath().substring(media.getPath().indexOf("=") + 1));
                }
            }
        }

    }


    interface ImageZoomCallback {
        void isZoomed(boolean isZoomed);
    }

}