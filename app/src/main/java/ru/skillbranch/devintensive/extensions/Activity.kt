package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_profile.*


fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
}

fun Activity.isKeyboardOpen(): Boolean {

    val activityRootView = this.activity_root

    var r: Rect = Rect()
    //r will be populated with the coordinates of your view that area still visible.
    activityRootView.getWindowVisibleDisplayFrame(r)

    val heightDiff = activityRootView.rootView.height - (r.bottom - r.top)
    return heightDiff > 100
}


fun Activity.isKeyboardClosed(): Boolean {

    val activityRootView = this.activity_root

    var r: Rect = Rect()
    //r will be populated with the coordinates of your view that area still visible.
    activityRootView.getWindowVisibleDisplayFrame(r)

    val heightDiff = activityRootView.rootView.height - (r.bottom - r.top)
    return heightDiff < 100
}