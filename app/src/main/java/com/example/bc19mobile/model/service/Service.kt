package com.example.bc19mobile.model.service

import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Service {

    private val url = "http://192.168.210.35:8000/"

    private val client = OkHttpClient()


    fun request(
        json: JSONObject,
        path: String,
        isPost: Boolean,
        okThen: (String) -> Unit,
        notOkThen: (IOException) -> Unit
    ) {
        val JSON = MediaType.parse("application/json; charset=utf-8")
        val request: Request
        if (isPost) {
            val body: RequestBody = RequestBody.create(JSON, json.toString())
            request = Request.Builder()
                .url(url + path)
                .post(body)
                .build()
        } else {
            request = Request.Builder()
                .url(url + path)
                .get()
                .build()
        }
        return sendRequest(request, okThen, notOkThen)
    }

    //General method to send a request and set the function (then) to be called when there is a response
    fun sendRequest(
        request: Request,
        okThen: (String) -> Unit,
        notOkThen: (IOException) -> Unit
    ) {

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                notOkThen(e)
            }

            override fun onResponse(call: Call, response: Response): Unit =
                okThen(response.body()!!.string())
        })
    }

}
