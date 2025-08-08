package ru.netology.services

import ru.netology.data.Comment
import ru.netology.exceptions.*

object CommentService : ContentService<Comment> () {
    val notes = NoteService

    override fun add(value: Comment): Int {
        for (noteList in notes.contents) {
            if (value.noteId == noteList.id && !noteList.isDelete) {
                return super.add(value)
            }
        }
        throw IdOutOfBoundsException()
    }

    override fun edit(value: Comment): Boolean {
        for (noteList in notes.contents) {
            if (value.noteId == noteList.id) {
                return super.edit(value)
            }
        }
        throw IdOutOfBoundsException()
    }

    fun readByNoteId(noteId: Int): List<Comment> {
        val tempList: ArrayList<Comment> = arrayListOf()
        if (noteId in 1..notes.contents.lastIndex + 1 && !notes.contents[noteId - 1].isDelete) {
            for (comment in contents) {
                if (comment.noteId == noteId && !comment.isDelete) {
                    tempList += comment
                }
            }
            return tempList
        }
        throw IdOutOfBoundsException()
    }

    override fun restore(id: Int): Boolean {
        if (id !in 1..(contents.lastIndex + 1) || !contents[id - 1].isDelete) {
            throw IdOutOfBoundsException()
        }
        if (notes.contents[id - 1].isDelete) {
            throw UnusedFunctionException()
        }
        contents[id - 1].isDelete = false
        return true
    }
}