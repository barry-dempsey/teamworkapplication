package com.dempsey.teamworkapp.dao.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dempsey.teamworkapp.data.ProjectEntity

private const val DATABASE_NAME = "teamwork_user_db"

@Database(
        entities = [ProjectEntity::class],
        version = 1, exportSchema = false
)

abstract class UserDatabase : RoomDatabase() {
    abstract fun localDao(): LocalDao
}

object DataBaseProvider {

    private var mInstance: UserDatabase? = null

    fun getInstance(context: Context) : UserDatabase {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    DATABASE_NAME
            ).build()
        }

        return mInstance!!
    }
}
