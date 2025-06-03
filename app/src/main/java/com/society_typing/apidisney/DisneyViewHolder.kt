package com.society_typing.apidisney


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.society_typing.apidisney.Data.DisneyItemResponse
import com.society_typing.apidisney.databinding.ItemDisneyBinding
import com.squareup.picasso.Picasso

class DisneyViewHolder(view: View): RecyclerView.ViewHolder(view)  {

    private  val binding = ItemDisneyBinding.bind(view)

    fun bind(disneyItemResponse: DisneyItemResponse, onItemSelected:(Int) -> Unit){
        binding.tvDisneyName.text = disneyItemResponse.name

        Picasso.get().load(disneyItemResponse.imageUrl).into(binding.ivDisney)
        binding.root.setOnClickListener {
            onItemSelected(disneyItemResponse._id)
        }
    }
}