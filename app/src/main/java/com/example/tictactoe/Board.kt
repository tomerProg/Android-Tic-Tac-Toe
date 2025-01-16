package com.example.tictactoe

class Board (private val boardGrids: Array<Array<BoardGrid>>){
    fun getGrid(row: Int, col: Int): BoardGrid = this.boardGrids[row][col]

    fun isFull(): Boolean = this.boardGrids.all{ row -> row.all { it.hasPlayerSign() } }

    fun disableButtons() = this.boardGrids.flatten().forEach {  it.disableButton() }

    fun reset() = this.boardGrids.flatten().forEach {it.reset()}

    fun hasWinner() : Boolean{
        var wins = false
        for (i in 0..2){
            wins = wins or this.winsInRow(i) or this.winsInColumn(i)
        }
        return wins or this.winsInMainDiagonal() or this.winsInSecondaryDiagonal()
    }

    private fun winsInRow(row: Int) =
        this.getGrid(row,0).samePlayerSign(this.getGrid(row,1)) and
            this.getGrid(row, 0).samePlayerSign(this.getGrid(row, 2))

    private fun winsInColumn(col: Int) =
        this.getGrid(0, col).samePlayerSign(this.getGrid(1, col)) and
            this.getGrid( 0, col).samePlayerSign(this.getGrid( 2, col))

    private fun winsInMainDiagonal() =
        this.getGrid(0,0).samePlayerSign(this.getGrid(1,1)) and
                this.getGrid(0, 0).samePlayerSign(this.getGrid(2, 2))

    private fun winsInSecondaryDiagonal() =
        this.getGrid(0,2).samePlayerSign(this.getGrid(1,1)) and
                this.getGrid(0, 2).samePlayerSign(this.getGrid(2, 0))
}