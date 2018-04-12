package com.ttvnp.ttj_point_business_user_android_client.presentation.wallet


import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.FragmentWalletBinding
import com.ttvnp.ttj_point_business_user_android_client.extensions.generateQRCodeImage
import com.ttvnp.ttj_point_business_user_android_client.model.ProfileModel
import com.ttvnp.ttj_point_business_user_android_client.presentation.redeem_points.RedeemPointsActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class WalletFragment : DaggerFragment() {
    @Inject lateinit var viewModel: WalletViewModel
    lateinit var binding: FragmentWalletBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getProfile { profile ->
            updatePointField(profile)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val applicationContext = activity!!.applicationContext
        binding.goToRedeemPointsButton.setOnClickListener {
            startActivity(Intent(applicationContext, RedeemPointsActivity::class.java))
        }

        viewModel.getProfile { profile ->
            updatePointField(profile)
            updateQRCodeImage(profile)
        }
    }

    companion object {
        fun newInstance(): WalletFragment {
            val fragment = WalletFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
    
    fun updatePointField(profile: ProfileModel) {
        var point = profile.account.point
        binding.pointField.text = "${point}pt"
    }

    fun updateQRCodeImage(profile: ProfileModel){
        try {
            val userID = profile.id as String
            val codeStr = "type=receipt&customer_id=$userID"
            val image = QRCodeWriter().generateQRCodeImage(codeStr,480)
            binding.qrCodeImageView.setImageBitmap(image)
        } catch (e: WriterException) {
            Log.e("ttj", "WriterException", e)
            binding.qrCodeImageView.setImageResource(0)
        }
    }
}
