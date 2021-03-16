@main def hello: Unit = {
  case class NonEmpty[T](e: T, tail: Option[NonEmpty[T]])

  println(TypeInfo[NonEmpty])
}
