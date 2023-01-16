package com.play.triki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.play.triki.databinding.FragmentMenuGameBinding

class FragmentGameMenu : Fragment() {

    private lateinit var binding: FragmentMenuGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMenuGameBinding.inflate(inflater, container, false)
        binding.play.setOnClickListener {
            findNavController().navigate(R.id.fragmentStartGame)
        }
        binding.exit.setOnClickListener {
            activity?.finish()
        }
        return binding.root
    }
}