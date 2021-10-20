package br.org.eldorado.millelds.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.org.eldorado.millelds.dao.CartDAO
import br.org.eldorado.millelds.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCartBinding.inflate(layoutInflater)
    }

    private val cartItems = CartDAO().getAll().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpViews()
    }

    private fun setUpViews() {
        with(binding) {
            if (cartItems.isNullOrEmpty()) {
                cartListConstraint.visibility = View.GONE
                emptyCartConstraint.visibility = View.VISIBLE
            }
            Log.d("MICAEL", "setup Views")
        }
    }
}