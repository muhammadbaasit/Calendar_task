package com.example.calenderapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calenderac.DViewModel
import com.example.calenderapp.databinding.RcLayoutBinding

class RcAdapter(var context: Context, var userList: ArrayList<Modell>, var mainModel: DViewModel): RecyclerView.Adapter<RcAdapter.VHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.rc_layout,parent,false)
        return VHolder(v,mainModel)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {

        val user: Modell =userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class VHolder(itemView : View, var mainModel: DViewModel) : RecyclerView.ViewHolder(itemView) {

        var lbinding: RcLayoutBinding? = null
        init {
            lbinding= DataBindingUtil.bind(itemView)
        }
        fun bind(model: Modell){
            this.lbinding!!.model=model
            lbinding!!.executePendingBindings()
        }
    }
}