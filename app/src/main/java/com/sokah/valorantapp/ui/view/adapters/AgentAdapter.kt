package com.sokah.valorantapp.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.AgentCardBinding
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel
import com.sokah.valorantapp.ui.view.fragments.AgentsFragmentDirections
import com.sokah.valorantapp.utils.IdlingResource

class AgentAdapter : RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AgentModel>() {
        override fun areItemsTheSame(oldItem: AgentModel, newItem: AgentModel): Boolean {
            return oldItem.uuid.contentEquals(newItem.uuid)
        }

        override fun areContentsTheSame(oldItem: AgentModel, newItem: AgentModel): Boolean {
            return when {

                oldItem.uuid.contentEquals(newItem.uuid) -> false

                oldItem.agentName.contentEquals(newItem.agentName) -> false

                else -> true
            }
        }

    }

    val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun setAgents(agents: MutableList<AgentModel>) {

        IdlingResource.increment()
        val callback = Runnable {
            IdlingResource.decrement()
        }
        differ.submitList(agents, callback)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return AgentViewHolder(layoutInflater.inflate(R.layout.agent_card, parent, false))
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class AgentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = AgentCardBinding.bind(view)

        fun bind(agent: AgentModel) {

            binding.tvAgentName.text = agent.agentName
            for (ability in agent.abilities) {

                Glide.with(view.context).load(ability.displayIcon)
            }

            IdlingResource.increment()
            Glide.with(view.context).load(agent.bustPortrait)
                .placeholder(R.drawable.fade)
                .override(800, 800)
                .thumbnail(0.5f)
                .into(binding.imgAgent)
            IdlingResource.decrement()




            binding.root.setOnClickListener {

                view.findNavController().navigate(
                    AgentsFragmentDirections.actionAgentsFragmentToAgentDetailsFragment(agent.uuid)
                )
            }

        }


    }


}
