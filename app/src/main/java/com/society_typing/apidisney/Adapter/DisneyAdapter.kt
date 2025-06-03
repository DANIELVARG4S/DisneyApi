package com.society_typing.apidisney.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.society_typing.apidisney.Data.DisneyItemResponse
import com.society_typing.apidisney.DisneyViewHolder
import com.society_typing.apidisney.R

class DisneyAdapter(
    var disneyList: List<DisneyItemResponse> = emptyList(),
    private val onItemSelected:(Int) -> Unit
): RecyclerView.Adapter<DisneyViewHolder>()
{
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<DisneyItemResponse>){
        disneyList =list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DisneyViewHolder {
        return DisneyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_disney, parent,false)
       )
    }

    override fun onBindViewHolder(
        holder: DisneyViewHolder,
        position: Int
    ) {
        holder.bind(disneyList[position],onItemSelected)
    }

    override fun getItemCount() = disneyList.size

}