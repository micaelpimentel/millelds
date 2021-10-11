package br.org.eldorado.millelds.extensions

import android.widget.ImageView
import coil.load

fun ImageView.tryLoadImage(url: String? = null) {
    load(url)
}