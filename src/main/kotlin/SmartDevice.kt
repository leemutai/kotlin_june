import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "online"
        protected set

    open val deviceType = "unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)
    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    override fun turnOn() {
        super.turnOn()
        println(
            "$name is turned on. Speaker volume set to $speakerVolume and channel number is " +
                    "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"
    private var brightnessLevel by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name is turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("$name turned off")
    }

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }
}

class SmartHome(val smartTvDevice: SmartTvDevice, val smartLightDevice: SmartLightDevice) {

    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    private var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

fun main() {
    val smartHome = SmartHome(
        SmartTvDevice(deviceName = "Android TV", deviceCategory = "Entertainment"),
        SmartLightDevice(deviceName = "Google light", deviceCategory = "Utility")
    )

    smartHome.turnOnTv()
    smartHome.turnOnLight()
    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}")
    println()

    smartHome.increaseTvVolume()
    smartHome.changeTvChannelToNext()
    smartHome.increaseLightBrightness()
    println()

    smartHome.turnOffAllDevices()
    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}.")
}




/*class SmartDevice {
    val name = "Android TV"
    val category = "Entertainment"
    var deviceStatus = "online"
    var speakerVolume = 2
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }
    var channelNumber = 1
        set(value) {
            if (value in 0..200) {
                field = value
            }
        }

    fun turnOn() {
        println("Smart device is turned on.")
    }

    fun turnOff() {
        println("Smart device is turned off.")
    }

}

fun main() {
    val smartTvDevice = SmartDevice()
    println("Device name is: ${smartTvDevice.name}")
    smartTvDevice.turnOn()
    smartTvDevice.turnOff()
}

/*class SmartTvDevice(name: String, category: String) :
    SmartDevice(name = name, category = category) {

    var speakerVolume = 2
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }
    var channelNumber = 1
        set(value) {
            if (value in 0..200) {
                field = value
            }
        }

    override fun turnOn() {
        super.turnOn()
        println("Smart TV turned on. Speaker volume set to $speakerVolume.")
    }

    override fun turnOff() {
        super.turnOff()
        println("Smart TV turned off")
    }

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
    }

    fun previousChannel() {
        channelNumber--
    }
}

 //VISIBILITY MODIFIERS
 /*class SmartHome(
     val smartTvDevice: SmartTvDevice,
     val smartLightDevice: SmartLightDevice
 ) {

     var deviceTurnOnCount = 0
         private set

     fun turnOnTv() {
         deviceTurnOnCount++
         smartTvDevice.turnOn()
     }

     fun turnOffTv() {
         deviceTurnOnCount--
         smartTvDevice.turnOff()
     }

     ...

     fun turnOnLight() {
         deviceTurnOnCount++
         smartLightDevice.turnOn()
     }

     fun turnOffLight() {
         deviceTurnOnCount--
         smartLightDevice.turnOff()
     }

     ...

 }*/

 VISIBILITY MODIFIERS FOR METHODS
 The syntax to specify a visibility modifier for a class starts with the private, protected, or internal modifiers followed by the syntax that defines a class. You can see the syntax in this diagram:
 */
 //internal open class SmartDevice(val name: String, val category: String) {

  //  ...

//}
// */


 //
 /*class SmartTvDevice(deviceName: String, deviceCategory: String) :
     SmartDevice(name = deviceName, category = deviceCategory) {

     private var speakerVolume = 2
         set(value) {
             if (value in 0..100) {
                 field = value
             }
         }
     private var channelNumber = 1
         set(value) {
             if (value in 0..200) {
                 field = value
             }
         }

     ...
 }

 class SmartLightDevice(deviceName: String, deviceCategory: String) :
     SmartDevice(name = deviceName, category = deviceCategory) {

     private var brightnessLevel = 0
         set(value) {
             if (value in 0..100) {
                 field = value
             }
         }

     ...
 }*/