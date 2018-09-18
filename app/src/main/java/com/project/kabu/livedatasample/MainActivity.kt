package com.project.kabu.livedatasample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.util.*

/**
 * LiveDataとは、ライフサイクルに応じて自動的に監視、監視状態を解除してくれるもの
 * 今までUI側は、データの変更を検知して自身を書き換えていた。
 * そのため、その検知する処理や検知をやめる処理を書かなければならなかった。
 * それらが、(AACのLifecycleに深く結びつきながら、)LiveDataにより自動でやってくれる
 *
 * LiveDataが機能しているかを確認するために、LiveDataを使っていないSubActivityを作った
 * AndroidManifestを書き換えて使用する
 *
 * 今回のサンプルでは、最初"HelloWorld"が表示された後、10秒後に日時更新を開始する。
 * LiveDataを使用した場合、Activityのライフサイクルに依存しなくなるので、
 * 画面回転したとき、そのまま日時の更新が続く。
 * LiveDataを使用しなかった場合、Activityのライフサイクルに依存するため、
 * 画面回転したとき、日時の更新が再度行われる。なので、画面回転すると、"HelloWorld"が再度表示される
 *
 * 参考１：http://kaleidot725.hatenablog.com/entry/2018/03/29/235304
 * 参考２：https://qiita.com/nukka123/items/f7af4445d737c821c24c
 */
class MainActivity : AppCompatActivity() {

    lateinit var clockViewModel: ClockViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewModelProviders.of(activity).get(ViewModelのクラス)にて、ViewModelのインスタンスを生成する
        // これでViewModelProvidersがViewModelのライフサイクルを管理してくれるので、画面を回転させても問題なくなる
        clockViewModel = ViewModelProviders.of(this).get(ClockViewModel::class.java)
        // 上で生成したViewModelインスタンスのLiveDataを購読する
        // ClockViewModelにて、Timerクラスによりデータ更新が行われる
        // その更新されたことをonChanged()で受け取り、情報をTextViewに反映する
        clockViewModel.localDateTime.observe(this, object :Observer<Date>{
            override fun onChanged(t: Date?) {
                ClockTextView.text = t.toString()
            }
        })
    }
}
