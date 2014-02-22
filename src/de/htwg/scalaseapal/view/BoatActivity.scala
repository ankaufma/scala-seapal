package de.htwg.scalaseapal.view

import org.scaloid.common._
import android.view.ViewGroup.LayoutParams
import android.graphics.Color
import android.net.ConnectivityManager
import de.htwg.scalaseapal.controller.DatabaseInjection
import de.htwg.scalaseapal.utils.ContextHolder
import android.view.{View, Gravity}


/**
 * Created with IntelliJ IDEA.
 * User: AK
 * Date: 28.11.13
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
class BoatActivity extends SActivity {

  ContextHolder.myContext = this
  val boatController = DatabaseInjection.boatController

  onCreate {
    boatController.openDB
    var boats = boatController.getAllBoats.reverse

    contentView = new SVerticalLayout {

        this += new SLinearLayout() {
          STextView("Bootsübersicht").
            textSize(35.dip).
            textColor(Color.WHITE).
            gravity(Gravity.CENTER)
        }
        this += new SLinearLayout() {
          style {
            case t: STextView => t.
              textSize(30.dip).
              textColor(Color.WHITE).
              <<.Weight(1).>>
          }
          STextView("\tBootname \t\t |-|-|")
          STextView("\tBootytyp \t\t |-|-|")
          STextView("\tBesitzer \t\t |-|-|")
          STextView("\tBaujahr")
        }

        val boatView = SListView()
        //String Array for ArrayAdapter
        val boatStringArray = boats.map(boat =>
          (boat.boat + " |-|-| " + boat.boat_type + " |-|-| " + boat.owner
          + " |-|-| " + boat.yearOfConstruction)).
          toArray
        //Set Style and String Array to ArrayAdapter
        val boatAdapter = SArrayAdapter(boatStringArray).
          dropDownStyle(_.textSize(25.dip)).
          style(_.textColor(Color.WHITE).textSize(25.dip))

        //Set Adapter to View
        boatView.setAdapter(boatAdapter)
        boatView.onItemClick((parent, view, position, id) => {
            val clickedBoatName =
              boats.filter(
                boatView.getAdapter.getItem(position).toString matches _.boat + ".*"
              ).map(_.boat).mkString
            new AlertDialogBuilder(
              "Delete this Boat", "Do you really want to delete the boat " + clickedBoatName + "?")
            {
              positiveButton(
                "Yes",
                deleteThisBoat(clickedBoatName)
              )
              neutralButton(
                "Show Details",
                startActivity(SIntent[BoatDetailsActivity].putExtra("Boat", clickedBoatName))
              )
              negativeButton("No")
            }.show
        })

      this += new SLinearLayout() {
        SButton("Add New").textSize(25.dip).<<.Weight(1).Gravity(Gravity.CENTER).>>.onClick(
          startActivity(SIntent[InsertBoatActivity])
        )
      }

    }.backgroundDrawable(R.drawable.img02)
    boatController.closeDB
  }

  broadcastReceiver(ConnectivityManager.CONNECTIVITY_ACTION) { (context, intent) =>
    boatController.closeDB
    boatController.openDB
  }(this, onStartStop)

  def deleteThisBoat (boat: String): Unit = {
    boatController.deleteBoatByName(boat)
    finish()
    startActivity(getIntent)
  }

}