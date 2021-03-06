/**
 * Copyright 2017 Keid
 */
package com.keidrun.qstackoverflow.app

import com.github.kevinsawicki.http.HttpRequest
import com.keidrun.qstackoverflow.lib.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.apache.commons.cli.*
import kotlin.system.exitProcess

/**
 * Stack Overflow Search CLI.
 *
 * @author Keid
 */
fun main(args: Array<String>) {

    try {
        val options: Options = buildOptions()
        val cmd: CommandLine = DefaultParser().parse(options, args)

        val formatter: HelpFormatter = HelpFormatter()
        if ((cmd.args.isEmpty() && cmd.options.isEmpty()) || (cmd.options.size == 1 && cmd.hasOption("h"))) {
            formatter.printHelp("sos [title] [option...]", options);
            exitProcess(0)
        }

        val varboseFlag: Boolean = cmd.hasOption("v")
        if (varboseFlag) showArgs(cmd)

        val inTitle: String = if (cmd.args.isNotEmpty()) cmd.args[0]
        else throw IllegalArgumentException("Title is required")

        val params: SOParams = buildParams(inTitle, cmd)
        val query: Query<SOParams, String> = SOQuery(params)

        if (varboseFlag) {
            println("url:")
            print("    ")
            println("${query.url}")
            println()
            println("results:")
        }

        val res: String = query.search(params)

        if (cmd.hasOption("r")) {
            println(res)
        } else {
            val moshi = Moshi.Builder().build()
            val adapter: JsonAdapter<SOItems> = moshi.adapter(SOItems::class.java)
            val itemsObj: SOItems = adapter.fromJson(res) as SOItems
            if (cmd.hasOption("c")) {
                for (item in itemsObj.items) {
                    println("\"${item.question_id}\",\"${item.title}\",\"${item.link}\"")
                }
            } else {
                for (item in itemsObj.items) {
                    println("[${item.question_id}][${item.title}][${item.link}]")
                }
                println("Total: ${itemsObj.items.size} questions.")
            }
        }

        exitProcess(0)

    } catch (e: IllegalArgumentException) {
        println(e.message)
        exitProcess(1)
    } catch (e: ParseException) {
        print("Unknown arguments: ")
        for (arg in args) print("$arg ")
        println()
        exitProcess(1)
    } catch (e: MissingOptionException) {
        print("Unknown arguments: ")
        for (arg in args) print("$arg ")
        println()
        exitProcess(1)
    } catch (e: HttpRequest.HttpRequestException) {
        println("Internet disconnected:")
        println("Error Discription -> $e")
        exitProcess(1)
    } catch (e: Exception) {
        println("An unexpected error occurred:")
        println("Error Discription -> $e")
        exitProcess(1)
    }

}

fun buildOptions(): Options {

    var options: Options = Options()
    options.addOption(Option.builder("p")
            .hasArg(true)
            .required(false)
            .argName("page")
            .desc("set a \"page\" parameter")
            .longOpt("page")
            .build())
    options.addOption(Option.builder("z")
            .hasArg(true)
            .required(false)
            .argName("pagesize")
            .desc("set a \"pagesize\" parameter")
            .longOpt("pagesize")
            .build())
    options.addOption(Option.builder("f")
            .hasArg(true)
            .required(false)
            .argName("fromdate")
            .desc("set a \"fromdate\" parameter, a format must be \"${SOSerector.Format.DATE}\"")
            .longOpt("fromdate")
            .build())
    options.addOption(Option.builder("t")
            .hasArg(true)
            .required(false)
            .argName("todate")
            .desc("set a \"todate\" parameter, a format must be \"${SOSerector.Format.DATE}\"")
            .longOpt("todate")
            .build())
    options.addOption(Option.builder("o")
            .hasArg(true)
            .required(false)
            .argName("order")
            .desc("set a \"order\" parameter, \"desc\"(default) or \"asc\"")
            .longOpt("order")
            .build())
    options.addOption(Option.builder("min")
            .hasArg(true)
            .required(false)
            .argName("mindate")
            .desc("set a \"min\" date parameter, a format must be \"${SOSerector.Format.DATE}\"")
            .longOpt("mindate")
            .build())
    options.addOption(Option.builder("max")
            .hasArg(true)
            .required(false)
            .argName("maxdate")
            .desc("set a \"max\" date parameter, a format must be \"${SOSerector.Format.DATE}\"")
            .longOpt("maxdate")
            .build())
    options.addOption(Option.builder("s")
            .hasArg(true)
            .required(false)
            .argName("sort")
            .desc("set a \"sort\" parameter, \"activity\"(default) or \"votes\" or \"creation\" or \"relevance\"")
            .longOpt("sort")
            .build())
    options.addOption(Option.builder("g")
            .hasArg(true)
            .required(false)
            .argName("tagged")
            .desc("set a \"tagged\" parameter.")
            .longOpt("tagged")
            .build())
    options.addOption(Option.builder("n")
            .hasArg(true)
            .required(false)
            .argName("notagged")
            .desc("set a \"notagged\" parameter")
            .longOpt("notagged")
            .build())
    options.addOption(Option.builder("l")
            .hasArg(true)
            .required(false)
            .argName("lang")
            .desc("access the language's site, \"en\"(default) or \"ja\"")
            .longOpt("lang")
            .build())
    options.addOption(Option.builder("v")
            .required(false)
            .desc("show verbose")
            .longOpt("verbose")
            .build())
    options.addOption(Option.builder("h")
            .required(false)
            .desc("show help")
            .longOpt("help")
            .build())
    options.addOption(Option.builder("r")
            .required(false)
            .desc("show raw json")
            .longOpt("raw")
            .build())
    options.addOption(Option.builder("c")
            .required(false)
            .desc("show csv format")
            .longOpt("csv")
            .build())

    return options
}

fun buildParams(inTitle: String, cmd: CommandLine): SOParams {

    val params: SOParams =
            if (cmd.hasOption("g")) SOParams(inTitle, cmd.getOptionValue("g")) else SOParams(inTitle, "")

    if (cmd.hasOption("p")) params.page = cmd.getOptionValue("p").toInt()
    if (cmd.hasOption("z")) params.pageSize = cmd.getOptionValue("z").toInt()
    if (cmd.hasOption("f")) params.fromDate = params.parseDate(cmd.getOptionValue("f"))
    if (cmd.hasOption("t")) params.toDate = params.parseDate(cmd.getOptionValue("t"))
    if (cmd.hasOption("o")) params.order = SOSerector.Order.DESC.serectorOf(cmd.getOptionValue("o"))
    if (cmd.hasOption("min")) params.minDate = params.parseDate(cmd.getOptionValue("min"))
    if (cmd.hasOption("max")) params.maxDate = params.parseDate(cmd.getOptionValue("max"))
    if (cmd.hasOption("s")) params.sort = SOSerector.Sort.ACTIVITY.serectorOf(cmd.getOptionValue("s"))
    if (cmd.hasOption("n")) params.noTagged = cmd.getOptionValue("n")
    if (cmd.hasOption("l")) params.site = SOSerector.Site.ENGLISH.serectorOf(cmd.getOptionValue("l"))

    return params
}

fun showArgs(cmd: CommandLine) {

    println("arguments: ")
    if (cmd.args.isNotEmpty()) println("    title: \"${cmd.args[0]}\"") else println("    title:\"\"")
    if (cmd.hasOption("p")) println("    page: \"${cmd.getOptionValue("p")}\"")
    if (cmd.hasOption("z")) println("    pagesize: \"${cmd.getOptionValue("s")}\"")
    if (cmd.hasOption("f")) println("    fromdate: \"${cmd.getOptionValue("f")}\"")
    if (cmd.hasOption("t")) println("    todate: \"${cmd.getOptionValue("t")}\"")
    if (cmd.hasOption("o")) println("    order: \"${cmd.getOptionValue("o")}\"")
    if (cmd.hasOption("min")) println("    mindate: \"${cmd.getOptionValue("min")}\"")
    if (cmd.hasOption("max")) println("    maxdate: \"${cmd.getOptionValue("max")}\"")
    if (cmd.hasOption("s")) println("    sort: \"${cmd.getOptionValue("s")}\"")
    if (cmd.hasOption("g")) println("    tagged: \"${cmd.getOptionValue("g")}\"")
    if (cmd.hasOption("n")) println("    notagged: \"${cmd.getOptionValue("n")}\"")
    if (cmd.hasOption("l")) println("    lang: \"${cmd.getOptionValue("l")}\"")
    // flag
    if (cmd.hasOption("v")) println("    verbose: yes")
    if (cmd.hasOption("r")) println("    raw: yes")
    else if (cmd.hasOption("c")) println("    csv: yes")
    println()

}