package com.sokah.valorantapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sokah.valorantapp.databinding.SkinCardBinding
import com.sokah.valorantapp.model.dataModel.SkinModel


class SkinAdapter(var listener: OnSkinListener) :
    RecyclerView.Adapter<SkinAdapter.ViewHolder>() {

    var skinList = mutableListOf<SkinModel>()

    companion object {
        var onSkinLister: OnSkinListener? = null
    }


    fun SetSkins(newList: MutableList<SkinModel>) {

        val diffUtil = SkinDiffUtil(skinList, newList)

        val diffresult = DiffUtil.calculateDiff(diffUtil)

        diffresult.dispatchUpdatesTo(this)



        this.skinList = newList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            SkinCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        onSkinLister = listener
        holder.binding.tvSkinTitle.text = skinList[position].displayName
        // no tiene display icon clase base
        Glide.with(holder.binding.root).load(skinList[position].levels[0].displayIcon)
            .override(500, 500)
            /*.thumbnail(0.5f)*/.into(holder.binding.imgSkin)

        holder.binding.root.setOnClickListener {

            onSkinLister!!.onSkinClick(position)
        }

    }

    override fun getItemCount(): Int {
        return this.skinList.size
    }

    class ViewHolder(val binding: SkinCardBinding) : RecyclerView.ViewHolder(binding.root) {



    }


    interface OnSkinListener {

        fun onSkinClick(position: Int)
    }
    class SkinDiffUtil(

        private val oldList: MutableList<SkinModel>,
        private val newList: MutableList<SkinModel>

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

            return oldList[oldItemPosition].displayName.contentEquals(newList[newItemPosition].displayName)
        }

    }

}