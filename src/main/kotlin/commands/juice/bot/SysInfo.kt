package commands.juice.bot

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import oshi.SystemInfo
import java.awt.Color
import kotlin.math.roundToInt

class SysInfo : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg = event.message
        if (msg.contentRaw.toLowerCase() == ">sys") {
            val channel = event.channel
            val si = SystemInfo()
            val hal = si.hardware
            val cpu = hal.processor
            val sen = hal.sensors
            val eb = EmbedBuilder()
                .setTitle("SYS INFO", null)
                .addBlankField(false)
                .setColor(Color(69, 69, 69))
                .addField("CPU", "NAME: Broadcom BCM2711 \n ARCH: ARM v8 \n SPEED: 1.50Ghz \n USAGE: ${cpu.systemCpuLoad.roundToInt()}% \n TEMPS: ${sen.cpuTemperature}°C", true)
                .addBlankField(false)
                .addField("RAM", "NAME: 2 GB LPDDR4-3200 SDRAM", false)
                .addBlankField(false)
                .addField("OS", "${si.operatingSystem}", false)
                .setThumbnail("https://www.raspberrypi.org/app/uploads/2012/03/raspberry_pi_logo_rgb_552x650-212x250.png")
                .setImage("https://oxyme.files.wordpress.com/2018/08/ubuntu-logo.png?w=700&h=158")
            channel.sendMessage(eb.build()).queue()
        }
    }
}