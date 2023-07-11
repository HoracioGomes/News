package com.example.news.data.util

import com.example.news.data.model.APIResponse
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