package com.jasperdenkers.play.utils

import org.scalatestplus.play._
import play.api.data.Form
import play.api.data.Forms._

class FormsSpec extends PlaySpec {

  "Forms" must {
    
    import Forms._

    val formWithEmailAddressesCommaSeparated = Form(
      single(
        "emails" -> emailAddressesCommaSeparated
      )
    )

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