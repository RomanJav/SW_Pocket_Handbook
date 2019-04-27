package by.tomal.sw.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import by.tomal.sw.data.db.dao.GuideDao
import by.tomal.sw.data.db.dao.MonsterDao
import by.tomal.sw.data.entity.MonsterGuideResponse
import by.tomal.sw.data.entity.MonsterListResponse

@Database(entities = [MonsterListResponse::class, MonsterGuideResponse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "monstersDb"

        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            ).build()
        }
    }

    abstract fun getMonsterDao(): MonsterDao

    abstract fun getGuideDao(): GuideDao
}