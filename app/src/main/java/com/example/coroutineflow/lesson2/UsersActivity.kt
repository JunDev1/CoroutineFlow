package com.example.coroutineflow.lesson2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.coroutineflow.databinding.ActivityUsersAcitvityBinding

class UsersActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUsersAcitvityBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[UsersViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.buttonAddUser.setOnClickListener {
            binding.editTextUsername.text.toString()
                .trim()
                .takeIf { it.isNotBlank() }
                ?.let {
                    viewModel.addUser(it)
                }
        }
        binding.buttonNextScreen.setOnClickListener {
            startActivity(Users2Activity.newIntent(this))
        }
    }

    private fun observeViewModel() {
        viewModel.users.observe(this) {
            binding.textViewUsers.text = it.joinToString()
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, UsersActivity::class.java)
    }
}