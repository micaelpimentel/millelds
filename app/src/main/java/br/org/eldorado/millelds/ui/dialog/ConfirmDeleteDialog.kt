package br.org.eldorado.millelds.ui.dialog

import android.app.AlertDialog
import android.content.Context

class ConfirmDeleteDialog(private val context: Context) {
    private val alertBuilder = AlertDialog.Builder(context)
        .setTitle("Confirmar remover...")
        .setMessage("Deseja mesmo remover este produto?")

    fun show() {
        this.alertBuilder.show()
    }

    fun onPositive(onPositveHandler: ()->Unit): ConfirmDeleteDialog {
        this.alertBuilder
            .setPositiveButton("Configmar") { _, _ ->
                onPositveHandler()
            }
        return this
    }

    fun onNegative(onNegaviteHandler: () -> Unit): ConfirmDeleteDialog {
        this.alertBuilder
            .setNegativeButton("Nao") { _, _ ->
                onNegaviteHandler()
            }
            .setOnCancelListener {
                onNegaviteHandler()
            }
        return this
    }
}