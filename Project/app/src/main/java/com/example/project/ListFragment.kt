package com.example.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R

class ListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val items = mutableListOf(
            ItemModel(R.mipmap.image1, "Item 1", 10.0),
            ItemModel(R.mipmap.image2, "Item 2", 15.0),
            ItemModel(R.mipmap.image3, "Item 3", 20.0),
            ItemModel(R.mipmap.image4, "Item 4", 25.0),
            ItemModel(R.mipmap.image5, "Item 5", 30.0),
            ItemModel(R.mipmap.image6, "Item 6", 35.0),
            ItemModel(R.mipmap.image7, "Item 7", 40.0),
            ItemModel(R.mipmap.image8, "Item 8", 45.0)
        )


        val adapter = ItemAdapter(items) { selectedItem ->
            val sharedViewModel = (requireActivity() as MainActivity).provideSharedViewModel()
            sharedViewModel.selectedItem.value = selectedItem
            sharedViewModel.total_cost += selectedItem.price
            sharedViewModel.i++

            if (activity is CardFragment.OnReloadTabListener) {
                (activity as CardFragment.OnReloadTabListener).reloadTab()
            }

        }

        recyclerView.adapter = adapter
    }

    data class ItemModel(val imageResId: Int, val textView: String, val price: Double)

    class ItemAdapter(private val items: List<ItemModel>, private val onClickListener: (ItemModel) -> Unit) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val itemImageView: ImageView = itemView.findViewById(R.id.imageView)
            val itemTextView: TextView = itemView.findViewById(R.id.textView)
            val itemButton: Button = itemView.findViewById(R.id.button)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentItem = items[position]

            holder.itemImageView.setImageResource(currentItem.imageResId)
            holder.itemTextView.text = currentItem.textView + "........ $" + currentItem.price
            holder.itemButton.text = "Add to Cart"

            holder.itemButton.setOnClickListener {
                onClickListener(currentItem)
            }
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }
}
