package com.sokah.valorantapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.FragmentWeaponListBinding
import com.sokah.valorantapp.view.adapters.WeaponAdapter
import com.sokah.valorantapp.viewmodel.WeaponListViewModel


class WeaponListFragment : Fragment(R.layout.fragment_weapon_list) {

    lateinit var viewmodel: WeaponListViewModel
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
        viewmodel = ViewModelProvider(this).get(WeaponListViewModel::class.java)

        viewmodel.weaponList.observe(viewLifecycleOwner) {

            if(it.isEmpty()) showSnackBar()
            adapter.setAgents(it)
            layoutManager.scrollToPositionWithOffset(0, 0)
        }

        viewmodel.isLoading.observe(viewLifecycleOwner) {

            binding.progressBar2.isVisible = it
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

        Snackbar.make(binding.root,"no data received, check your connection",LENGTH_INDEFINITE)
            .setAnimationMode(ANIMATION_MODE_SLIDE)
            .setAction("Retry") { viewmodel.getWeapons() }.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }

}