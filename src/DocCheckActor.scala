import akka.actor.Actor.Receive
import akka.actor.{Actor, UntypedActor}

import scala.collection.mutable

/**
 * Created by jcdesimp on 12/3/14.
 * Initial document checker in the TSA system
 */
class DocCheckActor(val lines: mutable.ArrayBuffer[TSALine]) extends Actor {

  override def receive: Receive = {

    case "PowerOn" => {
      println("I got it!")
    }

    case _ => unhandled(receive)

  }
}
