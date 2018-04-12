package com.ttvnp.ttj_point_business_user_android_client.presentation.notice

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ttvnp.ttj_point_business_user_android_client.App
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.FragmentNoticeBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class NoticeFragment : Fragment() {
    lateinit var binding: FragmentNoticeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    companion object {
        fun newInstance(): NoticeFragment {
            val fragment = NoticeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private fun initView() {
    }

}