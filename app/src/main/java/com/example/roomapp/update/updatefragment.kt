package com.example.roomapp.update

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.ModelClass.User
import com.example.roomapp.R
import com.example.roomapp.viewModel.UserViewModel


class updatefragment : Fragment() {
    private val args by navArgs<updatefragmentArgs>()
    lateinit var mUserViewModel:UserViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view=inflater.inflate(R.layout.fragment_updatefragment, container, false)

        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

       view.findViewById<EditText>(R.id.UpdateFristName).setText(args.currentUser.firstName)
        view.findViewById<EditText>(R.id.UpdateLastName).setText(args.currentUser.lastName)
        view.findViewById<EditText>(R.id.UpdateAge).setText(args.currentUser.age.toString())
view.findViewById<Button>(R.id.buttonUp).setOnClickListener()
{
    updateitem(view)

}


        view.findViewById<Button>(R.id.btnDelete).setOnClickListener {
            deleteUser()
        }
        view.findViewById<Button>(R.id.btn_deleteAllUser).setOnClickListener()
        {
            deleteAllUsers()
        }
        return view
    }
private fun updateitem(view:View)
{
    val firstName = view.findViewById<EditText>(R.id.UpdateFristName).text.toString()
    val LastName = view.findViewById<EditText>(R.id.UpdateLastName).text.toString()
    val age =Integer.parseInt(view.findViewById<EditText>(R.id.UpdateAge).text.toString())

    if(CheckInput(firstName, LastName , view.findViewById<EditText>(R.id.UpdateAge).text ))
    {
        //create instance
        val updatedUser = User(args.currentUser.id,firstName,LastName,age )
        //create updating
        mUserViewModel.updateUser(updatedUser)
        Toast.makeText(requireContext(), "updated successfully", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updatefragment2_to_listFragment)
    }
    else
    {
        Toast.makeText( requireContext(),"fill it out", Toast.LENGTH_SHORT).show()
    }
}
    private fun CheckInput(firstName:String, lastName:String, age: Editable):Boolean
    {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


    private fun deleteAllUsers() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updatefragment2_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Successfully removed ${args.currentUser.firstName}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updatefragment2_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.create().show()
    }
}