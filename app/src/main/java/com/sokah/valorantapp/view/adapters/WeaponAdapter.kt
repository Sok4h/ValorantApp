package com.sokah.valorantapp.view.adapters

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.sokah.valorantapp.databinding.WeaponCardBinding
import com.sokah.valorantapp.model.dataModel.WeaponModel
import com.sokah.valorantapp.view.fragments.WeaponListFragmentDirections

class WeaponAdapter : RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder>() {


    var weaponList = mutableListOf<WeaponModel>()

    fun setAgents(weapons: MutableList<WeaponModel>) {

        val diffUtil = WeaponDiffUtil(weaponList, weapons)
        val diffresult = DiffUtil.calculateDiff(diffUtil)
        diffresult.dispatchUpdatesTo(this)
        this.weaponList = weapons


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {

        return WeaponViewHolder(
            WeaponCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {

        val weapon = this.weaponList[position]

        holder.bind(weapon)

    }

    override fun getItemCount(): Int {
        return this.weaponList.size
    }


    class WeaponViewHolder(val binding: WeaponCardBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(weapon: WeaponModel) {
            Glide.with(binding.root).load(weapon.displayIcon).into(binding.imgWeapon)

            binding.tvWeaponName.text = weapon.displayName

            binding.tvWeaponPrice.text = weapon.shopData?.cost.toString()

            val category = weapon.category.split("::")
            binding.tvWeaponType.text = category[1]

            val weaponString = Gson().toJson(weapon)

            binding.root.setOnClickListener {

                it.findNavController().navigate(
                    WeaponListFragmentDirections.actionWeaponListFragmentToWeaponDetailFragment(
                        weaponString
                    )
                )

            }

        }
    }


    class WeaponDiffUtil(

        private val oldList: MutableList<WeaponModel>,
        private val newList: MutableList<WeaponModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            return oldList[oldItemPosition].uuid.contentEquals(newList[newItemPosition].uuid)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return when {

                !oldList[oldItemPosition].uuid.contentEquals(newList[newItemPosition].uuid) -> false

                !oldList[oldItemPosition].displayName.contentEquals(newList[newItemPosition].displayName) -> false

                else -> true
            }
        }
    }

}