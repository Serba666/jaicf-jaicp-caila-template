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
import kotlinx.serialization.json.*

val mainScenario = Scenario {
//    append(CitiesGame)
//    append(GameFunctions)
    state("Start") {
        activators {
            intent("StartGame")
        }
        getCitiesEntity()
        action {
//            context.session["gameCities"] = mutableListOf<String>()
//            context.client["sortedCitiesByLetter"] = {}
//            reactions.go("/CheckWhoStarts")
        }
    }
}


fun getCitiesEntity() {
    val client = OkHttpClient()
    val url = URL("https://app.jaicp.com/cailapub/api/caila/p/88124bdb-64ed-4af3-ac39-ffc6a362765d/entities-by-name/Cities")

    val request = Request.Builder()
        .url(url)
        .get()
        .build()

    val response = client.newCall(request).execute()

    val responseBody = response.body!!.string()

    val mapperAll = ObjectMapper()
    val objData = mapperAll.readTree(responseBody)
    val res = objData.get("records")
    val resValue = res[Random.nextInt(0, res.size() -1)]["value"].toString()
    val city =  resValue.let { Json.parseToJsonElement(it) }
    val hell = Json.parseToJsonElement(responseBody)
    val hellValue = hell.jsonObject["records"]
    val hellIndex = hellValue?.jsonArray?.get(Random.nextInt(0, res.size() -1))
    val value = hellIndex?.jsonObject?.get("value")
        .toString()
        .replace("\\","")
        .replace("\"{", "{")
        .replace("}\"", "}")
    val name = mapperAll.readTree(value)["name"]
    println(name)
}



