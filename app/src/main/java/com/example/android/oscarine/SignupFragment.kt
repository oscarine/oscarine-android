package com.example.android.oscarine


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.oscarine.databinding.FragmentSignupBinding

/**
 * A simple [Fragment] subclass for Signup.
 */
class SignupFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentSignupBinding>(inflater, R.layout.fragment_signup, container, false)
        return binding.root
    }
}
