package com.example.tictactoe

import android.widget.ImageButton

const val EMPTY_IMAGE: Int = 0

class BoardGrid (private val displayButton: ImageButton?){
    private var playerSign : PlayerSign? = null

    fun hasPlayerSign() : Boolean = this.playerSign != null

    fun putPlayerSign(playerSign: PlayerSign){
        this.playerSign = playerSign
        val image =  if (playerSign == PlayerSign.X) R.drawable.x_image else R.drawable.o_image
        this.displayButton?.setImageResource(image)
    }

    fun samePlayerSign(other: BoardGrid): Boolean =
        (this.playerSign != null) and (this.playerSign == other.playerSign)

    fun disableButton() {
        this.displayButton?.isEnabled = false
    }

    fun reset(){
        this.displayButton?.setImageResource(EMPTY_IMAGE)
        this.displayButton?.isEnabled = true
        this.playerSign = null
    }
}