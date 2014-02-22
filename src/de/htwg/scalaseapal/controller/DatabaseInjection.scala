package de.htwg.scalaseapal.controller

import de.htwg.scalaseapal.database.TBoatDatabase
import de.htwg.scalaseapal.utils.ContextHolder

/**
 * Created by AK on 18.12.13.
 */

// This is where the magic happens :)
object DatabaseInjection extends TBoatController with TBoatDatabase {
  override val boatDatabase = new SqLiteBoat(ContextHolder.myContext)
  // OR
  //val boatDatabase = new CouchDBBoat(ContextHolder.myContext)
}
