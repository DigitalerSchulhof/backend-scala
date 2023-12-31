package de.dsh.backend.utils

case class Duration private (
    seconds: Int
) extends AnyVal

object Duration:
  export math.Ordering.Implicits.infixOrderingOps

  val Instant: Duration = Duration(0)

  given Ordering[Duration] with
    def compare(x: Duration, y: Duration): Int =
      x.seconds - y.seconds

  def apply(
      hours: Int = 0,
      minutes: Int = 0,
      seconds: Int = 0
  ): Duration =
    val totalSeconds = lengthToSeconds(hours, minutes, seconds)
    require(totalSeconds >= 0, "Duration must be positive")

    new Duration(totalSeconds)

  private def lengthToSeconds(
      hours: Int,
      minutes: Int,
      seconds: Int
  ): Int =
    seconds + minutes * 60 + hours * 60 * 60
