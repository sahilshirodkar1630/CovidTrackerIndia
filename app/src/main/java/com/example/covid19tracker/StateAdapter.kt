package com.example.covid19tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_state.view.*

class StateAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<StatewiseItem>  = ArrayList()


    private val TYPE_HEADER : Int = 0
    private val TYPE_LIST : Int = 1

    override fun getItemViewType(position: Int): Int {

        if(position == 0)
        {
            return TYPE_HEADER
        }
        return TYPE_LIST
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_HEADER)
        {
            val header = LayoutInflater.from(parent.context).inflate(R.layout.item_header,parent,false)
            return ViewHolderHeader(header)
        }

       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.item_state,parent,false)

        return StateViewHolder(view)
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is ViewHolderHeader)
        {

        }

        if(holder is StateViewHolder)
        {
            holder.bind(list[position-1])
        }

    }

    override fun getItemCount(): Int  = list.size +1

    fun swapData(list: List<StatewiseItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolderHeader(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

    }
    inner class StateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: StatewiseItem) = with(itemView) {
            itemView.stateTv.text = item.state
            itemView.confirmedTv.text = SpannableDelta(
                "${item.confirmed} \n ↑${item.deltaconfirmed?:"0"}",
                "#F6162F",
                item.confirmed?.length?:0
            )
            itemView.activeTv.text = SpannableDelta(
                "${item.active} \n ↑${item.deltaactive?:"0"}",
                "#1976D2",
                item.active?.length?:0
            )
            itemView.recoveredTv.text = SpannableDelta(
                "${item.recovered} \n ↑${item.deltarecovered?:"0"}",
                "#CCDF97",
                item.recovered?.length?:0
            )
            itemView.deathTv.text = SpannableDelta(
                "${item.deaths} \n ↑${item.deltadeaths?:"0"}",
                "#FBC02D",
                item.deaths?.length?:0
            )
        }
    }
}