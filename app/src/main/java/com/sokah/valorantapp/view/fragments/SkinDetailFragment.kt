package com.sokah.valorantapp.view.fragments

import android.media.Image
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.SkinDetailFragmentBinding
import com.sokah.valorantapp.model.weapons.Skin
import com.sokah.valorantapp.viewmodel.SkinDetailViewModel
import com.sokah.valorantapp.viewmodel.SkinDetailViewModelFactory
import android.widget.RelativeLayout
import com.bumptech.glide.load.DecodeFormat


class SkinDetailFragment : Fragment(R.layout.skin_detail_fragment) {

    private var _binding :SkinDetailFragmentBinding?=null
    private lateinit var factory :SkinDetailViewModelFactory
    private val binding get() = _binding!!
    private lateinit var viewModel: SkinDetailViewModel
    lateinit var  chromasImg: Array<ImageView>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val arg = SkinDetailFragmentArgs.fromBundle(arguments!!)

        factory= SkinDetailViewModelFactory(arg.skin)



        _binding = SkinDetailFragmentBinding.inflate(inflater,container,false)
        chromasImg = arrayOf(binding.chroma0,binding.chroma1,binding.chroma2,binding.chroma3)
        viewModel = ViewModelProvider(this, factory  ).get(SkinDetailViewModel::class.java)

        viewModel.skinLive.observe(this,{

           loadSkin(it)
        })
        return binding.root
    }

    fun loadSkin(skin:Skin){

        Glide.with(this).load(skin.levels[0].displayIcon)
            .format(DecodeFormat.PREFER_ARGB_8888)
            .into(binding.imgSkinDetail)
        binding.tvSkinName.text=skin.displayName

        for((index,chroma) in skin.chromas.withIndex()){

            Glide.with(this).load(chroma.swatch)
                .override(200, 200)
                .thumbnail(0.5f)
                .into(chromasImg.get(index))


            chromasImg.get(index).visibility=View.VISIBLE

        }

        Log.e("TAG", skin.chromas.toString() )
        if(skin.chromas.get(0).swatch.isNullOrEmpty() )   binding.tvNoChromas.visibility=View.VISIBLE
        for((index,img) in chromasImg.withIndex()){

            img.setOnClickListener {

                Log.e("TAG", index.toString() )
                Glide.with(this).load(skin.chromas[index].fullRender).into(binding.imgSkinDetail)

            }

        }


    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}