package de.htwg.scalaseapal.model

/**
 * Created with IntelliJ IDEA.
 * User: AK
 * Date: 28.11.13
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */
case class BoatModel(boat: String,registerNr: String, sailSign: String,
                homePort: String, yachtClub: String, owner: String,
                insurance: String,callSign: String, boat_type: String,
                constructor: String, length: Double,width: Double,
                draft: Double, mastHeigt: Double, displacement: Double,
                rigging: String, yearOfConstruction: Int, motor: String,
                tankSize: Double, tankSizeWasteFresh: (Double, Double),
                sailMainGenuaSpi: (Double, Double, Double)) {

  //Default Constructor for empty Boat
  def this() = this("", "", "", "", "", "", "", "", "", "", 0.0, 0.0, 0.0, 0.0, 0.0, "", 0, "", 0.0, (0.0,0.0), (0.0,0.0,0.0))
}
