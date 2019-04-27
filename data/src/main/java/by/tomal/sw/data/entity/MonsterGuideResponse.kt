package by.tomal.sw.data.entity


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import by.tomal.sw.data.entity.DataConstants.monsterGuideTableName
import by.tomal.sw.domain.entity.MonsterGuide
import com.google.gson.annotations.SerializedName

@Entity(tableName = monsterGuideTableName)
data class MonsterGuideResponse(
    @PrimaryKey
    @SerializedName("monsterAwakenName")
    val monsterAwakenName: String,
    @SerializedName("runeRecommendations")
    val runeRecommendations: String,
    @SerializedName("review")
    val review: String,
    @SerializedName("goodFor")
    val goodFor: String,
    @SerializedName("positives")
    val positives: String,
    @SerializedName("negatives")
    val negatives: String
)

fun MonsterGuideResponse.guideResponseToEntity() = MonsterGuide(
    monsterAwakenName,
    runeRecommendations,
    review,
    goodFor,
    positives,
    negatives
)