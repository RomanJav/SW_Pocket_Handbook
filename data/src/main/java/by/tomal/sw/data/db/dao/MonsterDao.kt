package by.tomal.sw.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import by.tomal.sw.data.entity.DataConstants.monsterTableId
import by.tomal.sw.data.entity.DataConstants.monsterTableName
import by.tomal.sw.data.entity.MonsterListResponse
import io.reactivex.Single

@Dao
interface MonsterDao {

    @Query("SELECT * FROM $monsterTableName")
    fun get(): Single<List<MonsterListResponse>>

    @Query("SELECT * FROM $monsterTableName WHERE element=:element")
    fun getMonstersByElement(element: String): Single<List<MonsterListResponse>>

    @Query("SELECT * FROM $monsterTableName WHERE name_awaken=:awakenName")
    fun getMonsterByAwakenName(awakenName: String): Single<MonsterListResponse>

    @Query("SELECT * FROM $monsterTableName WHERE $monsterTableId = :id LIMIT 1")
    fun getById(id: Int): Single<MonsterListResponse>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(monsters: List<MonsterListResponse>)
}