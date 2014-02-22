package de.htwg.scalaseapal.model

/**
 * Created by AK on 30.01.14.
 */
case class Trip (var name: String, var startLocation: String, var endLocation: String, var skipper: String,
                 var crewMembers: List[Person], var startTime: Long, var endTime: Long, var duration:Long,
                 var motor: Integer, var fuel: Double, var note: String, var boat: BoatModel){

  def this() = this("", "", "", "", List(new Person()), 0L, 0L, 0L, 0, 0.0, "", new BoatModel())

}
