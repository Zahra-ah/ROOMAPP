package com.example.roomapp.list
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.ModelClass.User



class MyAdapter():
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var itemList = emptyList<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.object_row,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
val currentItem = itemList[position]
        holder.itemView.findViewById<TextView>(R.id.tv1).text= currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.FristName).text= currentItem.firstName
        holder.itemView.findViewById<TextView>(R.id.LastName).text= currentItem.lastName
        holder.itemView.findViewById<TextView>(R.id.tvAge).text= currentItem.age.toString()
        holder.itemView.findViewById<ConstraintLayout>(R.id.RowLayout).setOnClickListener { val action = ListFragmentDirections.actionListFragmentToUpdatefragment2(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
    fun setData(user : List<User>)
    {
        this.itemList = user
        notifyDataSetChanged()
    }
}






