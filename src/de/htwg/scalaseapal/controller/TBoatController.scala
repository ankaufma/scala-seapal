package de.htwg.scalaseapal.controller

import de.htwg.scalaseapal.database.TBoatDatabase
import de.htwg.scalaseapal.model.BoatModel

/**
 * Created by AK on 18.12.13.
 */
trait TBoatController { this: TBoatDatabase =>
  val boatController = BoatController
  object BoatController {

    def createBoat(boat: BoatModel) {
      boatDatabase.createBoat(boat)
    }

    def deleteBoatByName (boat: String) = {
      boatDatabase.deleteBoatByName(boat)
    }

    def getAllBoats: List[BoatModel]  = {
      boatDatabase.getAllBoats
    }

    def closeDB: Unit = {
      boatDatabase.close
    }

    def openDB: Unit = {
      boatDatabase.open
    }

    def isNotEmpty [A](param: A): Boolean = {
      param match {
        case s: String => if(s == "") false else true
        case _ => false
      }
    }

    def makeFlatStringAttList(attList: List[Any]) = {
      var flatList: List[String] = List()
      attList.foreach{
        case s: String => flatList ::= s
        case d: Double => flatList ::= d.toString
        case i: Integer => flatList ::= i.toString
        case (a,b) => flatList ::= a.toString; flatList ::= b.toString
        case (a,b,c) => flatList ::= a.toString; flatList ::= b.toString; flatList ::= c.toString
        case l: List[AnyRef] => l.foreach(elem => flatList ::= elem.toString)
      }
      flatList.reverse
    }
  }
}
