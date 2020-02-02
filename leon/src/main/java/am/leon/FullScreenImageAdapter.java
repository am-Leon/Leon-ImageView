package am.leon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

class FullScreenImageAdapter extends PagerAdapter implements View.OnClickListener {

    private View view;
    private Context context;
    private List<String> list;
    private String appLanguage;
    private int currentPosition;
    private AppCompatImageView ic_video;
    private LeonImageView imageView;


    FullScreenImageAdapter(Context context, String appLanguage) {
        this.context = context;
        this.list = new ArrayList<>();
        this.appLanguage = appLanguage;
    }


    void setMediaList(List<String> list, int currentPosition) {
        this.list = list;
        this.currentPosition = currentPosition;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater != null ? inflater.inflate(R.layout.item_media, container, false) : null;
        viewInit();

        String media = list.get(position);
        imageView.executePicasso(media, null);

        if (media != null && media.contains(Utils.YouTube_Thumb)) {
            ic_video.setVisibility(View.VISIBLE);
            imageView.setOnClickListener(this);
        } else {
            ic_video.setVisibility(View.GONE);
            imageView.setOnClickListener(null);
            imageView.setZoomEnabled(true);
        }

        container.addView(view);
        return view;
    }


    private void viewInit() {
        imageView = view.findViewById(R.id.image);
        ic_video = view.findViewById(R.id.videoImage);

        if (appLanguage != null) {
            if (appLanguage.equals("ar"))
                imageView.setRotationY(180);
        }
    }


    @Override
    public void onClick(View view) {
        String media = list.get(currentPosition);

        Utils.youtubePlay(context, media.substring(media.indexOf("=") + 1));
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
