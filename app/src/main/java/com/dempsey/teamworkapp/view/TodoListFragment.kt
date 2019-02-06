package com.dempsey.teamworkapp.view

import android.os.Bundle
import com.dempsey.teamwork.data.model.TodoList
import com.dempsey.teamworkapp.R
import com.dempsey.teamworkapp.base.BaseFragment
import com.dempsey.teamworkapp.presenter.task.TasksContract
import com.dempsey.teamworkapp.presenter.todolist.TodoListContract
import com.dempsey.teamworkapp.presenter.todolist.TodoListPresenter
import kotlinx.android.synthetic.main.fragment_todolist_detail.*

private const val TODO_LIST_BUNDLE = "taskBundle"

class TodoListFragment : BaseFragment<TodoListPresenter>(), TodoListContract.View{

    private var delegate: TasksContract.Delegate? = null

    private lateinit var todoList: TodoList

    override fun layoutId() = R.layout.fragment_todolist_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoList = arguments!![TODO_LIST_BUNDLE] as TodoList
    }

    override fun setUpUi() {
        task_header.text = todoList.todoList.name
        task_description.text = todoList.todoList.description
        complete_check.isChecked = todoList.status.equals("complete")
    }

    override fun instantiatePresenter() = TodoListPresenter.mewInstance()

    companion object {
        @JvmStatic
        fun newInstance(
                todoList: TodoList,
                delegate: TasksContract.Delegate
        ) = TodoListFragment().apply {
            this.arguments = Bundle().apply { putSerializable(TODO_LIST_BUNDLE, todoList) }
            this.delegate = delegate
        }
    }
}