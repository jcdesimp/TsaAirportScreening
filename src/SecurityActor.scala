import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Actor, UntypedActor}

import scala.collection.mutable

/**
 * Created by jcdesimp on 12/3/14.
 * Security guard actor in a TSA line
 */
class SecurityActor(val id: Int, val jail: ActorRef) extends Actor {

  private val passBagMap:mutable.Map[Passenger, Boolean] = mutable.Map()
  private var powerOffMess = 0


  override def receive: Receive = {

    case m: BagStatus =>
      if (passBagMap.contains(m.pass)) {
        //a check already came through
        if (!m.result || !passBagMap.apply(m.pass)) {
          sendToJail(m.pass)
        } else {
          println("Passenger " + m.pass.name + " leaves the TSA system.")
        }
        passBagMap.remove(m.pass)
      } else {
        //first time seeing this passenger
        passBagMap.put(m.pass, m.result)
      }
    case m: BodyStatus =>
      if (passBagMap.contains(m.pass)) {
        //a check already came through
        if (!m.result || !passBagMap.apply(m.pass)) {
          sendToJail(m.pass)
        } else {
          println("Passenger " + m.pass.name + " leaves the TSA system.")
        }
        passBagMap.remove(m.pass)
      } else {
        //first time seeing this passenger
        passBagMap.put(m.pass, m.result)
      }
    case m: PowerOn =>
      jail ! new PowerOn

    case m: PowerOff =>
      powerOffMess+=1
      if (powerOffMess >= 2) {
        println("Security booth " + id + " shuts down.")
        jail ! new PowerOff
      }



    case _ => unhandled(receive)

  }

  def sendToJail(p: Passenger): Unit = {
    println(p.name + " is sent to jail by security " + id +".")
    jail ! p
  }
}
