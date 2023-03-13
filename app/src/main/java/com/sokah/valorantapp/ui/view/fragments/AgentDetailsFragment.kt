package com.sokah.valorantapp.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.FragmentAgentDetailsBinding
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel
import com.sokah.valorantapp.ui.viewStates.AgentViewStates
import com.sokah.valorantapp.ui.viewmodel.AgentDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentDetailsFragment : Fragment(R.layout.fragment_agent_details) {

    private var _binding: FragmentAgentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: AgentDetailViewModel by viewModels()
    lateinit var abilitiesimg: Array<ImageView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgentDetailsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val args = AgentDetailsFragmentArgs.fromBundle(
            requireArguments()
        )

        abilitiesimg =
            arrayOf(binding.ability0, binding.ability1, binding.ability2, binding.ability3)

        viewmodel.getAgentDetail(args.agentUuid)

        viewmodel.viewState.observe(viewLifecycleOwner) {

            when (it) {
                is AgentViewStates.AgentSuccess -> setupAgent(it.data)
                is AgentViewStates.Error -> {

                    Toast.makeText(
                        context,
                        "Something went wrong try again later",
                        Toast.LENGTH_SHORT
                    ).show()

                    requireActivity().onBackPressed();
                }
                AgentViewStates.Loading -> TODO()
                else -> {}
            }
        }




        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }

    fun setupAgent(agent: AgentModel) {


        Glide.with(this).load(agent.bustPortrait)
            .thumbnail(0.5f)
            .into(binding.imgAgentDetail)
        Glide.with(this).load(agent.role.roleIcon).into(binding.imgAgentTypeDetail)

        binding.tvAgentNameDetail.text = agent.agentName
        binding.tvAgentRole.text = agent.role.roleName
        binding.tvAgentDescription.text = agent.description
        binding.tvAgentDeveloperName.text = agent.developerName

        // TODO: 29/12/2021  pensar como hacer esto sin dos loops
        for ((index, img) in abilitiesimg.withIndex()) {

            abilitiesimg[index].alpha = 0.3f

            img.setOnClickListener {
                abilitiesimg.forEach { img -> img.alpha = 0.3f }
                img.alpha = 1f
                binding.tvAbilityName.text = agent.abilities[index].displayName
                binding.tvAbilityDescription.text = agent.abilities[index].description
            }
        }

        //first time setup for ability
        abilitiesimg[0].alpha = 1f
        binding.tvAbilityName.text = agent.abilities[0].displayName
        binding.tvAbilityDescription.text = agent.abilities[0].description

        //loads every ability into a imageview
        for ((index, ability) in agent.abilities.withIndex()) {

            Glide.with(this).load(ability.displayIcon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(abilitiesimg[index])
        }


    }
}


