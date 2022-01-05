package com.sokah.valorantapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.github.mikephil.charting.data.Entry
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.FragmentWeaponDetailBinding
import com.sokah.valorantapp.model.weapons.WeaponModel
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

            loadWeapon(it)
        })
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun loadWeapon(weapon: WeaponModel){

        Glide.with(this).load(weapon.displayIcon).into(binding.imgWeaponDetail)
        binding.tvWeaponNameDetail.text=weapon.displayName

        val type = weapon.category.split("::")

        binding.tvWeaponTypeDetail.text=type[1]

        if(weapon.shopData==null){

            binding.tvWeaponPriceDetail.text="0"
        }
        else{
            binding.tvWeaponPriceDetail.text=weapon.shopData.cost.toString()
        }



        binding.tvFireRate.text= getString(R.string.fire_rate_value, String.format("%.1f",weapon.weaponStats.fireRate))
        binding.tvEquipSpeed.text= getString(R.string.equip_speed_value, String.format("%.1f",weapon.weaponStats.equipTimeSeconds))
        binding.tvReloadSpeed.text= getString(R.string.reload_speed_value, String.format("%.1f",weapon.weaponStats.reloadTimeSeconds))
        binding.tvMagazine.text= getString(R.string.magazine_value, weapon.weaponStats.magazineSize.toString())



    }
}