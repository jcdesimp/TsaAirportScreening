import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, UntypedActor}

/**
 * Created by jcdesimp on 12/2/14.
 */

class QueueActor(idnum: Int, bag: ActorRef, bod: ActorRef) extends Actor {

  val id = idnum
  val bagScan = bag
  val bodyScan = bod

  override def receive: Receive = {

    case _ => unhandled(receive)

  }
}
