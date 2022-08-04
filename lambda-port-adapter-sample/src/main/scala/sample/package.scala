package object sample {
  implicit class SameTypeEitherExtension[O](either: Either[O, O]) {
    def unwrap: O = either match {
      case Left(value)  => value
      case Right(value) => value
    }
  }
}
