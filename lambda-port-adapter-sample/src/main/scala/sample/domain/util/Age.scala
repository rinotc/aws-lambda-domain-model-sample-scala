package sample.domain.util

final case class Age(value: Int) {
  require(value >= 0, value <= 130)
}
