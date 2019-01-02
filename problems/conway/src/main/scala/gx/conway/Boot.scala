package gx.conway

object Boot extends App {

  try {
    require(args.length >= 2, "must provide input and output file path")
    ConwayGame.play(args(0), args(1))
  } catch {
    case e: Exception =>
      println("Error:")
      println(e.getMessage)
  }

}
