package tests.scala

import org.scalatest.{Matchers, FlatSpec}
import de.htwg.scalaseapal.controller.DatabaseInjection
import de.htwg.scalaseapal.model.BoatModel

/**
 * Created by AK on 20.12.13.
 */
class BoatTest extends FlatSpec with Matchers {
  val emptyBoat = new BoatModel() //Creating empty Boat


  "An empty boat" should "be initialized with Default Values (Default Constructor)" in {
    assert (emptyBoat.boat === "")
    assert (emptyBoat.boat_type === "")
    assert (emptyBoat.callSign === "")
    assert (emptyBoat.constructor === "")
    assert (emptyBoat.displacement === 0.0)
    assert (emptyBoat.draft === 0.0)
    assert (emptyBoat.homePort === "")
    assert (emptyBoat.insurance === "")
    assert (emptyBoat.length === 0.0)
    assert (emptyBoat.mastHeigt === 0.0)
    assert (emptyBoat.motor === "")
    assert (emptyBoat.owner === "")
    assert (emptyBoat.registerNr === "")
    assert (emptyBoat.sailMainGenuaSpi === (0.0,0.0,0.0))
    assert (emptyBoat.rigging === "")
    assert (emptyBoat.sailSign === "")
    assert (emptyBoat.tankSize === 0.0)
    assert (emptyBoat.tankSizeWasteFresh === (0.0,0.0))
    assert (emptyBoat.width === 0.0)
    assert (emptyBoat.yachtClub === "")
    assert (emptyBoat.yearOfConstruction === 0)
  }

  "A copy of an empty boat with boatName 'Pequad'" should "return the Name 'Pequad'" in {
    val pequad = emptyBoat.copy(boat="Pequad")
    assert (pequad.boat === "Pequad")
  }

  "Tupel sailMainGenuaSpiSize" should "be readable individual" in {
    val tupel3 = emptyBoat.copy(sailMainGenuaSpi=(20.5, 15.8, 10.1))
    assert (tupel3.sailMainGenuaSpi._1 === 20.5)
    assert (tupel3.sailMainGenuaSpi._2 === 15.8)
    assert (tupel3.sailMainGenuaSpi._3 === 10.1)
  }
}