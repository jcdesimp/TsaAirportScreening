import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, UntypedActor}

/**
 * Created by jcdesimp on 12/3/14.
 * Actor representing BodyScanner
 */
class BodyScanActor(val id: Int, val security: ActorRef) extends Actor {


  override def receive: Receive = {

    case _ => unhandled(receive)

  }
}
