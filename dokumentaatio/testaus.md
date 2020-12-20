# Testausdokumentti

Ohjelmaa on testattu JUnit yksikkö- ja intergraatiotestien avulla ja manuaalisilla käyttäjän testeillä. 

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka 
Sovelluslogiikan testauksen hoitaa testiluokka ajankayttosovellus.domain.  
  
CalenderServiceTest -luokka testaa CalenderService -luokkaan liittyvät metodit, jotka eivät sisällä kirjautumista tai tiedon tallentamiseen tai lukemiseen liittyviä metodeja. CalenderServiceUserTest -luokka hoitaa FileCalenderDao ja FileUserDao -luokan testauksen, käyttäen hyväkseen FakeCalenderDao ja FakeUserDao keskusmuistitoteutuksia.

Olioluokkia Calender, Todo ja User testataan tarvittavilla yksikkö- ja integraatiotesteillä.  

### Dao -luokkien testaus
Hyödynnettiin DAO-luokkien testaamiseen JUnitin TemporaryFolderia. Käytin tätä FileCalenderDao ja FileUserDaon toiminnallisuuksien mm. tiedon tallentamisen ja tiedon lukemisen testaamiseen. 

### Testauskattavuus
Sovelluksen testeissä huomiodaan muut luokat, paitsi käyttöliittymä-luokka ajankayttosovellus.ui.  
Sovelluksen rivikattavuus on 

### Asennus ja konfigurointi
Sovellusta on testattu Linux-ympäristössä Fuksi-läppärillä ja toisella Linuxia käyttävällä koneella. Sovellus on testattu niin, että config.properties-tiedostot ovat olleet olemassa, ja niin, että niitä ei ole ollut valmiiksi olemassa ja sovellus on luonut ne itse.

### Sovellukseen jääneet ongelmat
Kaikki muut määrittelydokumentin toiminnallisuudet, paitsi ajastettujen tehtävien näyttäminen on testattu toimivaksi.

Jossain tapauksissa sovellus tulostaa virheviestiä kesken käytön. Sen käyttöä voi kuitenkin jatkaa ilman ongelmia.  
