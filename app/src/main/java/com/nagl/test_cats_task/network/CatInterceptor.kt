package com.nagl.test_cats_task.network

import okhttp3.Interceptor
import okhttp3.Response

class CatInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("x-api-key", "live_r4PYFFHwfGbX5HizUiv0d9Vtg2E6psMJ5yISMlngaOev4llQ2JDHuNjKj7JagoXA") // TODO: load api key from build config
        return chain.proceed(requestBuilder.build())
    }
}