package de.htwg.scalaseapal.view

import org.scaloid.common._
import android.view.Gravity
import de.htwg.scalaseapal.controller.DatabaseInjection
import de.htwg.scalaseapal.utils.{BoatStrings, ContextHolder}
import android.widget.ImageView.ScaleType

/**
 * Created by AK on 11.12.13.
 */
class BoatDetailsActivity extends SActivity {

  ContextHolder.myContext = this
  val boatController = DatabaseInjection.boatController

  onCreate {
    boatController.openDB
    val thisBoat = boatController.getAllBoats.filter(_.boat == getIntent().getStringExtra("Boat"))(0)

    contentView = new SScrollView {
      this += new SVerticalLayout {
        style {
          case t: STextView => t.textSize(20.dip).<<.Weight(1).>>
        }
        this += new SLinearLayout {
          STextView("Details of " + thisBoat.boat).gravity(Gravity.CENTER_HORIZONTAL).textSize(35.dip)
        }

        if(thisBoat.boat == "Pequad") {
          val image = SImageView().<<(300.dip, 240.dip).Gravity(Gravity.CENTER).>>
          image.setImageResource(R.drawable.whalesinksship)
          image.setScaleType(ScaleType.FIT_CENTER)
        }
        val boatAttributes = boatController.makeFlatStringAttList(thisBoat.productIterator.toList)

        for(i <- 0 until BoatStrings.boatStrings.size by 2) {
          this += new SLinearLayout {
            STextView(BoatStrings.boatStrings(i))
            STextView(boatAttributes(i))
            STextView(BoatStrings.boatStrings(i+1))
            STextView(boatAttributes(i+1))
          }
        }
      }

    }.backgroundDrawable(R.drawable.img02)
    boatController.closeDB
  }
}
