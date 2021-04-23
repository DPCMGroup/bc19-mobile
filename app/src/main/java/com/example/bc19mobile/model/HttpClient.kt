package com.example.bc19mobile.model

import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class HttpClient {

    private val url = "http://192.168.210.35:8000/"

    private val client = OkHttpClient()

    fun login(json: JSONObject, then: (String) -> Unit) {

        val JSON = MediaType.parse("application/json; charset=utf-8")
        val body: RequestBody = RequestBody.create(JSON, json.toString())
        val request = Request.Builder()
            .url(url + "login")
            .post(body)
            .build()
        return sendRequest(request, then)

    }

    fun showUserBookings(json: JSONObject, then: (String) -> Unit, idutente: String) {
        val JSON = MediaType.parse("application/json; charset=utf-8")
        val body: RequestBody = RequestBody.create(JSON, json.toString())
        val request = Request.Builder()
            .url(url + "user/bookings/" + idutente)
            .post(body)
            .get()
            .build()
        return sendRequest(request, then)
    }

    fun sanitizeWorkstation(json: JSONObject, then: (String) -> Unit) {

        val JSON = MediaType.parse("application/json; charset=utf-8")
        val body: RequestBody = RequestBody.create(JSON, json.toString())
        val request = Request.Builder()
            .url(url + "workstation/sanitize")
            .post(body)
            .build()

        return sendRequest(request, then)

    }

    fun getWorkstationInfo(json: JSONObject, then: (String) -> Unit) {
        val JSON = MediaType.parse("application/json; charset=utf-8")
        val body: RequestBody = RequestBody.create(JSON, json.toString())
        val request = Request.Builder()
            .url(url + "workstation/getInfo")
            .post(body)
            .build()
        return sendRequest(request, then)
    }

    //General method to send a request and set the function (then) to be called when there is a response
    fun sendRequest(request: Request, then: (String) -> Unit) {

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response): Unit =
                then(response.body()!!.string())
        })
    }

    //This method is experimental. It does not work on another thread so, unless you set the threadPolicy to a permitAll() one, it throws an exception.
    //On the other hand, it doesn't need a callback function, it returns the result directly.
    @Throws(IOException::class)
    fun getRequest2(): String? {
        val request: Request = Request.Builder()
            .url(url + "list")
            .build()
        client.newCall(request).execute().use { response -> return response.body()!!.string() }
    }

}
