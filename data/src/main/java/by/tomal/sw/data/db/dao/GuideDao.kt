package by.tomal.sw.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import by.tomal.sw.data.entity.DataConstants.monsterGuideTableName
import by.tomal.sw.data.entity.MonsterGuideResponse
import io.reactivex.Single

@Dao
interface GuideDao {

    @Query("SELECT * FROM $monsterGuideTableName")
    fun get(): Single<List<MonsterGuideResponse>>

    @Query("SELECT * FROM $monsterGuideTableName WHERE monsterAwakenName=:awakenName")
    fun getMonsterByAwakenName(awakenName: String): Single<MonsterGuideResponse>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(monsters: List<MonsterGuideResponse>)
}