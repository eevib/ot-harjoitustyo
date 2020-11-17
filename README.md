# Ajankäyttösovellus

Ajankäyttösovellus on vielä todella yksinkertainen, se luo käynnistäessä kalenterin ja siihen pystyy lisäämään todon ja antamaan ajan jolloin todo tehdään. Kun tekstinkäyttöliittymässä on lisätty todo ja poistutaan, se tulostaa kuinka monta todota on lisätty kalenteriin, sovellus ei kuitenkaan osaa vielä ajastaa todoita, eikä tulostaa niitä.


## Dokumentaatio

* [Vaatimusmäärittely](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

* [Työaikakirjanpito](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Komentorivitoiminnot

Sovelluksen saa käyntiin komennolla: 
mvn compile exec:java -Dexec.mainClass=ajankayttosovellus.Main 

### Testaus

Testit suoritetaan komennolla: mvn test

Testikattavuusraportti luodaan komennolla: mvn jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html
