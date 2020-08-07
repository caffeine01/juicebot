package commands.juice.bot

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color

class Help : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg = event.message
        if (msg.contentRaw.toLowerCase() == ">help") {
            val channel = event.channel
            val eb = EmbedBuilder()
                .setTitle("Help:", null)
                .setColor(Color(69, 69, 69))
                .setDescription("Here are the commands currently implemented into the bot.")
                .addBlankField(false)
                .addField(">ping", "Relays API latency.", false)
                .addBlankField(false)
                .addField(">sys", "Displays bot system info", false)
                .addBlankField(false)
                .addField(">gag", "Silences mentioned user(s) for 5 minutes", false)
                .addBlankField(false)
                .addField(">hug", "Hugs mentioned user(s)", false)
                .addBlankField(false)
                .addField(">slap", "Slaps mentioned user(s)", false)
                .setThumbnail("https://cdn.discordapp.com/avatars/661789624934596619/0055a3bcc82a81de75be91251255d838.png?size=128")
            channel.sendMessage(eb.build()).queue()
        }
    }
}