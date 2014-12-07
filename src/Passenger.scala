/**
 * Created by jcdesimp on 12/4/14.
 * Passenger object that is going through security
 */
class Passenger(name: String) {

  val bags = new Baggage(this)

  def getName: String = name
  def getBags: Baggage = bags

}
