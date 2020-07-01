package org.nuc.labs.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.launch
import org.nuc.labs.db.dao.QuestionDao
import org.nuc.labs.db.database.QuestionDatabase
import org.nuc.labs.db.model.Question

class QuestionRepository constructor(context: Context) {
    private val questionDao: QuestionDao = QuestionDatabase.getInstance(context).getQuestionDao()
    private var allQuestionLiveData = liveData {
        val data = questionDao.getAllQuestionData()
        emit(data)
    }

    fun getListLiveData(): LiveData<List<Question>> {
        return allQuestionLiveData
    }

}