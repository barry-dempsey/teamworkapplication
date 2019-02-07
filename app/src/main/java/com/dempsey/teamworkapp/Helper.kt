package com.dempsey.teamworkapp

fun performOperationOnThread(
        longOperation: ()-> Unit,
        callback: ()-> Unit
) {
    Thread{
        longOperation()
        callback()
    }.start()
}