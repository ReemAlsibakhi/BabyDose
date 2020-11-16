package com.reemsib.doseapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.reemsib.doseapp.R
import com.reemsib.doseapp.model.AppConstants
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_result.img_info
import java.util.*


class ResultActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val data = intent
        val drug = data.getStringExtra(AppConstants.DRUG_NAME)!!
        val layout = data.getStringExtra(AppConstants.LAYOUT)
        val age_year = data.getIntExtra(AppConstants.AGE_YEAR, -1)
        val ageMonth = data.getIntExtra(AppConstants.AGE_MONTH, -1)
        val weight = data.getDoubleExtra(AppConstants.WEIGHT, 0.0)

        if (layout == AppConstants.WEIGHT) {
            showWeightBlock()
        } else if (layout == AppConstants.AGE) {
            showAgeBlock()

        }

        tv_drugName.text = drug
        tv_ageYear.text = "$age_year"
        tv_ageMonth.text = "$ageMonth"
        tv_weight.text = "$weight"
        calculate(drug, age_year, ageMonth, weight)

        img_left.setOnClickListener(this)
        tv_guideLink.setOnClickListener(this)
        img_info.setOnClickListener(this)
        checkIronGuide()

    }

    private fun checkIronGuide() {
        var drug = tv_drugName.text.toString()
        if (drug == getString(R.string.IRON_SYRUP50mg_elemental_iron_Prophylaxis) ||
            drug == getString(R.string.IRON_SYRUP50mg_elemental_iron_Treatment_below_8kg)) {
            tv_guideLink.text = getString(R.string.family_health_unrwa)
        }else if(drug==getString(R.string.NYSTATIN_ORAL_SUSP)){
            tv_guideLink.text = getString(R.string.medscape)

        }else{
            tv_guideLink.text = getString(R.string.stg_standard_treatment_guidlines)

        }

    }

    private fun calculate(drug: String, age_year: Int, ageMonth: Int, weight: Double) {
        when (drug) {
            getString(R.string.Chlorpheniramine_syr) -> {
                // <2
                if ((age_year == 0 && ageMonth < 12) || (age_year == 1 && ageMonth < 12)) {
                    tv_result.text = "Not recommended( No data available for age less than 2 years)"
                }
                // 2 < 6
                else if (age_year >= 2 && age_year < 6) {
                    tv_result.text =
                        "( 2.5 ml ) every 6 hours (do not exceed 6 mg/day) for 2-3 days when required (but do not give more than 7 days in a row)"
                }
                // 6 - 12 with equal
                else if (age_year >= 6 && age_year <= 12) {
                    tv_result.text = "( 5 ml ) every 6 hours (do not exceed 12 mg/day)"
                }
                // >12
                else if (age_year > 12) {
                    tv_result.text = "( 10 ml )  every 6 hours (do not exceed 24 mg/day"

                }
            }
            getString(R.string.Amoxicillin_sus250) -> {
                val mg = 50.0 * weight
                val ml = (mg * 5) / (250 * 3)

                val mg_acute = 90.0 * weight
                val ml_acute = (mg_acute * 5) / (250 * 2)
                tv_result.text =
                    "( " + String.format(Locale.ENGLISH,"%.2f", ml) + " ml ) every 8 hours for 10 days \n " +
                            "\n* For acute otitis media: ( " + String.format(Locale.ENGLISH,"%.2f", ml_acute) + " ml ) every 12 hours for 10 day"



            }
            getString(R.string.Azithromycin_sus200) -> {
                val mg = 10.0 * weight
                val ml = (mg * 5) / (200)
                tv_result.text =
                    "( " + String.format(Locale.ENGLISH,"%.2f", ml) + " ml ) once daily  for 5 days  "

            }
            getString(R.string.Cephalexin_sus250) -> {
               // atopic
                val mg = 37.5 * weight
                val ml = (mg * 5) / (250 * 4)
                //acute
                val mg_acute = 37.5 * weight
                val ml_acute = (mg_acute * 5) / (250 * 2)

                tv_result.text =
                    "* For dermatitis: ( " + String.format(Locale.ENGLISH,"%.2f", ml) + " ml ) every 6 hours for 7-10 days \n \n*For acute pharyngitis: ( "+String.format(Locale.ENGLISH,"%.2f",ml_acute) +" ml ) every 12 hours for 10 days"

            }

            getString(R.string.Salbutamol_inhale) -> {
                if (age_year >= 4) {
                    tv_result.text =
                        "( 100 mcg ) per metered dose. 1-2 puffs up to 4-6 times in 24 hours as needed regardless of whether 1 or 2 puffs"

                } else {
                    tv_result.text = "Not Indicated"

                }
            }
            getString(R.string.Salbutamol_syr2) -> {
                //2-6
                if (age_year >= 2 && age_year <= 6) {
                    tv_result.text = "( 2.5 to 5 ) ml every 8 or 6 hours "
                } else if (age_year > 6) {
                    tv_result.text = "( 5 ) ml evey 8 hours"
                } else {
                    tv_result.text = "Not Indicated"
                }

            }
            getString(R.string.Beclomethasone_inhaler) -> {
                // 5 to < 12
                if (age_year >= 5 && age_year < 12) {
                    tv_result.text = "( 50 mcg ) per metered dose twice daily"
                } else if (age_year >= 12) {
                    tv_result.text = "( 50 mcg ) per metered dose 100-300 mcg/day"
                } else {
                    tv_result.text = "Not Indicated"

                }
            }
            getString(R.string.Metronidazole_sus125) -> {
                val mg = 42.5 * weight
                val ml = (mg * 5) / (125 * 3)
                tv_result.text =
                    "( " + String.format(Locale.ENGLISH,"%.2f", ml) + " ml ) every 8 hours for 10 days"

            }
            getString(R.string.Metronidazole_sus200) -> {
                val mg = 42.5 * weight
                val ml = (mg * 5) / (200 * 3)
                tv_result.text =
                    "( " + String.format(Locale.ENGLISH,"%.2f", ml) + " ml ) every 8 hours for 10 days "

            }
            getString(R.string.AmoxicillinClavulanic_acid_sus250) -> {

                val mg = 25.0 * weight
                val ml = (mg * 5) / (250 * 3)

                val mg_atopic = 25.0 * weight
                val ml_atopic = (mg_atopic * 5) / (250 * 2)

                tv_result.text =
                    "*( " + String.format(Locale.ENGLISH,"%.2f", ml) + " ml ) every 8 hours for 7-10 days" +
                            "\n \n* For atopic Dermatitis: ( " + String.format(Locale.ENGLISH,"%.2f", ml_atopic) + " ml ) every 12 hours for 5 days"

            }
//            getString(R.string.Cefuroxime_sus125) -> {
//
//                val mg = 25.0 * weight
//                val ml = (mg * 5) / (125 * 2)
//                tv_result.text =
//                    "( " + String.format(Locale.ENGLISH,"%.2f", ml) + " ml ) every 12 hours for 10 days"
//
//            }
            getString(R.string.Paracetamol_syr_orally120) -> {

                val mg = 12.5 * weight
                val ml = (mg * 5) / (120)
                tv_result.text = "( " + String.format(Locale.ENGLISH,"%.2f", ml) + " ml ) every 6 hours when required for 2-3 days"

            }

            getString(R.string.Co_trimethoprim_sus240) -> {

                val mg = 8.0 * weight
                val ml = (mg * 5) / (40 * 2)
                tv_result.text =
                    "( " + String.format(Locale.ENGLISH,"%.2f", ml) + " ml ) every 12 hours for 10 days"

            }

            getString(R.string.Fluconazolecap50mg_children) -> {

                val mg = 4.5 * weight
                var cap = (mg * 1) / (50)

                tv_result.text =
                    "(" + String.format(Locale.ENGLISH,"%.2f", cap) + " cap ) once daily \n For Tinea Capitis & Corporis (4-6) weeks \n" +
                            "\n For Onychomycosis & Tinea Pedis & cruris (till disappearance of sings & symptoms)"
            }


            getString(R.string.IRON_SYRUP50mg_elemental_iron_Prophylaxis) -> {
                if ((age_year == 0 && (ageMonth >= 2 && ageMonth < 12)) || (age_year == 1 && ageMonth < 12)) {
                    tv_result.text = "Prophylaxis: ( 1.25 ml ) twice/weekly"

                } else if (age_year >= 2 && age_year < 5) {

                    tv_result.text = "Prophylaxis:  ( 2.5 ml ) twice/weekly"
                } else {

                    tv_result.text = "Prophylaxis: Not Indicated"
                }

                if (age_year >= 1 && age_year < 2) {
                    tv_result2.text = "Treatment: ( 2.5 ml ) once/daily"

                } else if (age_year >= 2 && age_year <= 12) {

                    tv_result2.text = "Treatment: ( 6 ml ) once/daily"

                } else {
                    tv_result2.text = "Treatment: Not Indicated"

                }


            }
            getString(R.string.IRON_SYRUP50mg_elemental_iron_Treatment_below_8kg) -> {
                if (weight < 8) {
                    val mg = 3.0 * weight
                    val ml = (mg * 5) / (50)
                    tv_result.text =
                        "( " + String.format(Locale.ENGLISH,"%.2f", ml) + " ml ) once/daily (Treatment)"
                } else {
                    tv_result.text = "Not Indicated, it is for less than 8 kg weight"
                }

            }

            getString(R.string.NYSTATIN_ORAL_SUSP) -> {

                if (age_year < 1) {
                    tv_result.text = "200,000 units orally 4 times a day"
                } else if (age_year >= 1) {
                    tv_result.text =
                        "400,000 to 600,000 units orally 4 times a day"

                } else {
                    tv_result.text = "Not Indicated"

                }
                tv_result2.text = "below one month: 100,000 units orally  4 times a day"
            }
//
//            getString(R.string.Cefuroxime_sus250) -> {
//                val mg = 25.0 * weight
//                val ml = (mg * 5) / (250 * 2)
//                tv_result.text =
//                    "( " + String.format(Locale.ENGLISH,"%.2f", ml) + " ml ) every 12 hours for 10 days"
//
//            }
            else -> {
                Toast.makeText(this, "d", Toast.LENGTH_LONG).show()


            }
        }
    }

    private fun showAgeBlock() {
        constraint_age.visibility = View.VISIBLE
        constraint_weight.visibility = View.GONE


    }

    private fun showWeightBlock() {
        constraint_weight.visibility = View.VISIBLE
        constraint_age.visibility = View.GONE
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.img_left -> {
                finish()
            }
            R.id.tv_guideLink -> {
              if(tv_guideLink.text==getString(R.string.stg_standard_treatment_guidlines)){
                  val uri: Uri =
                      Uri.parse("https://up4net.com/download136953.html")
                  val intent = Intent(Intent.ACTION_VIEW, uri)
                  startActivity(intent)
              }else if(tv_guideLink.text==getString(R.string.medscape)){
                  val uri: Uri =
                      Uri.parse("https://reference.medscape.com/drug/mycostatin-nilstat-nystatin-342594")
                  val intent = Intent(Intent.ACTION_VIEW, uri)
                  startActivity(intent)
                } else{
                  return
              }

            }
            R.id.img_info -> {
                val i = Intent(this, AboutUsActivity::class.java)
                startActivity(i)
            }
        }
    }
}