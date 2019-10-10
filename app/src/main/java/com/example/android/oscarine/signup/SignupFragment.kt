package com.example.android.oscarine.signup


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
        val binding = FragmentSignupBinding.inflate(inflater)

        @Suppress("UsePropertyAccessSyntax")
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        binding.submitButton.setOnClickListener {
            viewModel.submitButtonClicked()
        }
        viewModel.response.observe(this, Observer {
            if (it == "201") {
                Toast.makeText(activity, "User registration successful", Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }
}
