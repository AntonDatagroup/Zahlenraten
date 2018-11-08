package de.datagroup.zahlenraten


import android.app.ActivityOptions
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

import android.view.inputmethod.EditorInfo
import android.content.Intent
import android.os.Build
import android.view.KeyEvent

class MainActivity : AppCompatActivity() {

    private val gameHandler = GameHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        header.text = "Rate eine Zahl zwischen" + System.getProperty("line.separator") + " 1 und 99"
        ergebnis.text = "Bisher keine Zahl eingegeben"


        //trigger gameHandler on softkeyboard done action
        input.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                processGuess()
                true
            } else {
                false
            }
        }
    }

    //useful to enter guesses on keyboard
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                processGuess()
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }

    //Calls gameHandler and triggers secondActivity
    private fun processGuess() {
        if (gameHandler.handleGuess(input, ergebnis) == 0) {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("gameHandlerGuessCount", gameHandler.count.toString())

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val options = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(intent, options.toBundle())
            } else
                startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
