package br.org.eldorado.millelds.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import br.org.eldorado.millelds.dao.ProductDAO
import br.org.eldorado.millelds.databinding.ActivityProductAddBinding
import br.org.eldorado.millelds.model.Product
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal

class ProductAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupButton()
        setupImage()
    }

    private fun setupImage() {
        binding.productImageView.setOnClickListener {
            Snackbar.make(binding.root, "Clicked product image", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupButton() {
        binding.registerProductButton.setOnClickListener {
            createProduct().also {
                ProductDAO().add(it)
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
                price = price
            )
        }
    }
}