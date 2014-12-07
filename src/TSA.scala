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

    val system = ActorSystem("TSAsystem")

    val tsaJail = system.actorOf(Props(classOf[JailActor], NUM_LINES))

    val tsaLines = mutable.ArrayBuffer.empty[TSALine]
    //val docChecker = system.actorOf(Props(classOf[DocCheckActor]))
    //docChecker ! new WaitingMessage



    for (x <- 0 until NUM_LINES) {
      val sec = system.actorOf(Props(classOf[SecurityActor], x, tsaJail))
      val bag = system.actorOf(Props(classOf[BagScanActor], x, sec))
      val body = system.actorOf(Props(classOf[BodyScanActor], x, sec))
      val que = system.actorOf(Props(classOf[QueueActor], x, bag, body))
      tsaLines += new TSALine(sec, bag, body, que)
    }

    val docCheck = system.actorOf(Props(classOf[DocCheckActor], tsaLines))


    //setup complete, start throwing passengers at it

    docCheck.tell(new PowerOn, ActorRef.noSender)






    // todo remove shutdown call once program operates as expected.
    system.shutdown()

  }
}
