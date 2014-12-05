import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, UntypedActor}

/**
 * Created by jcdesimp on 12/3/14.
 */
class SecurityActor(idnum: Int, jail: ActorRef) extends Actor {

  var id = idnum
  var jailActor = jail

  override def receive: Receive = {

    case _ => unhandled(receive)

  }
}
