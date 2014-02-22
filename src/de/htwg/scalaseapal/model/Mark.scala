package de.htwg.scalaseapal.model

/**
 * Created by AK on 30.01.14.
 */
case class Mark (var name: String, var latitude: Double, var longitude: Double,
                 var btm: Int, var dtm: Int, var cog: Int, var Sog: Int,
                 var note: String, var date: Long, var isRouteMark: Boolean){

  def this() = this("", 0.0, 0.0, 0, 0, 0, 0, "", 0L, false)
}
