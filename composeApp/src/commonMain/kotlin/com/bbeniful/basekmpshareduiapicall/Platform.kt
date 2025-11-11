package com.bbeniful.basekmpshareduiapicall

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform