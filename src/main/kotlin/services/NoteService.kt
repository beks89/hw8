package ru.netology.services

import ru.netology.data.Note
import ru.netology.exceptions.*

object NoteService : ContentService <Note> () {
    val comments = CommentService

    override fun restore(id: Int): Boolean {
        throw UnusedFunctionException()
    }

    override fun delete(id: Int): Boolean {
        if (id !in 1..(contents.lastIndex + 1) || contents[id - 1].isDelete) {
            throw IdOutOfBoundsException()
        }
        contents[id - 1].isDelete = true
        for ((index, comment) in comments.contents.withIndex()) {
            if (comment.noteId == id && !comment.isDelete) {
                comments.contents[index].isDelete = true
            }
        }
        return true
    }

    fun clear() {
        contents = arrayListOf()
        comments.contents = arrayListOf()
    }
}