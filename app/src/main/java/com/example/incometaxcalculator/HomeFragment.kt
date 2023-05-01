package com.example.incometaxcalculator

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.widget.addTextChangedListener

class HomeFragment : Fragment() {
    var salary:Int = 0
    var standardDeduction:Int=0
    var taxableSalary:Int=0
    var taxableIncome:Int=0
    var housePropertyIncome:Int=0
    var businessProfessionIncome:Int=0
    var otherSourcesIncome:Int=0
    var taxAmount:Int=0
    var taxSlab:Int=0
    var sum:Int=0
    lateinit var message:String

    private val channelId = "Channel Id"
    private val channelName = "Channel Name"
    private val notificationId=1234

    lateinit var salaryValue:EditText
    lateinit var standardDeductionValue:TextView
    lateinit var taxableSalaryValue:TextView
    lateinit var taxableIncomeValue:TextView
    lateinit var housePropertyIncomeValue:TextView
    lateinit var businessProfessionIncomeValue:TextView
    lateinit var otherSourcesIncomeValue:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        salaryValue = view.findViewById(R.id.salaryValue)
        standardDeductionValue = view.findViewById(R.id.standardDeductionValue)
        taxableSalaryValue = view.findViewById(R.id.taxableSalaryValue)
        taxableIncomeValue = view.findViewById(R.id.taxableIncomeValue)
        housePropertyIncomeValue = view.findViewById(R.id.housePropertyIncomeValue)
        businessProfessionIncomeValue = view.findViewById(R.id.businessProfessionIncomeValue)
        otherSourcesIncomeValue = view.findViewById(R.id.otherSourcesIncomeValue)
        val btnTaxCalculator = view.findViewById<Button>(R.id.btnTaxCalculator)
        val cardView = view.findViewById<CardView>(R.id.cardView)
        val totalTaxValue = view.findViewById<TextView>(R.id.totalTaxValue)
        val taxCalculation = view.findViewById<TextView>(R.id.taxCalculation)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        val taxSlab1Result = view.findViewById<TextView>(R.id.taxSlab1Result)
        val taxSlab2Result = view.findViewById<TextView>(R.id.taxSlab2Result)
        val taxSlab3Result = view.findViewById<TextView>(R.id.taxSlab3Result)
        val taxSlab4Result = view.findViewById<TextView>(R.id.taxSlab4Result)
        val taxSlab5Result = view.findViewById<TextView>(R.id.taxSlab5Result)
        val taxSlab6Result = view.findViewById<TextView>(R.id.taxSlab6Result)

        val taxAmount1Result = view.findViewById<TextView>(R.id.taxAmount1Result)
        val taxAmount2Result = view.findViewById<TextView>(R.id.taxAmount2Result)
        val taxAmount3Result = view.findViewById<TextView>(R.id.taxAmount3Result)
        val taxAmount4Result = view.findViewById<TextView>(R.id.taxAmount4Result)
        val taxAmount5Result = view.findViewById<TextView>(R.id.taxAmount5Result)
        val taxAmount6Result = view.findViewById<TextView>(R.id.taxAmount6Result)


        taxIncomeCalculation()

        btnTaxCalculator.setOnClickListener {

            taxSlab1Result.setText("0")
            taxSlab2Result.setText("0")
            taxSlab3Result.setText("0")
            taxSlab4Result.setText("0")
            taxSlab5Result.setText("0")
            taxSlab6Result.setText("0")

            taxAmount1Result.setText("0")
            taxAmount2Result.setText("0")
            taxAmount3Result.setText("0")
            taxAmount4Result.setText("0")
            taxAmount5Result.setText("0")
            taxAmount6Result.setText("0")

            if (salaryValue.text.toString().isEmpty()) {
                Toast.makeText(activity, "Please provide salary value", Toast.LENGTH_SHORT).show()
            }
            else {
                taxCalculation.setText("")
                cardView.visibility=View.INVISIBLE
                progressBar.visibility=View.VISIBLE
                if (housePropertyIncomeValue.text.toString().isEmpty()) {
                    housePropertyIncomeValue.setText("0")
                }
                if (businessProfessionIncomeValue.text.toString().isEmpty()) {
                    businessProfessionIncomeValue.setText("0")
                }
                if (otherSourcesIncomeValue.text.toString().isEmpty()) {
                    otherSourcesIncomeValue.setText("0")
                }

                if (taxableIncome<=300000) {
                    taxSlab=0

                }
                else {
                    taxSlab=300000
                    taxAmount=0
                    taxSlab1Result.setText(taxSlab.toString())
                    taxAmount1Result.setText(taxAmount.toString())
                    if (taxableIncome>300000 && taxableIncome<=600000) {
                        taxSlab=taxableIncome-300000
//                      calculating 5% of taxSlab to get taxAmount
                        taxAmount=(taxSlab*5)/100
                        taxSlab2Result.setText(taxSlab.toString())
                        taxAmount2Result.setText(taxAmount.toString())
                        sum+=taxAmount
                    }
                    else {
                        taxSlab=600000-300000
                        taxAmount=(taxSlab)/20
                        taxSlab2Result.setText(taxSlab.toString())
                        taxAmount2Result.setText(taxAmount.toString())
                        sum+=taxAmount
                        if (taxableIncome>600000 && taxableIncome<=900000) {
                            taxSlab=taxableIncome-600000
//                          calculating 10% of taxSlab to get taxAmount
                            taxAmount=(taxSlab)/10
                            taxSlab3Result.setText(taxSlab.toString())
                            taxAmount3Result.setText(taxAmount.toString())
                            sum+=taxAmount
                        }
                        else {
                            taxSlab=900000-600000
                            taxAmount=(taxSlab)/10
                            taxSlab3Result.setText(taxSlab.toString())
                            taxAmount3Result.setText(taxAmount.toString())
                            sum+=taxAmount
                            if (taxableIncome>900000 && taxableIncome<=1200000) {
                                taxSlab=taxableIncome-900000
//                              calculating 15% of taxSlab to get taxAmount
                                taxAmount=(taxSlab*15)/100
                                taxSlab4Result.setText(taxSlab.toString())
                                taxAmount4Result.setText(taxAmount.toString())
                                sum+=taxAmount
                            }
                            else {
                                taxSlab=1200000-900000
                                taxAmount=(taxSlab*15)/100
                                taxSlab4Result.setText(taxSlab.toString())
                                taxAmount4Result.setText(taxAmount.toString())
                                sum+=taxAmount
                                if (taxableIncome>1200000 && taxableIncome<=1500000) {
                                    taxSlab=taxableIncome-1200000
//                                  calculating 20% of taxSlab to get taxAmount
                                    taxAmount=(taxSlab)/5
                                    taxSlab5Result.setText(taxSlab.toString())
                                    taxAmount5Result.setText(taxAmount.toString())
                                    sum+=taxAmount
                                }
                                else {
                                    taxSlab=1500000-1200000
                                    taxAmount=(taxSlab)/5
                                    taxSlab5Result.setText(taxSlab.toString())
                                    taxAmount5Result.setText(taxAmount.toString())
                                    sum+=taxAmount

                                }
                                if (taxableIncome>1500000) {
                                    taxSlab=taxableIncome-1500000
//                                  calculating 30% of taxSlab to get taxAmount
                                    taxAmount=(taxSlab*30)/100
                                    taxSlab6Result.setText(taxSlab.toString())
                                    taxAmount6Result.setText(taxAmount.toString())
                                    sum+=taxAmount
                                }
                            }
                        }
                    }
                }

                if (sum>0) {
                    message = "Congratulations! Your total tax amount has been calculated successfully \n" +
                            "Your Total Tax Amount is Rs $sum \n" +
                            "We would really appreciate your effort if you can provide us with the app rating. \n" +
                            "Thank You for choosing us\n" +
                            "Team Billzio "
                }
                else {
                    message = "Congratulations! Your total tax amount has been calculated successfully \n" +
                            "Your Total Tax Amount is Rs $sum \n" +
                            "Congratulations! You are exempted from any tax payment \n" +
                            "We would really appreciate your effort if you can provide us with the app rating. \n" +
                            "Thank You for choosing us\n" +
                            "Team Billzio "
                }
                totalTaxValue.setText("Rs $sum")

                Handler(Looper.getMainLooper()).postDelayed({
                    cardView.visibility=View.VISIBLE
                    progressBar.visibility=View.INVISIBLE
                    taxCalculation.setText("Tax Calculation:")
                    createNotificationChannel()
                    notification()
                    Toast.makeText(activity, "Scroll down to view detailed result", Toast.LENGTH_SHORT).show()
                },400)

            }

            sum=0
        }

        return view
    }

    fun taxIncomeCalculation() {
        salaryValue.addTextChangedListener {
//            Toast.makeText(activity, "Inside Salary Value", Toast.LENGTH_SHORT).show()
            if (salaryValue.text.toString().isNullOrEmpty()) {
                salary=0
                salaryValue.setText(salary.toString())
            }
            else {
                salary = salaryValue.text.toString().toInt()
                if (salary <= 50000) {
                    standardDeduction = salary
                } else {
                    standardDeduction = 50000
                }
                taxableSalary = salary - standardDeduction
                standardDeductionValue.setText(standardDeduction.toString())
                taxableSalaryValue.setText(taxableSalary.toString())
                taxableIncome = taxableSalary + housePropertyIncome + businessProfessionIncome + otherSourcesIncome
                taxableIncomeValue.setText("Rs $taxableIncome")
            }
        }

        housePropertyIncomeValue.addTextChangedListener {
//            Toast.makeText(activity, "House Prop Income Value", Toast.LENGTH_SHORT).show()
            if (housePropertyIncomeValue.text.toString().isEmpty()) {
                housePropertyIncome=0
                housePropertyIncomeValue.setText(housePropertyIncome.toString())
            }
            else {
                housePropertyIncome=housePropertyIncomeValue.text.toString().toInt()
            }
            taxableIncome = taxableSalary + housePropertyIncome + businessProfessionIncome + otherSourcesIncome
            taxableIncomeValue.setText("Rs $taxableIncome")
        }

        businessProfessionIncomeValue.addTextChangedListener {
//            Toast.makeText(activity, "Inside Business/Prof Value", Toast.LENGTH_SHORT).show()
            if (businessProfessionIncomeValue.text.toString().isEmpty()) {
                businessProfessionIncome=0
                businessProfessionIncomeValue.setText(businessProfessionIncome.toString())
            }
            else {
                businessProfessionIncome=businessProfessionIncomeValue.text.toString().toInt()
            }
            taxableIncome = taxableSalary + housePropertyIncome + businessProfessionIncome + otherSourcesIncome
            taxableIncomeValue.setText("Rs $taxableIncome")
        }

        otherSourcesIncomeValue.addTextChangedListener {
//            Toast.makeText(activity, "Inside Other Sources Value", Toast.LENGTH_SHORT).show()
            if (otherSourcesIncomeValue.text.toString().isEmpty()) {
                otherSourcesIncome=0
                otherSourcesIncomeValue.setText(otherSourcesIncome.toString())
            }
            else {
                otherSourcesIncome=otherSourcesIncomeValue.text.toString().toInt()
            }
            taxableIncome = taxableSalary + housePropertyIncome + businessProfessionIncome + otherSourcesIncome
            taxableIncomeValue.setText("Rs $taxableIncome")
        }

    }


    @SuppressLint("MissingPermission")
    fun notification() {
        val notificationBuilder = NotificationCompat.Builder(requireActivity(), channelId)
            .setContentTitle("Billzio")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSmallIcon(R.drawable.logo)
            .setStyle(NotificationCompat.BigTextStyle())
            .build()

        val notificationManager = NotificationManagerCompat.from(requireContext())
        notificationManager.notify(notificationId, notificationBuilder)
    }
    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            channel.description = "Billzio Tax calculation notification"
            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

}