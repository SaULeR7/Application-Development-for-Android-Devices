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
import com.example.project.databinding.FragmentCartBinding

class CardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter
    private val selectedItems = mutableListOf<ListFragment.ItemModel>()
    private lateinit var btnCheckout: Button
    private lateinit var userN: TextView
    private lateinit var total_cost: TextView
    lateinit var binding: FragmentCartBinding


    val itemsList = mutableListOf(
        ListFragment.ItemModel(R.mipmap.image1, "Item 1", 10.0),
        ListFragment.ItemModel(R.mipmap.image2, "Item 2", 15.0),
        ListFragment.ItemModel(R.mipmap.image3, "Item 3", 20.0),
        ListFragment.ItemModel(R.mipmap.image4, "Item 4", 25.0),
        ListFragment.ItemModel(R.mipmap.image5, "Item 5", 30.0),
        ListFragment.ItemModel(R.mipmap.image6, "Item 6", 35.0),
        ListFragment.ItemModel(R.mipmap.image7, "Item 7", 40.0),
        ListFragment.ItemModel(R.mipmap.image8, "Item 8", 45.0)
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = binding.cardRecyclerView
        userN = binding.userTextView
        btnCheckout = binding.checkOutBtn
        total_cost = binding.costTextview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = CardAdapter(mutableListOf())
        recyclerView.adapter = adapter


        val sharedViewModel = (requireActivity() as MainActivity).provideSharedViewModel()

        sharedViewModel.selectedItem.observe(viewLifecycleOwner, { selectedItem ->
            selectedItems.add(selectedItem)
            updateAdapterWithData()
        })


        if(sharedViewModel.logedin){
            btnCheckout.visibility = View.VISIBLE
            userN.visibility = View.VISIBLE
            userN.setText(sharedViewModel.username)
        }
        else {
            btnCheckout.visibility = View.GONE
            userN.visibility = View.GONE
        }


        btnCheckout.setOnClickListener {
            val sharedViewModel = (requireActivity() as MainActivity).provideSharedViewModel()
            sharedViewModel.total_cost = 0.0
            sharedViewModel.i = 0
            selectedItems.clear()
            reload()
            updateAdapterWithData()
            if (activity is OnReloadTabListener) {
                (activity as OnReloadTabListener).reloadTab()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        reload()
    }

    fun reload() {
        val sharedViewModel = (requireActivity() as MainActivity).provideSharedViewModel()
        total_cost.setText("Total: $" + sharedViewModel.total_cost.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val sharedViewModel = (requireActivity() as MainActivity).provideSharedViewModel()
        sharedViewModel.selectedItem.value = null
    }

    private fun updateAdapterWithData() {
        if (selectedItems.isEmpty()) {
            adapter.updateData(emptyList())
        } else {
            adapter.updateData(selectedItems.toList())
        }
    }

    inner class CardAdapter(private var items: MutableList<ListFragment.ItemModel>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val itemImageView: ImageView = itemView.findViewById(R.id.imageView)
            val itemTextView: TextView = itemView.findViewById(R.id.textView)
            val itemButton: Button = itemView.findViewById(R.id.button)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val currentItem = items[position]
            holder.itemImageView.setImageResource(currentItem.imageResId)
            holder.itemTextView.text = currentItem.textView
            holder.itemButton.text = "Remove"

            holder.itemButton.setOnClickListener {
                removeItem(currentItem.textView.toString(), position)
            }

        }

        override fun getItemCount(): Int {
            return items.size
        }

        fun updateData(newItems: List<ListFragment.ItemModel>) {
            items = newItems.toMutableList()
            reload()
            notifyDataSetChanged()
        }

        private fun removeItem(name: String, position: Int) {
            items.removeAt(position)
            selectedItems.removeAt(position)
            val sharedViewModel = (requireActivity() as MainActivity).provideSharedViewModel()

            for(it in itemsList) {
                if(name == it.textView) {
                    sharedViewModel.total_cost -= it.price
                    sharedViewModel.i--
                }
            }

            if (activity is OnReloadTabListener) {
                (activity as OnReloadTabListener).reloadTab()
            }

            reload()
            updateAdapterWithData()
            notifyItemRemoved(position)
        }

    }

    interface OnReloadTabListener {
        fun reloadTab()
    }

}
