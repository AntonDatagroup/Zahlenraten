package de.datagroup.zahlenraten

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import kotlinx.android.synthetic.main.activity_second.*
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
                exitTransition = Explode()
                enterTransition = Explode()
            }
        }

        setContentView(R.layout.activity_second)

        // display number of guesses
        textMid.text = textMid.text.toString() + System.getProperty("line.separator") +
                "Versuche: " + intent.getStringExtra("gameHandlerGuessCount")

        // download win image
        GlideApp.with(this)
            .load("https://image.ibb.co/bNhsAA/Gewonnen.png")
            .into(imgGewonnen)

        startImgAnimation()
    }

    fun startImgAnimation() {
        ObjectAnimator.ofFloat(imgGewonnen, "translationY", 180f).apply {
            duration = 500
            repeatCount = 10
            repeatMode = ValueAnimator.REVERSE
            start()
        }
    }
}

@GlideModule
class AppGlideModule : AppGlideModule()
