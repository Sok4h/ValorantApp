package com.sokah.valorantapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sokah.valorantapp.databinding.FragmentAgentDetailsBinding
import com.sokah.valorantapp.network.ValorantApiService
import com.sokah.valorantapp.viewmodel.AgentDetailViewModel
import com.sokah.valorantapp.viewmodel.AgentDetailViewModelFactory


class AgentDetailsFragment : Fragment(R.layout.fragment_agent_details) {

    private var _binding:FragmentAgentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewmodel : AgentDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val args = AgentDetailsFragmentArgs.fromBundle(arguments!!)

        val viewmodelfactory = AgentDetailViewModelFactory(args.agentUuid)

        viewmodel = ViewModelProvider(this,viewmodelfactory).get(AgentDetailViewModel::class.java)

        _binding = FragmentAgentDetailsBinding.inflate(inflater,container,false)

        viewmodel.agentDetail.observe(this,{

            Glide.with(this).load(it.fullPortrait).into(binding.imgAgentDetail)
            Glide.with(this).load(it.displayIcon).into(binding.imgAgentTypeDetail)
            binding.tvAgentNameDetail.text=it.displayName

        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null;
    }
}