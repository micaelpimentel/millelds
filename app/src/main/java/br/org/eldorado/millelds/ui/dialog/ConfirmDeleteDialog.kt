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

    fun onPositive(onPositiveHandler: ()->Unit): ConfirmDeleteDialog {
        this.alertBuilder
            .setPositiveButton("Confirmar") { _, _ ->
                onPositiveHandler()
            }
        return this
    }

    fun onNegative(onNegativeHandler: () -> Unit): ConfirmDeleteDialog {
        this.alertBuilder
            .setNegativeButton("Nao") { _, _ ->
                onNegativeHandler()
            }
            .setOnCancelListener {
                onNegativeHandler()
            }
        return this
    }
}