package br.org.eldorado.millelds.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.org.eldorado.millelds.dao.CartDAO
import br.org.eldorado.millelds.databinding.CartItemBinding
import br.org.eldorado.millelds.extensions.formatCurrencyToBr
import br.org.eldorado.millelds.extensions.setupProductImage
import br.org.eldorado.millelds.model.CartItem

class CartListAdapter(
    private val cartList: MutableList<CartItem>,
    val updateTotal: UpdateTotalPrice
) : RecyclerView.Adapter<CartListAdapter.ViewHolder>() {

    interface UpdateTotalPrice {
        fun updateTotalPriceTextView()
    }

    inner class ViewHolder(val itemBinding: CartItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private lateinit var cartItem: CartItem

        fun bind(cartItem: CartItem) {
            this.cartItem = cartItem
            with(itemBinding) {
                productNameTextView.text = cartItem.product.name
                amountCounter.setText(cartItem.amount.toString())
                productPriceTextView.text = cartItem.getSubTotal().formatCurrencyToBr()
                productImageView.setupProductImage(cartItem.product.imageUrl)
                increase.setOnClickListener { increaseCount() }
                decrease.setOnClickListener { decreaseCount() }
            }
        }

        fun increaseCount()  {
            with(itemBinding) {
                displayCount(amountCounter.text.toString().toInt() + 1)
            }
        }

        fun decreaseCount()  {
            with(itemBinding) {
                displayCount(amountCounter.text.toString().toInt() - 1)
            }
        }

        fun displayCount(amount: Int) {
            itemBinding.amountCounter.setText(amount.toString())
            cartItem.amount = amount
            itemBinding.productPriceTextView.text = cartItem.getSubTotal().formatCurrencyToBr()
            if (amount == 0) {
                cartList.removeAt(adapterPosition)
                CartDAO().remove(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
            updateTotal.updateTotalPriceTextView()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return CartItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let {
                ViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(cartList[position])

    override fun getItemCount(): Int = cartList.size
}