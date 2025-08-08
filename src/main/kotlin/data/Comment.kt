package ru.netology.data

data class Comment(
    override val id: Int,
    override val text: String,
    val noteId: Int,
    override var isDelete: Boolean = false
) : Content(noteId, text, isDelete) {
    override fun copy(id: Int, text: String): Comment {
        return Comment(id, text, noteId)
    }
}