package br.org.eldorado.millelds.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.org.eldorado.millelds.R
import br.org.eldorado.millelds.dao.CartDAO
import br.org.eldorado.millelds.databinding.ActivityCartBinding
import br.org.eldorado.millelds.extensions.formatCurrencyToBr
import br.org.eldorado.millelds.ui.adapter.CartListAdapter
import java.math.BigDecimal

class CartActivity : AppCompatActivity(), CartListAdapter.UpdateTotalPrice {
    private val binding by lazy {
        ActivityCartBinding.inflate(layoutInflater)
    }

    private val cartItems = CartDAO().getAll().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = getString(R.string.cart)
        setUpViews()
    }

    private fun setUpViews() {
        with(binding) {
            if (cartItems.isNullOrEmpty()) {
                cartListConstraint.visibility = View.GONE
                emptyCartConstraint.visibility = View.VISIBLE
            } else {
                cartListRecyclerView.apply {
                    adapter = CartListAdapter(cartItems, this@CartActivity)
                }
            }
        }
        setupTotalPrice()
    }

    private fun setupTotalPrice() {
        val price = cartItems.sumOf {
            it.product.price * it.amount.toBigDecimal()
        }.formatCurrencyToBr()

        binding.totalPriceTextView.text =
            getString(R.string.total_cart_price, price)
    }

    override fun updateTotalPriceTextView() {
        setupTotalPrice()
    }
}