package com.appturbo.base.repository.model

import com.appturbo.base.repository.keys.Keys
import com.google.gson.annotations.SerializedName

data class PortfolioModel(
    @SerializedName(Keys.CLIENT_ID)
    val clientId: String,
    @SerializedName(Keys.DATA)
    val mData: List<PortfolioData>?,
    @SerializedName(Keys.ERROR)
    val error: Any,
    val responseType: String,
    val timestamp: Long
)

data class PortfolioData(
    @SerializedName(Keys.AVG_PRICE)
    val avgPrice: String,
    @SerializedName(Keys.CLOSE)
    val close: Double,
    @SerializedName(Keys.CNC_USED_QTY)
    val cncUsedQuantity: Int,
    @SerializedName(Keys.COLLATERAL_QTY)
    val collateralQty: Int,
    @SerializedName(Keys.COLLATERAL_TYPE)
    val collateralType: String,
    @SerializedName(Keys.COLLATERAL_UPDATE_TYPE)
    val collateralUpdateQty: Int,
    @SerializedName(Keys.COMPANY_NAME)
    val companyName: String,
    @SerializedName(Keys.HAIRCUT)
    val haircut: Double,
    @SerializedName(Keys.HOLDINGS_UPDATE_QTY)
    val holdingsUpdateQty: Int,
    @SerializedName(Keys.ISIN)
    val isin: String,
    @SerializedName(Keys.LTP)
    val ltp: Double,
    @SerializedName(Keys.PRODUCT)
    val product: String,
    @SerializedName(Keys.QUANTITY)
    val quantity: Int,
    @SerializedName(Keys.SYMBOL)
    val symbol: String,
    @SerializedName(Keys.T1_HOLDING_QTY)
    val t1HoldingQty: Int,
    @SerializedName(Keys.TOKEN_BSE)
    val tokenBse: String,
    @SerializedName(Keys.TOKEN_NSE)
    val tokenNse: String,
    @SerializedName(Keys.WITHHELD_COLLATERAL_QTY)
    val withheldCollateralQty: Int,
    @SerializedName(Keys.WITHHELD_HOLDING_QTY)
    val withheldHoldingQty: Int,

    var currentValue: Double = 0.0,
    var investmentValue: Double = 0.0,
    var individualPNL: Double = 0.0,
    var sumCurrentValue: Double = 0.0,
    var sumInvestmentValue: Double = 0.0,
    var totalPNL: Double = 0.0,
    var todaysPNL: Double = 0.0,
)