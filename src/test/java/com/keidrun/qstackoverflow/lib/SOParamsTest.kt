/**
 * Copyright 2017 Keid
 */
package com.keidrun.qstackoverflow.lib

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SOParamsTest {

    @Test fun createWithMaxParams() {
        val intitle: String = "grammar"
        val tagged: String = "kotlin"

        val actual: SOParams = SOParams(intitle, tagged)

        assertEquals(expected = intitle, actual = actual.inTitle)
        assertEquals(expected = tagged, actual = actual.tagged)
    }

    @Test fun createWithIntitle() {
        val intitle: String = "grammar"
        val tagged: String = ""

        val actual: SOParams = SOParams(intitle, tagged)

        assertEquals(expected = intitle, actual = actual.inTitle)
        assertEquals(expected = tagged, actual = actual.tagged)
    }

    @Test fun createWithTagged() {
        val intitle: String = ""
        val tagged: String = "kotlin"

        val actual: SOParams = SOParams(intitle, tagged)

        assertEquals(expected = intitle, actual = actual.inTitle)
        assertEquals(expected = tagged, actual = actual.tagged)
    }

    @Test(expected = IllegalArgumentException::class) fun initRequiredError() {
        val intitle: String = ""
        val tagged: String = ""

        val actual: SOParams = SOParams(intitle, tagged)
    }

    @Test fun toMapWithConstructorArgs() {
        val intitle: String = "grammar"
        val tagged: String = "kotlin"
        val sut: SOParams = SOParams(intitle, tagged)

        val actual: Map<String, String> = sut.toMap()

        // constructor args
        assertTrue { actual.containsKey("intitle") }
        assertTrue { actual.containsKey("tagged") }
        // dafault
        assertTrue { actual.containsKey("order") }
        assertTrue { actual.containsKey("sort") }
        assertTrue { actual.containsKey("site") }
        // others
        assertFalse { actual.containsKey("page") }
        assertFalse { actual.containsKey("pagesize") }
        assertFalse { actual.containsKey("fromdate") }
        assertFalse { actual.containsKey("todate") }
        assertFalse { actual.containsKey("min") }
        assertFalse { actual.containsKey("max") }
        assertFalse { actual.containsKey("notagged") }
        // check values
        assertEquals(expected = intitle, actual = actual.get("intitle"))
        assertEquals(expected = tagged, actual = actual.get("tagged"))
        assertEquals(expected = "desc", actual = actual.get("order"))
        assertEquals(expected = "activity", actual = actual.get("sort"))
        assertEquals(expected = "stackoverflow", actual = actual.get("site"))
    }

    @Test fun toMapWithConstructorIntitle() {
        val intitle: String = "grammar"
        val tagged: String = ""
        val sut: SOParams = SOParams(intitle, tagged)

        val actual: Map<String, String> = sut.toMap()

        // constructor args
        assertTrue { actual.containsKey("intitle") }
        assertFalse { actual.containsKey("tagged") }
        // check values
        assertEquals(expected = intitle, actual = actual.get("intitle"))
    }

    @Test fun toMapWithConstructorTagged() {
        val intitle: String = ""
        val tagged: String = "kotlin"
        val sut: SOParams = SOParams(intitle, tagged)

        val actual: Map<String, String> = sut.toMap()

        // constructor args
        assertFalse { actual.containsKey("intitle") }
        assertTrue { actual.containsKey("tagged") }
        // check values
        assertEquals(expected = tagged, actual = actual.get("tagged"))
    }

    @Test fun toMapWithChangedDefaultValues() {
        val intitle: String = "grammar"
        val tagged: String = "kotlin"
        val sut: SOParams = SOParams(intitle, tagged)
        sut.order = SOSerector.Order.ASC
        sut.sort = SOSerector.Sort.VOTES
        sut.site = SOSerector.Site.JAPANESE

        val actual: Map<String, String> = sut.toMap()

        // dafault
        assertTrue { actual.containsKey("order") }
        assertTrue { actual.containsKey("sort") }
        assertTrue { actual.containsKey("site") }
        // check values
        assertEquals(expected = "asc", actual = actual.get("order"))
        assertEquals(expected = "votes", actual = actual.get("sort"))
        assertEquals(expected = "ja.stackoverflow", actual = actual.get("site"))
    }

    @Test fun toMapWithOthers() {
        val intitle: String = "grammar"
        val tagged: String = "kotlin"
        val sut: SOParams = SOParams(intitle, tagged)
        sut.page = 2
        sut.pageSize = 39
        sut.fromDate = sut.parseDate("2015-1-1")
        sut.toDate = sut.parseDate("2017-1-1")
        sut.minDate = sut.parseDate("2014-1-1")
        sut.maxDate = sut.parseDate("2018-1-1")
        sut.noTagged = "scala"

        val actual: Map<String, String> = sut.toMap()

        // others
        assertTrue { actual.containsKey("page") }
        assertTrue { actual.containsKey("pagesize") }
        assertTrue { actual.containsKey("fromdate") }
        assertTrue { actual.containsKey("todate") }
        assertTrue { actual.containsKey("min") }
        assertTrue { actual.containsKey("max") }
        assertTrue { actual.containsKey("notagged") }
        // check values
        assertEquals(expected = "2", actual = actual.get("page"))
        assertEquals(expected = "39", actual = actual.get("pagesize"))
        assertEquals(expected = "1420038000", actual = actual.get("fromdate"))
        assertEquals(expected = "1483196400", actual = actual.get("todate"))
        assertEquals(expected = "1388502000", actual = actual.get("min"))
        assertEquals(expected = "1514732400", actual = actual.get("max"))
        assertEquals(expected = "scala", actual = actual.get("notagged"))
    }

    @Test fun testParseDate() {
        val intitle: String = "grammar"
        val tagged: String = "kotlin"
        val sut: SOParams = SOParams(intitle, tagged)
        val dateArg: String = "2017-12-30"
        val expected: Date? = SimpleDateFormat("yyyy-MM-dd").parse(dateArg)

        val actual: Date? = sut.parseDate(dateArg)

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun parseDateError() {
        val intitle: String = "grammar"
        val tagged: String = "kotlin"
        val sut: SOParams = SOParams(intitle, tagged)
        val dateArg: String = "2017/12/30"
        val expected: Date? = null

        val actual: Date? = sut.parseDate(dateArg)

        assertEquals(expected = expected, actual = actual)
    }

}