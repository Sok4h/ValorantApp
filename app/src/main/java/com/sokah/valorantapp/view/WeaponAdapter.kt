package com.sokah.valorantapp.view

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.WeaponCardBinding
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.model.weapons.WeaponModel

class WeaponAdapter :RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder>() {


    var weaponList = mutableListOf<WeaponModel>()

    fun setAgents(weapons: MutableList<WeaponModel>) {

        this.weaponList = weapons

        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {

        return WeaponViewHolder(WeaponCardBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }


    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {

        val agent = this.weaponList[position]

        Glide.with(holder.binding.root).load(agent.displayIcon).into(holder.binding.imgWeapon)

        holder.binding.tvWeaponName.text= agent.displayName

        holder.binding.tvWeaponPrice.text= agent.shopData?.cost.toString()

        var category = agent.category.split("::")
        holder.binding.tvWeaponType.text = category[1]
    }

    override fun getItemCount(): Int {
        return this.weaponList.size
    }


    class WeaponViewHolder(val binding:WeaponCardBinding) : RecyclerView.ViewHolder(binding.root)



}