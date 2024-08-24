package com.pefacel.taskmanagementapp.tasks.ui.taskDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.pefacel.taskmanagementapp.tasks.core.DateHelper
import com.pefacel.taskmanagementapp.tasks.data.database.entity.TaskEntity
import com.pefacel.taskmanagementapp.tasks.ui.TaskViewModel
import com.pefacel.taskmanagmentapp.R
import com.pefacel.taskmanagmentapp.databinding.FragmentTaskDetailBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailFragment : Fragment() {

    private lateinit var binding: FragmentTaskDetailBinding

    private val taskViewModel: TaskViewModel by activityViewModels<TaskViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskDetailBinding.inflate(layoutInflater)
        initUI()
        return binding.root
    }

    private fun initUI() {
        initUIState()
        initUIListener()
    }

    private fun initUIListener() {

        taskViewModel.currentTask.observe(viewLifecycleOwner, Observer { currentTask ->
            if (currentTask != null) {
                binding.textViewDescription.text = currentTask.content
                binding.textViewTitle.text = currentTask.title
                binding.textViewCreationDate.text = "Creation date: " + currentTask.creationDateFormatted
                binding.textViewDueDate.text = "Limit date: " + currentTask.dueDateFormatted

                binding.buttonDelete.setOnClickListener {
                    taskViewModel.deleteTask(currentTask)
                }

                if (currentTask.image == "") {
                    binding.imageView.visibility = View.GONE

                } else {
                    binding.imageView.visibility = View.VISIBLE
                    Picasso.get().load(currentTask.image)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(binding.imageView);
                }

                initCompleteButtonUIState(currentTask)

            }
        })

    }

    private fun initUIState() {}


    private fun initCompleteButtonUIState(currentTask: TaskEntity) {

        if (currentTask.completed) {
            binding.buttonComplete.visibility = View.INVISIBLE
            binding.buttonIncomplete.visibility = View.VISIBLE
        } else {
            binding.buttonComplete.visibility = View.VISIBLE
            binding.buttonIncomplete.visibility = View.INVISIBLE
        }

        binding.buttonComplete.setOnClickListener {
            taskViewModel.updateCheckboxTask(currentTask)
            binding.buttonComplete.visibility = View.INVISIBLE
            binding.buttonIncomplete.visibility = View.VISIBLE

        }

        binding.buttonIncomplete.setOnClickListener {
            taskViewModel.updateCheckboxTask(currentTask)
            binding.buttonComplete.visibility = View.VISIBLE
            binding.buttonIncomplete.visibility = View.INVISIBLE
        }

        if (DateHelper().dateIsOverTheLimitDate(currentTask.dueDate)) {
            binding.buttonComplete.isEnabled = false
            binding.buttonIncomplete.isEnabled = false
        }
    }


}

