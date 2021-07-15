package com.justai.jaicf.template.scenario

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.builder
import com.google.common.collect.ImmutableBiMap.builder
import com.justai.jaicf.builder.Scenario
import io.ktor.client.call.*
import io.ktor.http.cio.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Array.get
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.net.http.HttpResponse
import java.util.stream.DoubleStream.builder
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.RandomStringUtils.random
import kotlin.random.Random

import javax.xml.crypto.Data
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

val mainScenario = Scenario {
//    append(CitiesGame)
//    append(GameFunctions)
    state("Start") {
        activators {
            intent("StartGame")
        }
        get123()
        action {
//            context.session["gameCities"] = mutableListOf<String>()
//            context.client["sortedCitiesByLetter"] = {}
//            reactions.go("/CheckWhoStarts")
        }
    }
}


fun get123() {
    val client = OkHttpClient()
    val url = URL("https://app.jaicp.com/cailapub/api/caila/p/88124bdb-64ed-4af3-ac39-ffc6a362765d/entities-by-name/Cities")

    val request = Request.Builder()
        .url(url)
        .get()
        .build()

    val response = client.newCall(request).execute()

    val responseBody = response.body!!.string()
//    val result = responseBody.let { Json.parseToJsonElement(it) }
    //Response
//    println("Response Body: $responseBody["records"]")

    //we could use jackson if we got a JSON
    val mapperAll = ObjectMapper()
    val objData = mapperAll.readTree(responseBody)
    val res = objData.get("records")
    val resValue = res[Random.nextInt(0, res.size() -1)]["value"].toString()
    var city =  resValue.let { Json.parseToJsonElement(it) }


//    println(city.jsonObject)
//        .forEachIndexed { index, jsonNode ->
//        println("$index $jsonNode")
//    }
}



@Serializable
data class Parent(
    @SerialName("Parent")
    val parent: SomeClass
)

@Serializable
data class SomeClass(
    @SerialName("SpaceShip")
    val ship:String,
    @SerialName("Mark")
    val mark:Int
)

fun main() {
    val parent = Json.parse(Parent.serializer(), "{\"Parent\":{\"SpaceShip\":\"Tardis\",\"Mark\":40}}")
    println(parent)
}