package ru.netology.data

data class Note(
    override val id: Int,
    override val text: String,
    val title: String,
    override var isDelete: Boolean = false
) : Content(id, text, isDelete) {
    override fun copy(id: Int, text: String): Note {
        return Note(id, text, title)
    }
}