package com.example.news.util

import com.example.news.data.model.APIResponse
import com.example.news.data.repository.Resource
import retrofit2.Response

class Converters {

    companion object {
        fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }
}