/**
 * Copyright 2017 Keid
 */
package com.keidrun.qstackoverflow.lib

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SOQueryTest {

    @Test fun testSearch() {
        var params: SOParams = SOParams("rule", "kotlin")
        params.page = 1
        params.pageSize = 10
        val sut: Query<SOParams> = SOQuery(params)

        val actual: String = sut.search(params)

        assertTrue { actual.isNotEmpty() }
    }
}