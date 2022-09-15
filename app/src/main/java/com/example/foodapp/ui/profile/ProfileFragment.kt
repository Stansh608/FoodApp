package com.example.foodapp.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }
    //initialize binding
    private var _binding: FragmentProfileBinding? = null

    //acc binding
    private  val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding= FragmentProfileBinding.inflate(layoutInflater, container, false)
        val root : View =binding.root

        return root
    }

    //onDestroy
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}