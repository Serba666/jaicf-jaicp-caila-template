package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.context.BotContext


val CitiesGame = Scenario {
    state("CheckWhoStarts") {
        action {
                if (context.session["gameCities"] !== null)  {
                    when (random(1,4)) {
                        1 -> {
                            reactions.go("./UserStarts")
                        }
                        2 -> {
                            reactions.go("./AskWhoStarts")
                        }
                        else -> {
                            reactions.go("./BotStarts")
                        }
                    }
                }
            }

        state("UserStarts") {
            activators {
                intent("UserStarts")
            }
            action {
                reactions.sayRandom("Назовите любой город.", "С какого города начнём?")
                reactions.go("../../UserStarts")
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
                reactions.go("../../BotStarts")
            }
        }
        state("LocalCatchAll") {
            activators {
                catchAll()
            }
            action {
                reactions.say("Не удалось распознать ваш запрос.")
                reactions.say("Давайте я начну.")
                reactions.go("../../BotStarts")
            }
        }
    }
    state("UserStarts") {
        action {
            reactions.say("UserStarts")
//                reactions.go("../../BotStarts")
        }
    }
    state("BotStarts") {
        action {
            context.temp["nextCity"] = getCity(context)
            context.session["gameCities"] = (context.temp["nextCity"] as String)
            reactions.say("В игре \"города\" нужно называть город на ту букву, на которую закончился предыдущий. Если последняя буква Ъ, Ь, Ы, то называем город на предпоследнюю букву.")
            reactions.say("Если захотите завершить игру, скажите мне об этом.")
            reactions.sayRandom("Мой город — ${context.temp["nextCity"]}.", "Пусть будет ${context.temp["nextCity"]}.", "Я называю город ${context.temp["nextCity"]}.")
            reactions.sayRandom("Какой город на букву ${getCityLetter(context)} вы знаете?", "Назовите город на букву ${getCityLetter(context)}.", "Получается, вы называете город на букву ${getCityLetter(context)}.")
            reactions.go("../Questions ")
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


