package br.org.eldorado.millelds.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.org.eldorado.millelds.databinding.ListItemBinding
import br.org.eldorado.millelds.model.Product

class ListAdapter(
    private val context: Context,
    private val dataSet: List<Product>
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {
        fun bind(product: Product) {
            with(listItemBinding) {
                productNameTextView.text = product.name
                productDescriptionTextView.text = product.description ?: ""
                productPriceTextView.text = "R$ ${product.price.toPlainString()}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ListItemBinding
            .inflate(LayoutInflater.from(context), parent, false)
            .let {
                ViewHolder(it)
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}