import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, UntypedActor}

import scala.collection.mutable

/**
 * Created by jcdesimp on 12/2/14.
 * Represents a passenger waiting queue in a TSA Line
 */

class QueueActor(val id: Int, val bagScan: ActorRef, val bodyScan: ActorRef) extends Actor {

  private var bodyScannerReady = true
  private var readyToShutdown = false

  private var shutdownSent = false

  private val waiting =  new mutable.Queue[Passenger]()



  override def receive: Receive = {

    case m: PowerOn =>
      println("Queue "+id+" is powered on.")

    case m: PowerOff =>
      readyToShutdown = true
      if (waiting.isEmpty) {
        shutdown()
      }

    case m: BodyReady =>
      if (waiting.isEmpty) {
        bodyScannerReady = true
      } else {
        bodyScannerReady = false
        val next = waiting.dequeue()
        send_passenger(next)

      }
      if (waiting.isEmpty && readyToShutdown && bodyScannerReady) {
        shutdown()
      }

    case m: WaitingMessage =>

      println("Passenger " + m.passenger.name + " enters queue " + id)
      waiting.enqueue(m.passenger)
      bagScan ! m.passenger.bags
      if (bodyScannerReady) {
        bodyScannerReady = false
        val next = waiting.dequeue()
        send_passenger(next)

      }

      /*if (waiting.isEmpty && readyToShutdown) {
        shutdown()
      }*/

    case _ => unhandled(receive)

  }

  def shutdown() = {
    if (!shutdownSent) {
      shutdownSent = true
      println("Queue " + id + " is powering off.")
      bodyScan ! new PowerOff
      bagScan ! new PowerOff
    }
  }

  def send_passenger(p: Passenger) = {
    println(p.name + " is sent to body scanner " + id + ".")
    bodyScan ! new StartBodyScan(p)
  }


}
