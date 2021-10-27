package br.org.eldorado.millelds.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.org.eldorado.millelds.R
import br.org.eldorado.millelds.database.MainDataBase
import br.org.eldorado.millelds.database.dao.ProductDAO
import br.org.eldorado.millelds.databinding.ActivityMainBinding
import br.org.eldorado.millelds.model.Product
import br.org.eldorado.millelds.ui.adapter.ProductListAdapter
import java.util.*


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var productDAO: ProductDAO

    private lateinit var productList: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        productDAO = MainDataBase.getInstance(this).getProductDao()
        productList = productDAO.getAll().toMutableList()

        setupViews()
    }

    override fun onResume() {
        super.onResume()
        updateProductList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.shoppingCartItem -> {
                openShoppingCart()
                true
            }
            R.id.pastOrdersItem -> {
                openOrdersHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openOrdersHistory() {
        val intent = Intent(this, OrdersActivity::class.java)
        startActivity(intent)
    }

    private fun openShoppingCart() {
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }

    private fun updateProductList() {
        productList.clear()
        productList.addAll(productDAO.getAll())
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun setupViews() {
        binding.recyclerView.adapter = ProductListAdapter(productList).apply {
            onItemClickListener = { product ->
                val intent = Intent(
                    this@MainActivity,
                    ProductDetailActivity::class.java
                ).apply {
                    putExtra(PRODUCT_TAG, product)
                }
                startActivity(intent)
            }
        }

        val itemTouchHelper = setupItemTouchHelper()

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this,ProductAddActivity::class.java))
        }
    }

    private fun setupItemTouchHelper(): ItemTouchHelper {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val swipeFlags = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
                val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val startingPosition = viewHolder.adapterPosition
                val endingPosition = target.adapterPosition
                Collections.swap(productList, startingPosition, endingPosition)
                binding.recyclerView.adapter?.notifyItemMoved(startingPosition, endingPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                productDAO.delete(productList[position])
                productList.removeAt(position)
                binding.recyclerView.adapter?.notifyItemRemoved(position)
            }
        })
        return itemTouchHelper
    }
}