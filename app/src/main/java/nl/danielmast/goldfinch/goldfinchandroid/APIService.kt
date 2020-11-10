package nl.danielmast.goldfinch.goldfinchandroid

import nl.danielmast.goldfinch.goldfinchandroid.user.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("/users/{Id}")
    suspend fun getUser(@Path("Id") userId: String): Response<User>
}