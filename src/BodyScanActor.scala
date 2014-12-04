import akka.actor.Actor.Receive
import akka.actor.{Actor, UntypedActor}

/**
 * Created by jcdesimp on 12/3/14.
 * Actor representing BodyScanner
 */
class BodyScanActor extends Actor {
  override def receive: Receive = {
    case "test" =>

    case _ => println("Unknown message recieved!")

  }
}
