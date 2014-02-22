package de.htwg.scalaseapal.database

import de.htwg.scalaseapal.model.BoatModel

/**
 * Created by AK on 19.12.13.
 */
trait TBoatInterface {
  def open
  def close
  def createBoat(boat: BoatModel)
  def deleteBoatByName (boat: String)
  def getAllBoats: List[BoatModel]
}
