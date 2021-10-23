package br.org.eldorado.millelds.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.org.eldorado.millelds.databinding.ListItemBinding
import br.org.eldorado.millelds.extensions.formatCurrencyToBr
import br.org.eldorado.millelds.extensions.setupProductImage
import br.org.eldorado.millelds.extensions.tryLoadImage
import br.org.eldorado.millelds.model.Product
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ProductListAdapter(
    private val productList: List<Product>,
    var onItemClickListener: (Product) -> Unit = {}
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    inner class ViewHolder(val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {

        private lateinit var product: Product

        init {
            itemView.setOnClickListener {
                onItemClickListener(product)
            }
        }
        fun bind(product: Product) {
            this.product = product
            with(listItemBinding) {
                productNameTextView.text = product.name
                productDescriptionTextView.text = product.description ?: ""
                productPriceTextView.text = product.price.formatCurrencyToBr()
                productImageView.setupProductImage(product.imageUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let {
                ViewHolder(it)
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(productList[position])

    override fun getItemCount() = productList.size
}