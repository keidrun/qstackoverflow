/**
 * Copyright 2017 Keid
 */
package com.keidrun.qstackoverflow.lib

import com.github.kevinsawicki.http.HttpRequest
import org.apache.commons.codec.net.URLCodec
import java.nio.charset.StandardCharsets

/**
 * Stack Overflow API's Query.
 *
 * @author Keid
 */
class SOQuery(params: SOParams, val apiVersion: String) : Query<SOParams> {

    constructor(params: SOParams) : this(params, "2.2")

    override var url: String = ""

    init {
        url = buildUrl(params)
    }

    private fun buildUrl(params: SOParams): String {

        var searchUrl: String = "https://api.stackexchange.com/$apiVersion/search"
        val codec: URLCodec = URLCodec(StandardCharsets.UTF_8.toString())

        var isFirst: Boolean = true
        for (param in params.toMap()) {
            searchUrl += if (isFirst) "?" else "&"
            isFirst = false

            searchUrl += if (param.value is String) "${param.key}=${codec.encode(param.value)}"
            else "${param.key}=${param.value}"
        }

        return searchUrl

    }

    override fun search(params: SOParams): String {

        val res = HttpRequest.get(url)
                .acceptGzipEncoding().uncompress(true)

        return res.body()

    }

}