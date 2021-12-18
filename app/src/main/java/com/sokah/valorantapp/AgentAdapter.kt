package com.sokah.valorantapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sokah.valorantapp.databinding.AgentCardBinding
import com.sokah.valorantapp.model.AgentModel

class AgentAdapter() : RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    var agentList = mutableListOf<AgentModel>()

    fun setAgents(agents: List<AgentModel>) {

        this.agentList = agents.filter { it.isPlayableCharacter }.toMutableList()

        Log.e("TAG", agentList[0].displayName )
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return AgentViewHolder(layoutInflater.inflate(R.layout.agent_card, parent, false))
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val listFilter = agentList.filter { it.isPlayableCharacter }
       val item = listFilter[position]

        if(item.isPlayableCharacter)holder.bind(item)

    }

    override fun getItemCount(): Int {
       return agentList.size
    }

    class AgentViewHolder(val view: View) :RecyclerView.ViewHolder(view) {

        val binding = AgentCardBinding.bind(view)

         fun bind(agent: AgentModel){

                if(agent.isPlayableCharacter){

                    binding.tvAgentName.text=agent.displayName
                    Glide.with(view.context).load(agent.killfeedPortrait).into(binding.imgAgent)

                    Glide.with(view.context).load(agent.role.displayIcon).into(binding.imgAgentIcon)
                }
             else{
                 return
             }



        }




    }
}