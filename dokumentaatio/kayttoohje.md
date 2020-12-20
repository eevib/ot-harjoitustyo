# Käyttöohje

Lataa tiedosto _ajankayttosovellus.jar_.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla   
_java -jar ajankayttosovellus.jar_  

## Ohjelman käyttö

## Kirjautuminen / uuden käyttäjän luominen  
  
![Kirjautumisnäkymä](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/images/loginPage.png)  
Kun käynnistät ohjelman aukeaa ikkuna, jossa voit luoda käyttäjän tai kirjautua. Kirjoita 'Username:' kenttän käyttäjänimi ja 'Password' kenttään salasana. Jos olet uusi käyttäjä paina "Register"-nappia tai jos sinulla on käyttäjätunnus paina "Login"-nappia. Kun painat "Register"-nappia luodaan uusi käyttäjätunnus ja kirjataan uusi käyttäjä sisään. Käyttäjätunnus ei saa olla sama kuin jo olemassa oleva käyttäjätunnus. 

## Ohjelman käyttö
![Kirjautumisen jälkeen avautuva näkymä](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/images/aloitusn%C3%A4kym%C3%A4.png) 

Tässä näkymässä on muut ohjelman toiminnallisuudet. 

- Tehtävän lisääminen:
  - Kirjoita kenttään tehtävän nimi ja paina "Add todo" -nappia.
  
- Ajastamattomien tehtävien listaus:
    - Nappi "Show unschdeduled todos" listaa ajastamattomat todot ja niiden id:n, jota käytetään ajastamisessa.
    
- Tehtävän ajastaminen:
  ![Tehtävän ajastaminen](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/images/ajastaTodo.png)  
    - Painamalla "Schedule todo" pääset ajastamaan lisättyjä tehtäviä. 
    - Lisää kenttiin niihin pyydetyt tiedot. Todo id:n löydät ajastamattomien tehtävien listasta. 
    - jos haluat ajastaa todon tiistaille klo 11, niin kirjoitat "Give day" kenttään "Tue" ja Give Time kenttään "11".
    - Paina lopuksi "Add timing" -nappia.
    - Jos aika on varattu, tai annat tiedot väärin sovellus kertoo tästä. Muuta tietoja ja paina uudestaan "Add timing".
   ![Ajastettu tehtävä](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/images/timedTodo.png)
    
 - Ajastettujen tehtävien listaaminen:
    - Nappi "Show scheduled todos" tulostaa listan ajastetuista tehtävistä.
    
 - Kalenterin näyttäminen:
      - Napista "Show calender" saat kalenterin ja ajastetut todot näkyviin.
      - Voit missä vaiheessa tahansa painaa "Show calender"-nappia ja saat näin näkyviin päivitetyn kalenterin.
  ![Kalenterissa kaksi ajastettua todota](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/images/calenderWTodo.png)
  
  - Tallentaminen ja uloskirjautuminen:
      - Painamalla "Save and logout" -napista palaat aloitusnäkymään. 
        

## Muuta huomioitaaa

Voit missä vaiheessa tahansa lisätä, ajastaa tai tarkastella ajastettuja tai ajastamattomia tehtäviä tai päivittää kalenteria, jossa näkyy ajastetut todot.  
  
Ajastamattomat todot eivät tallennu, jos suljet ohjelman, jos kirjaudut ulos ja sitten takaisin sisään, niin näet ajastamattomat tehtävät. 
  
Ohjelma sulkeutuu painamalla ruksia oikeassa yläkulmassa. 
