package org.nuc.labs.view.study

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import org.nuc.labs.repo.QuestionRepository

class StudyViewModel(application: Application) : AndroidViewModel(application) {
    private val questionRepository = QuestionRepository(application.applicationContext)

    fun getquestionList() = questionRepository.getListLiveData()

}