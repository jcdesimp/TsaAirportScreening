import akka.actor.Actor.Receive
import akka.actor.{Actor, UntypedActor}

/**
 * Created by jcdesimp on 12/3/14.
 * Jail actor for the TSA system
 */
class JailActor(numLines: Int) extends Actor {


  override def receive: Receive = {
    case "test" =>

    case _ => println("Unknown message recieved!")

  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = println("ENDING!")
}
