package me.botsko.prism.parameters;

import me.botsko.prism.actionlibs.QueryParameters;
import me.botsko.prism.actionlibs.QueryParameters.MatchRule;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PlayerParameter extends SimplePrismParameterHandler {

    /**
	 * 
	 */
    public PlayerParameter() {
        super( "Player", Pattern.compile( "[~|!]?[\\w,]+" ), "p" );
    }

    /**
	 * 
	 */
    @Override
    public void process(QueryParameters query, String alias, String input, CommandSender sender) {
 
        if( input.startsWith( "!" ) ) {
            query.setPlayerMatchRule( MatchRule.EXCLUDE );
            input = input.replace( "!", "" );
        } else if( input.startsWith( "~" ) ) {
            query.setPlayerMatchRule( MatchRule.PARTIAL );
            input = input.replace( "~", "" );
        }
        final String[] playerNames = input.split( "," );
        if( playerNames.length > 0 ) {
            for ( String playerName : playerNames ) {
                OfflinePlayer player = Bukkit.getOfflinePlayer( playerName );
                if( player == null ) continue;
                // @todo error if null
                query.addPlayer( player );
            }
        }
    }

    /**
     * 
     */
    @Override
    protected List<String> tabComplete(String alias, String partialParameter, CommandSender sender) {
        String prefix = "";
        String partialName = partialParameter;
        if( partialParameter.startsWith( "!" ) || partialParameter.startsWith( "~" ) ) {
            prefix = partialParameter.substring( 0, 1 );
            partialName = partialParameter.substring( 1 );
        }
        final int end = partialName.lastIndexOf( ',' );
        if( end != -1 ) {
            prefix = prefix + partialName.substring( 0, end ) + ",";
            partialName = partialName.substring( end + 1 );
        }
        partialName = partialName.toLowerCase();
        final List<String> completions = new ArrayList<String>();
        for ( final Player player : Bukkit.getOnlinePlayers() ) {
            if( player.getName().toLowerCase().startsWith( partialName ) )
                completions.add( prefix + player.getName() );
        }
        return completions;
    }
}