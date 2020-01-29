package am.leon;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.Locale;

class Utils {

    static final int PLACE_HOLDER_IMAGE_RESOURCE = R.drawable.ic_reupload;
    static final int DEFAULT_IMAGE_RESOURCE = R.drawable.ic_person_pin_black;
    static final String YouTube_Thumb = "https://img.youtube.com/vi/";

    static void youtubePlay(Context context, String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }


    private static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

}
