import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, UntypedActor}

/**
 * Created by jcdesimp on 12/2/14.
 * Represents a passenger waiting queue in a TSA Line
 */

class QueueActor(val id: Int, val bagScan: ActorRef, val bodyScan: ActorRef) extends Actor {

  override def receive: Receive = {

    case _ => unhandled(receive)

  }
}
