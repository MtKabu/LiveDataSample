package com.project.kabu.livedatasample

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import java.time.LocalDateTime
import java.util.*

/**
 * Dateクラスから受け取った日時情報を、1秒おきに通知するLiveDataを含んだViewModel（初回10秒後）
 *
 * Timerクラスの使い方
 * http://java-code.jp/282
 */
class ClockViewModel : ViewModel() {
    // LiveDate
    val localDateTime: MutableLiveData<Date> = MutableLiveData()
    val timer: Timer = Timer()

    init {
        // Timerクラスにより、1秒おきにDateクラスから日時情報を取得
        // その日時情報をLiveDataオブジェクトに設定（.postValue()）
        timer.schedule(object: TimerTask() {
            override fun run() {
                localDateTime.postValue(Date())
            }
        }, 10000, 1000)
    }

}