package org.nuc.labs.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tiku")
data class Question(
    @PrimaryKey @ColumnInfo(name = "tino") var id: Int,
    @ColumnInfo(name = "ticontent") var tiContent: String,
    @ColumnInfo(name = "ti1") var tiA: String,
    @ColumnInfo(name = "ti2") var tiB: String,
    @ColumnInfo(name = "ti3") var tiC: String,
    @ColumnInfo(name = "ti4") var tiD: String,
    @ColumnInfo(name = "tianswer") var tiAnswer: String,
    @ColumnInfo(name = "ticlassid") var tiClassId: Int
)