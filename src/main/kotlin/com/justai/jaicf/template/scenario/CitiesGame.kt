package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.builder.Scenario
import io.ktor.network.selector.SelectInterest.Companion.size


var gameCities =  emptyArray<String>()


val CitiesGame = Scenario {
    state("CheckWhoStarts") {
        activators {
            intent("CheckWhoStarts")
        }
        action {
            if (gameCities.isEmpty()) {
                val int = random(1,4)
                if (int == 1) {
                    reactions.go("./UserStarts")
                } else if (int == 2) {
                    reactions.go("./AskWhoStarts")
                } else {
                    reactions.go("./BotStarts")
                }
            } else if (gameCities.size == 1) {
                reactions.say("Один есть")
            } else {
                reactions.say("Больше одного")
            }
        }

        state("UserStarts") {
            activators {
                intent("UserStarts")
            }
            action {
                reactions.sayRandom("Назовите любой город.", "С какого города начнём?")
//                reactions.go("../../UserStarts")
            }
        }

        state("AskWhoStarts") {
            action {
                reactions.say("Кто из нас первым назовёт город?")
            }
        }

        state("BotStarts") {
            activators {
                intent("BotStarts")
            }
            action {
                reactions.say("Хорошо, я начну.")
//                reactions.go("../../BotStarts")
            }
        }
        state("LocalCatchAll") {
            activators {
                catchAll()
            }
            action {
                reactions.say("Не удалось распознать ваш запрос.")
                reactions.say("Давайте я начну.")
//                reactions.go("../../BotStarts")
            }
        }
    }
    state("BotStarts") {
        action {
            reactions.say("Хорошо, я начну.")
//                reactions.go("../../BotStarts")
        }
    }
}