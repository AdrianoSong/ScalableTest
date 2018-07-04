package test.capital.scalable.song.com.br.scalablecapitaltest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.android.gms.security.ProviderInstaller
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import test.capital.scalable.song.com.br.scalablecapitaltest.adapter.RepositoryAdapter
import test.capital.scalable.song.com.br.scalablecapitaltest.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity() {

    val mainActivityPresenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(applicationContext)

        ProviderInstaller.installIfNeeded(applicationContext)

        if (mainActivityPresenter.checkRepositoryDataInRealm()) {

            setupRecyclerView()
            hideLoading()

        } else {
            Thread( Runnable {
                mainActivityPresenter.getRepositoryData()

                runOnUiThread({
                    setupRecyclerView()
                    hideLoading()
                })

            }).start()
        }
    }

    private fun setupRecyclerView(){

        recyclerviewRepositoires.setHasFixedSize(true)
        recyclerviewRepositoires.layoutManager = LinearLayoutManager(this)
        recyclerviewRepositoires.adapter = RepositoryAdapter(mainActivityPresenter.repositoryList)
    }

    private fun hideLoading(){
        progressBarLoading.visibility = View.INVISIBLE
        recyclerviewRepositoires.visibility = View.VISIBLE
    }
}
