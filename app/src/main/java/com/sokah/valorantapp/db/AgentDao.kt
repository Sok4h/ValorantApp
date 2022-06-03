package com.sokah.valorantapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sokah.valorantapp.model.agents.AgentModel

@Dao
interface AgentDao {

    @Query("SELECT * from agents")

    suspend fun getAllAgents():MutableList<AgentModel>?

    @Query("SELECT * from agents where role=:role")

    suspend fun getbyRole(role: String):MutableList<AgentModel>?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(agents: MutableList<AgentModel>)



}