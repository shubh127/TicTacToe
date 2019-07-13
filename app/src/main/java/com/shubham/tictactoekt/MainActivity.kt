package com.shubham.tictactoekt

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.content.Intent
import android.os.Build


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVar();
    }

    private fun initVar() {
        btn_1.setOnClickListener(this)
        btn_2.setOnClickListener(this)
        btn_3.setOnClickListener(this)
        btn_4.setOnClickListener(this)
        btn_5.setOnClickListener(this)
        btn_6.setOnClickListener(this)
        btn_7.setOnClickListener(this)
        btn_8.setOnClickListener(this)
        btn_9.setOnClickListener(this)
    }

    var player1 = mutableListOf<Int>()
    var player2 = mutableListOf<Int>()
    var activePlayer = 1

    override fun onClick(view: View) {
        val btnSelected: Button = view as Button
        var cellId: Int = 0
        when (view.id) {
            R.id.btn_1 -> {
                cellId = 1
            }
            R.id.btn_2 -> {
                cellId = 2
            }
            R.id.btn_3 -> {
                cellId = 3
            }
            R.id.btn_4 -> {
                cellId = 4
            }
            R.id.btn_5 -> {
                cellId = 5
            }
            R.id.btn_6 -> {
                cellId = 6
            }
            R.id.btn_7 -> {
                cellId = 7
            }
            R.id.btn_8 -> {
                cellId = 8
            }
            R.id.btn_9 -> {
                cellId = 9
            }
        }
        playGame(cellId = cellId, btnSelected = btnSelected)
    }

    fun playGame(cellId: Int, btnSelected: Button) {
        btnSelected.isEnabled = false

        if (activePlayer == 1) {
            btnSelected.text = "X"
            btnSelected.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            activePlayer = 2
            findWinner(true)

        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundResource(R.color.darkgreen)
            player2.add(cellId)
            activePlayer = 1
            findWinner(false)
        }
    }

    fun findWinner(isPlayer1: Boolean) {
        var winner = -1

        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (winner != -1) {
            btn_1.isEnabled = false
            btn_2.isEnabled = false
            btn_3.isEnabled = false
            btn_4.isEnabled = false
            btn_5.isEnabled = false
            btn_6.isEnabled = false
            btn_7.isEnabled = false
            btn_8.isEnabled = false
            btn_9.isEnabled = false

            val winnerName = if (winner == 1) {
                getString(R.string.player_1)
            } else {
                getString(R.string.player_2)
            }
            Toast.makeText(this, String.format(getString(R.string.winner_is), winnerName), Toast.LENGTH_SHORT).show()
        } else {
            if (isPlayer1) {
                autoPlay()
            }
        }
    }

    fun autoPlay() {
        val emptyCells = mutableListOf<Int>()
        for (cellId in 1..9) {
            if (!(player1.contains(cellId) || player2.contains(cellId))) {
                emptyCells.add(cellId)
            }
        }

        if (emptyCells.size > 0) {
            val r = Random()
            val randIndex = r.nextInt(emptyCells.size - 0) + 0
            val cellId = emptyCells.get(randIndex)

            val btnSelected: Button

            when (cellId) {
                1 -> btnSelected = btn_1
                2 -> btnSelected = btn_2
                3 -> btnSelected = btn_3
                4 -> btnSelected = btn_4
                5 -> btnSelected = btn_5
                6 -> btnSelected = btn_6
                7 -> btnSelected = btn_7
                8 -> btnSelected = btn_8
                9 -> btnSelected = btn_9
                else -> btnSelected = btn_1
            }

            playGame(cellId, btnSelected)
        } else {

            btn_1.isEnabled = false
            btn_2.isEnabled = false
            btn_3.isEnabled = false
            btn_4.isEnabled = false
            btn_5.isEnabled = false
            btn_6.isEnabled = false
            btn_7.isEnabled = false
            btn_8.isEnabled = false
            btn_9.isEnabled = false

            Toast.makeText(this, R.string.draw, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.popup_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.restart -> {
                val intent = intent
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                finish()
                overridePendingTransition(0, 0)

                startActivity(intent)
                overridePendingTransition(0, 0)
                return true
            }
            R.id.exit -> {
                finishAffinity()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
