package com.example.component.data.service

import com.example.component.data.model.ResRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface ComposeStudyService {

    @GET("users/{user}/repos")
    suspend fun listRepos(
        @Path("user") user: String
    ): List<ResRepo>
}