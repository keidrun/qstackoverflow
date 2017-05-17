/**
 * Copyright 2017 Keid
 */
package com.keidrun.qstackoverflow.lib

/**
 * Stack Overflow API's Serector.
 *
 * @author Keid
 */
interface SOSerector {

    enum class Format(val value: String) : SOSerector {
        DATE("yyyy-MM-dd");

        override fun toString(): String {
            return this.value
        }
    }

    enum class Order(val value: String) : SOSerector {
        DESC("desc"),
        ASC("asc");

        override fun toString(): String {
            return this.value
        }

        fun serectorOf(value: String): Order {
            for (v in Order.values()) {
                if (value.toLowerCase().equals(v.value)) {
                    return v
                }
            }
            return Order.DESC
        }
    }

    enum class Sort(val value: String) : SOSerector {
        ACTIVITY("activity"),
        VOTES("votes"),
        CREATION("creation"),
        RELEVANCE("relevance");

        override fun toString(): String {
            return this.value
        }

        fun serectorOf(value: String): Sort {
            for (v in Sort.values()) {
                if (value.toLowerCase().equals(v.value)) {
                    return v
                }
            }
            return Sort.ACTIVITY
        }
    }

    enum class Site(val value: String, val siteName: String) : SOSerector {
        ENGLISH("en", "stackoverflow"),
        JAPANESE("ja", "ja.stackoverflow");

        override fun toString(): String {
            return this.value
        }

        fun serectorOf(value: String): Site {
            for (v in Site.values()) {
                if (value.toLowerCase().equals(v.value)) {
                    return v
                }
            }
            return Site.ENGLISH
        }
    }

}