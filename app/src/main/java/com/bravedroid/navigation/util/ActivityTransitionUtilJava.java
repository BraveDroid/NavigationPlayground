package com.bravedroid.navigation.util;

import android.app.Activity;
import android.widget.ImageView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.navigation.ActivityNavigator;

public class ActivityTransitionUtilJava {
    private ActivityNavigator.Extras extras;
    private Activity activity;
    private ImageView imageView;

    public ActivityTransitionUtilJava(Activity activity, ImageView imageView) {
        this.activity = activity;
        this.imageView = imageView;
    }

    public ActivityNavigator.Extras getExtras() {
        if (extras == null) {
            extras = createSharedObjectTransitionExtras(activity, imageView);
        }
        return extras;
    }

    private static ActivityNavigator.Extras createSharedObjectTransitionExtras(
            Activity activity,
            ImageView imageView
    ) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                Pair.create(imageView, "settings_imageView_transition")
        );
        return new ActivityNavigator.Extras.Builder().setActivityOptions(options).build();
    }
}
