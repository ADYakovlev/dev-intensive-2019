package ru.skillbranch.devintensive.ui.profile

import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    var isEditMode = false
    lateinit var viewFields: Map<String, TextView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_constraint)
        initViews(savedInstanceState)
    }

    private fun initViews(savedInstanceState: Bundle?) {
        viewFields = mapOf(
            "nickname" to tv_nick_name,
            "rank" to tv_rank,
            "firstname" to et_first_name,
            "lastname" to et_last_name,
            "about" to et_about,
            "repository" to et_repo,
            "rating" to tv_rating,
            "respect" to tv_respect
        )
        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE, false) ?: false
        btn_edit.setOnClickListener {
            it.id
            isEditMode = isEditMode.not()
            showCurrentMode(isEditMode)
        }
    }

    private fun showCurrentMode(isEdit: Boolean) {
        val info = viewFields.filter { setOf<String>("firstname", "lastname", "about", "repository").contains(it.key) }
        for ((_, v) in info) {
            v as EditText
            v.isFocusable = isEdit
            v.isFocusableInTouchMode = isEdit
            v.isEnabled = isEdit
            v.background.alpha = if (isEdit) 255 else 0
        }

        iv_eye.visibility = if (isEdit) View.GONE else View.GONE
        wr_about.isCounterEnabled = isEdit
        val filter: ColorFilter? = if (isEdit) {
            PorterDuffColorFilter(
                resources.getColor(R.color.color_accent, theme),
                PorterDuff.Mode.SRC_IN
            )
        } else {
            null
        }
        val icon = if (isEdit) {
            resources.getDrawable(R.drawable.ic_save_black_24dp)
        } else {
            resources.getDrawable(R.drawable.ic_edit_black_24dp)
        }

//        background.colorFilter = filter
//        setImageDrowable(icon)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putBoolean(IS_EDIT_MODE, isEditMode)
    }
}