package br.org.eldorado.millelds.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.org.eldorado.millelds.dao.ProductDAO
import br.org.eldorado.millelds.databinding.ActivityProductAddBinding
import br.org.eldorado.millelds.model.Product
import com.google.android.material.snackbar.Snackbar

class ProductAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupButton()
    }

    private fun setupButton() {
        with(binding) {
            registerProductButton.setOnClickListener {
                val name = "${productNameTextField.editText?.text}"
                val description = "${productDescriptionTextField.editText?.text}"
                val price = "${productPriceTextView.editText?.text}"

                Product(
                    name = name,
                    description = description,
                    price = price.toBigDecimal()
                ).also {
                    ProductDAO().add(it)
                }
                finish()
            }
        }
    }
}