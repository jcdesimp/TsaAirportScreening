import akka.actor.{ActorSystem, Props}

/**
 * Created by jcdesimp on 11/24/14.
 * Main file with main method for running program
 */
object TSA {
  def main (args: Array[String]) {

    val NUM_LINES = 3 // N Number of lines in the airport

    val system = ActorSystem("TSAsystem")
    //val docChecker = system.actorOf(Props(classOf[DocCheckActor], "hi"))
    //docChecker ! new WaitingMessage

    for (x <- 0 until NUM_LINES) {
      println(x);
    }

  }
}
