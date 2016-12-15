package com.jasperdenkers.play.utils

import play.api.data.Forms.jodaLocalDate
import play.api.data.Mapping

object Forms {

  def javaLocalDate(pattern: String): Mapping[java.time.LocalDate] = jodaLocalDate(pattern).transform(Dates.toLocalDate, Dates.toJodaLocalDate)

}