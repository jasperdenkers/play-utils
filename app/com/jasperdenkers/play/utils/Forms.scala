package com.jasperdenkers.play.utils

import play.api.data.Forms.of
import play.api.data.Mapping
import play.api.data.format.Formats._
import play.api.data.validation._

object Forms extends Constraints {

  val emailAddressesCommaSeparated: Mapping[String] = (of[String] verifying Constraint[String]("constraint.email.commaSeparated") { e =>
    val splitAndTrimmed = e.split(",").map(_.trim)

    if (splitAndTrimmed.map(emailAddress.apply).forall(_ == Valid))
      Valid
    else
      Invalid(ValidationError("error.email.commaSeparated"))
  }).transform(_.split(",").map(_.trim).distinct.mkString(","), identity)

}