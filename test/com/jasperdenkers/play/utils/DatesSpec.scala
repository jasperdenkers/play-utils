package com.jasperdenkers.play.utils

import org.scalatestplus.play._

class DatesSpec extends PlaySpec {

  "Dates" must {
    
    val javaLocalDate = java.time.LocalDate.of(2016, 1, 1)
    val jodaLocalDate = new org.joda.time.LocalDate(2016, 1, 1)

    "transform java.time.LocalDate into org.joda.time.LocalDate" in {
      Dates.toJodaLocalDate(javaLocalDate) mustBe jodaLocalDate
    }

    "transform org.joda.time.LocalDate into java.time.LocalDate" in {
      Dates.toLocalDate(jodaLocalDate) mustBe javaLocalDate
    }

  }

}