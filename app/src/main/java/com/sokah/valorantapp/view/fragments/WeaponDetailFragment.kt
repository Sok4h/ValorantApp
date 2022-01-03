package com.sokah.valorantapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.FragmentWeaponDetailBinding
import com.sokah.valorantapp.viewmodel.WeaponDetailViewModel
import com.sokah.valorantapp.viewmodel.WeaponDetailViewModelFactory
import java.lang.reflect.Array.get


class WeaponDetailFragment : Fragment(R.layout.fragment_weapon_detail) {

    private  var _binding: FragmentWeaponDetailBinding ?=null
    val binding  get() =_binding!!

    lateinit var viewModel :WeaponDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val args = WeaponDetailFragmentArgs.fromBundle(arguments!!)
        _binding = FragmentWeaponDetailBinding.inflate(inflater,container,false)

        val provider = WeaponDetailViewModelFactory(args.weapon)

        viewModel = ViewModelProvider(this,provider).get(WeaponDetailViewModel::class.java)

        viewModel.agentWeapon.observe(this,{

            Glide.with(this).load(it.displayIcon).into(binding.imgWeaponDetail)
            binding.tvWeaponNameDetail.text=it.displayName

            val type = it.category.split("::")

            binding.tvWeaponTypeDetail.text=type[1]
            if(it.shopData==null){

                binding.tvWeaponPriceDetail.text="0"
            }
            else{
                binding.tvWeaponPriceDetail.text=it.shopData.cost.toString()
            }
        })
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}