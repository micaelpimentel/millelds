package br.org.eldorado.millelds.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.org.eldorado.millelds.R
import br.org.eldorado.millelds.dao.CartDAO
import br.org.eldorado.millelds.dao.OrderDAO
import br.org.eldorado.millelds.databinding.ActivityCartBinding
import br.org.eldorado.millelds.extensions.formatCurrencyToBr
import br.org.eldorado.millelds.ui.adapter.CartListAdapter
import com.google.android.material.snackbar.Snackbar
import java.util.*

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
        setupCartList()
        setupTotalPrice()
    }

    private fun setupCartList() {
        if (!checkCartIsEmpty()) {
            binding.cartListRecyclerView.apply {
                adapter = CartListAdapter(cartItems, this@CartActivity)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.compleOrderItem -> {
                completeOrder()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun completeOrder() {
        if (!checkCartIsEmpty()) {
            val intent = Intent(this, OrderCompletedActivity::class.java)
            OrderDAO().add(cartItems, Date().time)
            startActivity(intent)
            CartDAO().removeAll()
            finish()
        } else {
            Snackbar.make(
                binding.root,
                "Seu carrinho está vazio",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    fun checkCartIsEmpty(): Boolean {
        if (cartItems.isNullOrEmpty()) {
            binding.showEmptyMessage()
            return true
        }
        return false
    }

    private fun ActivityCartBinding.showEmptyMessage() {
        cartListConstraint.visibility = View.GONE
        emptyCartConstraint.visibility = View.VISIBLE
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
        checkCartIsEmpty()
    }
}