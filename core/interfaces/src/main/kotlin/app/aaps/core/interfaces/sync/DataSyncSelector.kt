package app.aaps.core.interfaces.sync

import app.aaps.core.data.db.GV
import app.aaps.core.data.db.OE
import app.aaps.core.data.db.TE
import app.aaps.core.data.db.TT
import app.aaps.database.entities.Bolus
import app.aaps.database.entities.BolusCalculatorResult
import app.aaps.database.entities.Carbs
import app.aaps.database.entities.DeviceStatus
import app.aaps.database.entities.EffectiveProfileSwitch
import app.aaps.database.entities.ExtendedBolus
import app.aaps.database.entities.Food
import app.aaps.database.entities.ProfileSwitch
import app.aaps.database.entities.TemporaryBasal
import org.json.JSONObject

interface DataSyncSelector {

    interface DataPair {

        val value: Any
        val id: Long
        var confirmed: Boolean
    }

    data class PairTemporaryTarget(override val value: TT, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairGlucoseValue(override val value: GV, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairTherapyEvent(override val value: TE, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairFood(override val value: Food, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairBolus(override val value: Bolus, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairCarbs(override val value: Carbs, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairBolusCalculatorResult(override val value: BolusCalculatorResult, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairTemporaryBasal(override val value: TemporaryBasal, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairExtendedBolus(override val value: ExtendedBolus, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairProfileSwitch(override val value: ProfileSwitch, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairEffectiveProfileSwitch(override val value: EffectiveProfileSwitch, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairOfflineEvent(override val value: OE, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairProfileStore(override val value: JSONObject, override val id: Long, override var confirmed: Boolean = false) : DataPair
    data class PairDeviceStatus(override val value: DeviceStatus, override val id: Long, override var confirmed: Boolean = false) : DataPair

    fun queueSize(): Long

    fun resetToNextFullSync()

    suspend fun doUpload()

    /**
     * This function called when new profile is received from NS
     * Plugin should update internal timestamp to not send Profile back as a new/updated
     *
     * @param timestamp received timestamp of profile
     *
     */
    fun profileReceived(timestamp: Long)
}