package com.bbeniful.basekmpshareduiapicall

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual val httpClient: HttpClientEngine
    get() = OkHttp.create()