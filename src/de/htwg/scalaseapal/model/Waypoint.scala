package de.htwg.scalaseapal.model

/**
 * Created by AK on 30.01.14.
 */
case class Waypoint (var name: String, var latitude: Double, var longitude: Double, var date: Long,
                     var note: String, var btm: Integer, var dtm: Integer, var cod: Integer, var sog: Integer,
                     var  headedFor: Mark, var trip: Trip) {

  def this() = this("", 0.0, 0.0, 0L, "", 0, 0, 0, 0, new Mark(), new Trip())

}
