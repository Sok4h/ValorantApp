package com.sokah.valorantapp.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_FADE
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.FragmentWeaponListBinding
import com.sokah.valorantapp.ui.view.adapters.WeaponAdapter
import com.sokah.valorantapp.ui.viewStates.WeaponViewState
import com.sokah.valorantapp.ui.viewmodel.WeaponListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeaponListFragment : Fragment(R.layout.fragment_weapon_list) {

    val viewmodel: WeaponListViewModel by viewModels()
    private var _binding: FragmentWeaponListBinding? = null
    val binding get() = _binding!!
    val adapter = WeaponAdapter()
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeaponListBinding.inflate(inflater, container, false)

        setupRV()

        viewmodel.viewState.observe(viewLifecycleOwner) { state ->

            when (state) {

                is WeaponViewState.Loading -> binding.progressBar2.isVisible = true
                is WeaponViewState.Error -> {
                    binding.progressBar2.isVisible = false
                    Toast.makeText(context, state.error.message, Toast.LENGTH_LONG).show()
                }
                is WeaponViewState.Success -> {
                    binding.progressBar2.isVisible = false
                    adapter.setAgents(state.data)
                    layoutManager.scrollToPositionWithOffset(0, 0)
                }
            }
        }

        binding.chipGroupWeapons.setOnCheckedChangeListener { _, checkedId ->

            filterWeapons(checkedId)
        }

        return binding.root
    }

    fun setupRV() {

        binding.rvWeapons.adapter = adapter
        layoutManager = LinearLayoutManager(context)
        binding.rvWeapons.layoutManager = layoutManager
    }

    fun filterWeapons(checkedId: Int) {

        val chip = binding.chipGroupWeapons.findViewById<Chip>(checkedId)

        if (chip != null) {

            if (chip.id == binding.chipPistols.id) {

                viewmodel.sortWeapon("Sidearm")
            } else {

                viewmodel.sortWeapon(chip.text.toString())
            }

        } else {

            viewmodel.getWeapons()
        }
    }

    private fun showSnackBar() {

        val bottomNavView: BottomNavigationView =
            activity?.findViewById(R.id.bottomNavigationView)!!
        Snackbar.make(binding.root, R.string.no_internet, LENGTH_INDEFINITE)
            .setAnimationMode(ANIMATION_MODE_FADE)
            .setAnchorView(bottomNavView)
            .setAction("Retry") { viewmodel.getWeapons() }.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}