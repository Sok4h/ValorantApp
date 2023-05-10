package com.sokah.valorantapp.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.SkinsFragmentBinding
import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel
import com.sokah.valorantapp.ui.view.adapters.SkinAdapter
import com.sokah.valorantapp.ui.viewStates.SkinViewStates
import com.sokah.valorantapp.ui.viewmodel.SkinsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SkinsFragment : Fragment(R.layout.skins_fragment), SkinAdapter.OnSkinListener {

    private val viewModel: SkinsViewModel by viewModels()
    private var _binding: SkinsFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var weapons: Array<String>
    lateinit var skinsList: MutableList<SkinModel>
    lateinit var layoutManager: GridLayoutManager
    val adapter = SkinAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SkinsFragmentBinding.inflate(inflater, container, false)

        setupRV()

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->

            when (viewState) {
                is SkinViewStates.Error -> {
                    binding.progressBar3.isVisible = false
                    Toast.makeText(context, viewState.error.message, Toast.LENGTH_LONG).show()
                }
                SkinViewStates.Loading -> {
                    binding.progressBar3.isVisible = true
                }
                is SkinViewStates.Success -> {
                    binding.progressBar3.isVisible = false

                    adapter.SetSkins(viewState.data)
                    layoutManager.scrollToPositionWithOffset(0, 0)
                    skinsList = viewState.data
                }
            }


        }


        binding.autoCompleteTextView.setOnItemClickListener { _, _, position, id ->

            if (position == 0) {
                viewModel.getSkins()
            } else {
                viewModel.filterSkinsByWeaponType(weapons[position])
            }

        }


        return binding.root
    }

    fun setupRV() {

        layoutManager = GridLayoutManager(context, 2)
        binding.rvSkins.layoutManager = layoutManager
        binding.rvSkins.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null

    }

    override fun onResume() {
        super.onResume()

        weapons = resources.getStringArray(R.array.weapon_types)

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner, weapons)
        binding.autoCompleteTextView.setAdapter(spinnerAdapter)
    }

    override fun onSkinClick(position: Int) {

        val skin = skinsList[position].uuid

        binding.root.findNavController()
            .navigate(
                SkinsFragmentDirections.actionSkinsFragmentToSkinDetailFragment(
                    skin
                )
            )
    }

    private fun showSnackBar() {

        val bottomNavView: BottomNavigationView =
            activity?.findViewById(R.id.bottomNavigationView)!!

        Snackbar.make(binding.root, R.string.no_internet, BaseTransientBottomBar.LENGTH_INDEFINITE)
            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
            .setAction("Retry") {

                lifecycleScope.launch {
                    viewModel.getSkins()
                }

            }.setAnchorView(bottomNavView)

            .show()
    }

}