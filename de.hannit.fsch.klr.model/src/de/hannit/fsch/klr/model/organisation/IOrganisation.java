/**
 * 
 */
package de.hannit.fsch.klr.model.organisation;

import java.util.TreeMap;

import de.hannit.fsch.klr.model.mitarbeiter.Mitarbeiter;
import de.hannit.fsch.klr.model.team.Team;


/**
 * @author fsch
 *
 */
public interface IOrganisation
{
public String getName();
public void setMitarbeiter(TreeMap<Integer, Mitarbeiter> mitarbeiter);
public TreeMap<String, Mitarbeiter> getMitarbeiterNachName();
public TreeMap<Integer, Mitarbeiter> getMitarbeiterNachPNR();
public TreeMap<Integer, Team> getTeams();
}
