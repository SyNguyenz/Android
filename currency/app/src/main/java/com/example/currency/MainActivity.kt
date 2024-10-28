package com.example.currency


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.IOException

// Danh sách mã tiền tệ
private val currencyList = listOf(
    "USD",
    "AED",
    "AFN",
    "ALL",
    "AMD",
    "ANG",
    "AOA",
    "ARS",
    "AUD",
    "AWG",
    "AZN",
    "BAM",
    "BBD",
    "BDT",
    "BGN",
    "BHD",
    "BIF",
    "BMD",
    "BND",
    "BOB",
    "BRL",
    "BSD",
    "BTN",
    "BWP",
    "BYN",
    "BZD",
    "CAD",
    "CDF",
    "CHF",
    "CLP",
    "CNY",
    "COP",
    "CRC",
    "CUP",
    "CVE",
    "CZK",
    "DJF",
    "DKK",
    "DOP",
    "DZD",
    "EGP",
    "ERN",
    "ETB",
    "EUR",
    "FJD",
    "FKP",
    "FOK",
    "GBP",
    "GEL",
    "GGP",
    "GHS",
    "GIP",
    "GMD",
    "GNF",
    "GTQ",
    "GYD",
    "HKD",
    "HNL",
    "HRK",
    "HTG",
    "HUF",
    "IDR",
    "ILS",
    "IMP",
    "INR",
    "IQD",
    "IRR",
    "ISK",
    "JEP",
    "JMD",
    "JOD",
    "JPY",
    "KES",
    "KGS",
    "KHR",
    "KID",
    "KMF",
    "KRW",
    "KWD",
    "KYD",
    "KZT",
    "LAK",
    "LBP",
    "LKR",
    "LRD",
    "LSL",
    "LYD",
    "MAD",
    "MDL",
    "MGA",
    "MKD",
    "MMK",
    "MNT",
    "MOP",
    "MRU",
    "MUR",
    "MVR",
    "MWK",
    "MXN",
    "MYR",
    "MZN",
    "NAD",
    "NGN",
    "NIO",
    "NOK",
    "NPR",
    "NZD",
    "OMR",
    "PAB",
    "PEN",
    "PGK",
    "PHP",
    "PKR",
    "PLN",
    "PYG",
    "QAR",
    "RON",
    "RSD",
    "RUB",
    "RWF",
    "SAR",
    "SBD",
    "SCR",
    "SDG",
    "SEK",
    "SGD",
    "SHP",
    "SLE",
    "SLL",
    "SOS",
    "SRD",
    "SSP",
    "STN",
    "SYP",
    "SZL",
    "THB",
    "TJS",
    "TMT",
    "TND",
    "TOP",
    "TRY",
    "TTD",
    "TVD",
    "TWD",
    "TZS",
    "UAH",
    "UGX",
    "UYU",
    "UZS",
    "VES",
    "VND",
    "VUV",
    "WST",
    "XAF",
    "XCD",
    "XDR",
    "XOF",
    "XPF",
    "YER",
    "ZAR",
    "ZMW",
    "ZWL"
)

class MainActivity : AppCompatActivity() {

    private lateinit var sourceAmount: EditText
    private lateinit var sourceCurrency: Spinner
    private lateinit var targetCurrency: Spinner
    private lateinit var targetAmount: TextView
    private lateinit var exchangeRate: TextView
    private lateinit var lastUpdated: TextView
    private lateinit var updateRatesButton: Button
    private lateinit var exchangeRateData: ExchangeRate

    private val conversionRates = mapOf(
        "USD" to 1.0,
        "AED" to 3.6725,
        "AFN" to 66.747,
        "ALL" to 91.3568,
        "AMD" to 387.4389,
        "ANG" to 1.79,
        "AOA" to 918.7449,
        "ARS" to 985.92,
        "AUD" to 1.5065,
        "AWG" to 1.79,
        "AZN" to 1.7008,
        "BAM" to 1.814,
        "BBD" to 2.0,
        "BDT" to 119.466,
        "BGN" to 1.8142,
        "BHD" to 0.376,
        "BIF" to 2904.6033,
        "BMD" to 1.0,
        "BND" to 1.3224,
        "BOB" to 6.9143,
        "BRL" to 5.698,
        "BSD" to 1.0,
        "BTN" to 84.1078,
        "BWP" to 13.3714,
        "BYN" to 3.2885,
        "BZD" to 2.0,
        "CAD" to 1.3836,
        "CDF" to 2834.9958,
        "CHF" to 0.8667,
        "CLP" to 949.145,
        "CNY" to 7.1324,
        "COP" to 4260.5178,
        "CRC" to 515.0261,
        "CUP" to 24.0,
        "CVE" to 102.2671,
        "CZK" to 23.4099,
        "DJF" to 177.721,
        "DKK" to 6.919,
        "DOP" to 60.2243,
        "DZD" to 133.3165,
        "EGP" to 48.7188,
        "ERN" to 15.0,
        "ETB" to 120.3913,
        "EUR" to 0.9275,
        "FJD" to 2.2361,
        "FKP" to 0.7732,
        "FOK" to 6.9189,
        "GBP" to 0.7732,
        "GEL" to 2.7336,
        "GGP" to 0.7732,
        "GHS" to 16.1326,
        "GIP" to 0.7732,
        "GMD" to 70.774,
        "GNF" to 8646.2201,
        "GTQ" to 7.7242,
        "GYD" to 208.7014,
        "HKD" to 7.7706,
        "HNL" to 25.1386,
        "HRK" to 6.988,
        "HTG" to 131.531,
        "HUF" to 373.5312,
        "IDR" to 15631.0052,
        "ILS" to 3.7978,
        "IMP" to 0.7732,
        "INR" to 84.1063,
        "IQD" to 1307.5845,
        "IRR" to 41949.3465,
        "ISK" to 138.3526,
        "JEP" to 0.7732,
        "JMD" to 158.6558,
        "JOD" to 0.709,
        "JPY" to 152.653,
        "KES" to 128.8935,
        "KGS" to 85.4426,
        "KHR" to 4068.3358,
        "KID" to 1.5065,
        "KMF" to 456.2838,
        "KRW" to 1381.5731,
        "KWD" to 0.3064,
        "KYD" to 0.8333,
        "KZT" to 485.1403,
        "LAK" to 21930.3221,
        "LBP" to 89500.0,
        "LKR" to 292.9738,
        "LRD" to 192.3377,
        "LSL" to 17.7737,
        "LYD" to 4.8094,
        "MAD" to 9.9081,
        "MDL" to 17.9042,
        "MGA" to 4600.5931,
        "MKD" to 56.8259,
        "MMK" to 2097.76,
        "MNT" to 3377.7585,
        "MOP" to 8.0033,
        "MRU" to 39.6979,
        "MUR" to 46.1726,
        "MVR" to 15.4374,
        "MWK" to 1737.0208,
        "MXN" to 19.91,
        "MYR" to 4.3471,
        "MZN" to 63.8449,
        "NAD" to 17.7737,
        "NGN" to 1644.2477,
        "NIO" to 36.7867,
        "NOK" to 10.9935,
        "NPR" to 134.5724,
        "NZD" to 1.6648,
        "OMR" to 0.3845,
        "PAB" to 1.0,
        "PEN" to 3.75,
        "PGK" to 3.9837,
        "PHP" to 58.0846,
        "PKR" to 277.9116,
        "PLN" to 4.0273,
        "PYG" to 7899.5927,
        "QAR" to 3.64,
        "RON" to 4.6139,
        "RSD" to 108.5072,
        "RUB" to 96.27,
        "RWF" to 1355.162,
        "SAR" to 3.75,
        "SBD" to 8.4952,
        "SCR" to 13.5331,
        "SDG" to 543.0769,
        "SEK" to 10.598,
        "SGD" to 1.3223,
        "SHP" to 0.7732,
        "SLE" to 22.641,
        "SLL" to 22641.0243,
        "SOS" to 570.6357,
        "SRD" to 33.6448,
        "SSP" to 3221.9847,
        "STN" to 22.7229,
        "SYP" to 12935.3488,
        "SZL" to 17.7737,
        "THB" to 33.7671,
        "TJS" to 10.644,
        "TMT" to 3.4966,
        "TND" to 3.1017,
        "TOP" to 2.3447,
        "TRY" to 34.2922,
        "TTD" to 6.7785,
        "TVD" to 1.5065,
        "TWD" to 32.0364,
        "TZS" to 2716.4908,
        "UAH" to 41.2664,
        "UGX" to 3657.0058,
        "UYU" to 41.4693,
        "UZS" to 12787.9711,
        "VES" to 39.4617,
        "VND" to 25419.9228,
        "VUV" to 119.8269,
        "WST" to 2.7467,
        "XAF" to 608.3784,
        "XCD" to 2.7,
        "XDR" to 0.7527,
        "XOF" to 608.3784,
        "XPF" to 110.6765,
        "YER" to 250.0847,
        "ZAR" to 17.7735,
        "ZMW" to 26.4696,
        "ZWL" to 27.2821
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linear_layout)

        sourceAmount = findViewById(R.id.sourceAmount)
        targetAmount = findViewById(R.id.targetAmount)
        sourceCurrency = findViewById(R.id.sourceCurrency)
        targetCurrency = findViewById(R.id.targetCurrency)
        updateRatesButton = findViewById(R.id.updateRatesButton)

        sourceAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertCurrency()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        sourceCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        targetCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }



    }
    private fun convertCurrency() {
        val sourceAmountText = sourceAmount.text.toString()
        if (sourceAmountText.isEmpty()) {
            targetAmount.text = ""
            return
        }

        val amount = sourceAmountText.toDoubleOrNull() ?: return
        val sourceCurrencyCode = sourceCurrency.selectedItem.toString()
        val targetCurrencyCode = targetCurrency.selectedItem.toString()

        val sourceRate = conversionRates[sourceCurrencyCode] ?: 1.0
        val targetRate = conversionRates[targetCurrencyCode] ?: 1.0

        // Tính toán số tiền chuyển đổi
        val convertedAmount = (amount / sourceRate) * targetRate
        targetAmount.text = String.format("%.2f", convertedAmount)
    }
}
