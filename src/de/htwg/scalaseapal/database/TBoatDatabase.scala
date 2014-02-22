package de.htwg.scalaseapal.database

import android.content._
import android.database._
import android.database.sqlite.SQLiteDatabase
import de.htwg.scalaseapal.model.BoatModel
import android.util.Log
import org.ektorp.CouchDbConnector

/**
 * Created with IntelliJ IDEA.
 * User: AK
 * Date: 28.11.13
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */

trait TBoatDatabase {

  val boatDatabase: TBoatInterface

  class SqLiteBoat (context: Context) extends TBoatInterface {
    var db: SQLiteDatabase = _

    val dbHelper: CreateBoat = new CreateBoat(context)

    override def open = db = {
      dbHelper.getWritableDatabase
    }

    override def close = dbHelper.close

    val allColumns = Array(dbHelper.COLUMN_ID, dbHelper.COLUMN_BOAT, dbHelper.COLUMN_RegisterNr,
     dbHelper.COLUMN_SailSign, dbHelper.COLUMN_HomePort, dbHelper.COLUMN_YachtClub,
     dbHelper.COLUMN_Owner, dbHelper.COLUMN_Insucrance, dbHelper.COLUMN_CallSign,
     dbHelper.COLUMN_Type, dbHelper.COLUMN_Constructor, dbHelper.COLUMN_Length,
     dbHelper.COLUMN_Width, dbHelper.COLUMN_Draft, dbHelper.COLUMN_MastHeight,
     dbHelper.COLUMN_Displacement, dbHelper.COLUMN_Rigging, dbHelper.COLUMN_YearOfConstruction,
     dbHelper.COLUMN_Motor, dbHelper.COLUMN_TankSize, dbHelper.COLUMN_WasteWaterTankSize,
     dbHelper.COLUMN_FreshWaterTankSize, dbHelper.COLUMN_MainSailSize, dbHelper.COLUMN_GenuaSize, dbHelper.COLUMN_SpiSize
    )

    override def createBoat(boat:BoatModel): Unit = {
      val values = new ContentValues()
        values.put(dbHelper.COLUMN_BOAT, boat.boat)
        values.put(dbHelper.COLUMN_RegisterNr, boat.registerNr)
        values.put(dbHelper.COLUMN_SailSign, boat.sailSign)
        values.put(dbHelper.COLUMN_HomePort, boat.homePort)
        values.put(dbHelper.COLUMN_YachtClub, boat.yachtClub)
        values.put(dbHelper.COLUMN_Owner, boat.owner)
        values.put(dbHelper.COLUMN_Insucrance, boat.insurance)
        values.put(dbHelper.COLUMN_CallSign, boat.callSign)
        values.put(dbHelper.COLUMN_Type, boat.boat_type)
        values.put(dbHelper.COLUMN_Constructor, boat.constructor)
        values put(dbHelper.COLUMN_Length, boat.length)
        values.put(dbHelper.COLUMN_Width, boat.width)
        values.put(dbHelper.COLUMN_Draft, boat.draft)
        values.put(dbHelper.COLUMN_MastHeight, boat.mastHeigt)
        values.put(dbHelper.COLUMN_Displacement, boat.displacement)
        values.put(dbHelper.COLUMN_Rigging, boat.rigging)
        values.put(dbHelper.COLUMN_YearOfConstruction, boat.yearOfConstruction: Integer)
        values.put(dbHelper.COLUMN_Motor, boat.motor)
        values.put(dbHelper.COLUMN_TankSize, boat.tankSize)
        values.put(dbHelper.COLUMN_WasteWaterTankSize, boat.tankSizeWasteFresh._1)
        values.put(dbHelper.COLUMN_FreshWaterTankSize, boat.tankSizeWasteFresh._2)
        values.put(dbHelper.COLUMN_MainSailSize, boat.sailMainGenuaSpi._1)
        values.put(dbHelper.COLUMN_GenuaSize, boat.sailMainGenuaSpi._2)
        values.put(dbHelper.COLUMN_SpiSize, boat.sailMainGenuaSpi._3)
      val insertId = db.insert(dbHelper.TABLE_BOATS, null, values)
      var cursor = db.query(dbHelper.TABLE_BOATS, allColumns,
       dbHelper.COLUMN_ID + " = " + insertId, null, null, null, null)
      cursor.moveToFirst
      cursorToBoat(cursor)
      cursor.close()
    }

    override def deleteBoatByName (boat: String) {
     db.delete(dbHelper.TABLE_BOATS, dbHelper.COLUMN_BOAT + " = '" + boat + "'", null)
    }

    override def getAllBoats: List[BoatModel] = {
     var cursor = db.query(dbHelper.TABLE_BOATS,allColumns,null,null,null,null,null)
     cursor.moveToFirst
     var boats: List[BoatModel] = List()
     while(!cursor.isAfterLast) {
       val myBoat = cursorToBoat(cursor)
       boats ::= myBoat
       cursor.moveToNext
     }
     cursor.close
     boats.reverse
    }

    def cursorToBoat (cursor: Cursor) : BoatModel = {
     Log.d("BoatContol cursor: ", cursor.getString(1)+" "+cursor.getString(0)+" "+cursor.getString(2))
     val boat = BoatModel(boat = cursor.getString(1), registerNr = cursor.getString(2), sailSign = cursor.getString(3),
       owner = cursor.getString(6), insurance = cursor.getString(7), boat_type = cursor.getString(9),
       length = cursor.getDouble(11), width = cursor.getDouble(12), draft = cursor.getDouble(13),
       mastHeigt = cursor.getDouble(14), displacement = cursor.getDouble(15), yearOfConstruction = cursor.getInt(17),
       homePort = cursor.getString(4), yachtClub = cursor.getString(5), callSign = cursor.getString(8),
       constructor = cursor.getString(10), rigging = cursor.getString(16), motor = cursor.getString(18),
       tankSize = cursor.getDouble(19),
       tankSizeWasteFresh = (cursor.getDouble(20), cursor.getDouble(21)),
       sailMainGenuaSpi = (cursor.getDouble(22), cursor.getDouble(23), cursor.getDouble(24)))
      boat
    }
  }

  class CouchDBBoat (context: Context) extends TBoatInterface {
    val TAG: String  = "Boat-TouchDB"
    val DATABASE_NAME: String  = "seapal_boats_db"

    var touchDBBoatDatabase: CouchDBBoat = _
    var couchDbConnector: CouchDbConnector = _
    var dbHelper: CouchDBHelper = new CouchDBHelper(DATABASE_NAME)
    dbHelper.createDatabase(context)
    dbHelper.pullFromDatabase
    couchDbConnector = dbHelper.getCouchDbConnector;

    override def open: Unit = ???

    override def getAllBoats: List[BoatModel] = {
      val boatList: List[BoatModel] = List()
      boatList
    }

    override def deleteBoatByName(boat: String): Unit = {
      couchDbConnector.delete("boat", boat)
      //dbHelper.pushToDatabase
    }

    override def createBoat(boat: BoatModel): Unit = {
      couchDbConnector.create(boat)
      //dbHelper.pushToDatabase
    }

    override def close: Unit = ???
  }

  class DatabaseMock extends TBoatInterface {

    override def open: Unit = ???

    override def getAllBoats: List[BoatModel] = ???

    override def deleteBoatByName(boat: String): Unit = ???

    override def createBoat(boat: BoatModel): Unit = ???

    override def close: Unit = ???
  }

}
