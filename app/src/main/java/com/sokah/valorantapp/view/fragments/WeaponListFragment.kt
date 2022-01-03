package com.sokah.valorantapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.FragmentWeaponListBinding
import com.sokah.valorantapp.view.WeaponAdapter
import com.sokah.valorantapp.viewmodel.WeaponListViewModel


class WeaponListFragment : Fragment(R.layout.fragment_weapon_list) {

    lateinit var viewmodel :WeaponListViewModel
    private  var _binding :FragmentWeaponListBinding ?=null
    val binding get()=_binding!!
    lateinit var adapter : WeaponAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeaponListBinding.inflate(inflater, container, false)

        adapter = WeaponAdapter()
        binding.rvWeapons.adapter=adapter
        binding.rvWeapons.layoutManager= LinearLayoutManager(context)
        viewmodel=ViewModelProvider(this).get(WeaponListViewModel::class.java)

        viewmodel.weaponList.observe(this,{


            adapter.setAgents(it.data)
        })

        binding.chipGroupWeapons.setOnCheckedChangeListener {group,checkedId ->

            val chip = group.findViewById<Chip>(checkedId)

            if (chip != null) {

                if(chip.id==binding.chipPistols.id){

                    viewmodel.sortWeapon("Sidearm")
                }
                else{

                    viewmodel.sortWeapon(chip.text.toString())
                }

            }
            else{

                viewmodel.getWeapons()
            }
        }




        return binding.root
    }






    override fun onDestroy() {
        super.onDestroy()
        _binding=null;
    }

}