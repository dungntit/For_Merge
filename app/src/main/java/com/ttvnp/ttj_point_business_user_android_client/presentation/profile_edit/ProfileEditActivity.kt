package com.ttvnp.ttj_point_business_user_android_client.presentation.profile_edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.ActivityProfileEditBinding
import com.ttvnp.ttj_point_business_user_android_client.model.ProfileModel
import com.ttvnp.ttj_point_business_user_android_client.presentation.tabs.TabEnum
import com.ttvnp.ttj_point_business_user_android_client.presentation.tabs.TabsActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import com.squareup.picasso.Picasso
import java.io.*


class ProfileEditActivity : DaggerAppCompatActivity() {
    @Inject lateinit var viewModel: ProfileEditViewModel
    lateinit var profile: ProfileModel
    private val binding: ActivityProfileEditBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_profile_edit) as ActivityProfileEditBinding
    }

    companion object {
        private const val PROFILE_EDIT_REQUEST_CHOOSER = 1000
        fun intent(context: Context): Intent {
            return Intent(context, ProfileEditActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getProfile {
            profile = it
            initView()
        }
    }

    private fun initView() {
        viewModel.model.firstName = profile.firstName
        viewModel.model.middleName = profile.middleName ?: ""
        viewModel.model.lastName = profile.lastName
        viewModel.model.iconId = profile.iconId
        viewModel.model.icon = profile.icon

        refreshAvatar()

        binding.viewModel = viewModel
        binding.saveButton.setOnClickListener {
            val updatedProfile = ProfileModel(
                    id = profile.id,
                    email = profile.email,
                    firstName = viewModel.model.firstName,
                    middleName = viewModel.model.middleName,
                    lastName = viewModel.model.lastName,
                    iconId = viewModel.model.iconId,
                    icon = viewModel.model.icon,
                    account = profile.account
            )

            viewModel.updateProfile(updatedProfile, { startActivity(TabsActivity.intent(applicationContext, TabEnum.PROFILE)) })
        }

        binding.avatarLayout.setOnClickListener {
            // gallery intent
            val intentGallery = Intent(Intent.ACTION_GET_CONTENT)
            intentGallery.addCategory(Intent.CATEGORY_OPENABLE)
            intentGallery.type = "image/jpeg"

            startActivityForResult(intentGallery, PROFILE_EDIT_REQUEST_CHOOSER)
        }

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            startActivity(TabsActivity.intent(applicationContext, TabEnum.PROFILE))
        }
    }

    private fun getBytes(i: InputStream): ByteArray {
        val byteBuff = ByteArrayOutputStream()

        val buffSize = 1024
        val buff = ByteArray(buffSize)

        var len: Int
        while (true) {
            len = i.read(buff)
            if (len == -1) break

            byteBuff.write(buff, 0, len)
        }

        return byteBuff.toByteArray()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PROFILE_EDIT_REQUEST_CHOOSER) {
            if (resultCode != Activity.RESULT_OK) {
                return
            }

            val i = contentResolver.openInputStream(data?.data)

            val imageBytes = getBytes(i)

            viewModel.uploadImage(imageBytes, { image ->
                viewModel.model.icon = image
                viewModel.model.iconId = image.id
                refreshAvatar()
            })
        }
    }

    private fun refreshAvatar() {
        Picasso.with(applicationContext)
                .load(viewModel.model.icon?.original)
                .placeholder(R.drawable.tabbar_icon_profile)
                .error(R.drawable.tabbar_icon_profile)
                .into(binding.avatar)

    }

    override fun onDestroy() {
        viewModel.disposable.clear()
        super.onDestroy()
    }
}
