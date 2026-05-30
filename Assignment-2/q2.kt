class Thermostat {
    var temperature: Double = 0.0
        private set

    fun updateTemperature(temp: Double) {
        temperature = temp
    }
}

fun main() {
    val t = Thermostat()
    t.updateTemperature(25.5)
    println(t.temperature)
}
