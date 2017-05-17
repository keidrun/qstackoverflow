/**
 * Copyright 2017 Keid
 */
package com.keidrun.qstackoverflow.lib

import org.junit.Test
import kotlin.test.assertEquals

class SOSerectorTest {

    @Test fun testFormatDate() {
        val sut: SOSerector = SOSerector.Format.DATE
        val expected: String = "yyyy-MM-dd"

        val actual: String = sut.toString()

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun testOrderDesc() {
        val sut: SOSerector = SOSerector.Order.DESC
        val expected: String = "desc"

        val actual: String = sut.toString()

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun testOrderAsc() {
        val sut: SOSerector = SOSerector.Order.ASC
        val expected: String = "asc"

        val actual: String = sut.toString()

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun searchOrderDesc() {
        val expected: SOSerector = SOSerector.Order.DESC
        val arg: String = "desc"

        val actual: SOSerector = SOSerector.Order.DESC.serectorOf(arg)

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun searchOrderAsc() {
        val expected: SOSerector = SOSerector.Order.ASC
        val arg: String = "asc"

        val actual: SOSerector = SOSerector.Order.DESC.serectorOf(arg)

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun searchOrderDefault() {
        val expected: SOSerector = SOSerector.Order.DESC
        val arg: String = "hoge"

        val actual: SOSerector = SOSerector.Order.DESC.serectorOf(arg)

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun testSortActivity() {
        val sut: SOSerector = SOSerector.Sort.ACTIVITY
        val expected: String = "activity"

        val actual: String = sut.toString()

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun testSortVotes() {
        val sut: SOSerector = SOSerector.Sort.VOTES
        val expected: String = "votes"

        val actual: String = sut.toString()

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun testSortCreation() {
        val sut: SOSerector = SOSerector.Sort.CREATION
        val expected: String = "creation"

        val actual: String = sut.toString()

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun testSortRelevance() {
        val sut: SOSerector = SOSerector.Sort.RELEVANCE
        val expected: String = "relevance"

        val actual: String = sut.toString()

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun searchSortActivity() {
        val expected: SOSerector = SOSerector.Sort.ACTIVITY
        val arg: String = "activity"

        val actual: SOSerector = SOSerector.Sort.ACTIVITY.serectorOf(arg)

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun searchSortVotes() {
        val expected: SOSerector = SOSerector.Sort.VOTES
        val arg: String = "votes"

        val actual: SOSerector = SOSerector.Sort.ACTIVITY.serectorOf(arg)

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun searchSortCreation() {
        val expected: SOSerector = SOSerector.Sort.CREATION
        val arg: String = "creation"

        val actual: SOSerector = SOSerector.Sort.ACTIVITY.serectorOf(arg)

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun searchSortRelevance() {
        val expected: SOSerector = SOSerector.Sort.RELEVANCE
        val arg: String = "relevance"

        val actual: SOSerector = SOSerector.Sort.ACTIVITY.serectorOf(arg)

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun searchSortDefault() {
        val expected: SOSerector = SOSerector.Sort.ACTIVITY
        val arg: String = "hoge"

        val actual: SOSerector = SOSerector.Sort.ACTIVITY.serectorOf(arg)

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun testSiteEnglish() {
        val sut: SOSerector = SOSerector.Site.ENGLISH
        val expected: String = "en"

        val actual: String = sut.toString()

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun testSiteJapanese() {
        val sut: SOSerector = SOSerector.Site.JAPANESE
        val expected: String = "ja"

        val actual: String = sut.toString()

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun searchSiteEnglish() {
        val expected: SOSerector = SOSerector.Site.ENGLISH
        val arg: String = "en"

        val actual: SOSerector = SOSerector.Site.ENGLISH.serectorOf(arg)

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun searchSiteJapanese() {
        val expected: SOSerector = SOSerector.Site.JAPANESE
        val arg: String = "ja"

        val actual: SOSerector = SOSerector.Site.ENGLISH.serectorOf(arg)

        assertEquals(expected = expected, actual = actual)
    }

    @Test fun searchSiteDefault() {
        val expected: SOSerector = SOSerector.Site.ENGLISH
        val arg: String = "hoge"

        val actual: SOSerector = SOSerector.Site.ENGLISH.serectorOf(arg)

        assertEquals(expected = expected, actual = actual)
    }

}