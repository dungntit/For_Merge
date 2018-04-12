package com.ttvnp.ttj_point_business_user_android_client.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonDefaultValueString
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable


@JsonSerializable
data class ShopModel(
        val id: Int,
        val name: String,
        val email: String,
        val address: String,
        val receipt: Boolean,
        val use: Boolean,
        @Json(name = "campaign_name") val campaignName: String,
        @Json(name = "campaign_description") val campaignDescription: String,
        @Json(name = "campaign_image") val campaignImage: ImageModel?
) : Serializable

@JsonSerializable
data class AccountModel(
        val point: Long,
        val coin: Long
) : Serializable

@JsonSerializable
data class ProfileModel(
        val id: String?,
        val email: String?,
        @Json(name = "first_name") val firstName: String,
        @Json(name = "middle_name") val middleName: String?,
        @Json(name = "last_name") val lastName: String,
        @Json(name = "icon_id") val iconId: Int?,
        val icon: ImageModel?,
        val account: AccountModel
) : Serializable {
    val name: String
        get() = "${this.firstName} ${this.middleName ?: ""} ${this.lastName}"
}

@JsonSerializable
data class RankingItemModel(
        val order: Int,
        val customer: ProfileModel
)

@JsonSerializable
data class BonusModel(
        val name: String,
        val description: String
)

@JsonSerializable
data class BonusItemModel(
        val name: String,
        val description: String,
        val image: ImageModel?
)

@JsonSerializable
data class PrizeItemModel(
        val bonus: BonusModel,
        @Json(name = "bonus_item") val bonusItem: BonusItemModel,
        @Json(name = "bonus_item_count") val bonusItemCount: Int
) : Serializable

@JsonSerializable
data class ImageModel(
        val id: Int?,
        @Json(name = "file_name") val fileName: String?,
        val original: String,
        @Json(name = "small_thumbnail") val smallThumbnail: String?,
        @Json(name = "medium_thumbnail") val mediumThumbnail: String?,
        @Json(name = "large_thumbnail") val largeThumbnail: String?
) : Serializable

data class TabModel(
        val labelId: Int,
        val iconId: Int
) : Serializable

data class CustomerAuthModel(
        @JsonDefaultValueString("")
        val token: String
) : Serializable

data class UsePointModel(
        val point: Long
) : Serializable

@JsonSerializable
data class TransactionModel(
        val uuid: String
) : Serializable