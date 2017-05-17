/**
 * Copyright 2017 Keid
 */
package com.keidrun.qstackoverflow.app

import org.junit.Rule
import org.junit.Test
import org.junit.contrib.java.lang.system.ExpectedSystemExit
import java.io.IOException

class AppTest {

    fun ping(): Boolean {
        try {
            // If internet connected or disconnected.
            return ProcessBuilder("ping", "-c", "1", "-t", "3000", "api.stackexchange.com").start().waitFor() == 0
        } catch (e: IOException) {
            // Assume that internet connected if it failed.
            return true
        }
    }

    @get:Rule
    val exit = ExpectedSystemExit.none()

    @Test fun requiredArgs() {
        if (ping()) exit.expectSystemExitWithStatus(0) else exit.expectSystemExit()
        val args: Array<String> = arrayOf(
                "testing",
                "-v"
        )

        main(args)
    }

    @Test fun requiredArgsInJapanese() {
        if (ping()) exit.expectSystemExitWithStatus(0) else exit.expectSystemExit()
        val args: Array<String> = arrayOf(
                "テスト",
                "-l", "jp",
                "-v"
        )

        main(args)
    }

    @Test fun requiredArgsNoVerbose() {
        if (ping()) exit.expectSystemExitWithStatus(0) else exit.expectSystemExit()
        val args: Array<String> = arrayOf(
                "testing"
        )

        main(args)
    }

    @Test fun allArgs() {
        if (ping()) exit.expectSystemExitWithStatus(0) else exit.expectSystemExit()
        val args: Array<String> = arrayOf(
                "testing",
                "-p", "5",
                "-z", "10",
                "-f", "2015-1-1",
                "-t", "2017-1-1",
                "-o", "desc",
                "-min", "2014-1-1",
                "-max", "2018-1-1",
                "-s", "desc",
                "-g", "javascript",
                "-n", "Node.js",
                "-l", "en",
                "-v"
        )

        main(args)
    }

    @Test fun allArgsInJapanese() {
        if (ping()) exit.expectSystemExitWithStatus(0) else exit.expectSystemExit()
        val args: Array<String> = arrayOf(
                "テスト",
                "-p", "5",
                "-z", "10",
                "-f", "2015-1-1",
                "-t", "2017-1-1",
                "-o", "desc",
                "-min", "2014-1-1",
                "-max", "2018-1-1",
                "-s", "desc",
                "-g", "ジャバ",
                "-n", "ノード",
                "-l", "ja",
                "-v"
        )

        main(args)
    }

    @Test fun tooManyArgs() {
        if (ping()) exit.expectSystemExitWithStatus(0) else exit.expectSystemExit()
        val args: Array<String> = arrayOf(
                "testing",
                "-p", "5",
                "-z", "10",
                "-f", "2015-1-1",
                "-t", "2017-1-1",
                "-o", "desc",
                "-min", "2014-1-1",
                "-max", "2018-1-1",
                "-s", "desc",
                "-g", "javascript",
                "-n", "Node.js",
                "-l", "ja",
                "hoge",
                "fuga",
                "-v"
        )

        main(args)
    }

    @Test fun help() {
        if (ping()) exit.expectSystemExitWithStatus(0) else exit.expectSystemExit()
        val args: Array<String> = arrayOf(
                "-h"
        )

        main(args)
    }

    @Test fun noArgs() {
        if (ping()) exit.expectSystemExitWithStatus(0) else exit.expectSystemExit()
        val args: Array<String> = arrayOf()

        main(args)
    }

    @Test fun parseError() {
        exit.expectSystemExitWithStatus(1)
        val args: Array<String> = arrayOf(
                "testing",
                "--hoge"
        )

        main(args)
    }

    @Test fun missingOptionError() {
        exit.expectSystemExitWithStatus(1)
        val args: Array<String> = arrayOf(
                "testing",
                "-p"
        )

        main(args)
    }

    @Test fun internetDisconnectError() {
        if (ping()) exit.expectSystemExit() else exit.expectSystemExitWithStatus(1)
        val args: Array<String> = arrayOf(
                "testing"
        )

        main(args)
    }

    @Test fun unexpectedError() {
        exit.expectSystemExitWithStatus(1)
        val args: Array<String> = arrayOf(
                "-=fuga"
        )

        main(args)
    }

}