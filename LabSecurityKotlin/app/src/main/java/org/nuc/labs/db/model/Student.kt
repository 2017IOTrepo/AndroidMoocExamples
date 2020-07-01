package org.nuc.labs.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stud")
data class Student(
    @PrimaryKey @ColumnInfo(name = "stuno") var id: Int,
    @ColumnInfo(name = "stunumber") var stuNumber: String,
    @ColumnInfo(name = "stupwd") var stuPwd: String,
    @ColumnInfo(name = "stuname") var stuName: String,
    @ColumnInfo(name = "studep") var stuDep: String,
    @ColumnInfo(name = "testids") var testIds: String,
    @ColumnInfo(name = "answer") var answer: String,
    @ColumnInfo(name = "stuanswer") var stuAnswer: String,
    @ColumnInfo(name = "stutime") var stuTime: String,
    @ColumnInfo(name = "stuscore") var stuScore: String
)