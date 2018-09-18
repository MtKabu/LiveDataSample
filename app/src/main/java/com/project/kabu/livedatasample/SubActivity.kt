package com.project.kabu.livedatasample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class SubActivity : AppCompatActivity() {

    val timer: Timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()
        timer.schedule(object: TimerTask() {
            override fun run() {
                handler.post {
                    ClockTextView.text = Date().toString()
                }
            }
        }, 10000, 1000)
    }

}