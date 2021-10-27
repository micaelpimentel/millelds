package br.org.eldorado.millelds.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.org.eldorado.millelds.R
import br.org.eldorado.millelds.database.MainDataBase
import br.org.eldorado.millelds.database.dao.ProductDAO
import br.org.eldorado.millelds.databinding.ActivityProductAddBinding
import br.org.eldorado.millelds.extensions.tryLoadImage
import br.org.eldorado.millelds.model.Product
import br.org.eldorado.millelds.ui.dialog.ImageFormDialog
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal

class ProductAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductAddBinding.inflate(layoutInflater)
    }

    private lateinit var productDAO: ProductDAO

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = getString(R.string.new_product)
        productDAO = MainDataBase.getInstance(this).getProductDao()
        setupButton()
        setupImage()
    }

    private fun setupImage() {
        binding.productImageView.setOnClickListener {
            Snackbar.make(binding.root, "Clicked product image", Snackbar.LENGTH_SHORT).show()
            ImageFormDialog(this)
                .show(url) { imageUrl ->
                    url = imageUrl
                    binding.productImageView.tryLoadImage(url)
                }
        }
    }

    private fun setupButton() {
        binding.registerProductButton.setOnClickListener {
            createProduct().also {
                productDAO.add(it)
            }
            finish()
        }
    }

    private fun createProduct(): Product {
        with (binding){
            val name = productNameTextField.editText?.text.toString()
            val description = productDescriptionTextField.editText?.text.toString()
            val price = productPriceTextView.editText?.text.toString().let {
                if (it.isBlank()) BigDecimal.ZERO else BigDecimal(it)
            }

            return Product(
                name = name,
                description = description,
                priceString = price.toString(),
                imageUrl = url
            )
        }
    }
}