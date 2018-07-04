package test.capital.scalable.song.com.br.scalablecapitaltest.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.repository_item.view.*
import test.capital.scalable.song.com.br.scalablecapitaltest.R
import test.capital.scalable.song.com.br.scalablecapitaltest.model.Repository

class RepositoryAdapter(val myDataset: MutableList<Repository>) :
        RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName?.text = myDataset[position].name
        holder.txtDefaultBranch?.text = myDataset[position].defaultBranch
        holder.txtForks.text = "Forks: ${myDataset[position].forks}"
        holder.txtWatchers.text = "Watchers: ${myDataset[position].watchers}"
        holder.txtHTMLUrl.text = myDataset[position].htmlURL
        holder.txtOpenIssues.text = "Open issues: ${myDataset[position].openIssues}"
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName = itemView.textViewName
        val txtDefaultBranch = itemView.textViewDefaultBranch
        val txtForks = itemView.textViewForks
        val txtWatchers = itemView.textViewWatchers
        val txtHTMLUrl = itemView.textViewHTMLUrl
        val txtOpenIssues = itemView.textViewOpenIssues

    }
}