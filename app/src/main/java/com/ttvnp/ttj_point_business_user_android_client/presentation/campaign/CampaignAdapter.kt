package com.ttvnp.ttj_point_business_user_android_client.presentation.campaign

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ttvnp.ttj_point_business_user_android_client.databinding.CampaignItemBinding
import com.ttvnp.ttj_point_business_user_android_client.model.ShopModel

class CampaignAdapter(private val context: Context) : BaseAdapter() {
    var campaigns: List<ShopModel> = emptyList()

    override fun getCount(): Int = campaigns.size

    override fun getItem(position: Int): ShopModel = campaigns[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: CampaignItemBinding
        if (convertView == null) {
            binding = CampaignItemBinding.inflate(LayoutInflater.from(context), parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as CampaignItemBinding
        }
        binding.shop = getItem(position)

        return binding.root
    }
}