package ru.netology.services

import ru.netology.data.Content
import ru.netology.exceptions.*

abstract class ContentService <T : Content> : CrudService<T> {
    var contents: ArrayList<T> = arrayListOf()
    var id = 0


    override fun add(value: T): Int {
        contents += value.copy(id = ++id) as T
        return contents.last.id
    }

    override fun delete(id: Int): Boolean {
        if (id !in 1..(contents.lastIndex + 1) || contents[id - 1].isDelete) {
            throw IdOutOfBoundsException()
        }
        contents[id - 1].isDelete = true
        return true
    }

    override fun read(startId: Int, lastId: Int): List<T> {
        if (startId !in 1..(contents.lastIndex + 1) ||
            lastId !in 1..(contents.lastIndex + 1) ||
            startId > lastId
        ) {
            throw IdRangeOutOfBoundsException()
        }
        contents.subList(startId - 1, lastId)
        val tempList: ArrayList<T> = arrayListOf()
        for (value in contents.subList(startId - 1, lastId)) {
            if (!value.isDelete) {
                tempList += value
            }
        }
        return tempList
    }

    override fun getById(id: Int): T {
        if (id !in 1..(contents.lastIndex + 1) || contents[id - 1].isDelete) {
            throw IdOutOfBoundsException()
        }
        return contents[id - 1]
    }

    override fun edit(value: T): Boolean {
        if (value.id !in 1..(contents.lastIndex + 1) || contents[value.id - 1].isDelete) {
            throw IdOutOfBoundsException()
        }
        contents[value.id - 1] = value.copy() as T
        return true
    }

    override fun restore(id: Int): Boolean {
        if (id !in 1..(contents.lastIndex + 1) || !contents[id - 1].isDelete) {
            throw IdOutOfBoundsException()
        }
        contents[id - 1].isDelete = false
        return true
    }

}