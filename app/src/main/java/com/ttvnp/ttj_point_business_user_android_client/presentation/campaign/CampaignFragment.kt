package com.ttvnp.ttj_point_business_user_android_client.presentation.campaign

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.FragmentCampaignBinding
import com.ttvnp.ttj_point_business_user_android_client.model.ShopModel
import javax.inject.Inject
import dagger.android.support.DaggerFragment


class CampaignFragment : DaggerFragment() {
    @Inject lateinit var viewModel: CampaignViewModel
    lateinit var binding: FragmentCampaignBinding
    lateinit var campaign: List<ShopModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_campaign, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fetch()
    }

    companion object {
        val TAG: String = CampaignFragment::class.java.simpleName
        fun newInstance(): CampaignFragment {
            val fragment = CampaignFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private fun fetch() {
        viewModel.getCampaigns {
            campaign = it
            initView()
        }
    }

    private fun initView() {
        val listAdapter = CampaignAdapter(activity!!.applicationContext)
        // listAdapter.campaigns = campaign.sortedBy { it -> -it.id }
        listAdapter.campaigns = campaign
        binding.campaignlistView.adapter = listAdapter

        binding.setOnItemClick { adapterView, view, i, l ->
            val campaign = adapterView.getItemAtPosition(i) as ShopModel
            Log.d(TAG,"campaign click id: ${campaign.id}")

            val intent = CampaignDetailActivity.intent(activity!!.applicationContext)
            intent.putExtra("campaign", campaign)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        viewModel.disposable.clear()
        super.onDestroy()
    }
}