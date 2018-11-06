package de.datagroup.zahlenraten

import android.widget.EditText
import android.widget.TextView
import java.security.SecureRandom

class GameHandler {

    private val rnd : Int = SecureRandom().nextInt(99)
    private var count: Int = 0
    private var lastGuess: Int = 0

    fun handleGuess(input: EditText, result: TextView) : Int
    {
        if(input.text.toString().isNullOrBlank()) {
            result.text = "Bitte Zahl eigeben."
            return -2
        }
        val inputInt = input.text.toString().toInt()
        if(inputInt > 99 || inputInt < 1)
        {
            result.text = "Bitte Zahl eigeben."
            return 2
        }
        if(inputInt == lastGuess)
        {
            result.text = "Bitte Zahl eigeben."
            return 3
        }
        lastGuess = inputInt
        count++

        when {
            inputInt == rnd -> {
                result.text = "Gewonnen!"+ System.getProperty("line.separator") + "Versuch: " + count
                return 0
            }
            inputInt < rnd -> {
                result.text = "Gesuchte Zahl ist größer."+ System.getProperty("line.separator") + "Versuch: " + count
                return -1
            }
            else -> {
                result.text = "Gesuchte Zahl ist kleiner."+ System.getProperty("line.separator") + "Versuch: " + count
                return 1
            }
        }
    }
}