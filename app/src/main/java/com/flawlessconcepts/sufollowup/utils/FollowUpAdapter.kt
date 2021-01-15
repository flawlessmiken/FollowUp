package com.flawlessconcepts.sufollowup.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flawlessconcepts.sufollowup.database.FollowUpItem

//
//class FollowUpDiffCallback : DiffUtil.ItemCallback<FollowUpItem>() {
//    override fun areItemsTheSame(oldItem: FollowUpItem, newItem: FollowUpItem): Boolean {
//        return oldItem.followUpID == newItem.followUpID
//    }
//
//    override fun areContentsTheSame(oldItem: FollowUpItem, newItem: FollowUpItem): Boolean {
//        return oldItem == newItem
//    }
//}
//
//class FollowUpAdapter(val clickListener: FollowUpItemtListener) : ListAdapter<FollowUpItem, FollowUpAdapter.ViewHolder>(FollowUpDiffCallback()) {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder.from(parent)
//    }
//
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        holder.bind(getItem(position)!!, clickListener)
//    }
//
//
//    class ViewHolder private constructor(val binding:  ListItemSleepNightBinding): RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(item: FollowUpItem, clickListener: FollowUpItemtListener) {
//            binding.followup = item
//            binding.clickListener = clickListener
//            binding.executePendingBindings()
//        }
//
//        companion object {
//             fun from(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                 val binding =
//                        ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(binding)
//            }
//        }
//    }
//    class FollowUpItemtListener(val clickListener: (sleepId: Long) -> Unit) {
//       // fun onClick(night: SleepNight) = clickListener(night.nightId)
//    }


//}

