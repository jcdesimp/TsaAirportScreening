import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, UntypedActor}

import scala.util.Random

/**
 * Created by jcdesimp on 12/3/14.
 * Actor representing BodyScanner
 */
class BodyScanActor(val id: Int, val security: ActorRef) extends Actor {

  private var readyToShutdown = false

  override def receive: Receive = {

    case m: StartBodyScan =>
      println("Passenger " + m.passenger.name + " enters body scanner " + id)
      send_passenger(m.passenger)


      if (!readyToShutdown) {
        println("Body scanner " + id + " is ready")
        sender() ! new BodyReady
      }

    case m: PowerOn =>
      println("Queue "+id+" is powered on.")
      security ! new PowerOn

    case m: PowerOff =>
      readyToShutdown = true
      println("Body scanner " + id + " is powering off.")

    case _ => unhandled(receive)

  }


  def shutdown() = {
    println("Body scanner "+id+" is powering off.")
    security ! new PowerOff
  }


  def send_passenger(p: Passenger) = {

    val rand = new Random()
    println(p.name + " exits body scanner " + id + ".")

    if(rand.nextInt(100) < 20) {
      println(p.name + "\'s does not pass body scan!")
      println(p.name + " is sent to security " + id + ".")
      security ! new BodyStatus(p, false)
    } else {
      println(p.name + "\'s passes body scan.")
      println(p.name + " is sent to security " + id + ".")
      security ! new BodyStatus(p, true)
    }

  }
}
