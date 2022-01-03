package com.sokah.valorantapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.sokah.valorantapp.model.agents.AgentModel

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

            !oldList[oldItemPosition].displayName.contentEquals(newList[newItemPosition].displayName) ->false

            else -> true
        }
    }
}