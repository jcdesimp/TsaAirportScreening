import akka.actor.ActorRef

/**
 * Created by jcdesimp on 12/4/14.
 * Container class representing a line in the TSA system
 * Contains a security
 */
class TSALine(val security: ActorRef, val bagScan: ActorRef, val bodyScan: ActorRef, val queue: ActorRef) {



}
