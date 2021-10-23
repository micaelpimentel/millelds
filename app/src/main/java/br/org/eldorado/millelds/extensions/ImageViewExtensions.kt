package br.org.eldorado.millelds.extensions

import android.view.View
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

fun ImageView.setupProductImage(url: String?) {
    if (url != null) {
        this.visibility = View.VISIBLE
        this.tryLoadImage(url)
    } else {
        this.visibility = View.GONE
    }
}