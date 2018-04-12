package com.ttvnp.ttj_point_business_user_android_client.presentation.ranking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.PrizeItemBinding
import com.ttvnp.ttj_point_business_user_android_client.databinding.RankingItemBinding
import com.ttvnp.ttj_point_business_user_android_client.model.PrizeItemModel
import com.ttvnp.ttj_point_business_user_android_client.model.RankingItemModel
import com.ttvnp.ttj_point_business_user_android_client.util.formatToKilo


data class Item(
        val title: String?,
        val rankingItem: RankingItemModel?,
        val prizeItem: PrizeItemModel?
        )

class RankingAdapter(private val context: Context) : BaseAdapter() {
    var items: List<Item> = emptyList()

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Item = items[position]

    override fun getItemId(position: Int): Long = 0

    override fun getViewTypeCount(): Int = 2

    override fun getItemViewType(position: Int): Int = if (items[position].rankingItem != null) 0 else 1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(position)
        if (item.rankingItem != null) {
            val binding: RankingItemBinding
            if (convertView == null) {
                binding = RankingItemBinding.inflate(LayoutInflater.from(context), parent, false)
                binding.root.tag = binding
            } else {
                binding = convertView.tag as RankingItemBinding
            }
            val rankingItem = item.rankingItem
            binding.title = item.title
            binding.showTitle = item.title != null
            binding.rankingItem = rankingItem
            binding.amount = rankingItem.customer.account.point.formatToKilo()
            binding.ranking = (position + 1).toString()

            Picasso.with(context)
                    .load(rankingItem.customer.icon?.original)
                    .placeholder(R.drawable.tabbar_icon_profile)
                    .error(R.drawable.tabbar_icon_profile)
                    .into(binding.profileImage)

            return binding.root
        } else if (item.prizeItem != null) {
            val binding: PrizeItemBinding
            if (convertView == null) {
                binding = PrizeItemBinding.inflate(LayoutInflater.from(context), parent, false)
                binding.root.tag = binding
            } else {
                binding = convertView.tag as PrizeItemBinding
            }
            val prizeItem = item.prizeItem
            binding.title = item.title
            binding.showTitle = item.title != null
            binding.prizeItem = prizeItem

            Picasso.with(context)
                    .load(prizeItem.bonusItem.image?.original)
                    .placeholder(R.drawable.icon_pencil_on_a_notes_paper)
                    .error(R.drawable.icon_pencil_on_a_notes_paper)
                    .into(binding.imageView)

            return binding.root
        } else {
            // dummy
            return View(context)
        }
    }
}