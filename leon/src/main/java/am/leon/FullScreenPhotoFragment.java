package am.leon;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import am.leon.swipePackage.SwipeDialogFragment;

public class FullScreenPhotoFragment extends SwipeDialogFragment implements FullScreenImageAdapter.ImageZoomCallback {

    private View view;
    private ViewPager2 viewpager;
    private LeonObject leonObject;
    private FullScreenImageAdapter adapter;
    private FullScreenStatus fullScreenStatus;
    private static final String LEON_TAG = "leon_tag";


    public FullScreenPhotoFragment() {
    }

    // to use dialog dismiss interface
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (getActivity() instanceof DialogInterface.OnDismissListener)
            ((DialogInterface.OnDismissListener) getActivity()).onDismiss(dialog);
    }


    static FullScreenPhotoFragment getInstance(LeonObject leonObject, FullScreenStatus fullScreenStatus) {
        FullScreenPhotoFragment sheet = new FullScreenPhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(LEON_TAG, leonObject);
        sheet.fullScreenStatus = fullScreenStatus;
        sheet.setArguments(bundle);
        return sheet;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            leonObject = getArguments().getParcelable(LEON_TAG);
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
        viewpager.setCurrentItem(leonObject.getCurrentPosition(), false);

        return view;
    }


    private void viewInit() {
        viewpager = view.findViewById(R.id.leon_viewpager);

        adapter = new FullScreenImageAdapter(getContext(), leonObject, this);
        viewpager.setAdapter(adapter);

        if (leonObject.getAppLanguage() != null) {
            if (leonObject.getAppLanguage().equals("ar"))
                viewpager.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }


    @Override
    public void dismiss() {
        fullScreenStatus.fullScreenStatus(false);
        super.dismiss();
    }


    @Override
    public void isZoomed(boolean isZoomed) {
        setSwipeable(!isZoomed);
        viewpager.setUserInputEnabled(!isZoomed);
    }


    public interface FullScreenStatus {
        void fullScreenStatus(boolean status);
    }

}