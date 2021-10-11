package br.org.eldorado.millelds.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import br.org.eldorado.millelds.R
import br.org.eldorado.millelds.databinding.ListItemBinding
import br.org.eldorado.millelds.model.Product
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ProductListAdapter(
    private val productList: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    class ViewHolder(val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {
        fun bind(product: Product) {
            with(listItemBinding) {
                productNameTextView.text = product.name
                productDescriptionTextView.text = product.description ?: ""
                productPriceTextView.text = formatCurrencyToBr(product.price)
            }
        }

        fun formatCurrencyToBr(price: BigDecimal): String {
            val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return formatter.format(price)
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