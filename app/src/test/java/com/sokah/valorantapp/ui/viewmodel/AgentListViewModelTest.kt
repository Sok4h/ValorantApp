package com.sokah.valorantapp.ui.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sokah.valorantapp.MainDispatcherRule
import com.sokah.valorantapp.data.repository.IAgentRepository
import com.sokah.valorantapp.ui.viewStates.AgentViewStates
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)

class AgentListViewModelTest {

    val repository: IAgentRepository = mockk()

    val agentViewModel = AgentListViewModel(repository)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Test
    fun `when fetching agents succeds the viewstate is displayed correctly`() = runTest {

        //given
        coEvery { repository.getAllAgents() } returns Result.success(mockk())
        agentViewModel.getAgents()
        assert(agentViewModel.viewState.value is AgentViewStates.AgentListSuccess)

    }

    @Test
    fun `when fetching agent list fails the viewstate is displayed correctly`() = runTest {

        //given
        coEvery { repository.getAllAgents() } returns (Result.failure(Exception()))
        agentViewModel.getAgents()
        assert(agentViewModel.viewState.value is AgentViewStates.Error)
    }

    @Test
    fun `when fetching agent by id returns errorState`() = runTest {

        coEvery { repository.getAgentByRole("") } returns mutableListOf()
        agentViewModel.filterAgent("")
        assert(agentViewModel.viewState.value is AgentViewStates.Error)
    }
}