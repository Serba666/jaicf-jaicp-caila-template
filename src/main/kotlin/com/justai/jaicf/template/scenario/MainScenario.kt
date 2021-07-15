package com.justai.jaicf.template.scenario

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.builder
import com.google.common.collect.ImmutableBiMap.builder
import com.justai.jaicf.builder.Scenario
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
    val url = URL("https://app.jaicp.com/cailapub/api/caila/p/88124bdb-64ed-4af3-ac39-ffc6a362765d/entities/68044/records")

    val request = Request.Builder()
        .url(url)
        .get()
        .build()

    val response = client.newCall(request).execute()

    val responseBody = response.body!!.string()

    //Response
    println("Response Body: " + responseBody)

    //we could use jackson if we got a JSON
//    val mapperAll = ObjectMapper()
//    val objData = mapperAll.readTree(responseBody)
//
//    objData.get("data").forEachIndexed { index, jsonNode ->
//        println("$index $jsonNode")
//    }
}