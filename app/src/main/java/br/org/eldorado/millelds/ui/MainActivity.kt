package br.org.eldorado.millelds.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.org.eldorado.millelds.dao.ProductDAO
import br.org.eldorado.millelds.databinding.ActivityMainBinding
import br.org.eldorado.millelds.ui.adapter.ProductListAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags
import java.util.*


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val productList = ProductDAO().getAll().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
    }

    override fun onResume() {
        super.onResume()
        updateProductList()
    }

    private fun updateProductList() {
        productList.clear()
        productList.addAll(ProductDAO().getAll().toMutableList())
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
                ProductDAO().swap(startingPosition, endingPosition)
                Collections.swap(productList, startingPosition, endingPosition)
                binding.recyclerView.adapter?.notifyItemMoved(startingPosition, endingPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                ProductDAO().remove(position)
                productList.removeAt(position)
                binding.recyclerView.adapter?.notifyItemRemoved(position)
            }
        })
        return itemTouchHelper
    }
}