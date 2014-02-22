package de.htwg.scalaseapal.view

import org.scaloid.common._
import android.text.InputType._
import android.graphics.Color
import de.htwg.scalaseapal.controller.DatabaseInjection
import de.htwg.scalaseapal.utils.{BoatViewVars, ContextHolder}
import de.htwg.scalaseapal.model.BoatModel

/**
 * Created with IntelliJ IDEA.
 * User: AK
 * Date: 04.12.13
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
class InsertBoatActivity extends SActivity with BoatViewVars {

  ContextHolder.myContext = this
  val boatController = DatabaseInjection.boatController

  onCreate {
    boatController.openDB

    contentView = new SScrollView() {
      this += new SVerticalLayout {

      style {
        case t: STextView => t.<<.Weight(1).>>
        case e: SEditText => e.<<.Weight(1).>> inputType TEXT
      }

      this += new SLinearLayout {
        STextView("Boat Name")
        boot = SEditText("")
        STextView("Registerno.")
        registernr = SEditText("")
      }
      this += new SLinearLayout {
        STextView("Sail Sign")
        sailSign = SEditText("")
        STextView("Home Port")
        homePort = SEditText("")
      }
      this += new SLinearLayout {
        STextView("Yacht Club")
        yachtClub = SEditText("")
        STextView("Owner")
        owner = SEditText("")
      }
      this += new SLinearLayout {
        STextView("Insucrance")
        insucrance = SEditText("")
        STextView("Call Sign")
        callSign = SEditText("")
      }
      this += new SLinearLayout {
        STextView("Type")
        boat_type = SEditText("")
        STextView("Constructor")
        constructor = SEditText("")
      }
      this += new SLinearLayout {
        STextView("length")
        length = SEditText("") inputType TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL
        STextView("width")
        boat_width = SEditText("") inputType TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL
      }
      this += new SLinearLayout {
        STextView("Draft")
        draft = SEditText("") inputType TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL
        STextView("Mast Height")
        mastHeight = SEditText("") inputType TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL
      }
      this += new SLinearLayout {
        STextView("Displacement")
        displacement = SEditText("") inputType TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL
        STextView("Rigging")
        rigging = SEditText("")
      }
      this += new SLinearLayout {
        STextView("Year of Construction")
        yearOfConstruction = SEditText("") inputType TYPE_CLASS_NUMBER
        STextView("Motor")
        motor = SEditText("")
      }
      this += new SLinearLayout {
        STextView("Tank Size")
        tankSize = SEditText("") inputType TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL
        STextView("Waste Water Tank Size")
        wasteWaterTankSize = SEditText("") inputType TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL
      }
      this += new SLinearLayout {
        STextView("Fresh Water Tank Size")
        freshWaterTankSize = SEditText("") inputType TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL
        STextView("Main Sail Size")
        mainSailSize = SEditText("") inputType TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL
      }
      this += new SLinearLayout {
        STextView("Genua Size")
        genuaSize = SEditText("") inputType TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL
        STextView("Spi Size")
        spiSize = SEditText("") inputType TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL
      }
      this += new SLinearLayout {
        SButton("Add Boat",addBoat).<<.Weight(1).>>
        SButton("Back to Boat View",backToBoatView).<<.Weight(1).>>
      }
      }
    }.backgroundDrawable(R.drawable.img02)

  }

  def backToBoatView = {
    finish()
    startActivity(SIntent[BoatActivity])
  }

  def addBoat = {
    val boatVars = List(boot,registernr,sailSign,homePort,yachtClub,owner,insucrance,callSign,boat_type,constructor,length,boat_width,
      draft,mastHeight,displacement,rigging,yearOfConstruction,motor,tankSize,wasteWaterTankSize,freshWaterTankSize,mainSailSize,genuaSize,spiSize)
    if(boatVars.count(boatController isNotEmpty _.getText.toString) < 24) {
      toast("Can't Save that Boat. Please fill out all Fields")
    } else {
        val boat = BoatModel(boat = boot.getText.toString, registerNr = registernr.getText.toString,
          sailSign = sailSign.getText.toString, owner = owner.getText.toString,
          insurance = insucrance.getText.toString, boat_type = boat_type.getText.toString,
          length = length.getText.toString.toDouble, width = boat_width.getText.toString.toDouble,
          draft = draft.getText.toString.toDouble, mastHeigt = mastHeight.getText.toString.toDouble,
          displacement = displacement.getText.toString.toDouble, yearOfConstruction = yearOfConstruction.getText.toString.toInt,
          homePort = homePort.getText.toString, yachtClub = yachtClub.getText.toString,
          callSign = callSign.getText.toString, constructor = constructor.getText.toString,
          rigging = rigging.getText.toString, motor = motor.getText.toString,
          tankSize = tankSize.getText.toString.toDouble,
          tankSizeWasteFresh = (wasteWaterTankSize.getText.toString.toDouble,
            freshWaterTankSize.getText.toString.toDouble),
          sailMainGenuaSpi =(mainSailSize.getText.toString.toDouble,
            genuaSize.getText.toString.toDouble,
            spiSize.getText.toString.toDouble)
        )
      boatController createBoat boat
      //Refresh
      finish()
      startActivity(getIntent)
    }
  }
}
