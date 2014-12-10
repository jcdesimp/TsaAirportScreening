import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, UntypedActor}

import scala.util.Random

/**
 * Created by jcdesimp on 12/3/14.
 * Actor for scanning bags in the TSA system
 */
class BagScanActor(val id: Int, val security: ActorRef) extends Actor {
  var readyToShutdown = false

  override def receive: Receive = {

    case m: PowerOn =>
      println("Queue "+id+" is powered on.")


    case m: PowerOff =>
      readyToShutdown = true
      shutdown()

    case m: Baggage =>
      val p = m.getOwner
      val rand = new Random()
      println("Bag scanner " + id + " receives passenger " + p.name + "\'s" + " baggage.")

      if(rand.nextInt(100) < 20) {
        println(p.name + "\'s baggage not pass bag scan!")
        println(p.name + " is sent to security " + id + ".")
        security ! new BagStatus(p, false)
      } else {
        println(p.name + "\'s passes bag scan.")
        println(p.name + " is sent to security " + id + ".")
        security ! new BagStatus(p, true)
      }

    case _ => unhandled(receive)

  }

  def shutdown() = {
    println("Bag scanner "+id+" is powering off.")
    security ! new PowerOff
  }

}
