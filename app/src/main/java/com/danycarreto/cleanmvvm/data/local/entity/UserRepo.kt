package com.danycarreto.cleanmvvm.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_repo_table")
data class UserRepo(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    @ColumnInfo(name = "description")
    val description:String,
    val name:String,
    @ColumnInfo(name= "full_name")
    val fullName:String,
    val url:String
)