package com.ttvnp.ttj_point_business_user_android_client.presentation.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.FragmentProfileBinding

import com.ttvnp.ttj_point_business_user_android_client.model.ProfileModel
import com.squareup.picasso.Picasso
import com.ttvnp.ttj_point_business_user_android_client.presentation.login.LoginActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.profile_edit.ProfileEditActivity
import javax.inject.Inject
import dagger.android.support.DaggerFragment


class ProfileFragment : DaggerFragment() {
    @Inject lateinit var viewModel: ProfileViewModel
    lateinit var binding: FragmentProfileBinding
    lateinit var profile: ProfileModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fetch()
    }


    companion object {
        fun newInstance(): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private fun fetch() {
        viewModel.getProfile {
            profile = it
            initView()
        }
    }

    private fun initView() {
        binding.name = profile.name

        binding.editButton.setOnClickListener {
            startActivity(ProfileEditActivity.intent(activity!!.applicationContext))
        }

        binding.logoutButton.setOnClickListener {
            Log.d("ProfileFragment", "logout clicked")
            viewModel.logout { startActivity(LoginActivity.intent(activity!!.applicationContext)) }
        }

        refreshAvatar()
    }

    private fun refreshAvatar() {
        Picasso.with(activity!!.applicationContext)
                .load(profile.icon?.original)
                .placeholder(R.drawable.tabbar_icon_profile)
                .error(R.drawable.tabbar_icon_profile)
                .into(binding.avatar)

    }

    override fun onDestroy() {
        viewModel.disposable.clear()
        super.onDestroy()
    }
}
