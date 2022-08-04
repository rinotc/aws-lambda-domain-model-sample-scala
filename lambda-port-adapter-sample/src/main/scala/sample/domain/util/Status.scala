package sample.domain.util

final class Status(val statusCode: Int, val message: String) {

  override def equals(other: Any): Boolean = other match {
    case that: Status =>
      statusCode == that.statusCode &&
        message == that.message
    case _ => false
  }

  override def hashCode(): Int = {
    Seq(statusCode, message).map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString = s"Status($statusCode, $message)"
}

object Status {
  def apply(statusCode: Int, message: String) = new Status(statusCode, message)
}
