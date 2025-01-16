package com.example.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var board: Board? = null
    private var currentPlayer:PlayerSign  = PlayerSign.X
    private var tvStatus: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.board = createBoard()
        this.tvStatus = findViewById(R.id.tvStatus)
        findViewById<Button>(R.id.btnReset).setOnClickListener { resetGame() }
    }

    private fun createBoard(): Board{
        val boardGrids: Array<Array<BoardGrid>>  = Array(3) { row ->
            Array(3) { col ->
                val button = getButtonById("btn$row$col")
                button?.apply {
                    setOnClickListener { onButtonClick( row, col) }
                }
                BoardGrid(button)
            }
        }
        return Board(boardGrids)
    }

    private fun getButtonById(buttonId: String) : ImageButton?{
        return when(buttonId) {
            "btn00" -> findViewById(R.id.btn00)
            "btn01" -> findViewById(R.id.btn01)
            "btn02" -> findViewById(R.id.btn02)
            "btn10" -> findViewById(R.id.btn10)
            "btn11" -> findViewById(R.id.btn11)
            "btn12" -> findViewById(R.id.btn12)
            "btn20" -> findViewById(R.id.btn20)
            "btn21" -> findViewById(R.id.btn21)
            "btn22" -> findViewById(R.id.btn22)
            else -> null
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onButtonClick(row: Int, col: Int) {
        val boardGrid:BoardGrid? = this.board?.getGrid(row,col)
        if ((boardGrid == null) or (boardGrid?.hasPlayerSign() == true))
            return

        boardGrid?.putPlayerSign(this.currentPlayer)

        if (this.board?.hasWinner() == true) {
            tvStatus?.text = "Player ${this.currentPlayer} Wins!"
            this.board?.disableButtons()
        } else if (this.board?.isFull() == true) {
            tvStatus?.text = "It's a Draw!"
        } else {
            currentPlayer = if (currentPlayer == PlayerSign.X) PlayerSign.O else PlayerSign.X
            tvStatus?.text = "Player ${this.currentPlayer}'s Turn"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun resetGame() {
        this.board?.reset()
        this.currentPlayer= PlayerSign.X
        this.tvStatus?.text = "Player X's Turn"
    }
}