package com.pefacel.taskmanagementapp.tasks.ui.taskList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.pefacel.taskmanagementapp.tasks.core.DateHelper
import com.pefacel.taskmanagementapp.tasks.data.database.entity.TaskEntity
import com.pefacel.taskmanagmentapp.R
import com.pefacel.taskmanagmentapp.databinding.ItemTaskListBinding
import com.squareup.picasso.Picasso

class TaskListAdapter(
    private var tasks: List<TaskEntity>,
    private var taskClickListener: (TaskEntity) -> Unit,
    private var checkboxClickListener: (TaskEntity) -> Unit
) : RecyclerView.Adapter<TaskListAdapter.TaskHolder>() {


    inner class TaskHolder(val binding: ItemTaskListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun render(task: TaskEntity) {
            if (!DateHelper().dateIsOverTheLimitDate(task.dueDate)) {
                binding.imageView.isEnabled = false
                binding.checkbox.isEnabled = false
                binding.textViewDate.isEnabled = false
                binding.textViewTitle.isEnabled = false
                binding.textViewContent.isEnabled = false
            } else {
                binding.checkbox.setOnClickListener {
                    checkboxClickListener(task)
                }
            }

            binding.textViewTitle.text = task.title
            binding.textViewDate.text = "Limit date: " + task.dueDateFormatted
            binding.textViewContent.text = task.content
            binding.checkbox.isChecked = task.completed

            if (task.image != "") {
                binding.imageView.visibility = View.VISIBLE
                Picasso.get().load(task.image).error(R.drawable.ic_launcher_foreground).into(binding.imageView);
            } else {
                binding.imageView.visibility = View.GONE
            }
            binding.itemTask.setOnClickListener {
                taskClickListener(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val binding = ItemTaskListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.render(tasks.get(position))
    }

}