package com.sokah.valorantapp.data.repository

import com.sokah.valorantapp.data.database.AgentDao
import com.sokah.valorantapp.data.exceptions.ErrorMessages
import com.sokah.valorantapp.data.model.BaseModel
import com.sokah.valorantapp.data.model.entities.AgentEntity
import com.sokah.valorantapp.data.network.ValorantApiService
import com.sokah.valorantapp.model.agents.AgentDto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class AgentRepositoryTest {

    @RelaxedMockK
    private lateinit var service: ValorantApiService


    @RelaxedMockK
    private lateinit var agentDao: AgentDao

    lateinit var agentRepository: IAgentRepository

    val agentEntity = mockk<AgentEntity>(relaxed = true)
    val agentDto = mockk<AgentDto>(relaxed = true)
    val agent = mockk<AgentEntity>(relaxed = true)

    val agents = mutableListOf(agent)

    @Before()
    fun setup() {
        MockKAnnotations.init(this)
        agentRepository = AgentRepository(service, agentDao)
    }

    @Test
    fun `when api call fails it gets data from the local database and returns success`() =
        runBlocking {

            //given
            coEvery { service.getAgents() } returns Response.error(
                400,
                ResponseBody.create(null, "")
            )
            coEvery { agentDao.getAllAgents() } returns mutableListOf(agentEntity)

            //when
            agentRepository.getAllAgents()

            //then

            assert(agentRepository.getAllAgents().isSuccess)

        }

    @Test
    fun `when api call is successful it saves the data in the database`() = runBlocking {

        //given
        coEvery { service.getAgents() } returns Response.success(
            BaseModel(
                200,
                mutableListOf(agentDto)
            )
        )

        //when
        agentRepository.getAllAgents()

        //then

        coVerify { agentDao.insertAgents(any()) }
    }

    @Test
    fun `when api call fails and theres no cache it returns no cache exception`() = runBlocking {

        //given

        coEvery { service.getAgents() } returns Response.error(501, ResponseBody.create(null, ""))
        coEvery { agentDao.getAllAgents() } returns mutableListOf()

        //when
        agentRepository.getAllAgents()

        //then

        assert(
            agentRepository.getAllAgents()
                .exceptionOrNull()?.message == ErrorMessages.API_FAILED_AND_NO_CACHE.error
        )
    }

    @Test
    fun `when theres an error network and no cache it returns no conexion available`() =
        runBlocking {

            //given

            coEvery { service.getAgents() }.throws(Exception())
            coEvery { agentDao.getAllAgents() } returns mutableListOf()


            //when
            agentRepository.getAllAgents()

            assert(
                agentRepository.getAllAgents()
                    .exceptionOrNull()?.message == ErrorMessages.NO_INTERNET_CONNECTION.error
            )
        }

    @Test
    fun `get agent by uuid returns agent if provided with valid uuid`() = runBlocking {

        //given
        coEvery { agent.uuid } returns "1"
        coEvery { agentDao.getAgentById("") } returns agents.find { it.uuid == "1" }!!

        //when

        val response = agentRepository.getAgentById("")

        //then
        assert(response.isSuccess)

    }


    @Test
    fun `get agent by uuid returns error if provided with invalid uuid`() = runBlocking {

        //given
        coEvery { agent.uuid } returns "1"
        coEvery { agentDao.getAgentById("") } returns agents.find { it.uuid == "4" }

        //when

        val response = agentRepository.getAgentById("")

        //then
        assert(response.isFailure)

    }

}