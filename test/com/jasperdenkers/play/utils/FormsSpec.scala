package com.jasperdenkers.play.utils

import org.scalatestplus.play._
import play.api.data.Form
import play.api.data.Forms._

class FormsSpec extends PlaySpec {

  "Forms" must {
    
    import Forms._

    val formWithJavaDate = Form(
      single(
        "date" -> javaLocalDate("yyyy-MM-dd")
      )
    )

    val formWithEmailAddressesCommaSeparated = Form(
      single(
        "emails" -> emailAddressesCommaSeparated
      )
    )

    "validate a valid java.time.LocalDate" in {
      val bindedForm = formWithJavaDate.bind(Map(
        "date" -> "2016-01-01"
      ))

      bindedForm.value mustBe Some(java.time.LocalDate.of(2016,1,1))
    }

    "invalidate an invalid java.time.LocalDate" in {
      val bindedForm = formWithJavaDate.bind(Map(
        "date" -> "2016-01-x"
      ))

      bindedForm.value mustBe None
      bindedForm.errors.exists {
        formError => formError.key == "date" && formError.messages.contains("error.date")
      } mustBe true
    }

    "validate a list of comma separated emails" in {
      val bindedForm = formWithEmailAddressesCommaSeparated.bind(Map(
        "emails" -> "foor@bar.com, baz@bar.com"
      ))

      bindedForm.value mustBe Some("foor@bar.com,baz@bar.com")
    }

    "filter out duplicates in a list of valid comma separated emails" in {
      val bindedForm = formWithEmailAddressesCommaSeparated.bind(Map(
        "emails" -> "foor@bar.com, baz@bar.com, foor@bar.com"
      ))

      bindedForm.value mustBe Some("foor@bar.com,baz@bar.com")
    }

  }

}