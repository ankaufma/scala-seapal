package de.htwg.scalaseapal.model

/**
 * Created by AK on 30.01.14.
 */
case class Route (var  name: String, var date: Long, var marks: List[Mark], var routeEntryPoint: String,
                  var distance: Double){

  def this() = this("", 0L, List(new Mark()), "", 0.0)

}
