package br.org.eldorado.millelds.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import br.org.eldorado.millelds.databinding.ImageFormBinding
import br.org.eldorado.millelds.extensions.tryLoadImage

class ImageFormDialog (private val context: Context) {
    fun show(
        defaultUrl: String? = null,
        onImageLoaded: (String) -> Unit
    ) {
        ImageFormBinding
            .inflate(LayoutInflater.from(context)).apply {

                defaultUrl?.let {
                    productImageView.tryLoadImage(it)
                    imageUrlEditText.setText(it)
                }

                loadImageButton.setOnClickListener {
                    val url = imageUrlEditText.text.toString()
                    productImageView.tryLoadImage(url)
                }

                AlertDialog.Builder(context)
                    .setView(root)
                    .setPositiveButton("Confirmar") {_, _ ->
                        val url = imageUrlEditText.text.toString()
                        onImageLoaded(url)
                    }
                    .setNegativeButton("Cancelar") {_, _ ->

                    }
                    .show()
            }
    }
}