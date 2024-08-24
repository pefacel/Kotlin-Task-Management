package com.pefacel.taskmanagmentapp.tasks.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pefacel.taskmanagementapp.tasks.data.database.entity.TaskEntity
import com.pefacel.taskmanagementapp.tasks.ui.TaskViewModel
import com.pefacel.taskmanagementapp.tasks.ui.newTask.NewTaskFragment
import com.pefacel.taskmanagementapp.tasks.ui.taskDetail.TaskDetailFragment
import com.pefacel.taskmanagementapp.tasks.ui.taskList.TaskListFragment
import com.pefacel.taskmanagmentapp.R
import com.pefacel.taskmanagmentapp.databinding.ActivityTaskMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskMainBinding

    private val taskViewModel: TaskViewModel by viewModels<TaskViewModel>()

    private val taskDetailFragment = TaskDetailFragment()
    private val newTaskFragment = NewTaskFragment()
    private val taskListFragment = TaskListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

    }

    private fun initUI() {
        initUIState()
        initUIListener()
    }

    private fun initUIListener() {
        binding.imageViewBackIcon.setOnClickListener { taskViewModel.setCurrentTask(null) }

        taskViewModel.currentTask.observe(this, Observer {
            println("it " + it)
            if (it == null) {
                initTaskListFragment()
            } else {
                initTaskDetailFragment(it)
            }
        })
    }

    private fun initUIState() {
        taskViewModel.getTasks()
        loadingState()
        binding.imageViewAddIcon.setOnClickListener {
            initNewTaskDialogFragment()
        }
    }

    private fun loadingState() {
        taskViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
            binding.frameLayoutFragment.isVisible = !it
        })
    }

    private fun initTaskListFragment() {
        setCurrentFragment(taskListFragment)
        binding.imageViewMenuIcon.visibility = View.VISIBLE
        binding.imageViewAddIcon.visibility = View.VISIBLE
        binding.imageViewBackIcon.visibility = View.INVISIBLE
        binding.textViewTitle.setText(R.string.app_name)
    }


    private fun initTaskDetailFragment(task: TaskEntity) {
        setCurrentFragment(taskDetailFragment)
        binding.imageViewMenuIcon.visibility = View.INVISIBLE
        binding.imageViewAddIcon.visibility = View.INVISIBLE
        binding.imageViewBackIcon.visibility = View.VISIBLE
        binding.textViewTitle.setText(task.title)
    }

    private fun initNewTaskDialogFragment() {
        newTaskFragment.show(supportFragmentManager, "customDialog")
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.frameLayoutFragment.id, fragment)
            commit()
        }
    }

}