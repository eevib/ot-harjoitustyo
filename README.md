# Ajankäyttösovellus

Ajankäyttösovellukseen voi lisätä tehtäviä (todoita) ja halutessaan tämän voi ajastaa valitsemalla viikonpäivän ja ajan. Käyttäjä voi myös lisätä aikoja varatuiksi, näihin ei ole mahdollista lisätä tehtäviä. On mahdollista tulostaa ajastamattomat tehtävät ja ajastetut tehtävät. Voit myös valita ajastamattoman tehtävän ja ajastaa sen.   
  
Ajankäyttösovellus toimii viikkokalenterina, eli ajastus toimii yhdelle viikolle. Tällä hetkellä käytössä on vain tekstinkäyttöliittymä, ensi viikoksi on tarkoitus tehdä graafinen käyttöliittymä. 


## Dokumentaatio

* [Vaatimusmäärittely](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

* [Työaikakirjanpito](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

* [Arkkitehtuuri](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset
[Viikko 5]()

## Komentorivitoiminnot

Sovelluksen saa käyntiin komennolla: 
mvn compile exec:java -Dexec.mainClass=ajankayttosovellus.Main 

### Testaus

Testit suoritetaan komennolla: **mvn test**

Testikattavuusraportti luodaan komennolla: **mvn jacoco:report**

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*

### Suoritettavan jarin generointi
Komento **mvn package**    
generoi hakemistoon target suoritettavan jar-tiedoston *Ajankayttosovellus-1.0-SNAPSHOT.jar*

### Checkstyle
Tiedostoon [chekstyle.xml](https://github.com/eevib/ot-harjoitustyo/blob/master/Ajankayttosovellus/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla **mvn jxr:jxr checkstyle:chekstyle**  
Mahdolliset virheilmoitukset näet avaamalla selaimella tiedoston *target/site/checkstyle.html*
