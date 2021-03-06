package br.org.eldorado.millelds.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.org.eldorado.millelds.database.dao.OrderDAO
import br.org.eldorado.millelds.databinding.ActivityOrdersBinding
import br.org.eldorado.millelds.ui.adapter.OrdersListAdapter
import org.koin.android.ext.android.inject

class OrdersActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityOrdersBinding.inflate(layoutInflater)
    }

    private val orderDao: OrderDAO by inject()

    private val ordersList = orderDao.getAll().toMutableList()

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