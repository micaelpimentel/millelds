package br.org.eldorado.millelds.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.org.eldorado.millelds.R
import br.org.eldorado.millelds.database.dao.CartDAO
import br.org.eldorado.millelds.database.dao.OrderDAO
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
            setupItemTouchHelper().attachToRecyclerView(binding.cartListRecyclerView)
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

    private fun setupItemTouchHelper(): ItemTouchHelper {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val swipeFlags = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
                val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP
                return makeMovementFlags(0, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                CartDAO().remove(position)
                cartItems.removeAt(position)
                binding.cartListRecyclerView.adapter?.notifyItemRemoved(position)
                updateTotalPriceTextView()
            }
        })
        return itemTouchHelper
    }
}