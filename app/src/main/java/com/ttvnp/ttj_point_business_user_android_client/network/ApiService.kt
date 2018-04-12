package com.ttvnp.ttj_point_business_user_android_client.network

import com.ttvnp.ttj_point_business_user_android_client.model.*
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {
    @Multipart
    @POST("api/v1/image")
    fun uploadImage(@Part original: MultipartBody.Part): Single<ImageModel>

    @FormUrlEncoded
    @POST("api/v1/customer/auth")
    fun login(@Field("email") email: String, @Field("password") password: String): Single<CustomerAuthModel>

    @FormUrlEncoded
    @POST("api/v1/customer/registration")
    fun signUp(@Field("email") email: String, @Field("password") password: String): Completable

    @FormUrlEncoded
    @POST("api/v1/customer/activation")
    fun activate(@Field("email") email: String, @Field("activation_code") activationCode: String): Completable

    @GET("api/v1/shop")
    fun getCampaigns(): Single<List<ShopModel>>

    @GET("api/v1/shop/{id}")
    fun getCampaign(@Path("id") campaignId: String): Single<ShopModel>

    @GET("api/v1/customer/me")
    fun getProfile(): Single<ProfileModel>

    @PUT("api/v1/customer/me")
    fun updateProfile(@Body profile: ProfileModel): Completable

    @GET("api/v1/ranking")
    fun getRanking(): Single<List<RankingItemModel>>

    @GET("api/v1/customer/lottery-result")
    fun getPrizes(): Single<List<PrizeItemModel>>

    @POST("api/v1/shop/transaction/use")
    fun usePoint(@Body pointUse: UsePointModel): Single<TransactionModel>
}
