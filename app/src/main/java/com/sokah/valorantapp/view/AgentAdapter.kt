package com.sokah.valorantapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.AgentCardBinding
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.view.fragments.AgentsFragmentDirections

class AgentAdapter : RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    var agentList = mutableListOf<AgentModel>()

    fun setAgents(agents: MutableList<AgentModel>) {

        this.agentList = agents

        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return AgentViewHolder(layoutInflater.inflate(R.layout.agent_card, parent, false))
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {

        val item = this.agentList[position]

        holder.bind(item)


    }

    override fun getItemCount(): Int {
        return agentList.size
    }

    class AgentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = AgentCardBinding.bind(view)

        fun bind(agent: AgentModel) {

            binding.tvAgentName.text = agent.displayName
            Glide.with(view.context).load(agent.bustPortrait)
                .override(1000, 1000)
                .thumbnail(0.5f)
                .into(binding.imgAgent)

            binding.root.setOnClickListener {

                view.findNavController().navigate(
                    AgentsFragmentDirections.actionAgentsFragmentToAgentDetailsFragment(agent.uuid)
                )
            }
        }


    }


}
