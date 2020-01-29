package am.leon.swipePackage;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import androidx.fragment.app.DialogFragment;

public class SwipeDialogFragment extends DialogFragment {

    private boolean mSwipeable = true;
    private boolean mTiltEnabled = true;
    private boolean mSwipeLayoutGenerated = false;
    private SwipeVerticalTouchListener mListener = null;

    /**
     * Set whether dialog can be swiped away.
     */
    public void setSwipeable(boolean swipeable) {
        mSwipeable = swipeable;
    }

    /**
     * Get whether dialog can be swiped away.
     */
    public boolean isSwipeable() {
        return mSwipeable;
    }

    /**
     * Set whether tilt effect is enabled on swiping.
     */
    public void setTiltEnabled(boolean tiltEnabled) {
        mTiltEnabled = tiltEnabled;
        if (mListener != null) {
            mListener.setTiltEnabled(tiltEnabled);
        }
    }

    /**
     * Get whether tilt effect is enabled on swiping.
     */
    public boolean isTiltEnabled() {
        return mTiltEnabled;
    }

    /**
     * Called when dialog is swiped away to dismiss.
     *
     * @return true to prevent dismissing
     */
    public boolean onSwipedAway(boolean toRight) {
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!mSwipeLayoutGenerated && getShowsDialog()) {
            Window window = getDialog().getWindow();
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            View content = decorView.getChildAt(0);
            decorView.removeView(content);

            SwipeableFrameLayout layout = new SwipeableFrameLayout(getActivity());
            layout.addView(content);
            decorView.addView(layout);

            mListener = new SwipeVerticalTouchListener(decorView, "layout", new SwipeVerticalTouchListener.DismissCallbacks() {
                @Override
                public boolean canDismiss(Object token) {
                    return isCancelable() && mSwipeable;
                }

                @Override
                public void onDismiss(View view, boolean toRight, Object token) {
                    if (!onSwipedAway(toRight)) {
                        dismiss();
                    }
                }
            });
            mListener.setTiltEnabled(mTiltEnabled);
            layout.setSwipeDismissTouchListener(mListener);
            layout.setOnTouchListener(mListener);
            layout.setClickable(true);
            mSwipeLayoutGenerated = true;
        }
    }


    // ---------------------------------------------------------------------------------------------

    private class SwipeableFrameLayout extends FrameLayout {

        private SwipeVerticalTouchListener mTouchListener;

        public SwipeableFrameLayout(Context context) {
            super(context);
        }

        public void setSwipeDismissTouchListener(SwipeVerticalTouchListener touchListener) {
            mTouchListener = touchListener;
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            if (mTouchListener != null) {
                if (mTouchListener.onTouch(this, ev))
                    return true;
            }
            return super.onInterceptTouchEvent(ev);
        }
    }

}
