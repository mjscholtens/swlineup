package core;

import java.awt.Image;

import javax.swing.ImageIcon;

@SuppressWarnings("unused")
public class CountryDet {
	
	// use this version for eclipse
	public Image fetchCountryAbbrv(String country) {
		Image returnFlag = null;		
		switch(Country.valueOf(country)) {		
		
		case holland: case netherlands:	returnFlag = new ImageIcon("flags/nl.png").getImage(); break;
		case germany: returnFlag = new ImageIcon("flags/de.png").getImage(); break;
		case belgium: returnFlag = new ImageIcon("flags/be.png").getImage(); break;
		case unitedkingdom: returnFlag = new ImageIcon("flags/gb.png").getImage(); break;
		case france: returnFlag = new ImageIcon("flags/fr.png").getImage(); break;
		case spain: returnFlag = new ImageIcon("flags/es.png").getImage(); break;
		case portugal: returnFlag = new ImageIcon("flags/pt.png").getImage(); break;
		case unitedstates: returnFlag = new ImageIcon("flags/us.png").getImage(); break;
		case england: returnFlag = new ImageIcon("flags/england.png").getImage(); break;
		case scotland: returnFlag = new ImageIcon("flags/scotland.png").getImage(); break;
		case wales: returnFlag = new ImageIcon("flags/wales.png").getImage(); break;
		case ireland: returnFlag = new ImageIcon("flags/ie.png").getImage(); break;
		case austria: returnFlag = new ImageIcon("flags/at.png").getImage(); break;
		case switzerland: returnFlag = new ImageIcon("flags/ch.png").getImage(); break;
		case italy: returnFlag = new ImageIcon("flags/it.png").getImage(); break;
		case norway: returnFlag = new ImageIcon("flags/no.png").getImage(); break;
		case sweden: returnFlag = new ImageIcon("flags/se.png").getImage(); break;
		case finland: returnFlag = new ImageIcon("flags/fi.png").getImage(); break;
		case denmark: returnFlag = new ImageIcon("flags/dk.png").getImage(); break;
		case russia: returnFlag = new ImageIcon("flags/ru.png").getImage(); break;
		case southafrica: returnFlag = new ImageIcon("flags/za.png").getImage(); break;
		case china: returnFlag = new ImageIcon("flags/cn.png").getImage(); break;
		case japan: returnFlag = new ImageIcon("flags/jp.png").getImage(); break;
		case brazil: returnFlag = new ImageIcon("flags/br.png").getImage(); break;
		case argentina: returnFlag = new ImageIcon("flags/ar.png").getImage(); break;
		case peru: returnFlag = new ImageIcon("flags/pe.png").getImage(); break;
		case canada: returnFlag = new ImageIcon("flags/ca.png").getImage(); break;
		case mexico: returnFlag = new ImageIcon("flags/mx.png").getImage(); break;
		case columbia: returnFlag = new ImageIcon("flags/co.png").getImage(); break;
		case venezuela: returnFlag = new ImageIcon("flags/ve.png").getImage(); break;
		case chile: returnFlag = new ImageIcon("flags/cl.png").getImage(); break;
		case poland: returnFlag = new ImageIcon("flags/pl.png").getImage(); break;
		case iceland: returnFlag = new ImageIcon("flags/is.png").getImage(); break;
		case ukraine: returnFlag = new ImageIcon("flags/ua.png").getImage(); break;
		case greece: returnFlag = new ImageIcon("flags/gr.png").getImage(); break;
		case slovenia: returnFlag = new ImageIcon("flags/si.png").getImage(); break;
		case czechrepublic: returnFlag = new ImageIcon("flags/cz.png").getImage(); break;
		case slovakia: returnFlag = new ImageIcon("flags/sk.png").getImage(); break;
		case croatia: returnFlag = new ImageIcon("flags/hr.png").getImage(); break;
		case bosniaherzegovina: returnFlag = new ImageIcon("flags/ba.png").getImage(); break;
		case serbia: returnFlag = new ImageIcon("flags/sr.png").getImage(); break;
		case montenegro: returnFlag = new ImageIcon("flags/me.png").getImage(); break;
		case albania: returnFlag = new ImageIcon("flags/al.png").getImage(); break;
		case turkey: returnFlag = new ImageIcon("flags/tr.png").getImage(); break;
		case australia: returnFlag = new ImageIcon("flags/au.png").getImage(); break;
		case newzealand: returnFlag = new ImageIcon("flags/nz.png").getImage(); break;
		case luxembourg: returnFlag = new ImageIcon("flags/lu.png").getImage(); break;
		case lithuania: returnFlag = new ImageIcon("flags/lt.png").getImage(); break;
		case latvia: returnFlag = new ImageIcon("flags/lv.png").getImage(); break;
		case estonia: returnFlag = new ImageIcon("flags/ee.png").getImage(); break;
		case hungary: returnFlag = new ImageIcon("flags/hu.png").getImage(); break;
		case romania: returnFlag = new ImageIcon("flags/ro.png").getImage(); break;
		case bulgaria: returnFlag = new ImageIcon("flags/bg.png").getImage(); break;
		case moldova: returnFlag = new ImageIcon("flags/md.png").getImage(); break;
		
		default: break;
		}
		
		return returnFlag;
	}
		
	// use this version for jar
	/*public ImageIcon fetchCountryAbbrv(String country) {
		ImageIcon returnFlag = null;		
		switch(Country.valueOf(country)) {	
	
		case holland: case netherlands:	returnFlag = new ImageIcon(getClass().getResource("/flags/nl.png")); break;
		case germany: returnFlag = new ImageIcon(getClass().getResource("/flags/de.png")); break;
		case belgium: returnFlag = new ImageIcon(getClass().getResource("/flags/be.png")); break;
		case unitedkingdom: returnFlag = new ImageIcon(getClass().getResource("/flags/gb.png")); break;
		case france: returnFlag = new ImageIcon(getClass().getResource("/flags/fr.png")); break;
		case spain: returnFlag = new ImageIcon(getClass().getResource("/flags/es.png")); break;
		case portugal: returnFlag = new ImageIcon(getClass().getResource("/flags/pt.png")); break;
		case unitedstates: returnFlag = new ImageIcon(getClass().getResource("/flags/us.png")); break;
		case england: returnFlag = new ImageIcon(getClass().getResource("/flags/england.png")); break;
		case scotland: returnFlag = new ImageIcon(getClass().getResource("/flags/scotland.png")); break;
		case wales: returnFlag = new ImageIcon(getClass().getResource("/flags/wales.png")); break;
		case ireland: returnFlag = new ImageIcon(getClass().getResource("/flags/ie.png")); break;
		case austria: returnFlag = new ImageIcon(getClass().getResource("/flags/at.png")); break;
		case switzerland: returnFlag = new ImageIcon(getClass().getResource("/flags/ch.png")); break;
		case italy: returnFlag = new ImageIcon(getClass().getResource("/flags/it.png")); break;
		case norway: returnFlag = new ImageIcon(getClass().getResource("/flags/no.png")); break;
		case sweden: returnFlag = new ImageIcon(getClass().getResource("/flags/se.png")); break;
		case finland: returnFlag = new ImageIcon(getClass().getResource("/flags/fi.png")); break;
		case denmark: returnFlag = new ImageIcon(getClass().getResource("/flags/dk.png")); break;
		case russia: returnFlag = new ImageIcon(getClass().getResource("/flags/ru.png")); break;
		case southafrica: returnFlag = new ImageIcon(getClass().getResource("/flags/za.png")); break;
		case china: returnFlag = new ImageIcon(getClass().getResource("/flags/cn.png")); break;
		case japan: returnFlag = new ImageIcon(getClass().getResource("/flags/jp.png")); break;
		case brazil: returnFlag = new ImageIcon(getClass().getResource("/flags/br.png")); break;
		case argentina: returnFlag = new ImageIcon(getClass().getResource("/flags/ar.png")); break;
		case peru: returnFlag = new ImageIcon(getClass().getResource("/flags/pe.png")); break;
		case canada: returnFlag = new ImageIcon(getClass().getResource("/flags/ca.png")); break;
		case mexico: returnFlag = new ImageIcon(getClass().getResource("/flags/mx.png")); break;
		case columbia: returnFlag = new ImageIcon(getClass().getResource("/flags/co.png")); break;
		case venezuela: returnFlag = new ImageIcon(getClass().getResource("/flags/ve.png")); break;
		case chile: returnFlag = new ImageIcon(getClass().getResource("/flags/cl.png")); break;
		case poland: returnFlag = new ImageIcon(getClass().getResource("/flags/pl.png")); break;
		case iceland: returnFlag = new ImageIcon(getClass().getResource("/flags/is.png")); break;
		case ukraine: returnFlag = new ImageIcon(getClass().getResource("/flags/ua.png")); break;
		case greece: returnFlag = new ImageIcon(getClass().getResource("/flags/gr.png")); break;
		case slovenia: returnFlag = new ImageIcon(getClass().getResource("/flags/si.png")); break;
		case czechrepublic: returnFlag = new ImageIcon(getClass().getResource("/flags/cz.png")); break;
		case slovakia: returnFlag = new ImageIcon(getClass().getResource("/flags/sk.png")); break;
		case croatia: returnFlag = new ImageIcon(getClass().getResource("/flags/hr.png")); break;
		case bosniaherzegovina: returnFlag = new ImageIcon(getClass().getResource("/flags/ba.png")); break;
		case serbia: returnFlag = new ImageIcon(getClass().getResource("/flags/sr.png")); break;
		case montenegro: returnFlag = new ImageIcon(getClass().getResource("/flags/me.png")); break;
		case albania: returnFlag = new ImageIcon(getClass().getResource("/flags/al.png")); break;
		case turkey: returnFlag = new ImageIcon(getClass().getResource("/flags/tr.png")); break;
		case australia: returnFlag = new ImageIcon(getClass().getResource("/flags/au.png")); break;
		case newzealand: returnFlag = new ImageIcon(getClass().getResource("/flags/nz.png")); break;
		case luxembourg: returnFlag = new ImageIcon(getClass().getResource("/flags/lu.png")); break;
		case lithuania: returnFlag = new ImageIcon(getClass().getResource("/flags/lt.png")); break;
		case latvia: returnFlag = new ImageIcon(getClass().getResource("/flags/lv.png")); break;
		case estonia: returnFlag = new ImageIcon(getClass().getResource("/flags/ee.png")); break;
		case hungary: returnFlag = new ImageIcon(getClass().getResource("/flags/hu.png")); break;
		case romania: returnFlag = new ImageIcon(getClass().getResource("/flags/ro.png")); break;
		case bulgaria: returnFlag = new ImageIcon(getClass().getResource("/flags/bg.png")); break;
		case moldova: returnFlag = new ImageIcon(getClass().getResource("/flags/md.png")); break;
		
		default: break;
		}
		
		return returnFlag;
	}*/
}
