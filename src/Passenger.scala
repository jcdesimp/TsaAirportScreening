/**
 * Created by jcdesimp on 12/4/14.
 * Passenger object that is going through security
 */
class Passenger(val name: String) {

  val bags = new Baggage(this)

}
