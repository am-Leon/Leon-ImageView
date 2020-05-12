package am.leon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context context;
    private List<Media> items;
    private String deviceLanguage;


    public GalleryAdapter(Context context, String deviceLanguage) {
        this.context = context;
        this.deviceLanguage = deviceLanguage;
        this.items = new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Media media = items.get(position);

        holder.item_image.loadImage(media);
    }


    public void addAll(List<Media> items) {
        for (Media result : items) {
            add(result);
        }
    }


    private void add(Media r) {
        items.add(r);
        notifyItemInserted(items.size() - 1);
    }


    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LeonImageView item_image;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.img_itemPhoto);
            item_image.setOnClickListener(this);
            itemView.setOnClickListener(this);

//            item_image.setReloadImageRes(R.drawable.ic_delete_sweep_black_24dp);
        }

        @Override
        public void onClick(View v) {
            item_image.loadMediaImages(items, getAdapterPosition(), deviceLanguage);
            item_image.show();
        }
    }

}