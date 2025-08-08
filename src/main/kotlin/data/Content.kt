package ru.netology.data

abstract class Content (open val id: Int, open val text: String, open var isDelete: Boolean) {
    abstract fun copy(id: Int = this.id, text: String = this.text): Content
}