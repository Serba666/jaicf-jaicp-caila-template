package com.justai.jaicf.template.scenario

import com.justai.jaicf.context.BotContext

    fun validateLetters(letters: String): String {
        var letter = letters.substring(1);
        fun getLetter(letter: String) = when (letter) {
            "ё" -> "е"
            "ы", "ь" -> letters.substring(0, 1)
            else -> letter
        }
        return letter.toUpperCase()
    }

    fun getCityLetter(context: BotContext): String {
        var letter: String = ""
        var gameCities = mutableListOf(context.session["gameCities"] as String)
        var letters: String
        var lastCityName = gameCities[gameCities.size - 1]
        if (gameCities.isNotEmpty() && lastCityName.isNotEmpty()) {
            letters = lastCityName.substring(lastCityName.length - 2)
            letter = validateLetters(letters)
        }
        return letter
    }

    fun getCity(context: BotContext): String {
        return "Москва"
        var city: String
        var letter = getCityLetter(context)
        if (letter.isNotEmpty()) {
            var letter = arrayListOf("А", "Б", "В", "Г", "Д", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т").random()
        }
/*        var citiesNumber = Object.keys($Cities).length;
        var sortedCityNames = findCities(letter, citiesNumber);
        if (sortedCityNames.length != 0) {
            city = chooseCity(sortedCityNames);
            $session.gameCities = $session.gameCities || [];
            $session.gameCities.push(city);
        }
        var id = $client.sortedCitiesByLetter[letter].indexOf(city);
        $client.sortedCitiesByLetter[letter].slice(id, 1);
        return city;
    }*/
    }

/*

 */
//    gameCities.add("Москва")
//    var letter = arrayListOf("А", "Б", "В", "Г", "Д", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т").random()
//    var letter = getCityLetter()
//    println(letter)
//        var citiesNumber = Object.keys($Cities).length;
//        var sortedCityNames = findCities(letter, citiesNumber);
//        if (sortedCityNames.length != 0) {
//            city = chooseCity(sortedCityNames);
//            $session.gameCities = $session.gameCities || [];
//            $session.gameCities.push(city);
//        }
//        var id = $client.sortedCitiesByLetter[letter].indexOf(city);
//        $client.sortedCitiesByLetter[letter].slice(id, 1);
//        return city;
//    }