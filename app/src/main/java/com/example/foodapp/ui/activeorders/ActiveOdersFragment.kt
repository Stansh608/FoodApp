package com.example.foodapp.ui.activeorders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.databinding.FragmentActiveOdersBinding

class ActiveOdersFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveOdersFragment()
    }
    // Initialize binding
    private var _binding: FragmentActiveOdersBinding? = null
    //binding for onCreate and onDestroy
    private val binding get() = _binding!!

    //preserve
    private lateinit var viewModel: ActiveOdersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val orderViewModel =ViewModelProvider(this).get(ActiveOdersViewModel::class.java)

        _binding = FragmentActiveOdersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    //fxn onDestroy
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}