package com.example.data.remote.api

import android.content.Context
import com.example.data.R
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

internal class MockInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
        val resourceId = when (val path = url.getLastPathSegment()) {
            "tasks.json" -> R.raw.mock_tasks_response

            "add_task" -> {
                return buildResponse("{}", 200, chain.request())
            }

            else -> {
                throw Exception("Unknown resource $path")
            }
        }

        val response = loadMockResponse(resourceId)
        return buildResponse(response, 200, chain.request())
    }

    private fun buildResponse(responseBody: String, code: Int, request: Request): Response {
        return Response.Builder()
            .code(code)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .body(responseBody.toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }

    private fun loadMockResponse(resourceId: Int): String {
        val inputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }.also {
            require(it.isNotEmpty()) { "JSON file resource should not be empty" }
        }
    }

    private fun HttpUrl.getLastPathSegment(): String = this.pathSegments.last()
}