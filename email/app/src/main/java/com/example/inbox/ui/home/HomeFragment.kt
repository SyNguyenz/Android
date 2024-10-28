package com.example.inbox.ui.home

import EmailAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inbox.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!  // Khởi tạo binding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var emailAdapter: EmailAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Khởi tạo ViewModel
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Khởi tạo binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Khởi tạo RecyclerView và layoutManager
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Quan sát LiveData
        homeViewModel.emails.observe(viewLifecycleOwner) { emails ->
            emailAdapter = EmailAdapter(emails)
            recyclerView.adapter = emailAdapter
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
