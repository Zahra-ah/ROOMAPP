package com.example.roomapp.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.viewModel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
class ListFragment : Fragment() {
    lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

      val view= inflater.inflate(R.layout.fragment_list, container, false)

        val adapter=MyAdapter()
        val recycleView= view.findViewById<RecyclerView>(R.id.recyclerv)
        recycleView.adapter=adapter
        recycleView.layoutManager=LinearLayoutManager(requireContext())


        //userViewModel

        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {user ->adapter.setData(user)


        })

      view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener(){
          findNavController().navigate(R.id.action_listFragment_to_addFragment)
      }
      return view
    }


}