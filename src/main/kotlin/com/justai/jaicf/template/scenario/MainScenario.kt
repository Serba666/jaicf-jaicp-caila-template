package com.justai.jaicf.template.scenario
import com.justai.jaicf.context.BotContext
import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.builder.Scenario

val mainScenario = Scenario {
    append(CitiesGame)
//    GameFunctions(context)
    state("Start") {
        activators {
            intent("WhatGamesYouHave")
        }
        action {
            reactions.run {
                say("Есть {города}{город+а}. Играем?")
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