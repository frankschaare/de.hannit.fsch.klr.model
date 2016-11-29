/**
 * 
 */
package de.hannit.fsch.klr.model.mitarbeiter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;

/**
 * @author fsch
 *
 */
public class Vorstand extends Mitarbeiter
{
private	Properties props;	
private ArrayList<Mitarbeiter> stellvertreter = new ArrayList<>();

	/**
	 * 
	 */
	public Vorstand()
	{
	props = new Properties();

		try 
		{
		URL configURL = this.getClass().getClassLoader().getResource("HannITProperties.xml");
		File configFile = new File(FileLocator.toFileURL(configURL).getPath());	
		InputStream in = new FileInputStream(configFile);
		props.loadFromXML(in);
		
		setPersonalNR(Integer.parseInt(props.getProperty("vorstandPNR")));
		setTarifGruppe(props.getProperty("vorstandGehaltsgruppe"));
		setNachname(props.getProperty("vorstandName"));
		setVorname(props.getProperty("vorstandVorname"));
		
			for (int i = 1; i <= Integer.parseInt(props.getProperty("anzahlStellvertreter")); i++)
			{
			Mitarbeiter vertreter = new Mitarbeiter();
			vertreter.setPersonalNR(Integer.parseInt(props.getProperty("stellvertretenderVorstand" + i + "PNR")));
			vertreter.setTarifGruppe(props.getProperty("stellvertretenderVorstand" + i + "Gehaltsgruppe"));
			vertreter.setNachname(props.getProperty("stellvertretenderVorstand" + i + "Name"));
			vertreter.setVorname(props.getProperty("stellvertretenderVorstand" + i + "Vorname"));

			stellvertreter.add(vertreter);
			}
		} 
		catch (IOException e) 
		{
		e.printStackTrace();
		}
	}		
}
