package com.jasperdenkers.play.utils

import play.api.data.Forms.{jodaLocalDate, of}
import play.api.data.Mapping
import play.api.data.format.Formats._
import play.api.data.validation._

object Forms extends Constraints {

  def javaLocalDate(pattern: String): Mapping[java.time.LocalDate] = jodaLocalDate(pattern).transform(Dates.toLocalDate, Dates.toJodaLocalDate)

  val emailAddressesCommaSeparated: Mapping[String] = (of[String] verifying Constraint[String]("constraint.email.commaSeparated") { e =>
    val splitAndTrimmed = e.split(",").map(_.trim)

    if (splitAndTrimmed.map(emailAddress(_)).forall(_ == Valid))
      Valid
    else
      Invalid(ValidationError("error.email.commaSeparated"))
  }).transform(_.split(",").map(_.trim).distinct.mkString(","), identity)

}