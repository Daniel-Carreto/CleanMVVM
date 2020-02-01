package com.danycarreto.cleanmvvm.presentation.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.danycarreto.cleanmvvm.R
import com.danycarreto.cleanmvvm.domain.model.Repo

class ReposAdapter(val repoList: List<Repo>) :
    RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RepoViewHolder(inflater.inflate(R.layout.item_repo, parent, false))
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.txtName.text = repoList[position].name
        holder.txtDescription.text = repoList[position].description
    }

    class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName = view.findViewById<TextView>(R.id.txtName)
        val txtDescription = view.findViewById<TextView>(R.id.txtDescription)
    }
}