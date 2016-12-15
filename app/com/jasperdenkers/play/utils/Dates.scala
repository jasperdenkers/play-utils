package com.jasperdenkers.play.utils

object Dates {

  def toLocalDate(jodaLocalDate: org.joda.time.LocalDate): java.time.LocalDate =
    java.time.LocalDate.of(jodaLocalDate.getYear, jodaLocalDate.getMonthOfYear, jodaLocalDate.getDayOfMonth)

  def toJodaLocalDate(localDate: java.time.LocalDate): org.joda.time.LocalDate =
    new org.joda.time.LocalDate(localDate.getYear, localDate.getMonthValue, localDate.getDayOfMonth)

}
