package com.bravedroid.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val dialogFragment =
            supportFragmentManager.findFragmentByTag(ConfirmationProfileDialogFragment.TAG) as? DialogFragment

//        if (hasFullScreenDialog(dialogFragment))
        if (dialogFragment.isStillVisible())
            dialogFragment?.dismiss()
        else
            super.onBackPressed()

    }

    private fun hasFullScreenDialog(dialogFragment: DialogFragment?): Boolean {
        return dialogFragment != null
                && dialogFragment.isVisible
    }

    private fun DialogFragment?.isStillVisible(): Boolean {
        return this != null && this.isVisible
    }
}
