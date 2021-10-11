package br.org.eldorado.millelds.extensions

import android.widget.ImageView
import br.org.eldorado.millelds.R
import coil.load

fun ImageView.tryLoadImage(url: String? = null) {
    load(url) {
        fallback(R.drawable.image_error)
        error(R.drawable.image_error)
        placeholder(R.drawable.placeholder)
    }
}