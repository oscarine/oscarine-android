package com.example.android.oscarine.signup


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
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
        val binding = FragmentSignupBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.submitButton.setOnClickListener {
            viewModel.submitButtonClicked()
        }
        binding.loginTextView.setOnClickListener {
            navigateToSigninFragment()
        }

        viewModel.messages.observe(this, Observer {
            Toast.makeText(activity, "User registration successful", Toast.LENGTH_LONG).show()
        })

        viewModel.signupSuccessful.observe(this, Observer {
            if (it == true) {
                navigateToSigninFragment()
                viewModel.signupSuccessfullyCompleted()
            }
        })

        return binding.root
    }

    private fun navigateToSigninFragment() {
        val navController = Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
        navController.navigate(R.id.action_signupFragment_to_signinFragment)
    }
}
