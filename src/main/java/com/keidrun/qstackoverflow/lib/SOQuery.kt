/**
 * Copyright 2017 Keid
 */
package com.keidrun.qstackoverflow.lib

import com.github.kevinsawicki.http.HttpRequest

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

    fun buildUrl(params: SOParams): String {

        var searchUrl: String = "https://api.stackexchange.com/$apiVersion/search"
        var isFirst: Boolean = true
        for (param in params.toMap()) {
            if (isFirst) {
                searchUrl += "?${param.key}=${param.value}"
                isFirst = false
            } else {
                searchUrl += "&${param.key}=${param.value}"
            }
        }
        return searchUrl

    }

    override fun search(params: SOParams): String {

        val res = HttpRequest.get(url)
                .acceptGzipEncoding().uncompress(true)

        return res.body()

    }

}