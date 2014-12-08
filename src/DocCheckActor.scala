import akka.actor.Actor.Receive
import akka.actor.{Actor, UntypedActor}

import scala.collection.mutable
import scala.util.Random

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
      val rand = new Random()
      println("Document Check is processing passenger " + m.name +".")

      if(rand.nextInt(100) < 20) {
        println(m.name + " is turned away for document problems.")
      } else {
        val nextLine = getNextLine
        println(m.name + " is sent to queue " + nextLine.id + ".")
      }


    }

    case m: PowerOff => println("Document Check is powering down.")

    case _ => unhandled(receive)

  }
}
