package com.gb.firestore.service

import com.gb.firestore.modal.Version
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val BASE_URL = "https://firestore.googleapis.com/v1/projects/firestoreforceupdate/databases/"

class FirebaseClient {

    // Coroutines
    private val job = Job()
    private val coroutineContext = Dispatchers.IO + job
    private val uiScope = CoroutineScope(coroutineContext)

    fun getAppVersionFromApi(requestCallback: (isSuccess: Boolean, response: Version?, message: String?) -> (Unit)) {

        uiScope.launch {
            ApiClient.api(BASE_URL)
                    .create(ApiService::class.java)
                    .getVersion()
                    .enqueue(object : Callback<Version> {
                        override fun onResponse(call: Call<Version>, response: Response<Version>) {

                            response.body()?.let { response ->
                                requestCallback(true, response, null)
                            }
                        }

                        override fun onFailure(call: Call<Version>, t: Throwable) {

                            requestCallback(false, null, t.localizedMessage)
                        }
                    })
        }
    }
}