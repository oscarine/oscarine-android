package com.example.android.oscarine.signup


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.android.oscarine.R
import com.example.android.oscarine.databinding.FragmentSignupBinding

/**
 * A simple [Fragment] subclass for Signup.
 */
class SignupFragment : Fragment() {

    private val viewModel: SignupViewModel by lazy {
        ViewModelProviders.of(this).get(SignupViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//        val binding = DataBindingUtil.inflate<FragmentSignupBinding>(inflater,
//            R.layout.fragment_signup, container, false)
        val binding = FragmentSignupBinding.inflate(inflater)

        @Suppress("UsePropertyAccessSyntax")
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        return binding.root
    }
}
