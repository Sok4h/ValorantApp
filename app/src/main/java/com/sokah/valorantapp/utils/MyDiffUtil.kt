package com.sokah.valorantapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel

class MyDiffUtil (

    private val oldList: MutableList<AgentModel>,
    private val newList : MutableList<AgentModel>
        ):DiffUtil.Callback() {

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
        return when{

            !oldList[oldItemPosition].uuid.contentEquals(newList[newItemPosition].uuid) ->false

            !oldList[oldItemPosition].agentName.contentEquals(newList[newItemPosition].agentName) ->false

            else -> true
        }
    }

}