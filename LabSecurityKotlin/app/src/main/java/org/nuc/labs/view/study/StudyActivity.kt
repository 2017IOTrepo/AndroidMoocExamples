package org.nuc.labs.view.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.nuc.labs.databinding.ActivityStudyBinding
import org.nuc.labs.db.model.Question

class StudyActivity : AppCompatActivity() {
    private val model by viewModels<StudyViewModel>()
    protected lateinit var binding: ActivityStudyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.getquestionList().observe(this, Observer {
            bindQuestions(it.get(0))
        })
    }

    fun bindQuestions(question: Question) {
        Log.d("zhazha", question.toString())
        binding.titleTextView.text = question.tiContent
        binding.a.text = question.tiA
        binding.b.text = question.tiB
        binding.c.text = question.tiC
        binding.d.text = question.tiD
    }
}
