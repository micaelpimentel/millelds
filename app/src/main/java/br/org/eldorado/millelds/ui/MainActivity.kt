package br.org.eldorado.millelds.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.org.eldorado.millelds.R
import br.org.eldorado.millelds.dao.ProductDAO
import br.org.eldorado.millelds.databinding.ActivityMainBinding
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
                getString(R.string.clicked_button),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}