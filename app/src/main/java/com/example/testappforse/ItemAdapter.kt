package com.example.testappforse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: MutableList<DataModel>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantity_text_view)
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.productName
        //holder.quantityTextView.text = item.quantity
        holder.quantityTextView.text = "  Quantity: ${item.quantity.toInt()}"
        val imgResource = holder.itemView.context.resources.getIdentifier(
            //item.img, "drawable",
            item.img.split('.')[0], "drawable",
            holder.itemView.context.packageName

        )
        holder.imageView.setImageResource(imgResource)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

}
