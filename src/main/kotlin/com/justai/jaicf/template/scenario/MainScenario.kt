package com.justai.jaicf.template.scenario
import com.justai.jaicf.context.BotContext

import com.justai.jaicf.builder.Scenario
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.nio.file.Paths
import java.nio.file.Files
import org.apache.commons.csv.CSVRecord

import java.io.FileReader




fun readAll(context: BotContext) {
    val CSV_File_Path = "src/main/kotlin/com/justai/jaicf/template/dictionaries/cities.csv"
    // read the file
    val reader = Files.newBufferedReader(Paths.get(CSV_File_Path))
    // parse the file into csv values
    val csvParser = CSVParser(reader, CSVFormat.DEFAULT)
    for (csvRecord in csvParser) {
        if (csvRecord.get(0) == "4400") {
            context.session["airport"] = csvRecord.get(1)
            context.session["country"]= csvRecord.get(2)
            context.session["city"] = csvRecord.get(3)
        }
        // Accessing Values by Column Index
        println (csvRecord.get(3))
    }
}

val mainScenario = Scenario {
    append(CitiesGame)
    append(GameFunctions)
    state("Start") {
        activators {
            intent("WhatGamesYouHave")
        }
        action {
            reactions.run {
                say("Есть {города}{город+а}. Играем?")
                readAll(context)
                val airport = context.session["airport"]
                val country = context.session["country"]
                val city = context.session["city"]
                say(
                    "code : AAD\n airport: $airport \n country: $country \n city: $city "
                )
            }
        }

        state("Yes") {
            activators {
                intent("LetsStart")
            }
            action {
                reactions.go("/CheckWhoStarts")
            }
        }

        state("No") {
            activators {
                intent("Disagree")
            }
            action {
                reactions.say("Хорошо, сыграем в {города}{город+а} в следующий раз.")
            }
        }
    }

    state("CatchAll") {
        activators {
            catchAll()
        }
        action {
            reactions.say("Не удалось распознать ваш запрос.")
            reactions.go("/Start")
        }
    }
}