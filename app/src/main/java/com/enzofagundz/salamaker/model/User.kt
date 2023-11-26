package com.enzofagundz.salamaker.model

class User {
    var id: Int = 0
    var username: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    var password: String = ""

    constructor(username: String, firstName: String, lastName: String, email: String, password: String) {
        this.username = username
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }

    constructor(id: Int, username: String, firstName: String, lastName: String, email: String, password: String) {
        this.id = id
        this.username = username
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }
}