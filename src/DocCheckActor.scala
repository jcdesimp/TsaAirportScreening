import akka.actor.Actor.Receive
import akka.actor.{Actor, UntypedActor}

import scala.collection.mutable

/**
 * Created by jcdesimp on 12/3/14.
 * Initial document checker in the TSA system
 */
class DocCheckActor(val lines: mutable.ArrayBuffer[TSALine]) extends Actor {

  private var currLine: Int = 0

  private def getNextLine: TSALine = {
    val nextLine = lines.apply(currLine)

    currLine+=1
    if (currLine >= lines.length) {
      currLine = 0
    }

    nextLine
  }

  override def receive: Receive = {

    case m: PowerOn => println("Document Check is powering on.")

    case m: Passenger => {
      println("Document Check is processing passenger " + m.name +".")
    }

    case m: PowerOff => println("Document Check is powering down.")

    case _ => unhandled(receive)

  }
}
