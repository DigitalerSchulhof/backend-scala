object Versioning {
  def version: String =
    sys.env.getOrElse("VERSION", "dev")
}
