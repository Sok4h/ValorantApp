package com.sokah.valorantapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sokah.valorantapp.data.model.entities.AgentEntity

@Dao
interface AgentDao {

    @Query("SELECT * from agents")

    suspend fun getAllAgents():MutableList<AgentEntity>

    @Query("SELECT * from agents where roleName ==:role")

    suspend fun getAgentbyRole(role: String):MutableList<AgentEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgents(agents: MutableList<AgentEntity>)

    @Query("SELECT * from agents where uuid==:agentId")
    suspend fun  getAgentById(agentId: String): AgentEntity



}