# Ajankäyttösovellus

Ajankäyttösovellukseen voi lisätä tehtäviä (todoita) ja halutessaan tämän voi ajastaa valitsemalla viikonpäivän ja ajan. Jo varattuun aikaan ei ole mahdollista lisätä tehtävää. Käyttäjä voi tulostaa ajastamattomat ja ajastetut tehtävät ja valita listasta minkä tehtävän haluaa ajastaa.   
  
Ajankäyttösovellus toimii viikkokalenterina, eli ajastus toimii yhdelle viikolle kerrallaan, voit tarkastella kalenteria ja lisätä tehtäviä tunnin tarkkuudella. 


## Dokumentaatio
* [Käyttöohjeet](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

* [Vaatimusmäärittely](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

* [Testausdokumentti](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

* [Arkkitehtuuri](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

* [Työaikakirjanpito](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)
  

## Releaset
[Viikko 5](https://github.com/eevib/ot-harjoitustyo/releases/viikko5)  
[Viikko 6](https://github.com/eevib/ot-harjoitustyo/releases/tag/Viikko6)  
[Loppupalautus]()
  

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
  

### JavaDoc
Generoi JavaDoc komennolla **mvn javadoc:javadoc**  
Tarkasetele JavaDocia avaamalla selaimella tiedosto *target/site/apidocs/index.html*  

