package com.androidmodule.kotlinroomrxjava.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidmodule.kotlinroomrxjava.R
import com.androidmodule.kotlinroomrxjava.data.local.entity.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val userList:ArrayList<User>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    class MainViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(user: User){
            itemView.textViewUserEmail.text = user.email
            itemView.textViewUserName.text = user.name
            Glide.with(itemView.imageViewAvatar.context).load(user.avatar).into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
         return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun addList(users:List<User>){
        userList.addAll(users)
    }
}