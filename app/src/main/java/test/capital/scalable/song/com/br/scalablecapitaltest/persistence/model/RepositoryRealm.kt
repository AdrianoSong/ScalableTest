package test.capital.scalable.song.com.br.scalablecapitaltest.persistence.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import test.capital.scalable.song.com.br.scalablecapitaltest.model.Repository

@RealmClass
open class RepositoryRealm: RealmObject() {

    var repositoryId: Int = 0
    var name: String = ""
    var fullName: String = ""
    var private: Boolean = false
    var htmlURL: String = ""
    var forks: Int = 0
    var openIssues: Int = 0
    var watchers: Int = 0
    var defaultBranch: String = ""

    fun convertToModel(): Repository{

        return Repository(
                repositoryId,
                name,
                fullName,
                private,
                htmlURL,
                forks,
                openIssues,
                watchers,
                defaultBranch)
    }
}