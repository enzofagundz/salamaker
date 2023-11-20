package com.enzofagundz.salamaker.model

import java.time.LocalDate

class Reservation {
    var id: Int = 0
    var startDate: LocalDate = LocalDate.now()
    var endDate: LocalDate = LocalDate.now()
    var status: String = ""
    var user: User = User()
}