import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, UntypedActor}

/**
 * Created by jcdesimp on 12/3/14.
 * Actor representing BodyScanner
 */
class BodyScanActor(val id: Int, val security: ActorRef) extends Actor {

  private var readyToShutdown = false

  override def receive: Receive = {

    case m: StartBodyScan => {
      println("Passenger " + m.passenger.name + " enters body scanner " + id)


      if (!readyToShutdown) {
        println("Body scanner " + id + " is ready")
        sender() ! new BodyReady
      }
    }

    case m: PowerOff => {
      readyToShutdown = true
      println("Body scanner " + id + " is powering off.")


    }

    case _ => unhandled(receive)

  }
}
