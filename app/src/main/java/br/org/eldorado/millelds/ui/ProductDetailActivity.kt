package br.org.eldorado.millelds.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.org.eldorado.millelds.R
import br.org.eldorado.millelds.dao.CartDAO
import br.org.eldorado.millelds.databinding.ActivityProductDetailBinding
import br.org.eldorado.millelds.extensions.formatCurrencyToBr
import br.org.eldorado.millelds.extensions.tryLoadImage
import br.org.eldorado.millelds.model.Product
import com.google.android.material.snackbar.Snackbar

const val PRODUCT_TAG = "product_tag"

class ProductDetailActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductDetailBinding.inflate(layoutInflater)
    }

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryGetProduct()
    }

    private fun tryGetProduct() {
        intent.getParcelableExtra<Product>(PRODUCT_TAG)?.let {
            product = it
            setupViews(product)
        } ?: finish()
    }

    fun setupViews(product: Product) {
        with(binding) {
            productNameTextView.text = product.name
            productDescriptionTextView.text = product.description
            productImageView.tryLoadImage(product.imageUrl)
            productPriceTextView.text = product.price.formatCurrencyToBr()
        }
        setupFab(product)
    }

    private fun setupFab(product: Product) {
        binding.floatingActionButton.setOnClickListener {
            CartDAO().add(product)
            Snackbar.make(
                binding.root,
                "${product.name} adicionado ao carrinho",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

}