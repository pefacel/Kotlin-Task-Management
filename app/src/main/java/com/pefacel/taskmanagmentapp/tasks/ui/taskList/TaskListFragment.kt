package com.pefacel.taskmanagementapp.tasks.ui.taskList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pefacel.taskmanagementapp.tasks.ui.TaskViewModel
import com.pefacel.taskmanagmentapp.databinding.FragmentTaskListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private lateinit var binding: FragmentTaskListBinding

    private val taskViewModel: TaskViewModel by activityViewModels<TaskViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskListBinding.inflate(layoutInflater)
        initUI()
        return binding.root
    }

    private fun initUI() {
        initUIState()
        initUIListener()
    }

    private fun initUIListener() {}

    private fun initUIState() {
        initRecyclerViewState()
    }

    private fun initRecyclerViewState() {
        taskViewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            binding.recyclerViewTaskList.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewTaskList.adapter = TaskListAdapter(tasks,
                { taskViewModel.setCurrentTask(it) },
                { taskViewModel.updateCheckboxTask(it) }
            )
        })
    }


}