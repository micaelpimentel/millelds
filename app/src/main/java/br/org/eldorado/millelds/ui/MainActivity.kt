package br.org.eldorado.millelds.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.org.eldorado.millelds.dao.ProductDAO
import br.org.eldorado.millelds.databinding.ActivityMainBinding
import br.org.eldorado.millelds.model.Product
import br.org.eldorado.millelds.ui.adapter.ProductListAdapter
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {

        binding.recyclerView.adapter = ProductListAdapter(ProductDAO().getAll())

        binding.button.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Clicou no botao",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}