# Ajankäyttösovellus

Ajankäyttösovellukseen voi lisätä tehtäviä (todoita) ja halutessaan tämän voi ajastaa valitsemalla viikonpäivän ja ajan. Jo varattuun aikaan ei ole mahdollista lisätä tehtävää. Käyttäjä voi tulostaa ajastamattomat ja ajastetut tehtävät ja valita listasta minkä tehtävän haluaa ajastaa.   
  
Ajankäyttösovellus toimii viikkokalenterina, eli ajastus toimii yhdelle viikolle. Jatkossa on tarkoitus lisätä mahdollisuus tulostaa kalenteri, varata aikoja ilman tehtävää ja lisätä kirjautuminen, jolloin käyttäjälle näkyy vain oma kalenteri.


## Dokumentaatio
* [Käyttöohjeet](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

* [Vaatimusmäärittely](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

* [Työaikakirjanpito](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

* [Arkkitehtuuri](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset
[Viikko 5](https://github.com/eevib/ot-harjoitustyo/releases/viikko5)

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
