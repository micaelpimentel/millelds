package br.org.eldorado.millelds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.org.eldorado.millelds.databinding.ActivityMainBinding
import br.org.eldorado.millelds.model.Product
import br.org.eldorado.millelds.ui.adapter.ListAdapter
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val productLit = listOf(
        Product(
            name = "Cesta de frutas 1",
            description = "Cesta básica padrão",
            price = "19.00".toBigDecimal()
        ),
        Product(
            name = "Cesta de frutas 2",
            price = "39.00".toBigDecimal()
        ),
        Product(
            name = "Cesta de frutas 3",
            price = "49.00".toBigDecimal()
        ),
        Product(
            name = "Mega Cesta de frutas",
            price = "69.90".toBigDecimal()
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {

        binding.recyclerView.adapter = ListAdapter(this, productLit)

        binding.button.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Clicou no botao",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}