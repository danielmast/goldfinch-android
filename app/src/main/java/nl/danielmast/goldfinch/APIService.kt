package nl.danielmast.goldfinch

import nl.danielmast.goldfinch.user.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("/users/{Id}")
    suspend fun getUserById(@Path("Id") userId: String): Response<User>

    @GET("/users/username/{Username}")
    suspend fun getUserByUsername(@Path("Username") username: String): Response<User>

    @GET("/users/")
    suspend fun getUsers(): Response<List<User>>
}