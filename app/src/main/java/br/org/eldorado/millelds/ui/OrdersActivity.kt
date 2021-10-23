package br.org.eldorado.millelds.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.org.eldorado.millelds.R
import br.org.eldorado.millelds.dao.OrderDAO
import br.org.eldorado.millelds.databinding.ActivityCartBinding
import br.org.eldorado.millelds.databinding.ActivityOrdersBinding
import br.org.eldorado.millelds.ui.adapter.CartListAdapter
import br.org.eldorado.millelds.ui.adapter.OrdersListAdapter

class OrdersActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityOrdersBinding.inflate(layoutInflater)
    }

    private val ordersList = OrderDAO().getAll().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Pedidos"
        setupOrdersList()
    }

    private fun setupOrdersList() {
        if (!checkOrdersIsEmpty()) {
            binding.ordersListRecyclerView.apply {
                adapter = OrdersListAdapter(ordersList)
            }
        }
    }

    fun checkOrdersIsEmpty(): Boolean {
        if (ordersList.isNullOrEmpty()) {
            binding.showEmptyMessage()
            return true
        }
        return false
    }

    private fun ActivityOrdersBinding.showEmptyMessage() {
        ordersListConstraint.visibility = View.GONE
        emptyOrdersConstraint.visibility = View.VISIBLE
    }
}