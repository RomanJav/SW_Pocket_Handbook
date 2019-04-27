package by.tomal.sw.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import by.tomal.sw.data.entity.DataConstants.monsterTableId
import by.tomal.sw.data.entity.DataConstants.monsterTableName
import by.tomal.sw.domain.entity.Monster
import by.tomal.sw.domain.entity.MonsterInfo
import com.google.gson.annotations.SerializedName

@Entity(tableName = monsterTableName)
data class MonsterListResponse(

    @SerializedName("objectId")
    @PrimaryKey
    @ColumnInfo(name = monsterTableId)
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("grade")
    val grade: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("name_awaken")
    val name_awaken: String,

    @SerializedName("element")
    val element: String,

    @SerializedName("awaken_bonus")
    val awakeBonus: String,

    @SerializedName("skill_first")
    val skillFirst: String,

    @SerializedName("skill_second")
    val skillSecond: String,

    @SerializedName("skill_third")
    val skillThird: String,

    @SerializedName("skill_forth_or_lead")
    val skillForth: String
)

fun MonsterListResponse.monsterListResponseToEntity() = Monster(
    id,
    name,
    name_awaken,
    icon
)

fun MonsterListResponse.monsterInfoResponseToEntity() = MonsterInfo(
    name,
    name_awaken,
    grade,
    type,
    icon,
    element,
    awakeBonus,
    skillFirst,
    skillSecond,
    skillThird,
    skillForth
)