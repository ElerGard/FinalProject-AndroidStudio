package com.example.final_project.data

class User {

    var username: String? = null
    var password: String? = null

    constructor(name: String, pass: String)
    {
        this.username = name
        this.password = pass
    }

    constructor()
    {

    }
}