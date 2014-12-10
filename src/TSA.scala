import akka.actor.{ActorRef, ActorSystem, Props}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * Created by jcdesimp on 11/24/14.
 * Main file with main method for running program
 */
object TSA {
  def main (args: Array[String]) {

    val NUM_LINES = 3 // N Number of lines in the airport

    // Build up the TSA system

    val system = ActorSystem("TSAsystem")

    val tsaJail = system.actorOf(Props(classOf[JailActor], NUM_LINES))

    val tsaLines = mutable.ArrayBuffer.empty[TSALine]


    for (x <- 0 until NUM_LINES) {
      val sec = system.actorOf(Props(classOf[SecurityActor], x, tsaJail))
      val bag = system.actorOf(Props(classOf[BagScanActor], x, sec))
      val body = system.actorOf(Props(classOf[BodyScanActor], x, sec))
      val que = system.actorOf(Props(classOf[QueueActor], x, bag, body))
      tsaLines += new TSALine(x, sec, bag, body, que)
    }

    val docCheck = system.actorOf(Props(classOf[DocCheckActor], tsaLines))




    // System power on
    docCheck ! new PowerOn

    // Setup complete, start sending passengers at it
    /*docCheck ! new Passenger("Bob")
    docCheck ! new Passenger("Bill")
    docCheck ! new Passenger("Joe")
    docCheck ! new Passenger("John")
    docCheck ! new Passenger("Mike")
    docCheck ! new Passenger("Ted")
    docCheck ! new Passenger("Pete")
    docCheck ! new Passenger("Rob")
    docCheck ! new Passenger("Jake")
    docCheck ! new Passenger("Steve")*/

    docCheck ! new Passenger("p1")
    docCheck ! new Passenger("p2")
    docCheck ! new Passenger("p3")

    // All passengers have finished arriving, start system shutdown
    docCheck ! new PowerOff





    // todo remove shutdown call once program operates as expected.
    //system.shutdown()

  }
}
