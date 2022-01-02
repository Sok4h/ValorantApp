package com.sokah.valorantapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.FragmentAgentDetailsBinding
import com.sokah.valorantapp.viewmodel.AgentDetailViewModel
import com.sokah.valorantapp.viewmodel.AgentDetailViewModelFactory


class AgentDetailsFragment : Fragment(R.layout.fragment_agent_details) {

    private var _binding:FragmentAgentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewmodel : AgentDetailViewModel
    lateinit var  abilitiesimg: Array<ImageView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgentDetailsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val args = AgentDetailsFragmentArgs.fromBundle(arguments!!)

        abilitiesimg= arrayOf(binding.ability0, binding.ability1, binding.ability2,binding.ability3)

        val viewmodelfactory = AgentDetailViewModelFactory(args.agentUuid)

        viewmodel = ViewModelProvider(this,viewmodelfactory).get(AgentDetailViewModel::class.java)

        setupAgent()



        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null;
    }

    fun setupAgent(){

        viewmodel.agentDetail.observe(this,{agent ->

            Glide.with(this).load(agent.bustPortrait)
                .override(1000,1000)
                .thumbnail( 0.5f )
                .into(binding.imgAgentDetail)
            Glide.with(this).load(agent.role.displayIcon).into(binding.imgAgentTypeDetail)
            binding.tvAgentNameDetail.text=agent.displayName
            binding.tvAgentRole.text=agent.role.displayName
            binding.tvAgentDescription.text=agent.description
            binding.tvAgentDeveloperName.text=agent.developerName

            // TODO: 29/12/2021  pensar como hacer esto sin dos loops
            for ((index,img) in abilitiesimg.withIndex()){

                abilitiesimg[index].alpha=0.3f
                // TODO: 29/12/2021  hacer que no explote cuando el agente tiene 5 habilidades 
                img.setOnClickListener {
                    abilitiesimg.forEach { img ->img.alpha=0.3f }
                    img.alpha=1f
                    binding.tvAbilityName.text=agent.abilities[index].displayName
                    binding.tvAbilityDescription.text=agent.abilities[index].description
                }
            }

            //first time setup for ability
            abilitiesimg[0].alpha=1f
            binding.tvAbilityName.text=agent.abilities[0].displayName
            binding.tvAbilityDescription.text=agent.abilities[0].description

            //loads every ability into a imageview
            for ((index, ability) in agent.abilities.withIndex()) {

                Glide.with(this).load(ability.displayIcon).into(abilitiesimg[index])
            }



        })
    }


}