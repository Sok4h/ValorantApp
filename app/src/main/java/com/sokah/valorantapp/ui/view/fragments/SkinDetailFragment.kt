package com.sokah.valorantapp.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.SkinDetailFragmentBinding
import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel
import com.sokah.valorantapp.ui.view.adapters.SkinAdapter2
import com.sokah.valorantapp.ui.viewmodel.SkinDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SkinDetailFragment : Fragment(R.layout.skin_detail_fragment), SkinAdapter2.OnSkinListener {

    private var _binding: SkinDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SkinDetailViewModel by viewModels()
    lateinit var chromasImg: Array<ImageView>
    lateinit var skinsList: MutableList<SkinModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val arg =
            SkinDetailFragmentArgs.fromBundle(requireArguments())
        _binding = SkinDetailFragmentBinding.inflate(inflater, container, false)
        chromasImg = arrayOf(binding.chroma0, binding.chroma1, binding.chroma2, binding.chroma3)
        viewModel.getSkin(arg.skin)
        val adapter = SkinAdapter2(this)
        viewModel.getSkinsFiltered()
        binding.rvSkinsFromCollection.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSkinsFromCollection.adapter = adapter

        viewModel.skinLive.observe(viewLifecycleOwner) {

            loadSkin(it)
        }

        viewModel.mutableSkinList.observe(viewLifecycleOwner) {

            adapter.SetSkins(it)
            skinsList = it
        }
        return binding.root
    }


    fun loadSkin(skin: SkinModel) {

        Glide.with(this).load(skin.levels[0].displayIcon)
            .format(DecodeFormat.PREFER_ARGB_8888)
            .into(binding.imgSkinDetail)
        binding.tvSkinName.text = skin.displayName

        for ((index, chroma) in skin.chromas.withIndex()) {

            Glide.with(this).load(chroma.swatch)
                .override(200, 200)
                .thumbnail(0.5f)
                .into(chromasImg.get(index))


            chromasImg.get(index).visibility = View.VISIBLE

        }
        //muestra texto si no hay ningun chroma
        if (skin.chromas.size == 1) {
            binding.tvNoChromas.visibility = View.VISIBLE

            for (img in chromasImg) {

                img.visibility = View.GONE
            }
        } else {
            binding.tvNoChromas.visibility = View.INVISIBLE

        }

        for (chroma in skin.chromas) {

            for ((index, img) in chromasImg.withIndex()) {
                // cambia imagenes skin
                img.setOnClickListener {
                    Glide.with(this).load(skin.chromas[index].fullRender)
                        .into(binding.imgSkinDetail)
                }

            }

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSkinClick(position: Int) {

        loadSkin(skinsList.get(position))
    }
}