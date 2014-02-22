package de.htwg.scalaseapal.view

import org.scaloid.common._
import android.view.ViewGroup.LayoutParams
import android.view.Gravity
import de.htwg.scalaseapal.model.Person
import android.graphics.{Typeface, Color}

class MainActivity extends SActivity {

   onCreate {
     contentView = new SVerticalLayout {
       this += new SLinearLayout {
         SButton("Map").textSize(30.dip).<<(0.dip,LayoutParams.WRAP_CONTENT).Weight(1).margin(-5.dip,-5.dip,-5.dip,-5.dip).>>.onClick(
           startActivity(SIntent[MapActivity])
         )
         SButton("Logbook").textSize(30.dip).<<(0.dip, LayoutParams.WRAP_CONTENT).Weight(1).margin(-5.dip,-5.dip,-5.dip,-5.dip).>>.onClick(
          startActivity(SIntent[BoatActivity])
         )
      }
       this += new SLinearLayout {
         SImageView().<<.Weight(1).marginRight(-350.dip).>>.setImageResource(R.drawable.icon_72)
         STextView("SeaPal").textSize(30.dip).<<.Weight(1).>>
       }.<<.fill.>>.gravity(Gravity.CENTER).backgroundDrawable(R.drawable.img02)
     }
   }

}

