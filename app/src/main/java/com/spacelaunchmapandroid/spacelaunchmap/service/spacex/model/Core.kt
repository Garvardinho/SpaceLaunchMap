package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model

data class Core(
    var core: String?,
    var flight: Int?,
    var gridfins: Boolean?,
    var legs: Boolean?,
    var reused: Boolean?,
    var landing_attempt: Boolean?,
    var landing_success: Boolean?,
    var landing_type: String?,
    var landpad: String?
)
