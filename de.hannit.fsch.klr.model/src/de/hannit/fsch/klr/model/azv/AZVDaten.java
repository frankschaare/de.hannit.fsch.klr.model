/**
 * 
 */
package de.hannit.fsch.klr.model.azv;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.TreeMap;

import de.hannit.fsch.klr.model.Datumsformate;

/**
 * @author fsch
 *
 */
public class AZVDaten
{
private ArrayList<AZVDatensatz> azvMeldungen = null;
private TreeMap<Integer, Integer> teamMitglieder = null;
private String webServiceIP = null;
private String requestedMonth = null;
private String requestedYear = null;
private boolean requestComplete = false;
private boolean checked = false;
private boolean errors = false;
private java.sql.Date berichtsMonatSQL;

	/**
	 * 
	 */
	public AZVDaten()
	{

	}

	public ArrayList<AZVDatensatz> getAzvMeldungen()
	{
	return azvMeldungen;
	}

	public TreeMap<Integer, Integer> getTeamMitglieder() {return teamMitglieder;}
	public void setTeamMitglieder(TreeMap<Integer, Integer> teamMitglieder) {this.teamMitglieder = teamMitglieder;}

	public String getWebServiceIP() {return webServiceIP;}
	public void setWebServiceIP(String webServiceIP){this.webServiceIP = webServiceIP;}

	/*
	 * Speichert die eingehenden AZV-Meldungen.
	 * Gleichzeitig wird versucht, Informationen über die Teammitgliedschaften zu generieren
	 */
	public void setAzvMeldungen(ArrayList<AZVDatensatz> azvMeldungen)
	{
	this.azvMeldungen = azvMeldungen;
	}

	/*
	 * Generiert eine Liste mit Personal- und Teamnummern
	 */
	public TreeMap<Integer, ArrayList<Integer>> setTeamMitgliedschaft()
	{
	TreeMap<Integer, ArrayList<Integer>>  tm = new TreeMap<Integer, ArrayList<Integer>>();
	ArrayList<Integer> teamNummern = null;
	
		for (AZVDatensatz azv : azvMeldungen)
		{
			if (tm.containsKey(azv.getPersonalNummer()))
			{
			teamNummern = tm.get(azv.getPersonalNummer());
				if (! teamNummern.contains(azv.getiTeam()))
				{
				teamNummern.add(azv.getiTeam());	
				}			
			}
			else
			{
			teamNummern = new ArrayList<>();
			teamNummern.add(azv.getiTeam());
			tm.put(azv.getPersonalNummer(), teamNummern);
			}
		}
	return tm;	
	}

	public boolean isChecked(){return checked;}
	public void setChecked(boolean checked){this.checked = checked;}
	
	public boolean hasErrors(){return errors;}
	public void setErrors(boolean errors){this.errors = errors;}
	
	public boolean isRequestComplete() {return requestComplete;}
	public void setRequestComplete(boolean requestComplete)	{this.requestComplete = requestComplete;}

	public String getRequestedMonth(){return requestedMonth;}
	public void setRequestedMonth(String requestedMonth){this.requestedMonth = requestedMonth;}

	public String getRequestedYear(){return requestedYear;}
	public void setRequestedYear(String requestedYear)
	{
	this.requestedYear = requestedYear;
		if (this.requestedMonth != null)
		{
		setBerichtsMonatSQL();	
		}
	}

	public String getName(){return "OS/ECM Webservice an IP: " + getWebServiceIP() + " " + requestedMonth + " " + requestedYear;}
	
	public java.sql.Date getBerichtsMonatSQL() {return (berichtsMonatSQL != null) ? berichtsMonatSQL : getAzvMeldungen().get(0).getBerichtsMonatSQL();}

	//TODO: prüfen, ob alle Monate übereinstimmen
	public void setBerichtsMonatSQL()
	{
		if (requestedMonth != null && requestedYear != null)
		{
			try
			{
			java.util.Date date = Datumsformate.MONATLANG_JAHR.parse(requestedMonth + " " + requestedYear);
			this.berichtsMonatSQL = new java.sql.Date(date.getTime());
			}
			catch (ParseException e)
			{
			e.printStackTrace();
			}	
		}	
	}
	






}
