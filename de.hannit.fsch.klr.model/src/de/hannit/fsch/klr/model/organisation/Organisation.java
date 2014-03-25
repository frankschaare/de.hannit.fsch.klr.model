/**
 * 
 */
package de.hannit.fsch.klr.model.organisation;

import java.util.Date;
import java.util.TreeMap;

import de.hannit.fsch.klr.model.mitarbeiter.Mitarbeiter;
import de.hannit.fsch.klr.model.team.Team;


/**
 * @author fsch
 *
 */
public class Organisation implements IOrganisation
{
private final String name = "Hannoversche Informationstechnologien";
private TreeMap<Date, Monatsbericht> monatsBerichte = new TreeMap<Date, Monatsbericht>(); 

private TreeMap<Integer, Mitarbeiter> mitarbeiterPNR = null;
private TreeMap<String, Mitarbeiter> mitarbeiterNachname = null;
private TreeMap<Integer, Team> teams = null;

	/**
	 * 
	 */
	public Organisation(){}
	
	public TreeMap<Date, Monatsbericht> getMonatsBerichte()	{return monatsBerichte;}
	public void setMonatsBerichte(TreeMap<Date, Monatsbericht> monatsBerichte) {this.monatsBerichte = monatsBerichte;}

	/* (non-Javadoc)
	 * @see de.hannit.fsch.common.organisation.IOrganisation#getName()
	 */
	@Override
	public String getName() {return this.name;}

	@Override
	public void setMitarbeiter(TreeMap<Integer, Mitarbeiter> incoming)
	{
	this.mitarbeiterPNR = incoming;	
	mitarbeiterNachname = new TreeMap<String, Mitarbeiter>();	
	teams = new TreeMap<Integer, Team>();
	
		for (Mitarbeiter mPNR : mitarbeiterPNR.values())
		{
		mitarbeiterNachname.put(mPNR.getNachname(), mPNR);
			
		int teamNR = mPNR.getTeamNR();
			if (! teams.containsKey(teamNR))
			{
			Team team = new Team(teamNR);
			teams.put(teamNR, team);
			}
		teams.get(teamNR).addMitarbeiter(mPNR);
		}
	}

	@Override
	public TreeMap<String, Mitarbeiter> getMitarbeiterNachName(){return this.mitarbeiterNachname;}
	@Override
	public TreeMap<Integer, Mitarbeiter> getMitarbeiterNachPNR() {return this.mitarbeiterPNR;}

	@Override
	public TreeMap<Integer, Team> getTeams()
	{
	return teams;
	}

}
