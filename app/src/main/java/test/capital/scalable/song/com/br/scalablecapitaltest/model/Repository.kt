package test.capital.scalable.song.com.br.scalablecapitaltest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repository (

        @SerializedName("id")
        val repositoryId: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("full_name")
        val fullName: String,

        @SerializedName("private")
        val private: Boolean,

        @SerializedName("html_url")
        val htmlURL: String,

        @SerializedName("forks")
        val forks: Int,

        @SerializedName("open_issues")
        val openIssues: Int,

        @SerializedName("watchers")
        val watchers: Int,

        @SerializedName("default_branch")
        val defaultBranch: String

) : Serializable