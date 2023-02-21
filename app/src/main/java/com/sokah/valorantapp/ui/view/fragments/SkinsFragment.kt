package com.sokah.valorantapp.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.SkinsFragmentBinding
import com.sokah.valorantapp.ui.view.adapters.SkinAdapter
import com.sokah.valorantapp.ui.viewmodel.SkinsViewModel
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel
import kotlinx.coroutines.launch


class SkinsFragment : Fragment(R.layout.skins_fragment), SkinAdapter.OnSkinListener {

    private val viewModel: SkinsViewModel by viewModels()
    private var _binding: SkinsFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var weapons: Array<String>
    lateinit var skinsList: MutableList<SkinModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SkinsFragmentBinding.inflate(inflater, container, false)

        val layoutManager = GridLayoutManager(context, 2)
        binding.rvSkins.layoutManager = layoutManager

        val adapter = SkinAdapter(this)

        binding.rvSkins.adapter = adapter

        viewModel.mutableSkinList.observe(viewLifecycleOwner) {

            if (it.isEmpty()) {
                showSnackBar()
            } else {

            }
            adapter.SetSkins(it)
            // hace que se suba el scroll cuando cambia algo en la lista
            layoutManager.scrollToPositionWithOffset(0, 0)
            skinsList = it
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {

            binding.progressBar3.isVisible = it
        }



        binding.autoCompleteTextView.setOnItemClickListener { _, _, position, id ->

            if (position == 0) {

                viewModel.filterSkins("")
            } else {
                viewModel.filterSkins(weapons.get(position))
            }

        }


        return binding.root
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