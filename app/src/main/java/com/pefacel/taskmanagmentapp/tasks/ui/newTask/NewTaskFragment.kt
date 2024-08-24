package com.pefacel.taskmanagementapp.tasks.ui.newTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.pefacel.taskmanagementapp.tasks.ui.TaskViewModel
import com.pefacel.taskmanagmentapp.databinding.FragmentNewTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskFragment : DialogFragment() {

    private lateinit var binding: FragmentNewTaskBinding
    private val taskViewModel: TaskViewModel by viewModels<TaskViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        initUIState()
        initUIListener()
    }

    private fun initUIListener() {}

    private fun initUIState() {
        binding.buttonAddTask.setOnClickListener {
            dismiss()
        }
    }
}