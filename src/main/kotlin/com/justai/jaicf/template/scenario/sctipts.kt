package com.justai.jaicf.template.scenario
import com.justai.jaicf.context.BotContext

class GameFunctions() {
    fun getCity() {
/*        var city: String
        var letter = getCityLetter()
        if (letter) {
            letter = selectRandomArg("А", "Б", "В", "Г", "Д", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т")
        }
        var citiesNumber = Object.keys($Cities).length;
        var sortedCityNames = findCities(letter, citiesNumber);
        if (sortedCityNames.length != 0) {
            city = chooseCity(sortedCityNames);
            $session.gameCities = $session.gameCities || [];
            $session.gameCities.push(city);
        }
        var id = $client.sortedCitiesByLetter[letter].indexOf(city);
        $client.sortedCitiesByLetter[letter].slice(id, 1);
        return city;
    }

    fun getCityLetter() {
        var letter = false
        var letters: String
        var lastCityName = gameCities[gameCities.size - 1]
        if (gameCities && lastCityName) {
            letters = lastCityName.substring(lastCityName.length - 2);
            letter = validateLetters(letters);
        }
        return letter;
    }
    fun validateLetters(letters) {
        var letter = letters.substring(1);
        switch (letter) {
            case "ё":
            letter = "е";
            break;
            case "ы":
            case "ь":
            letter = letters.substring(0, 1);
            break;
        }
        return letter.toUpperCase()*/
    }
}
