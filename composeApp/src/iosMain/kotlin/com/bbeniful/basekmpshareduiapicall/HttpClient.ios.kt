package com.bbeniful.basekmpshareduiapicall

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual val httpClient: HttpClientEngine
    get() = Darwin.create()