/**
 * Copyright 2017 Keid
 */
package com.keidrun.qstackoverflow.lib

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Stack Overflow Search API's Parameters.
 *
 * @author Keid
 */
class SOParams(val _inTitle: String, val _tagged: String) {

    init {
        require(_inTitle.isNotEmpty() || _tagged.isNotEmpty())
    }

    var page: Int? = null
    var pageSize: Int? = null
    var fromDate: Date? = null
    var toDate: Date? = null
    var order: SOSerector = SOSerector.Order.DESC
    var minDate: Date? = null
    var maxDate: Date? = null
    var sort: SOSerector = SOSerector.Sort.ACTIVITY
    var tagged: String = _tagged
    var noTagged: String = ""
    var inTitle: String = _inTitle
    var site: SOSerector = SOSerector.Site.ENGLISH

    fun toMap(): Map<String, String> {

        var map: MutableMap<String, String> = mutableMapOf()
        if (this.page != null) map.set("page", this.page.toString())
        if (this.pageSize != null) map.set("pagesize", this.pageSize.toString())
        if (this.fromDate != null) map.set("fromdate", this.fromDate?.time?.div(1000).toString())
        if (this.toDate != null) map.set("todate", this.toDate?.time?.div(1000).toString())
        map.set("order", this.order.toString())
        if (this.minDate != null) map.set("min", this.minDate?.time?.div(1000).toString())
        if (this.maxDate != null) map.set("max", this.maxDate?.time?.div(1000).toString())
        map.set("sort", this.sort.toString())
        if (this.tagged.isNotEmpty()) map.set("tagged", this.tagged)
        if (this.noTagged.isNotEmpty()) map.set("notagged", this.noTagged)
        if (this.inTitle.isNotEmpty()) map.set("intitle", this.inTitle)
        map.set("site", (this.site as SOSerector.Site).siteName)

        return map
    }

    fun parseDate(date: String): Date? {

        try {
            return SimpleDateFormat(SOSerector.Format.DATE.toString()).parse(date)
        } catch (e: ParseException) {
            return null
        }

    }

}