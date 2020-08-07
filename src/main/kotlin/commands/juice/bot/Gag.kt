package commands.juice.bot

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.*
import kotlin.concurrent.schedule

class Gag : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg = event.message
        if (msg.contentStripped.toLowerCase().contains(">gag") && msg.member?.roles?.any { role ->
                role.name.toLowerCase() in listOf(
                    "staff",
                    "helper",
                    "moderator",
                    "admin",
                    "administrator",
                    "owner"
                )
            } != false && msg . mentionedUsers . isNotEmpty ()) {
            val channel = event.channel
            if (msg.mentionedUsers.contains(msg.author)) {
                channel.sendMessage("You can't gag yourself!").queue()
                return
            }
            if (msg.mentionedUsers.any { it.isBot }) {
                channel.sendMessage("You can't gag a bot!").queue()
                return
            }
            if (msg.mentionedMembers.first() .roles.any { it.name.equals("Muted", true) }) {
                channel.sendMessage("User already gagged/muted!").queue()
                return
            }
            val mute = event.guild.getRolesByName("Muted", true).first()
            val user = msg.mentionedUsers.first().id
            channel.sendMessage("Gagged " + msg.mentionedUsers.joinToString(", ") { it.asMention })
                .queue()
            event.guild.addRoleToMember(user, mute).queue()
            Timer().schedule(300000) {
                event.guild.modifyMemberRoles(msg.mentionedMembers.first(), emptyList(), listOf(mute)).queue()
            }
        }
    }
}