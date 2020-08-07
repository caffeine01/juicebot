@file:Suppress("DEPRECATION")

package juice.bot

import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity.playing
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import settings.juice.bot.Settings.BOT_TOKEN
import javax.security.auth.login.LoginException

fun main() {
    try {
        JDABuilder(AccountType.BOT)
            .setBulkDeleteSplittingEnabled(false)
            .setToken(BOT_TOKEN)
            .setStatus(OnlineStatus.ONLINE)
            .setActivity(playing(">help | ~/IdeaProjects/Juice/"))
            .addEventListeners(PingPong())
            .addEventListeners(commands.juice.bot.Help())
            .addEventListeners(commands.juice.bot.SysInfo())
            .addEventListeners(commands.juice.bot.Hug())
            .addEventListeners(commands.juice.bot.Slap())
            .addEventListeners(commands.juice.bot.Gag())
            .build()
    } catch (ex: LoginException) {
        println("LOGIN FAILED")
    }
}

class PingPong : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg = event.message
        if (msg.contentRaw.toLowerCase() == ">ping") {
            val channel = event.channel
            val time = System.currentTimeMillis()
            channel.sendMessage("Pong: .")
                .queue { response ->
                    (response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue())
                }
        }
        if (msg.contentRaw.startsWith(">")) {
            println(msg.author.name)
            println(msg.contentRaw)
        }
    }
}





