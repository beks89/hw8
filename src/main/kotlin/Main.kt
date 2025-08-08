package ru.netology

import ru.netology.data.Comment
import ru.netology.data.Note
import ru.netology.exceptions.*
import ru.netology.services.NoteService

fun main() {
    val noteService = NoteService

    // проверка заметок
    var note = Note(1, "Note 1", "Welcome")
    println(note)
    println(noteService.add(note))
    println(noteService.add(Note(5, "Try 2", "Do you like me?")))
    println(noteService.add(note.copy(id = 1)))
    println(noteService.read(1,3))
    noteService.delete(3)
    println(noteService.read(1,3))
    noteService.edit(Note(1,"Editing","Welcome home!"))
    noteService.add(Note(8, "How are you?", "What about your mood?"))
    println(noteService.read(1,4))
    println(noteService.getById(2))
    println("")

    // проверка комментариев
    val comment = Comment(1,"Hello!", 1)
    noteService.comments.add(comment)
    noteService.comments.add(Comment(4, "Nice to meet you", 4))
    noteService.comments.add(Comment(13, "Of course!", 2))
    noteService.comments.add(Comment(8, "Yes, I do!", 2))
    noteService.comments.add(Comment(11, "So bad!", 4))
    println(noteService.comments.read(1,3))
    noteService.comments.edit(Comment(1, "Little mistake", 1))
    println(noteService.comments.read(1,3))
    noteService.comments.delete(2)
    println(noteService.comments.read(1,3))
    noteService.delete(1)
    println(noteService.read(1,4))
    println(noteService.comments.read(1,4))
    println(noteService.comments.readByNoteId(4))
    noteService.comments.restore(2)
    println(noteService.comments.read(1,5))

}