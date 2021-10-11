package br.org.eldorado.millelds.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.org.eldorado.millelds.dao.ProductDAO
import br.org.eldorado.millelds.databinding.ActivityMainBinding
import br.org.eldorado.millelds.ui.adapter.ProductListAdapter
import com.google.android.material.snackbar.Snackbar

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
                Snackbar.make(
                    binding.root,
                    "Clicked product ${product.name}",
                    Snackbar.LENGTH_SHORT
                ).show()
                val intent = Intent(this@MainActivity, ProductDetailActivity::class.java).apply {
                    putExtra(PRODUCT_TAG, product)
                }
                startActivity(intent)
            }
        }


        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this,ProductAddActivity::class.java))
        }
    }
}