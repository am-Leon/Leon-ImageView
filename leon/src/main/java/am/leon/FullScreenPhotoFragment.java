package am.leon;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import am.leon.swipePackage.SwipeDialogFragment;

public class FullScreenPhotoFragment extends SwipeDialogFragment {

    private View view;
    private int position;
    private String appLanguage;
    private ViewPager viewpager;
    private FullScreenImageAdapter adapter;
    private FullScreenStatus fullScreenStatus;
    private List<Media> mediaList = new ArrayList<>();
    private static final String MEDIA_TAG = "media_tag";
    private static final String APP_LANGUAGE = "appLanguage";
    private static final String MEDIA_LIST_TAG = "mediaList_tag";
    private static final String MEDIA_CURRENT_POSITION = "currentPosition";


    public FullScreenPhotoFragment() {
    }

    // to use dialog dismiss interface
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (getActivity() instanceof DialogInterface.OnDismissListener)
            ((DialogInterface.OnDismissListener) getActivity()).onDismiss(dialog);
    }


    public static FullScreenPhotoFragment getInstance(Media media, FullScreenStatus fullScreenStatus) {
        FullScreenPhotoFragment sheet = new FullScreenPhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MEDIA_TAG, media);
        sheet.fullScreenStatus = fullScreenStatus;
        sheet.setArguments(bundle);
        return sheet;
    }


    public static FullScreenPhotoFragment getInstance(List<Media> mediaList, int currentPosition, String appLanguage, FullScreenStatus fullScreenStatus) {
        FullScreenPhotoFragment sheet = new FullScreenPhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(MEDIA_LIST_TAG, (ArrayList<? extends Parcelable>) mediaList);
        bundle.putInt(MEDIA_CURRENT_POSITION, currentPosition);
        bundle.putString(APP_LANGUAGE, appLanguage);
        sheet.fullScreenStatus = fullScreenStatus;
        sheet.setArguments(bundle);
        return sheet;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().getParcelableArrayList(MEDIA_LIST_TAG) != null) {
                mediaList = getArguments().getParcelableArrayList(MEDIA_LIST_TAG);
                position = getArguments().getInt(MEDIA_CURRENT_POSITION, 0);
                appLanguage = getArguments().getString(APP_LANGUAGE);

            } else if (getArguments().getParcelable(MEDIA_TAG) != null)
                mediaList.add(getArguments().getParcelable(MEDIA_TAG));
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.getAttributes().windowAnimations = R.style.DialogAnimation;
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_media_dialog, null);
        viewInit();

        fullScreenStatus.fullScreenStatus(true);

        viewpager.setCurrentItem(position);
        adapter.setMediaList(mediaList, viewpager.getCurrentItem());

        return view;
    }


    private void viewInit() {
        viewpager = view.findViewById(R.id.media_viewpager_test);

        adapter = new FullScreenImageAdapter(getContext(), appLanguage);
        viewpager.setAdapter(adapter);

        if (appLanguage != null) {
            if (appLanguage.equals("ar")) {
                viewpager.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                viewpager.setRotationY(180);
            }
        }
    }

    @Override
    public void dismiss() {
        fullScreenStatus.fullScreenStatus(false);
        super.dismiss();
    }

    public interface FullScreenStatus {
        void fullScreenStatus(boolean status);
    }
}