package com.example.gentevent.network

import com.example.gentevent.model.Events
import retrofit2.http.GET
import retrofit2.http.Headers

interface EventApiService {
    @Headers("x-hasura-admin-secret: zhYR6GbBEHM3WwGCK2KeLh6rM4ROFH37Qv0IZxV6xWOXCKWdde3C4HrYnOVwcQi2")
    @GET("events")
    suspend fun getEvents(): Events

    //normaly the api key must be hidden in a .env file or something like that
}