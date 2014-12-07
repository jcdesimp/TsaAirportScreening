import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, UntypedActor}

/**
 * Created by jcdesimp on 12/3/14.
 */
class SecurityActor(val id: Int, val jailActor: ActorRef) extends Actor {


  override def receive: Receive = {

    case _ => unhandled(receive)

  }
}
