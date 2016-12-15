package com.jasperdenkers.play.utils

import org.scalatestplus.play._
import play.api.data.Form
import play.api.data.Forms._

class FormsSpec extends PlaySpec {

  "Forms" must {
    
    import Forms.javaLocalDate

    val formWithJavaDate = Form(
      single(
        "date" -> javaLocalDate("yyyy-MM-dd")
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

  }

}