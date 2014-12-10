import akka.actor.Actor.Receive
import akka.actor.{Actor, UntypedActor}

import scala.collection.mutable

/**
 * Created by jcdesimp on 12/3/14.
 * Jail actor for the TSA system
 */
class JailActor(numLines: Int) extends Actor {

  var numShutdowns = 0
  val inJail = mutable.ArrayBuffer.empty[Passenger]

  override def receive: Receive = {
    case m: Passenger =>
      println("Passenger " + m.name + " is in jail.")
      inJail+=m

    case m: PowerOff =>
      numShutdowns+=1
      if (numShutdowns == numLines) {
        for(x <- 0 until inJail.size) {
          println("Passenger " + inJail(x).name + " is sent to permanent detention facilities.")
        }
        println("Jail shutting down.")
        this.context.system.shutdown()
      }


    case _ => println("Unknown message recieved!")

  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = println("System terminating!!")
}
