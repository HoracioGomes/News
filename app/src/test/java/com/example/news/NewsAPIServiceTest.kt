package com.example.news

import com.example.news.data.api.NewsAPIService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {
    private lateinit var webServer: MockWebServer
    private lateinit var service: NewsAPIService

    @Before
    fun setUp() {
        webServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(webServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
        equeueMockResponse("newsresponse.json")
    }

    @Test
    fun getNews_sendRequest_expectedResponse() {
        runBlocking {
            val body = service.getNews(page = 1, country = "us").body()
            val request = webServer.takeRequest()
            assertThat(body).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?apiKey=34a065c23cc3415bb272582ddcfc2b01&page=1&country=us")
        }
    }

    @Test
    fun getNews_sendRequest_correctContentSize() {
        runBlocking {
            val body = service.getNews(page = 1, country = "us").body()
            val articlesList = body?.articles
            assertThat(articlesList?.size).isEqualTo(3)
        }
    }

    @Test
    fun getNews_sendRequest_correctIsCorrect() {
        runBlocking {
            val body = service.getNews(page = 1, country = "us").body()
            val article = body?.articles?.get(1)
            assertThat(article?.author).isEqualTo("Andrew Cunningham")
            assertThat(article?.title).isEqualTo("Windows 95, 98, and other decrepit versions can grab online updates again - Ars Technica")
        }
    }

    @After
    fun tearDown() {
        webServer.shutdown()
    }

    private fun equeueMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader.getResourceAsStream(fileName)
        val buffer = inputStream.source().buffer()
        val response = MockResponse()
        response.setBody(buffer.readString(Charsets.UTF_8))
        webServer.enqueue(response)
    }
}