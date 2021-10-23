package br.org.eldorado.millelds.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.org.eldorado.millelds.R
import br.org.eldorado.millelds.databinding.OrderItemBinding
import br.org.eldorado.millelds.extensions.formatCurrencyToBr
import br.org.eldorado.millelds.extensions.getDateString
import br.org.eldorado.millelds.extensions.getItensCount
import br.org.eldorado.millelds.model.Order

class OrdersListAdapter(private val ordersList: MutableList<Order>) :
    RecyclerView.Adapter<OrdersListAdapter.ViewHolder>() {

    inner class ViewHolder(val itemBinding: OrderItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private lateinit var order: Order

            fun bind(order: Order) {
                this.order = order
                with(itemBinding) {
                    orderNumberTextView.text = itemView.context.getString(R.string.order_number_title, order.orderDate.toString())
                    dateTextView.text = order.getDateString()
                    quantityTextView.text = "${order.getItensCount()}"
                    totalPriceTextView.text = order.orderTotal.formatCurrencyToBr()
                }
            }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return OrderItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let {
                ViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(ordersList[position])

    override fun getItemCount(): Int = ordersList.size
}
