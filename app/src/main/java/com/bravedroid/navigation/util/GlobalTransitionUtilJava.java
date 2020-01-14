package com.bravedroid.navigation.util;

import androidx.navigation.NavOptions;

import com.bravedroid.navigation.R;

public class GlobalTransitionUtilJava {
    private static GlobalTransitionUtilJava instance = null;

    private NavOptions navOptions;

    private GlobalTransitionUtilJava() {
        if (navOptions == null)
            navOptions = createDefaultFragmentNavOptions();
    }

    private NavOptions createDefaultFragmentNavOptions() {
        return new NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_from_right_to_left)
                .setExitAnim(R.anim.slide_out_from_right_to_left)
                .setPopEnterAnim(R.anim.slide_in_from_left_to_right)
                .setPopExitAnim(R.anim.slide_out_from_left_to_right).build();
    }

    private static GlobalTransitionUtilJava getInstance() {
        if (instance == null) {
            instance = new GlobalTransitionUtilJava();
        }
        return instance;
    }

    public NavOptions getNavOptions() {
        return navOptions;
    }
}
