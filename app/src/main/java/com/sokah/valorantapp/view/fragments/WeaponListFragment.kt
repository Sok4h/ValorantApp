package com.sokah.valorantapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.FragmentWeaponListBinding


class WeaponListFragment : Fragment(R.layout.fragment_weapon_list) {

    private  var _binding :FragmentWeaponListBinding ?=null
    val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeaponListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null;
    }

}