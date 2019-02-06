package com.dempsey.teamworkapp.presenter.todolist

import com.dempsey.teamworkapp.base.BasePresenter
import com.dempsey.teamworkapp.business.AppTodoListBusiness
import com.dempsey.teamworkapp.business.TodoListBusiness
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TodoListPresenter(
        private val todoListBusiness: TodoListBusiness,
        private val io: Scheduler,
        private val mainThread: Scheduler
) : BasePresenter(), TodoListContract.ActionListener {

    private lateinit var disposable: Disposable

    override fun onViewCreated() {}

    override fun onViewDestroyed() {
        disposable.dispose()
    }

    override fun getTodoListForTask(id: String) {
        disposable = todoListBusiness.getTodoListForTask(id)
                .subscribeOn(io)
                .observeOn(mainThread)
                .subscribe {  }
    }

    companion object {
        @JvmStatic
        fun mewInstance() = TodoListPresenter(
                AppTodoListBusiness.newInstance(),
                Schedulers.io(),
                AndroidSchedulers.mainThread()
        )
    }
}