/**
 * Copyright 2017 Keid
 */
package com.keidrun.qstackoverflow.lib

/**
 * API's Query.
 *
 * @author Keid
 */
interface Query<P> {

    var url: String

    fun search(params: P): String

}