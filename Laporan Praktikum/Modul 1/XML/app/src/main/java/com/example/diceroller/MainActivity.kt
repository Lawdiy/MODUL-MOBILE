package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.roll_button)
        val diceImage1: ImageView = findViewById(R.id.dice_image1)
        val diceImage2: ImageView = findViewById(R.id.dice_image2)
        val rootLayout = findViewById<android.view.View>(R.id.root_layout)

        rollButton.setOnClickListener {
            val result1 = (1..6).random()
            val result2 = (1..6).random()

            diceImage1.setImageResource(getDiceResource(result1))
            diceImage2.setImageResource(getDiceResource(result2))

            if (result1 == result2) {
                Snackbar.make(rootLayout, "Selamat, anda dapat dadu double!", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(rootLayout, "Anda belum beruntung!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDiceResource(result: Int): Int {
        return when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}