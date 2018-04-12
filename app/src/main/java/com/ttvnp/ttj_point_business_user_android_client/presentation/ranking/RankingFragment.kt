package com.ttvnp.ttj_point_business_user_android_client.presentation.ranking

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.FragmentRankingBinding
import com.ttvnp.ttj_point_business_user_android_client.model.PrizeItemModel
import com.ttvnp.ttj_point_business_user_android_client.model.RankingItemModel
import javax.inject.Inject
import dagger.android.support.DaggerFragment


class RankingFragment : DaggerFragment() {
    @Inject lateinit var viewModel: RankingViewModel
    lateinit var binding: FragmentRankingBinding
    lateinit var ranking: List<RankingItemModel>
    lateinit var prizes: List<PrizeItemModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ranking, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fetch()
    }

    companion object {
        fun newInstance(): RankingFragment {
            val fragment = RankingFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private fun fetch() {
        viewModel.getRanking {
            ranking = it

            viewModel.getPrizes {
                prizes = it
                initView()
            }
        }

    }

    private fun initView() {
        val rankingListAdapter = RankingAdapter(activity!!.applicationContext)


        val list = mutableListOf<Item>()
        ranking.sortedBy({ it -> -it.order }).forEachIndexed { index, it -> list.add(Item(
                title = if (index == 0) getString(R.string.ranking_title_ranking) else null,
                rankingItem = it,
                prizeItem = null
        ))}
        prizes.forEachIndexed { index, it -> list.add(Item(
                title = if (index == 0) getString(R.string.ranking_title_prize) else null,
                rankingItem = null,
                prizeItem = it
        ))}

        rankingListAdapter.items = list

        binding.rankingListView.adapter = rankingListAdapter
    }

    override fun onDestroy() {
        viewModel.disposable.clear()
        super.onDestroy()
    }
}

