package com.example.android.oscarine.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.oscarine.SharedPreference
import com.example.android.oscarine.databinding.FragmentSigninBinding

class SigninFragment : Fragment() {

    private lateinit var viewModel: SigninViewModel
    private lateinit var viewModelFactory: SigninViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentSigninBinding.inflate(inflater)

        viewModelFactory = SigninViewModelFactory(SharedPreference(context!!))
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SigninViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.loginSubmitButton.setOnClickListener {
            viewModel.login()
        }

        viewModel.response.observe(this, Observer {
            if (it == "200") {
                Toast.makeText(activity, "You are now logged in", Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }

}