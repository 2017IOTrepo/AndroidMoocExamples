package org.nuc.labs.db.dao

import androidx.room.*
import org.nuc.labs.db.model.Question

@Dao
interface QuestionDao {
    @Insert
    suspend fun insertQuestion(vararg questions: Question)// 注: vararg->可变参数s

    @Update
    suspend fun updateQuestion(vararg questions: Question)

    @Delete
    suspend fun deleteQuestion(vararg questions: Question)

    @Query("select * from tiku")
    suspend fun getAllQuestionData(): List<Question>
}