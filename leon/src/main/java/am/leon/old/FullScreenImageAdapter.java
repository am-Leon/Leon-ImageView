//package am.leon;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentStatePagerAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FullScreenImageAdapter extends FragmentStatePagerAdapter {
//
//    private List<String> list;
//    private String appLanguage;
//    private SingleImageFragment.ImageZoomCallback zoomCallback;
//
//
//    FullScreenImageAdapter(@NonNull FragmentManager fm, int behavior) {
//        super(fm, behavior);
//        list = new ArrayList<>();
//    }
//
//    void setPageSetting(String appLanguage, SingleImageFragment.ImageZoomCallback zoomCallback) {
//        this.appLanguage = appLanguage;
//        this.zoomCallback = zoomCallback;
//    }
//
//    void setStringList(List<String> list) {
//        this.list = list;
//        notifyDataSetChanged();
//    }
//
//    void setStringList(List<String> list, int currentPosition) {
//        this.list = list;
//        notifyDataSetChanged();
//        getItem(currentPosition);
//    }
//
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        return SingleImageFragment.getInstance(list.get(position), appLanguage, zoomCallback);
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//}