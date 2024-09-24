package controllers

import models.User

class UserStore {

    val users = ArrayList<User>()

    private var lastId = 0
    private fun getId() = lastId++

    fun findAll(): List<User> {
        return users
    }

    fun create(user: User) {
        user.id = getId()
        users.add(user)
    }

    fun findOne(id: Int): User? {
        return users.find { p -> p.id == id }
    }

    fun delete(user: User?): Boolean {
        return users.remove(user)
    }



}



