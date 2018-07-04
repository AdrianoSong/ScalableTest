package test.capital.scalable.song.com.br.scalablecapitaltest.persistence

import io.realm.Realm
import test.capital.scalable.song.com.br.scalablecapitaltest.model.Repository
import test.capital.scalable.song.com.br.scalablecapitaltest.persistence.model.RepositoryRealm

class RepositoryPersistence{

    companion object {

        fun persistDataToRealm(repositoryList: MutableList<Repository>){

            val realm = Realm.getDefaultInstance()

            repositoryList.forEach { item ->

                realm.beginTransaction()
                val repositoryRealm = realm.createObject(RepositoryRealm::class.java)

                repositoryRealm.repositoryId = item.repositoryId
                repositoryRealm.name = item.name
                repositoryRealm.fullName = item.fullName
                repositoryRealm.private = item.private
                repositoryRealm.htmlURL = item.htmlURL
                repositoryRealm.forks = item.forks
                repositoryRealm.openIssues = item.openIssues
                repositoryRealm.watchers = item.watchers
                repositoryRealm.defaultBranch = item.defaultBranch

                realm.commitTransaction()
            }
        }
    }
}