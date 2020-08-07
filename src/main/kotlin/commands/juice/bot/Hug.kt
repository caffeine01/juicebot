package commands.juice.bot

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.io.File

class Hug : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg = event.message
        if (msg.contentStripped.toLowerCase().contains(">hug") && msg.mentionedUsers.isNotEmpty()) {
            val channel = event.channel
            if (msg.mentionedUsers.contains(msg.author)) {
                channel.sendMessage("You can't hug yourself fuckin' loser.").queue()
                return
            }
            if (msg.mentionedUsers.any { it.isBot }) {
                channel.sendMessage("no.").queue()
                return
            }
            channel.sendMessage(msg.author.asMention + " hugs you. " + msg.mentionedUsers.joinToString(", ") { it.asMention })
                .addFile(File("/home/ubuntu/images/hug.gif")).queue()
        }
    }
}