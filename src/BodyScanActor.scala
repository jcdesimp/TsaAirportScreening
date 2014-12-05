import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, UntypedActor}

/**
 * Created by jcdesimp on 12/3/14.
 * Actor representing BodyScanner
 */
class BodyScanActor(idnum: Int, sec: ActorRef) extends Actor {

  val id = idnum
  val security = sec

  override def receive: Receive = {

    case _ => unhandled(receive)

  }
}
