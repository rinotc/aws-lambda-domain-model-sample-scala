package sample.domain.util

import arch.ddd.ValueObject

import scala.util.matching.Regex

final case class EmailAddress(value: String) extends ValueObject {
  import EmailAddress._

  require(isValid(value), s"address: $value is not valid email address patterns")
}

object EmailAddress {

  private val emailAddressRegex: Regex =
    "^[a-zA-Z0-9_+-]+(.[a-zA-Z0-9_+-]+)*@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$".r

  private def isValid(value: String) = emailAddressRegex.matches(value)
}
