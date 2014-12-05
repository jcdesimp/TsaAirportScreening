import akka.actor.ActorRef

/**
 * Created by jcdesimp on 12/4/14.
 */
class TSALine(sec: ActorRef, bag: ActorRef, body: ActorRef, que: ActorRef) {
  val security = sec
  val bagScan = bag
  val bodyScan = body
  val queue = que



}
