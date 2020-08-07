package commands.juice.bot

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.io.File

class Slap : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg = event.message
        if (msg.contentStripped.toLowerCase().contains(">slap") && msg.mentionedUsers.isNotEmpty()) {
            val channel = event.channel
            if (msg.mentionedUsers.contains(msg.author)) {
                channel.sendMessage("Why are you slapping yourself? weirdo.").queue()
                return
            }
            if (msg.mentionedUsers.any { it.isBot }) {
                channel.sendMessage("I'm not doing that.").queue()
                return
            }
            channel.sendMessage(msg.author.asMention + " just slapped you. " + msg.mentionedUsers.joinToString(", ") { it.asMention })
                .addFile(File("/home/ubuntu/images/slap.gif")).queue()
        }
    }
}