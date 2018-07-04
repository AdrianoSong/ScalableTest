package test.capital.scalable.song.com.br.scalablecapitaltest.presenter

import com.google.gson.Gson
import io.realm.Realm
import org.json.JSONArray
import test.capital.scalable.song.com.br.scalablecapitaltest.model.Repository
import test.capital.scalable.song.com.br.scalablecapitaltest.persistence.RepositoryPersistence
import test.capital.scalable.song.com.br.scalablecapitaltest.persistence.model.RepositoryRealm
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.SSLContext

class MainActivityPresenter {

    val repositoryList = mutableListOf<Repository>()

    fun checkRepositoryDataInRealm(): Boolean{

        val realm = Realm.getDefaultInstance()

        val realmResult = realm.where(RepositoryRealm::class.java).findAll()

        if (realmResult.size > 0) {

            realmResult.forEach { item ->

                repositoryList.add(item.convertToModel())
            }

            return true
        } else {
            return false
        }
    }

    private fun setSSLProtocol(){
        val sslContext = SSLContext.getInstance("TLSv1.2")
        sslContext.init(null, null, null)
        val engine = sslContext.createSSLEngine()
        engine.enabledProtocols
    }

    fun getRepositoryData() {
        val url = "https://api.github.com/users/mralexgray/repos"
        val obj = URL(url)

        with(obj.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"

            setSSLProtocol()

            println("\nSending 'GET' request to URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }

                parseRepositoryData(response.toString())
            }
        }
    }

    private fun parseRepositoryData(response: String){

        val myJsonArray = JSONArray(response)

        for (i in 0..(myJsonArray.length() - 1)) {
            val item = Gson().fromJson(myJsonArray.get(i).toString(), Repository::class.java)

            repositoryList.add(item)
        }

        RepositoryPersistence.persistDataToRealm(repositoryList)
    }
}