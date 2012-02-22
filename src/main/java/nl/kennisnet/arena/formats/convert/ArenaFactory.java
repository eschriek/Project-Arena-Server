package nl.kennisnet.arena.formats.convert;

import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import nl.kennisnet.arena.formats.Arena;
import nl.kennisnet.arena.model.Participant;
import nl.kennisnet.arena.model.Positionable;
import nl.kennisnet.arena.model.Progress;
import nl.kennisnet.arena.model.Quest;
import nl.kennisnet.arena.services.ParticipantService;
import nl.kennisnet.arena.utils.ArenaDataBean;
import nl.kennisnet.arena.utils.ConvertorConfiguration;
import nl.kennisnet.arena.utils.UtilityHelper;

public class ArenaFactory {

	public static Arena getInstance(ArenaDataBean arenaDataBean,
			final Quest quest, final CompositeConfiguration configuration, final String player,  long participationId, 
			ParticipantService participantService) {
		final ConvertorConfiguration config = new ConvertorConfiguration(arenaDataBean, configuration, quest);
		String baseUrl = UtilityHelper.getBaseUrl(configuration);
		Arena arena = new Arena();
		List<Positionable> positionables = quest.getVisiblePositionables(arenaDataBean.getLocation());
		
		arena.addPositionResults(positionables, baseUrl, quest.getId(), player, participationId, participantService);
		arena.checkStats();
		return arena;
	}

	private static String formatProgress(final Progress progress) {
		return String.format("Score: %d/%d", progress.getCurr(),
				progress.getMax());
	}

	/**
	 * Calculates if the current position is within the border. Always returns
	 * true if there is no border specified.
	 * 
	 * @param location
	 * @param border
	 * @return
	 */
	private static boolean isInBorder(final Point location, final Polygon border) {
		if (border == null) {
			return true;
		}
		return location.within(border);
	}
}
